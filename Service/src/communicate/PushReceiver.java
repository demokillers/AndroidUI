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
			// ��ȡ͸������
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");
			
			// smartPush��������ִ���ýӿڣ�action��ΧΪ90000-90999���ɸ���ҵ�񳡾�ִ��
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			System.out.println("��������ִ�ӿڵ���" + (result ? "�ɹ�" : "ʧ��"));
			
			if (payload != null) {
				String data = new String(payload);
				
				Log.d("GetuiSdk", "Got Payload:" + data);
				//����͸����Ϣ
				try {
					JSONObject json = new JSONObject(data);
					String type = json.getString("type");
					if (type.equals("help")) {
						// ������Ϣ
						showNotification("�յ�������Ϣ", "����δ����������Ϣ", AssistTipsActivity.class);
					} else if (type.equals("aid")) {
						// Ԯ����Ϣ
					} else if (type.equals("endhelp")) {
						// ���������¼�
					} else if (type.equals("invite")) {
						// ��������
					} else if (type.equals("remove")) {
						// �Ƴ�����
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			break;
		case PushConsts.GET_CLIENTID:
			//��ȡClientID(CID)
			//������Ӧ����Ҫ��CID�ϴ��������������������ҽ���ǰ�û��˺ź�CID���й������Ա��պ�ͨ���û��˺Ų���CID������Ϣ����
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
				.setWhen(System.currentTimeMillis()) // ����ʱ��
				.setAutoCancel(true) // �������
				.setSmallIcon(R.drawable.ic_launcher) // ����ͼ��
				.setContentTitle(title) // ���ñ���
				.setContentText(content); // ��������
		Notification notice = builder.getNotification();
		
		NotificationManager notifier = (NotificationManager) PushConfig.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
		notifier.notify(1, notice);
	}
	
}