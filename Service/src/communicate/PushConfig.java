package communicate;

import android.app.Activity;
import android.content.Context;

import com.igexin.sdk.PushManager;

public class PushConfig {
	public static final String SERVICEURL = "http://114.215.133.61:8080/api/"; // 服务器地址
	public static final int CONNECTION_TIMEOUT_INT = 8000; // 默认连接服务器超时时间
	public static final int READ_TIMEOUT_INT = 5000; // 默认读取服务器数据超时时间
	
	// 用户名，未登录时指定为空字符串
	public static String username = "";
	
	// 以下变量自动维护
	public static Context applicationContext = null;
	public static String clientId = "";
	
	/**
	 * 初始化推送服务
	 * @param mContext
	 */
	public static void init(Activity mContext) {
		PushConfig.applicationContext = mContext.getApplicationContext();
        PushManager.getInstance().initialize(mContext.getApplicationContext());
	}
	
	/**
	 * 停止推送服务
	 * @param mContext
	 */
	public static void stop(Activity mContext) {
		PushManager.getInstance().stopService(mContext.getApplicationContext());
	}
}