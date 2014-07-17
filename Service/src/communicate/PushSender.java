package communicate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

public class PushSender {
	
	/**
	 * 功能模块
	 */
	/*public static final String REGISTER = "register"; // 注册
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
	public static final String UPDATE_CID = "updatecid"; // 更新cid
	*/
	
	/**
	 * 向服务器发送数据
	 * @param action
	 * @param map
	 * @param connection_timeout
	 * @param read_timeout
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map, int connection_timeout, int read_timeout) {
		if (isNetworkConnected()) {
			Log.i("HttpPost", "prepare for data");
			// 数据预处理
			Iterator<String> ite = map.keySet().iterator();
			String key;
			while (ite.hasNext()) {
				key = ite.next();
				// 处理Bitmap图片数据
				if (map.get(key) instanceof Bitmap) {
					map.put(key, bitmapToString((Bitmap)map.get(key)));
				}
				// 处理File数据
				else if (map.get(key) instanceof File) {
					map.put(key, fileToString((File)map.get(key)));
				}
			}
			
			return GetuiSdkHttpPost.httpPost(action, map, connection_timeout, read_timeout);
		}
		
		return "network error";
	}
	
	/**
	 * 向服务器发送数据
	 * @param action
	 * @param map
	 * @param connection_timeout
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map, int connection_timeout) {
		return sendMessage(action, map, connection_timeout, PushConfig.READ_TIMEOUT_INT);
	}
	
	/**
	 * 向服务器发送数据
	 * @param action
	 * @param map
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map) {
		return sendMessage(action, map, PushConfig.CONNECTION_TIMEOUT_INT, PushConfig.READ_TIMEOUT_INT);
	}
	
	/**
	 * Bitmap转String
	 * @param bitmap
	 * @return
	 */
	private static String bitmapToString(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 0, stream);
		byte[] photo = stream.toByteArray();
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encode(photo, Base64.DEFAULT));
	}
	
	/**
	 * File转String
	 * @param file
	 * @return
	 */
	private static String fileToString(File file) {
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file.getPath());
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			int i;
			while ((i = fstream.read()) != -1) {
				ostream.write(i);
			}
			fstream.close();
			byte[] fstring = ostream.toByteArray();
			ostream.close();
			return new String(Base64.encode(fstring, Base64.DEFAULT));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
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
	
	/**
	 * 上传ClientId
	 */
	public static void sendClientId() {
		if (!PushConfig.username.equals("") && !PushConfig.clientId.equals("")) {
			new UpdateCID().execute();
		}
	}
	
	private static class UpdateCID extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	Map<String, Object> data = new HashMap<String, Object>();
        	data.put("username", PushConfig.username); 
            data.put("cid", PushConfig.clientId);
            return PushSender.sendMessage("updatecid",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}