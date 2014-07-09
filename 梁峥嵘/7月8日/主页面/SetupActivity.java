package com.example.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	
	private ListView list;
	private ArrayList listItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��listview
		list = (ListView)findViewById(R.id.listView1);
		
		listItem = new ArrayList<Map<String,String>>();
		
		setData();
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, listItem, R.layout.setting_list_items, new String[] {"title"}, new int[] {R.id.textView1});
		
		list.setAdapter(listAdapter); 

		list.setOnItemClickListener(new OnItemClickListener(){
			@SuppressWarnings("unchecked") 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView listView = (ListView)arg0;  
				Map<String, String> map = (Map<String, String>) listView.getItemAtPosition(arg2);  

				
                if(map.get("title").equals("��������")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(MainActivity.this, aboutYiZhu.class);
                    startActivity(intent); 
                    finish();
                } 
                if(map.get("title").equals("�������")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(MainActivity.this, Feekback.class);
                    startActivity(intent); 
                    finish();
                } 

			}
			
		});
	}
	
	//����ѡ������
	private void setData()
	{
		Map<String, String> map = new HashMap<String, String>();   
	    map.put("title", "������Ϣ");   
        listItem.add(map);   
		map = new HashMap<String, String>();  
	    map.put("title", "��������");   
        listItem.add(map);   
		map = new HashMap<String, String>();  
	    map.put("title", "�޸�����");   
        listItem.add(map); 
		map = new HashMap<String, String>();     
	    map.put("title", "�������");  
        listItem.add(map);   
		map = new HashMap<String, String>();   
	    map.put("title", "ע��");  
        listItem.add(map);   
		map = new HashMap<String, String>();    
	    map.put("title", "�˳�");  
        listItem.add(map);   
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
