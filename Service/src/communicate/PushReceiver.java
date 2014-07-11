package communicate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

public class PushReceiver extends BroadcastReceiver {
	
	public static String clientId = "";
	
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
			}
			break;
		case PushConsts.GET_CLIENTID:
			//获取ClientID(CID)
			//第三方应用需要将CID上传到第三方服务器，并且将当前用户账号和CID进行关联，以便日后通过用户账号查找CID进行消息推送
			clientId = bundle.getString("clientid");
			break;
		default:
			break;
		}
	}
}