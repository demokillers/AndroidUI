package routeplan;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class HelpApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
	
}