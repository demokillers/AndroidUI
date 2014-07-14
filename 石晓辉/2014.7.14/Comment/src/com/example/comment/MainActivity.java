package com.example.comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import android.R;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	ListView mlist;
	RatingBar ratingbar;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mlist = (ListView)findViewById(android.R.id.list);
		ratingbar=(RatingBar)findViewById(R.id.ratingBar1);
		button=(Button) findViewById(R.id.button1);
		SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.item,new String[]{"title","img","textView2"},new int[]{R.id.title,R.id.img,R.id.textView2});
		setListAdapter(adapter);
		
//		ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//			
//			@Override
//			public void onRatingChanged(RatingBar ratingBar, float rating,
//					boolean fromUser) {
//				// TODO Auto-generated method stub
//				
//				
//			}
//		});
	}
	
	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", "帮客1");
		map.put("img", R.drawable.img01);
		map.put("textView2","服务态度");
		list.add(map);
		
		map=new HashMap<String,Object>();
		map.put("title", "帮客2");
		map.put("img", R.drawable.img02);
		map.put("textView2","服务态度");
		list.add(map);
		
		map=new HashMap<String,Object>();
		map.put("title", "帮客3");
		map.put("img", R.drawable.img03);
		map.put("textView2","服务态度");
		list.add(map);
		
		map=new HashMap<String,Object>();
		map.put("title", "帮客4");
		map.put("img", R.drawable.img04);
		map.put("textView2","服务态度");
		list.add(map);
		return list;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
