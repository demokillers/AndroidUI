package fragment;

import java.util.ArrayList;
import java.util.List;

import radialdemo.RadialMenuItem;
import radialdemo.RadialMenuWidget;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import client.ui.R;
import client.ui.SendHelpMsgActivity;

public class HelpFragment extends Fragment{
	private RadialMenuWidget pieMenu;
	public RadialMenuItem menuItem, menuClose1Item, menuClose2Item;
	public RadialMenuItem firstChildItem, secondChildItem, thirdChildItem;
	private List<RadialMenuItem> children = new ArrayList<RadialMenuItem>();
	private View view;

	@SuppressWarnings("serial")
	public HelpFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {       

		
		
		 //pieMenu.addMenuEntry(menuItem);
		ViewGroup p = (ViewGroup) view.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
        } 

        return view;
    }

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
				&& Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR2) {
			getActivity().setTheme(android.R.style.Theme_Holo_Light);
			view=View.inflate(getActivity(),R.layout.activity_radial,null); 
			getActivity().getActionBar().setDisplayShowHomeEnabled(true);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			
			getActivity().setTheme(android.R.style.Theme_Holo_Light_DarkActionBar);
			view=View.inflate(getActivity(),R.layout.activity_radial,null); 
			
			getActivity().getWindow().setUiOptions(
					ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
			getActivity().getActionBar().setDisplayShowHomeEnabled(true);
			
		} else {
			getActivity().setTheme(R.style.RadialMenuLegacyTitleBar);
			getActivity().requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			view=View.inflate(getActivity(),R.layout.activity_radial,null); 
		}*/	
		view=View.inflate(getActivity(),R.layout.help,null); 
		//pieMenu = new RadialMenuWidget(getActivity());
		pieMenu =(RadialMenuWidget)view.findViewById(R.id.raidal);
		//menuClose1Item = new RadialMenuItem(getString(R.string.close1), null);
		//menuClose1Item.setDisplayIcon(android.R.drawable.ic_menu_close_clear_cancel);		
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
						getActivity().startActivity(new Intent(getActivity(),SendHelpMsgActivity.class));
						pieMenu.dismiss();
						Toast.makeText(getActivity(), R.string.life, Toast.LENGTH_SHORT).show();
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
						getActivity().startActivity(new Intent(getActivity(),SendHelpMsgActivity.class));
						pieMenu.dismiss();
						Toast.makeText(getActivity(), R.string.security, Toast.LENGTH_SHORT).show();
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
						getActivity().startActivity(new Intent(getActivity(),SendHelpMsgActivity.class));
						pieMenu.dismiss();
						Toast.makeText(getActivity(), R.string.health, Toast.LENGTH_SHORT).show();
					}
				});


		//if(children.size() != 3)
		//{
		children.add(firstChildItem);
		children.add(secondChildItem);
		children.add(thirdChildItem);
		//}
		menuItem.setMenuChildren(children);
		menuClose2Item.setMenuChildren(children);

		// pieMenu.setDismissOnOutsideClick(true, menuLayout);
		pieMenu.setAnimationSpeed(0L);
		pieMenu.setSourceLocation(200, 200);
		pieMenu.setCenterLocation(360, 450);
		pieMenu.setIconSize(30, 30);
		pieMenu.setTextSize(20);
		pieMenu.setOutlineColor(Color.BLACK, 5);
		//pieMenu.setInnerRingColor(0xAA66CC, 180);
		pieMenu.setInnerRingColor(0xEECA5B, 180);
		//pieMenu.setOuterRingColor(0x0099CC, 180);
		pieMenu.setOuterRingColor(0x2691e, 180);
		
		pieMenu.setInnerRingRadius(0, 80);
		pieMenu.setOuterRingRadius(85, 140);
		
		//pieMenu.setHeader("Test Menu", 20);
		//pieMenu.setCenterCircle(menuClose1Item);

		pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {
			{
				add(menuItem);
				add(menuClose2Item);
				//add(menuClose1Item);
			}
		});
	}
	
}
