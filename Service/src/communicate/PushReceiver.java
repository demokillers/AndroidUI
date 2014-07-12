package communicate;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import client.ui.MainActivity;

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
				//TODO:处理透传消息
				showNotification("Title", data, MainActivity.class);
			}
			break;
		case PushConsts.GET_CLIENTID:
			//获取ClientID(CID)
			//第三方应用需要将CID上传到第三方服务器，并且将当前用户账号和CID进行关联，以便日后通过用户账号查找CID进行消息推送
			PushConfig.clientId = bundle.getString("clientid");
			syncClientId();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 上传clientId
	 */
	public static void syncClientId() {
	}
	
	/**
	 * 显示通知
	 * @param title
	 * @param text
	 */
	public static void showNotification(String title, String text, Class<?> cls) {
		NotificationManager notifier = (NotificationManager) PushConfig.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notify = new Notification(R.drawable.btn_default, title, System.currentTimeMillis());
		notify.flags = Notification.FLAG_AUTO_CANCEL;
		Intent main = new Intent(PushConfig.applicationContext, cls);
		PendingIntent intent = PendingIntent.getActivity(PushConfig.applicationContext, 0, main, 0);
		notify.setLatestEventInfo(PushConfig.applicationContext, title, text, intent);
		notifier.notify(1, notify);
	}
}