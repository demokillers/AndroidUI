package communicate;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import client.ui.AssistTipsActivity;
import client.ui.R;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

public class PushReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdk", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		
		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");
			
			// smartPush第三方回执调用接口，action范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));
			
			if (payload != null) {
				String data = new String(payload);
				
				Log.d("GetuiSdk", "Got Payload:" + data);
				//处理透传消息
				try {
					JSONObject json = new JSONObject(data);
					String type = json.getString("type");
					if (type.equals("help")) {
						// 求助信息
						showNotification("收到求助信息", "您有未读的求助信息", AssistTipsActivity.class);
					} else if (type.equals("aid")) {
						// 援助信息
					} else if (type.equals("endhelp")) {
						// 结束求助事件
					} else if (type.equals("invite")) {
						// 好友请求
					} else if (type.equals("remove")) {
						// 移除好友
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;
		case PushConsts.GET_CLIENTID:
			//获取ClientID(CID)
			//第三方应用需要将CID上传到第三方服务器，并且将当前用户账号和CID进行关联，以便日后通过用户账号查找CID进行消息推送
			PushConfig.clientId = bundle.getString("clientid");
			PushSender.sendClientId();
			break;
		default:
			break;
		}
	}
	
	public static void showNotification(String title, String content, Class<?> cls) {
		Intent intent = new Intent(PushConfig.applicationContext, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getBroadcast(PushConfig.applicationContext, 0, intent, 0);
		
		Builder builder = new Notification.Builder(PushConfig.applicationContext);
		builder.setContentIntent(contentIntent)
				.setWhen(System.currentTimeMillis()) // 发生时间
				.setAutoCancel(true) // 可以清除
				.setSmallIcon(R.drawable.ic_launcher) // 设置图标
				.setContentTitle(title) // 设置标题
				.setContentText(content); // 设置内容
		Notification notice = builder.getNotification();
		
		NotificationManager notifier = (NotificationManager) PushConfig.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
		notifier.notify(1, notice);
	}
	
}