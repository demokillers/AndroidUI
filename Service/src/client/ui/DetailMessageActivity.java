package client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.AssistListViewAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class DetailMessageActivity extends Activity {
	
	private ListView listView;  
	private AssistListViewAdapter assistListViewAdapter;
	private List<Map<String, Object>> listItems; 
	private Integer[] imgeIDs = {R.drawable.cake,    
            R.drawable.gift, R.drawable.letter,   
            R.drawable.love, R.drawable.mouse,   
            R.drawable.music};  
	private String[] name = {"蛋糕", "礼物",    
            "邮票", "爱心", "鼠标", "音乐CD"}; 
	
	private String[] time = {
			 "2011.02.12",
			 "2041.11.21",
			 "2012.03.24",
			 "2014.05.12",
			 "2012.07.15",
			 "2013.06.22"};
	 
	 private String[] content = {   
	            "蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。",    
	            "礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。",    
	            "环游世界。环游世界。环游世界。环游世界。",    
	            "世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。",   
	            "反应敏捷。反应敏捷。反应敏捷。反应敏捷。反应敏捷。反应敏捷。",   
	            "酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。"}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailmessage_activity);
		
		
		listView = (ListView)findViewById(R.id.assist_list);
		listItems = getListItems(); 
		assistListViewAdapter = new AssistListViewAdapter(this, listItems);
		listView.setAdapter(assistListViewAdapter);
	}
	
	private List<Map<String, Object>> getListItems(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); 
		
		//加入求助信息
		Map<String, Object> first_map = new HashMap<String, Object>();   
		first_map.put("image", (Integer)R.drawable.shopping);    //图片资源   
		first_map.put("name", (String)"求助者");                  //用户名
		first_map.put("time", (String)"2014.07.09 17:10");      //时间   
		first_map.put("content", (String)"肚子饿了怎么办？？？");      //求助内容   
		first_map.put("concern", (String)"关注(0)");      //关注人数
		first_map.put("assist", (String)"帮助(0)");       //援助人数
		
        listItems.add(first_map);   
		
		//加入援助信息
        for(int i = 0; i < name.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imgeIDs[i]);               //图片资源   
            map.put("name", name[i]);              //用户名
            map.put("time", time[i]);              //时间   
            map.put("content", content[i]);        //援助内容   
            listItems.add(map);   
        }      
        
		return listItems;
	}



}
