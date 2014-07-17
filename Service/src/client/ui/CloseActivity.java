package client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

public class CloseActivity extends ListActivity {

	ListView mlist;
	RatingBar ratingbar;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.close);
		
		mlist = (ListView)findViewById(android.R.id.list);
		ratingbar=(RatingBar)findViewById(R.id.ratingBar1);
		button=(Button) findViewById(R.id.button1);
		SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.closeitem,new String[]{"title","img","textView2"},new int[]{R.id.title,R.id.img,R.id.textView2});
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
		map.put("img", R.drawable.img01);
		map.put("textView2","服务态度");
		list.add(map);
		
		map=new HashMap<String,Object>();
		map.put("title", "帮客3");
		map.put("img", R.drawable.img01);
		map.put("textView2","服务态度");
		list.add(map);
		
		map=new HashMap<String,Object>();
		map.put("title", "帮客4");
		map.put("img", R.drawable.img01);
		map.put("textView2","服务态度");
		list.add(map);
		return list;
	}


}
