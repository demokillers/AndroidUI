package com.example.detailmessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
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
		
		//����������Ϣ
		Map<String, Object> first_map = new HashMap<String, Object>();   
		first_map.put("image", (Integer)R.drawable.shopping);    //ͼƬ��Դ   
		first_map.put("name", (String)"������");                  //�û���
		first_map.put("time", (String)"2014.07.09 17:10");      //ʱ��   
		first_map.put("content", (String)"���Ӷ�����ô�죿����");      //��������   
		first_map.put("concern", (String)"��ע(0)");      //��ע����
		first_map.put("assist", (String)"Ԯ��(0)");       //Ԯ������
		
        listItems.add(first_map);   
		
		//����Ԯ����Ϣ
        for(int i = 0; i < name.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imgeIDs[i]);               //ͼƬ��Դ   
            map.put("name", name[i]);              //�û���
            map.put("time", time[i]);              //ʱ��   
            map.put("content", content[i]);        //Ԯ������   
            listItems.add(map);   
        }      
        
		return listItems;
	}



}
