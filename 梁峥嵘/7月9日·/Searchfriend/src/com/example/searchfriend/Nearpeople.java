package com.example.searchfriend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;


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
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, listItem, R.layout.child_item_layout, new String[] {"img","name","detail"}, new int[] {R.id.imageView1, R.id.item_name,R.id.item_detail});
	
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
