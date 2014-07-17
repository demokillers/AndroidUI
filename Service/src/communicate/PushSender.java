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
	 * ����ģ��
	 */
	/*public static final String REGISTER = "register"; // ע��
	public static final String LOGIN = "login"; // ��¼
	public static final String USER_AUTHENTICATION = "userauthentication"; // �û���֤
	public static final String LOGOUT = "logout"; // �ǳ�
	public static final String CANCEL = "cancel"; // ע��
	public static final String CHECK_RELATIVES = "checkrelatives"; // �鿴�����б�
	public static final String DELETE_RELATIVES = "deleterelatives"; // ɾ������
	public static final String ADD_RELATIVES = "addrelatives"; // �������
	public static final String CHECK_HISTORY = "history"; // �鿴��Ϣ��ʷ��¼
	public static final String SEND_HELP_MESSAGE = "pophelpmessage"; // ����������Ϣ
	public static final String ADD_SUPPORT_MESSAGE = "supportmessage"; // ��Ӹ�����Ϣ
	public static final String END_HELP = "finish"; // ��������
	public static final String GIVE_CREDIT = "givecredit"; // ����
	public static final String ADD_AID = "addaid"; // ������� 
	public static final String SEND_SUPPORT_MESSAGE = "sendsupport"; // ����Ԯ����Ϣ
	public static final String QUIT_AID = "quitaid"; // �˳�����
	public static final String UPDATE_CID = "updatecid"; // ����cid
	*/
	
	/**
	 * ���������������
	 * @param action
	 * @param map
	 * @param connection_timeout
	 * @param read_timeout
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map, int connection_timeout, int read_timeout) {
		if (isNetworkConnected()) {
			Log.i("HttpPost", "prepare for data");
			// ����Ԥ����
			Iterator<String> ite = map.keySet().iterator();
			String key;
			while (ite.hasNext()) {
				key = ite.next();
				// ����BitmapͼƬ����
				if (map.get(key) instanceof Bitmap) {
					map.put(key, bitmapToString((Bitmap)map.get(key)));
				}
				// ����File����
				else if (map.get(key) instanceof File) {
					map.put(key, fileToString((File)map.get(key)));
				}
			}
			
			return GetuiSdkHttpPost.httpPost(action, map, connection_timeout, read_timeout);
		}
		
		return "network error";
	}
	
	/**
	 * ���������������
	 * @param action
	 * @param map
	 * @param connection_timeout
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map, int connection_timeout) {
		return sendMessage(action, map, connection_timeout, PushConfig.READ_TIMEOUT_INT);
	}
	
	/**
	 * ���������������
	 * @param action
	 * @param map
	 * @return
	 */
	public static String sendMessage(String action, Map<String, Object> map) {
		return sendMessage(action, map, PushConfig.CONNECTION_TIMEOUT_INT, PushConfig.READ_TIMEOUT_INT);
	}
	
	/**
	 * BitmapתString
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
	 * FileתString
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
	 * �ж������Ƿ�����
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
	 * �ϴ�ClientId
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