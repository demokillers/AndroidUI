package fragment;

import java.util.ArrayList;
import java.util.List;

import adapter.FriendAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import base.user;
import client.ui.R;

public class FriendFragment extends Fragment implements OnChildClickListener{
	
	private ExpandableListView mListView = null; 
    private FriendAdapter mAdapter = null; 
    private List<List<user>> mData = new ArrayList<List<user>>(); //��ʾ�û���Ϣ���б�
    
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
	public FriendFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {       
        //return inflater.inflate(R.layout.fragment_3, container, false);
		mListView = new ExpandableListView(getActivity()); 
        mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        mAdapter = new FriendAdapter(getActivity(), mData, mGroupArrays); 
        mListView.setAdapter(mAdapter); 
        mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS); 
        mListView.setOnChildClickListener(this); 
		return mListView;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData(); 
    
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, 
            int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		user item = mAdapter.getChild(groupPosition, childPosition); 
        new AlertDialog.Builder(getActivity()) 
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
        	List<user> list = new ArrayList<user>(); 
        	for(int j = 0; j < 2; j++)
        	{
        		user item = new user(mImageIds[i][j], mGroupArraysChild[i][j], mDetail[i][j]);
        		list.add(item);
        	}
        	mData.add(list);
        }
    } 
	
}