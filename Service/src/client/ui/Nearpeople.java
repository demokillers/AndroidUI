package client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.AddFriendAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;


public class Nearpeople extends Activity {
	
	private ListView list;
	private ArrayList listItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearpeoplelist);
		
		list = (ListView)findViewById(R.id.listView1);
		
		listItem = new ArrayList<Map<String,Object>>();
		
		setData();
		
		AddFriendAdapter listAdapter = new AddFriendAdapter(this, listItem);
		
		list.setAdapter(listAdapter); 
		
	}
	
	private void setData()
	{
		Map<String, Object> map = new HashMap<String, Object>();   
	    map.put("img", R.drawable.img00);   
	    map.put("name", "ÕÅÈý");
	    map.put("detail", "Good job!");
        listItem.add(map);    
	}
	

}
