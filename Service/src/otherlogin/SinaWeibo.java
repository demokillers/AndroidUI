package otherlogin;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class SinaWeibo extends Platform implements SinaWeiboAPI.APIRequestCallback {
	private String access_token;
	private String uid;
	
	public static final int UNKNOWN = 1; // no access token
	public static final int AUTHORIZED = 2; // get access token and user has logged in
	
	private static SinaWeibo instance = null;
	int state;
	private SinaWeiboAPI api;
	
	private SinaWeibo() {
		api = SinaWeiboAPI.getInstance();
		api.setAPIRequestCallback(this);
		state = UNKNOWN;
	}
	
	public static void init(Context context) {
		SinaWeiboAPI.init(context);
	}

	public static SinaWeibo getInstance() {
		if (instance == null) {
			instance = new SinaWeibo();
		}
		return instance;
	}

	public void login() throws StateSwitchExecption {
		if (state == UNKNOWN) {
			api.authorize(null);
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public void revokeAuthorize() throws StateSwitchExecption {
		if (state == AUTHORIZED) {
			api.revokeauthorize(null);
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public void logout() throws StateSwitchExecption {
		if (state == AUTHORIZED) {
			access_token = null;
			state = UNKNOWN;
			thirdPartyLogout();
		} else {
			throw new StateSwitchExecption("×´Ì¬×ª»»´íÎó");
		}
	}
	
	public class StateSwitchExecption extends Exception {
		private static final long serialVersionUID = 1L;
		public StateSwitchExecption() {
			super();
		}
		public StateSwitchExecption(String msg) {
			super(msg);
		}
	}

	@Override
	protected void thirdPartyLogin() {
		// TODO Auto-generated method stub
		String poststr = "access_token=" + access_token;
		HttpsVisiter.post(thirdPartyLogin, poststr);
	}

	@Override
	protected void thirdPartyLogout() {
		// TODO Auto-generated method stub
		HttpsVisiter.post(thirdPartyLogout, null);
	}

	@Override
	protected void thirdPartyRemoveAccount() {
		// TODO Auto-generated method stub
		HttpsVisiter.post(thirdPartyRemoveAccount, null);
	}

	@Override
	public void onAuthorizeComplete(String code) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
		params.put("code", code);
		api.access_token(params);
		state = AUTHORIZED;
	}

	@Override
	public void onAccessTokenComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		access_token = result.get("access_token");
		uid = result.get("uid");
		thirdPartyLogin();
	}

	@Override
	public void onGetTokenInfoComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRevokeAuthorizeComplete(Map<String, String> result) {
		// TODO Auto-generated method stub
		access_token = null;
		state = UNKNOWN;
		thirdPartyRemoveAccount();
	}
}
