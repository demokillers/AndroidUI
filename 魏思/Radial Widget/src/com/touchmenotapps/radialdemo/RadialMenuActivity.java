package com.touchmenotapps.radialdemo;

import java.util.ArrayList;
import java.util.List;

//import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
//import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;





import com.touchmenotapps.radialdemo.widgets.RadialMenuItem;
import com.touchmenotapps.radialdemo.widgets.RadialMenuWidget;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class RadialMenuActivity extends FragmentActivity {

	private RadialMenuWidget pieMenu;

	private FrameLayout mFragmentContainer;
	public RadialMenuItem menuItem, menuClose1Item, menuClose2Item;
	public RadialMenuItem firstChildItem, secondChildItem, thirdChildItem;
	private List<RadialMenuItem> children = new ArrayList<RadialMenuItem>();
	
	int count = 1;

	@SuppressWarnings("serial")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Check the OS and set the app bar likewise
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
				&& Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR2) {
			setTheme(android.R.style.Theme_Holo_Light);
			setContentView(R.layout.activity_radial);
			getActionBar().setDisplayShowHomeEnabled(true);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			setTheme(android.R.style.Theme_Holo_Light_DarkActionBar);
			setContentView(R.layout.activity_radial);
			getWindow().setUiOptions(
					ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
			getActionBar().setDisplayShowHomeEnabled(true);
		} else {
			setTheme(R.style.RadialMenuLegacyTitleBar);
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			setContentView(R.layout.activity_radial);
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.layout_appbar);
			TextView barHeader = (TextView) findViewById(R.id.appbar_title_text);
			barHeader.setText(R.string.app_name);
		}
		
		mFragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

		pieMenu = new RadialMenuWidget(this);
		
		menuClose1Item = new RadialMenuItem(getString(R.string.close1), null);
		menuClose1Item.setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);		
		/*menuClose1Item.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
			@Override
			public void execute() {
			// menuLayout.removeAllViews();
				pieMenu.dismiss();
			}
		});*/

		menuClose2Item = new RadialMenuItem(getString(R.string.help2), getString(R.string.help2));
		//menuClose2Item.setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);
		
		
		menuItem = new RadialMenuItem(getString(R.string.help1),
				getString(R.string.help1));
		
		firstChildItem = new RadialMenuItem(getString(R.string.life),
				getString(R.string.life));
		firstChildItem.setDisplayIcon(R.drawable.ic_action_life);
		firstChildItem
				.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
					@Override
					public void execute() {
						// Can edit based on preference. Also can add animations
						// here.
						getSupportFragmentManager().popBackStack(null,
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
						getSupportFragmentManager()
								.beginTransaction()
								.replace(mFragmentContainer.getId(),
										new RadialMenuLifeFragment()).addToBackStack(null)
								.commit();
						pieMenu.dismiss();
						Toast.makeText(RadialMenuActivity.this, R.string.life, Toast.LENGTH_SHORT).show();
						count--;
					}
				});

		secondChildItem = new RadialMenuItem(getString(R.string.security),
				getString(R.string.security));
		secondChildItem.setDisplayIcon(R.drawable.ic_action_security);
		secondChildItem
				.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
					@Override
					public void execute() {
						// Can edit based on preference. Also can add animations
						// here.
						getSupportFragmentManager().popBackStack(null,
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
						getSupportFragmentManager()
								.beginTransaction()
								.replace(mFragmentContainer.getId(),
										new RadialMenuSecurityFragment()).addToBackStack(null)
								.commit();
						pieMenu.dismiss();
						Toast.makeText(RadialMenuActivity.this, R.string.security, Toast.LENGTH_SHORT).show();
						count--;
					}
				});

		thirdChildItem = new RadialMenuItem(getString(R.string.health),
				getString(R.string.health));
		thirdChildItem.setDisplayIcon(R.drawable.ic_action_health);
		thirdChildItem
				.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
					@Override
					public void execute() {
						// Can edit based on preference. Also can add animations
						// here.
						getSupportFragmentManager().popBackStack(null,
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
						getSupportFragmentManager()
								.beginTransaction()
								.replace(mFragmentContainer.getId(),
										new RadialMenuHealthFragment()).addToBackStack(null)
								.commit();
						pieMenu.dismiss();
						Toast.makeText(RadialMenuActivity.this, R.string.health, Toast.LENGTH_SHORT).show();
						count--;
					}
				});


		children.add(firstChildItem);
		children.add(secondChildItem);
		children.add(thirdChildItem);
		menuItem.setMenuChildren(children);
		menuClose2Item.setMenuChildren(children);

		// pieMenu.setDismissOnOutsideClick(true, menuLayout);
		pieMenu.setAnimationSpeed(0L);
		pieMenu.setSourceLocation(200, 200);
		pieMenu.setIconSize(40, 40);
		pieMenu.setTextSize(30);
		pieMenu.setOutlineColor(Color.BLACK, 10);
		//pieMenu.setInnerRingColor(0xAA66CC, 180);
		pieMenu.setInnerRingColor(0xEECA5B, 180);
		//pieMenu.setOuterRingColor(0x0099CC, 180);
		pieMenu.setOuterRingColor(0x2691e, 180);
		
		pieMenu.setInnerRingRadius(0, 120);
		pieMenu.setOuterRingRadius(125, 240);
		
		//pieMenu.setHeader("Test Menu", 20);
		//pieMenu.setCenterCircle(menuClose1Item);

		pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {
			{
				add(menuItem);
				add(menuClose2Item);
				//add(menuClose1Item);
			}
		});

		 //pieMenu.addMenuEntry(menuItem);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// Init
		
		findViewById(R.id.fragment_container).post(new Runnable() {
			public void run() {
				pieMenu.show(findViewById(R.id.fragment_container));
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			
			getSupportFragmentManager().popBackStack();
			
			if(count++ > 0)
			{
				finish();
			}
			
			else
			{
				pieMenu.show(); 
			}
			return true;
	    }

		return super.onKeyDown(keyCode, event);
	}
}
