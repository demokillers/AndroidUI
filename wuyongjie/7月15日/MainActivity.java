package com.example.history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TabActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends TabActivity implements OnGestureListener {

	private ListView listView1, listView2;
	private HelpHistoryAdapter helpHistoryAdapter;
	private AssistHistoryAdapter assistHistoryAdapter;
	private List<Map<String, Object>> listItems1, listItems2;
	
	private TabHost tabhost;
	private int ID = 1;
	private GestureDetector detector;
	
	private Integer[] imageIds1 = {R.drawable.img00, R.drawable.img01,
			R.drawable.img02, R.drawable.img10, R.drawable.img11};
	private Integer[] imageIds2 = {R.drawable.img12, R.drawable.img13,
			R.drawable.img14, R.drawable.img15, R.drawable.img16};
	
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
		tabhost = getTabHost();
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
		
		tabhost.setCurrentTab(1);
		detector = new GestureDetector(this);
	}
	////
	/*
	@SuppressWarnings("deprecation")
	private GestureDetector detector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if ((e2.getRawX() - e1.getRawX()) > 80) {
                        showNext();
                        Log.e("haha", "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                        //return true;
                }
                else if ((e1.getRawX() - e2.getRawX()) > 80) {
                        showPre();
                        Log.e("haha", "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
                        //return true;
                }
                //return super.onFling(e1, e2, velocityX, velocityY);
                return true;
        }

	});
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
            detector.onTouchEvent(event);
            return super.onTouchEvent(event);
    }

  
    protected void showNext() {

    	if(ID == 1)
    	{
    		tabhost.setCurrentTab(0);
    		ID = 0;
    	}

    }


    protected void showPre() {
    	if(ID == 0)
    	{
    		tabhost.setCurrentTab(1);
    		ID = 1;
    	}

    }
    */
	////

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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//重点是添加如下;
	@Override
		public boolean onTouchEvent(MotionEvent event) {
			Log.e("haha", "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
			return detector.onTouchEvent(event);
	}
	
	public boolean dispatchTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        listView1.onTouchEvent(ev);
        listView2.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);

	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if ((e2.getRawX() - e1.getRawX()) > 80) {
            showNext();
            return true;
	    }
	    else if ((e1.getRawX() - e2.getRawX()) > 80) {
            showPre();
            return true;
	    }
	    return true;

	}
	
	protected void showNext() {
    	if(ID == 1)
    	{
    		tabhost.setCurrentTab(0);
    		ID = 0;
    	}
    	else 
    	{
    		tabhost.setCurrentTab(1);
    		ID = 1;
    	}
    }


    protected void showPre() {
    	if(ID == 0)
    	{
    		tabhost.setCurrentTab(1);
    		ID = 1;
    	}
    	else 
    	{
    		tabhost.setCurrentTab(0);
    		ID = 0;
    	}
    }
}
