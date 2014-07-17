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
	private String[] name = {"����", "����",    
            "��Ʊ", "����", "���", "����CD"}; 
	
	private String[] time = {
			 "2011.02.12",
			 "2041.11.21",
			 "2012.03.24",
			 "2014.05.12",
			 "2012.07.15",
			 "2013.06.22"};
	 
	 private String[] content = {   
	            "����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�",    
	            "�������ء��������ء��������ء��������ء��������ء��������ء��������ء��������ء�",    
	            "�������硣�������硣�������硣�������硣",    
	            "���綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а���",   
	            "��Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ�",   
	            "�������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡�"}; 
	
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
		// ����PullToRefresh	    
		mListView.setMode(Mode.BOTH);
		mListView.setOnRefreshListener(new OnRefreshListener2<ListView>(){
		 
		    // ����Pulling Down
		    @Override
		    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		    	// ������ʱ����������
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		            
		    // ����Pulling Up
		    @Override
		    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		    		 // ������ʱ�����ѡ��
		    	handler.postDelayed(new Runnable() {
					public void run() {
						mListView.onRefreshComplete();
					}
				}, 1000);
		    }
		
		});	
	}
	 
	private void setdata(){
		
		
		//����������Ϣ
		Map<String, Object> first_map = new HashMap<String, Object>();   
		first_map.put("image", (Integer)R.drawable.shopping);    //ͼƬ��Դ   
		first_map.put("name", (String)"������");                  //�û���
		first_map.put("time", (String)"2014.07.09 17:10");      //ʱ��   
		first_map.put("content", (String)"���Ӷ�����ô�죿����");      //��������   
			
		datalist.add(first_map);   
			
		//����Ԯ����Ϣ
	    for(int i = 0; i < name.length; i++) {   
	    	Map<String, Object> map = new HashMap<String, Object>();    
	        map.put("image", imgeIDs[i]);               //ͼƬ��Դ   
	        map.put("name", name[i]);              //�û���
	        map.put("time", time[i]);              //ʱ��   
	        map.put("content", content[i]);        //Ԯ������   
	        datalist.add(map);   
	    }      
	    myadapter.notifyDataSetChanged();
	        
	}

}