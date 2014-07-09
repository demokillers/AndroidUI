package com.example.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SetUpActivity extends Activity {

		ListView list;
		ArrayList listItem;

		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_up);
		
		list = (ListView)findViewById(R.id.MyListView);
		listItem = new ArrayList<Map<String,Object>>();
		getData();
		
		SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.item_setting,new String[]{"title"},new int[]{R.id.title});
		list.setAdapter(adapter);
		
		//实现跳转到修改密码界面
		list.setOnItemClickListener(new OnItemClickListener(){
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0,View arg1,int arg2,long arg3) {
				// TODO Auto-generated method stub
				ListView listView =(ListView)arg0;
				Map<String, String> map = (Map<String, String>) listView.getItemAtPosition(arg2);  
				
				if(map.get("title").equals("修改密码")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(SetUpActivity.this, ChgPwdActivity.class);
                    startActivity(intent); 
                    finish();
                } 
			}
		});
		
		
	
	}
	
	private void getData(){
		ArrayList data = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title","个人信息");
		//map.put("img",R.drawable.icon);
		listItem.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title","修改密码");
		//map.put("img",R.drawable.icon);
		listItem.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title","关于易助");
		//map.put("img",R.drawable.icon);
		listItem.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title","反馈");
		map.put("img",R.drawable.icon);
		listItem.add(map);
		
		map = new HashMap<String,Object>();
		map.put("title","退出登录");
		//map.put("img",R.drawable.icon);
		listItem.add(map);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_up, menu);
		return true;
	}
}
