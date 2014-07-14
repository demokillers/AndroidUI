package com.example.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AssistTipsActivity extends ExpandableListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assist_tips);
		
		
		
		//设置组视图的显示文字  
        String[] assist_type = new String[] { "  生活知识", "  健康知识", "  安全知识",};  
        //子视图显示文字  
        String[][] type_item = new String[][] {  
                { "家居", "家电", "动物", "植物", "婴幼儿"},  
                { "心脏病", "中风", "急性疾病", "其他疾病"},  
                { "遭遇火灾", "遭遇小偷","遭遇抢劫", "危险建筑物", "毒品" } 
        }; 
		
		//定义一个List，该List对象为一级条目提供数据  
        List<Map<String, String>> groups = new ArrayList<Map<String,String>>();
        List<List<Map<String, String>>> childs = new ArrayList<List<Map<String,String>>>();
        for(int i=0; i<assist_type.length; i++){
        	Map<String, String> group = new HashMap<String, String>();
        	group.put("group", assist_type[i]);
        	groups.add(group);
        	 	
        	List<Map<String, String>> child = new ArrayList<Map<String,String>>();
        	for(int j=0; j<type_item[i].length; j++){
        		Map<String, String> child_data = new HashMap<String, String>();
        		child_data.put("child", type_item[i][j]);
        		child.add(child_data);
        	}
        	childs.add(child);
        }
        
       
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this  
                , groups  
                , R.layout.tips_group
                , new String[]{"group"}  
                , new int[] {R.id.groupTo}  
                , childs  
                , R.layout.tips_child
                , new String[]{"child"}  
                , new int[] {R.id.childTo}  
                );  
        //将SimpleExpandableListAdapter对象设置给当前的ExpandableListActivity  
        setListAdapter(adapter); 
	}
}
