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
	            "����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�����úóԡ�",    
	            "�������ء��������ء��������ء��������ء��������ء��������ء��������ء��������ء�",    
	            "�������硣�������硣�������硣�������硣",    
	            "���綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а������綼�а���",   
	            "��Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ���Ӧ���ݡ�",   
	            "�������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡��������֡�"}; 
	 private String[] content2 = {   
	            "�������������������������������������������������",    
	            "һҶ֪��",    
	            "������������������������������",    
	            "vvvvvvvvvvv",   
	            "�������������������������",   
	            "��������������������������������������������������������������"}; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		TabHost tabhost = getTabHost();
		LayoutInflater.from(this).inflate(R.layout.history_main, tabhost.getTabContentView(), true);
		
		// ��ӵ�һ����ǩҳ
		tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("������ʷ").setContent(R.id.tab1));
		listView1 = (ListView)findViewById(R.id.askhelp_history);
		listItems1 = getListItems1();
		helpHistoryAdapter = new HelpHistoryAdapter(this, listItems1);
		listView1.setAdapter(helpHistoryAdapter);
		
		// ��ӵڶ�����ǩҳ
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("Ԯ����ʷ").setContent(R.id.tab2));
		listView2 = (ListView)findViewById(R.id.assist_history);
		listItems2 = getListItems2();
		assistHistoryAdapter = new AssistHistoryAdapter(this, listItems2);
		listView2.setAdapter(assistHistoryAdapter);
	}

	private List<Map<String, Object>> getListItems1(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); 
		
		//����������¼
        for(int i = 0; i < name1.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imageIds1[i]);               //ͼƬ��Դ   
            map.put("name", name1[i]);              //�û���
            map.put("time", time1[i]);              //ʱ��   
            map.put("content", content1[i]);        //Ԯ������   
            listItems.add(map);   
        }
        return listItems;
	}
	private List<Map<String, Object>> getListItems2(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>(); 
        //����Ԯ����¼
        for(int i = 0; i < name2.length; i++) {   
            Map<String, Object> map = new HashMap<String, Object>();    
            map.put("image", imageIds2[i]);               //ͼƬ��Դ   
            map.put("name", name2[i]);              //�û���
            map.put("time", time2[i]);              //ʱ��   
            map.put("content", content2[i]);        //Ԯ������   
            listItems.add(map);   
        }
		return listItems;
	}
	

}
