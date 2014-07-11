package communicate;

import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class PushSender {
	
	/**
	 * 功能模块
	 */
	public static final String REGISTER = "register"; // 注册
	public static final String LOGIN = "login"; // 登录
	public static final String USER_AUTHENTICATION = "userauthentication"; // 用户验证
	public static final String LOGOUT = "logout"; // 登出
	public static final String CANCEL = "cancel"; // 注销
	public static final String CHECK_RELATIVES = "checkrelatives"; // 查看亲友列表
	public static final String DELETE_RELATIVES = "deleterelatives"; // 删除亲友
	public static final String ADD_RELATIVES = "addrelatives"; // 添加亲友
	public static final String CHECK_HISTORY = "history"; // 查看消息历史记录
	public static final String SEND_HELP_MESSAGE = "pophelpmessage"; // 发送求助信息
	public static final String ADD_SUPPORT_MESSAGE = "supportmessage"; // 添加辅助信息
	public static final String END_HELP = "finish"; // 结束求助
	public static final String GIVE_CREDIT = "givecredit"; // 评分
	public static final String ADD_AID = "addaid"; // 加入帮助 
	public static final String SEND_SUPPORT_MESSAGE = "sendsupport"; // 发送援助信息
	public static final String QUIT_AID = "quitaid"; // 退出帮助
	
	/**
	 * 向服务器发送数据
	 * @param action
	 * @param map
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map) {
		if (isNetworkConnected()) {
			return GetuiSdkHttpPost.httpPost(action, map);
		} else {
			//检查本机网络
			return "network error";
		}
	}
	
	/**
	 * 判断网络是否连接
	 * @return
	 */
	public static boolean isNetworkConnected() {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) PushConfig.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}
}