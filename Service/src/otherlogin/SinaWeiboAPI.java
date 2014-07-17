package otherlogin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SinaWeiboAPI {
	private static final String appkey = "880517188";
	private static final String appsecret = "d5c2f15e5437ebf4a7ea39110c114d78";
	private static final String redirect_uri = "114.215.133.61:8888";

	private static final String encoding = "utf-8";
	private static final String api_authorize = "https://open.weibo.cn/oauth2/authorize";
	private static Map<String, String> params_authorize = new HashMap<String, String>();
	static {
		params_authorize.put("client_id", appkey);
		params_authorize.put("redirect_uri", redirect_uri);
		params_authorize.put("scope", "friendships_groups_read");
		params_authorize.put("display", "mobile");
		params_authorize.put("forcelogin", "true");
	}
	private static final String api_access_token = "https://api.weibo.com/oauth2/access_token";
	private static Map<String, String> params_access_token = new HashMap<String, String>();
	static {
		params_access_token.put("client_id", appkey);
		params_access_token.put("client_secret", appsecret);
		params_access_token.put("grant_type", "authorization_code");
		params_access_token.put("code", ""); // must set new code
		params_access_token.put("redirect_uri", redirect_uri);
	}
	private static final String api_revokeauthorize = "https://api.weibo.com/oauth2/revokeoauth2";
	private static Map<String, String> params_revokeauthorize = new HashMap<String, String>();
	static {
		params_revokeauthorize.put("access_token", "");
	}
	private static final String api_get_token_info = "https://api.weibo.com/oauth2/get_token_info";
	private static Map<String, String> params_get_token_info = new HashMap<String, String>();
	static {
		params_get_token_info.put("access_token", "");
	}
	
	private static Class<SinaWeiboLoginActivity> SinaWeiboLoginActivityClass = SinaWeiboLoginActivity.class;
	private static SinaWeiboAPI instance = null;
	private static Context context;
	//public String AuthorizeCode;
	APIRequestCallback callback;
	
	private SinaWeiboAPI() {
	}
	
	public static void init(Context _context) {
		context = _context;
	}
	
	public static SinaWeiboAPI getInstance() {
		if (instance == null) {
			instance = new SinaWeiboAPI();
		}
		return instance;
	}
	
	private static String getParams(Map<String, String> params, Map<String, String> default_params) {
		String paramStr = "";
		String key;
		try {
			if (params == null) {
				for (Map.Entry<String, String> entry : default_params.entrySet()) {
					paramStr += "&" + entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), encoding);
				}
			} else {
				for (Map.Entry<String, String> entry : default_params.entrySet()) {
					key = entry.getKey();
					Log.v("key", key);
					paramStr += "&" + key + "=";
					if (params.containsKey(key)) {
						paramStr += URLEncoder.encode(params.get(key), encoding);
					} else {
						paramStr += URLEncoder.encode(entry.getValue(), encoding);
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return paramStr.substring(1, paramStr.length());
	}
	
	private static Map<String, String> String2Map(String jsonstr) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			JSONObject json = new JSONObject(jsonstr);
			@SuppressWarnings("unchecked")
			Iterator<String> iter = json.keys();
			String key;
			while (iter.hasNext()) {
				key = iter.next();
				result.put(key, json.getString(key));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void setAPIRequestCallback(APIRequestCallback _callback) {
		callback = _callback;
	}
	
	public void authorize(Map<String, String> params) {
		String gets;
		gets = getParams(params, params_authorize);
		Intent intent = new Intent(context, SinaWeiboLoginActivityClass);
		intent.putExtra("url", api_authorize);
		intent.putExtra("gets", gets);
		intent.putExtra("redirect_uri", redirect_uri);
		context.startActivity(intent);
		Log.v("mark", "authorize");
	}
	
	public void access_token(Map<String, String> params) {
		final String posts = getParams(params, params_access_token);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String resultstr = HttpsVisiter.post(api_access_token, posts);
				Map<String, String> result = String2Map(resultstr);
				callback.onAccessTokenComplete(result);
			}

		}).start();
	}
	
	public void get_token_info(Map<String, String> params) {
		final String posts = getParams(params, params_get_token_info);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String resultstr = HttpsVisiter.post(api_get_token_info, posts);
				Map<String, String> result = String2Map(resultstr);
				callback.onGetTokenInfoComplete(result);
			}

		}).start();
	}
	
	public void revokeauthorize(Map<String, String> params) {
		final String posts = getParams(params, params_revokeauthorize);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String resultstr = HttpsVisiter.post(api_revokeauthorize, posts);
				Map<String, String> result = String2Map(resultstr);
				callback.onRevokeAuthorizeComplete(result);
			}

		}).start();
	}
	
	public interface APIRequestCallback {
		public void onAuthorizeComplete(String code);
		public void onAccessTokenComplete(Map<String, String> result);
		public void onGetTokenInfoComplete(Map<String, String> result);
		public void onRevokeAuthorizeComplete(Map<String, String> result);
	}
	
	
}
