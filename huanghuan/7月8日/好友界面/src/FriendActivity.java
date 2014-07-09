package com.example.expandablelv;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

public class FriendActivity extends Activity implements OnChildClickListener{

	private ExpandableListView mListView = null; 
    private ExpandAdapter mAdapter = null; 
    private List<List<Item>> mData = new ArrayList<List<Item>>(); //��ʾ�û���Ϣ���б�
    
    //��ʾ������ĸ�����
    private String[] mGroupArrays = new String[] {  
            "����", 
            "����",  
            "İ����",
            "������"
    }; 
    
    //ÿ�������µ�����
    private String[][] mGroupArraysChild = new String[][] {
    	{"����", "����"}, 
    	{"����", "����"}, 
    	{"С��", "СĪ"},
    	{"�ܽ���", "�°���"}
    };
 
    //���ÿ������������������ڸ���ǩ��
    private String[][] mDetail = new String[][] {  
    		{"����������", "Ư��������"}, 
    		{"������麣", "֪������ɽ"}, 
        	{"�����ĳ�ɳ", "δ֪��¦��"},
        	{"��������ܽ���", "���ǰ°���"}
    }; 
 
    //����ÿ�������ͼƬ
    private int[][] mImageIds = new int[][] { 
            { R.drawable.img00,    
              R.drawable.img01 }, 
            { R.drawable.img10,   
              R.drawable.img11 }, 
            { R.drawable.img20, 
              R.drawable.img21 },
            { R.drawable.img30, 
              R.drawable.img31 }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData(); 
        mListView = new ExpandableListView(this); 
        mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        setContentView(mListView); 
         
        //Adapter��ǰ�˺�������ϵ�������������¼�
        //mListView.setGroupIndicator(getResources().getDrawable(R.drawable.portrait)); 
        mAdapter = new ExpandAdapter(this, mData, mGroupArrays); 
        mListView.setAdapter(mAdapter); 
        mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS); 
        mListView.setOnChildClickListener(this); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//ÿ������ļ���������һ���Ի������ʾ��Ϣ
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, 
            int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		Item item = mAdapter.getChild(groupPosition, childPosition); 
        new AlertDialog.Builder(this) 
                .setTitle(item.getName()) 
                .setMessage(item.getDetail()) 
                .setIcon(android.R.drawable.ic_menu_more) 
                .setNegativeButton("ȡ��", 
                        new OnClickListener() { 
                            @Override 
                            public void onClick(DialogInterface dialog, 
                                    int which) { 
                                // TODO Auto-generated method stub 
 
                            } 
                        }).create().show(); 
        return true; 
	}
	
	//��ʼ��mData������
	private void initData() {     
        for(int i = 0; i < mGroupArrays.length; i++)
        {
        	List<Item> list = new ArrayList<Item>(); 
        	for(int j = 0; j < 2; j++)
        	{
        		Item item = new Item(mImageIds[i][j], mGroupArraysChild[i][j], mDetail[i][j]);
        		list.add(item);
        	}
        	mData.add(list);
        }
    } 
 


}
