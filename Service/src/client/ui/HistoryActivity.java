package client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.AssistHistoryAdapter;
import adapter.HelpHistoryAdapter;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;

public class HistoryActivity extends TabActivity {

	private ListView listView1, listView2;
	private HelpHistoryAdapter helpHistoryAdapter;
	private AssistHistoryAdapter assistHistoryAdapter;
	private List<Map<String, Object>> listItems1, listItems2;
	
	private Integer[] imageIds1 = {R.drawable.img00, R.drawable.img01,
			R.drawable.img01, R.drawable.img10, R.drawable.img11};
	private Integer[] imageIds2 = {R.drawable.img01, R.drawable.img01,
			R.drawable.img01, R.drawable.img01, R.drawable.img01};
	
	private String name1[] = {"ReReHello", "Kitty", "Apple", "Hychil", "Cindy"};
	private String name2[] = {"ReReHello", "Kitty", "Apple", "Hychil", "Cindy"};
	
	private String[] time1 = {
			 "2011.02.12",
			 "2041.11.21",
			 "2012.03.24",
			 "2014.05.12",
			 "2013.06.22"};
	private String[] time2 = {
			 "2011.02.12",
			 "2041.11.21",
			 "2012.03.24",
			 "2014.05.12",
			 "2013.06.22"};
	 
	 private String[] content1 = {   
	            "蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。蛋糕好好吃。",    
	            "礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。礼轻情重。",    
	            "环游世界。环游世界。环游世界。环游世界。",    
	            "世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。世界都有爱。",   
	            "反应敏捷。反应敏捷。反应敏捷。反应敏捷。反应敏捷。反应敏捷。",   
	            "酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。酷我音乐。"}; 
	 private String[] content2 = {   
	            "吼吼吼吼吼吼吼吼吼吼吼吼哈哈哈吼吼吼吼吼吼吼吼吼吼吼吼哈哈哈吼吼吼吼吼吼吼吼吼吼吼吼",    
	            "一叶知秋",    
	            "切切切切切切切切切切切切切切切",    
	            "vvvvvvvvvvv",   
	            "快快快快快快快快快快快快快快快快快快快快快快快快",   
	            "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦拉了拉"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		TabHost tabhost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.history_main, tabhost.getTabContentView(), true);
		
		// 添加第一个标签页
		tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("求助历史").setContent(R.id.tab1));
		listView1 = (ListView)findViewById(R.id.askhelp_history);
		listItems1 = getListItems1();
		helpHistoryAdapter = new HelpHistoryAdapter(this, listItems1);
		listView1.setAdapter(helpHistoryAdapter);
		
		// 添加第二个标签页
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("援助历史").setContent(R.id.tab2));
		listView2 = (ListView)findViewById(R.id.assist_history);
		listItems2 = getListItems2();
		assistHistoryAdapter = new AssistHistoryAdapter(this, listItems2);
		listView2.setAdapter(assistHistoryAdapter);
	}

	private List<Map<String, Object>> getListItems1(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); 
		
		//加入求助记录
        for(int i = 0; i < name1.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imageIds1[i]);               //图片资源   
            map.put("name", name1[i]);              //用户名
            map.put("time", time1[i]);              //时间   
            map.put("content", content1[i]);        //援助内容   
            listItems.add(map);   
        }
        return listItems;
	}
	private List<Map<String, Object>> getListItems2(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); 
        //加入援助记录
        for(int i = 0; i < name2.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imageIds2[i]);               //图片资源   
            map.put("name", name2[i]);              //用户名
            map.put("time", time2[i]);              //时间   
            map.put("content", content2[i]);        //援助内容   
            listItems.add(map);   
        }
		return listItems;
	}
	

}
