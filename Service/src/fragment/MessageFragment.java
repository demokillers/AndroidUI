package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.MessageAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import client.ui.R;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MessageFragment extends Fragment{
	private PullToRefreshListView mListView;
	private MessageAdapter myadapter;
	private View view;
	private final Handler handler = new Handler();
	private List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>(); ; 
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
	
	public MessageFragment(){
		
	}
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) { 
		
		ViewGroup p = (ViewGroup) view.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
        } 	      
		return view;
   }
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		 
		super.onCreate(savedInstanceState);
		view=View.inflate(getActivity(),R.layout.pull_to_refreshlist,null); 
	    mListView=(PullToRefreshListView)view.findViewById(R.id.pull_to_refresh_list);   
	    myadapter=new MessageAdapter(getActivity());    
	    myadapter.setData(datalist);
	    mListView.setAdapter(myadapter);
	    setdata();
		// 设置PullToRefresh	    
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>(){
		 
		    // 下拉Pulling Down
		    @Override
		    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		    	// 下拉的时候数据重置
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		            
		    // 上拉Pulling Up
		    @Override
		    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		    		 // 上拉的时候添加选项
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		
		});	
	}
	 
	private void setdata(){
		
		
		//加入求助信息
		Map<String, Object> first_map = new HashMap<String, Object>();   
		first_map.put("image", (Integer)R.drawable.shopping);    //图片资源   
		first_map.put("name", (String)"求助者");                  //用户名
		first_map.put("time", (String)"2014.07.09 17:10");      //时间   
		first_map.put("content", (String)"肚子饿了怎么办？？？");      //求助内容   
			
		datalist.add(first_map);   
			
		//加入援助信息
	    for(int i = 0; i < name.length; i++) {   
	    	Map<String, Object> map = new HashMap<String, Object>();    
	        map.put("image", imgeIDs[i]);               //图片资源   
	        map.put("name", name[i]);              //用户名
	        map.put("time", time[i]);              //时间   
	        map.put("content", content[i]);        //援助内容   
	        datalist.add(map);   
	    }      
	    myadapter.notifyDataSetChanged();
	        
	}

}