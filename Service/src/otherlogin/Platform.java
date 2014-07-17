package otherlogin;

import otherlogin.SinaWeibo.StateSwitchExecption;

public abstract class Platform {
	public static String thirdPartyLogin = "https://114.215.133.61:8888/thirdPartLogin";
	public static String thirdPartyLogout = "https://114.215.133.61:8888/thirdPartLogout";
	public static String thirdPartyRemoveAccount = "https://114.215.133.61:8888/thirdPartRemoveAccount";
	
	public abstract void login() throws StateSwitchExecption;
	
	protected abstract void thirdPartyLogin();
	
	public abstract void logout() throws StateSwitchExecption;
	
	protected abstract void thirdPartyLogout();

	public abstract void revokeAuthorize() throws StateSwitchExecption;
	
	protected abstract void thirdPartyRemoveAccount();
}
