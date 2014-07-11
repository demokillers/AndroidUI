package client.ui;

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

public class SetupActivity extends Activity {
	
	private ListView list;
	private ArrayList listItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup);
		
		//绑定listview
		list = (ListView)findViewById(R.id.listView1);
		
		listItem = new ArrayList<Map<String,String>>();
		
		setData();
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, listItem, R.layout.setlist_items, new String[] {"title"}, new int[] {R.id.textView1});
		
		list.setAdapter(listAdapter); 

		list.setOnItemClickListener(new OnItemClickListener(){
			@SuppressWarnings("unchecked") 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ListView listView = (ListView)arg0;  
				Map<String, String> map = (Map<String, String>) listView.getItemAtPosition(arg2);  

				
                if(map.get("title").equals("关于易助")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(SetupActivity.this, aboutYiZhu.class);
                    startActivity(intent); 
                } 
                if(map.get("title").equals("意见反馈")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(SetupActivity.this, Feekback.class);
                    startActivity(intent); 
                } 
                
                if(map.get("title").equals("个人信息")) 
                { 
                    Intent intent = new Intent(); 
                    intent.setClass(SetupActivity.this, PersonalInfoActivity.class);
                    startActivity(intent); 
                } 

			}
			
		});
	}
	
	//载入选项内容
	private void setData()
	{
		Map<String, String> map = new HashMap<String, String>();   
	    map.put("title", "个人信息");   
        listItem.add(map);   
		map = new HashMap<String, String>();  
	    map.put("title", "关于易助");   
        listItem.add(map);   
		map = new HashMap<String, String>();  
	    map.put("title", "修改密码");   
        listItem.add(map); 
		map = new HashMap<String, String>();     
	    map.put("title", "意见反馈");  
        listItem.add(map);   
		map = new HashMap<String, String>();   
	    map.put("title", "注销");  
        listItem.add(map);   
		map = new HashMap<String, String>();    
	    map.put("title", "退出");  
        listItem.add(map);   
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
