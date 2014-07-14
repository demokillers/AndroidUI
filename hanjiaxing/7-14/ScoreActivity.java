package com.example.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class ScoreActivity extends Activity{
	
	private ListView listView;  
	private ScoreAdapter scoreAdapter;
	private List<Map<String, Object>> listItems;

	
	private Integer[] imageId = {R.drawable.bb_boy,    
            R.drawable.cross, R.drawable.sex};
	private String[] name=new String[]{"Äã","ÎÒ","Ëû"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list);
		
		listView = (ListView)findViewById(R.id.score_listview);	
		listItems = new ArrayList<Map<String, Object>>(); 
        for(int i = 0; i < name.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imageId[i]);
            map.put("name", name[i]);
            listItems.add(map);   
        } 
		scoreAdapter = new ScoreAdapter(this, listItems);
		listView.setAdapter(scoreAdapter);	
	}
}
