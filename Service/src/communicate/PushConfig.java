package communicate;

import android.app.Activity;
import android.content.Context;

import com.igexin.sdk.PushManager;

public class PushConfig {
	public static final String SERVICEURL = "http://114.215.133.61:8080/api/"; // ��������ַ
	public static final int CONNECTION_TIMEOUT_INT = 8000; // Ĭ�����ӷ�������ʱʱ��
	public static final int READ_TIMEOUT_INT = 5000; // Ĭ�϶�ȡ���������ݳ�ʱʱ��
	
	// �û�����δ��¼ʱָ��Ϊ���ַ���
	public static String username = "";
	
	// ���±����Զ�ά��
	public static Context applicationContext = null;
	public static String clientId = "";
	
	/**
	 * ��ʼ�����ͷ���
	 * @param mContext
	 */
	public static void init(Activity mContext) {
		PushConfig.applicationContext = mContext.getApplicationContext();
        PushManager.getInstance().initialize(mContext.getApplicationContext());
	}
	
	/**
	 * ֹͣ���ͷ���
	 * @param mContext
	 */
	public static void stop(Activity mContext) {
		PushManager.getInstance().stopService(mContext.getApplicationContext());
	}
}