package otherlogin;

import android.content.Context;

public class ThirdPartyPlatform {
	public static final int SINAWEIBO = 1;
	
	public static void init(Context context) {
		SinaWeibo.init(context);
	}
	
	public static Platform getPlatform(int PLATFORM) {
		Platform platform = null;
		switch (PLATFORM) {
			case SINAWEIBO:
				platform = SinaWeibo.getInstance();
				break;
			default:
				break;
		}
		return platform;
	}
}
