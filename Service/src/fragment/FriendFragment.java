package fragment;

import java.util.ArrayList;
import java.util.List;

import adapter.FriendAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import base.friend;
import base.user;
import client.ui.FriendInfoActivity;
import client.ui.R;

public class FriendFragment extends Fragment implements OnChildClickListener{
	
	private ExpandableListView mListView = null; 
    private FriendAdapter mAdapter = null; 
    private List<List<friend>> mData = new ArrayList<List<friend>>(); //表示用户信息的列表
    private View view;
    
    //表示定义的四个分组
    private String[] mGroupArrays = new String[] {  
            "亲人", 
            "好友",  
            "陌生人",
            "黑名单"
    }; 
    
    //每个分组下的子项
    private String[][] mGroupArraysChild = new String[][] {
    	{"张三", "李四"}, 
    	{"王二", "麻子"}, 
    	{"小明", "小莫"},
    	{"周杰伦", "奥巴马"}
    };
 
    //针对每个子项的描述，类似于个性签名
    private String[][] mDetail = new String[][] {  
    		{"美丽的兴义", "漂亮的遵义"}, 
    		{"下雨的珠海", "知名的中山"}, 
        	{"热辣的长沙", "未知的娄底"},
        	{"我真的是周杰伦", "我是奥巴马"}
    }; 
 
    //设置每个子项的图片
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
		ViewGroup p = (ViewGroup) view.getParent(); 
        if (p != null) { 
            p.removeAllViewsInLayout(); 
        } 

		return view;
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view=View.inflate(getActivity(),R.layout.friend,null); 
		mListView = (ExpandableListView)view.findViewById(R.id.friendlist);
        //mListView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT)); 
        mAdapter = new FriendAdapter(getActivity(), mData, mGroupArrays);
        mListView.setAdapter(mAdapter); 
        //mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS); 
        //mListView.setOnChildClickListener(this);
        mListView.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int which, long id) {
				// TODO Auto-generated method stub
				final String[] friend_manage = new String[]{"查看亲友","删除亲友"};
				new AlertDialog.Builder(getActivity())
						.setItems(friend_manage, new DialogInterface.OnClickListener() {		
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								if(which==0)
									startActivity(new Intent(getActivity(),FriendInfoActivity.class));
								else if(which==1)
									;
							}
						}).show();
				return true;
			}
        });
		initData(); 
    
	}
	@Override
	public boolean onChildClick(ExpandableListView parent, View v, 
            int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		friend item = mAdapter.getChild(groupPosition, childPosition); 
        new AlertDialog.Builder(getActivity()) 
                .setTitle(item.getName()) 
                .setMessage(item.getDetail()) 
                .setIcon(android.R.drawable.ic_menu_more) 
                .setNegativeButton("取消", 
                        new OnClickListener() { 
                            @Override 
                            public void onClick(DialogInterface dialog, 
                                    int which) { 
                                // TODO Auto-generated method stub 
 
                            } 
                        }).create().show(); 
        return true; 
	}
	
	//初始化mData的内容
	private void initData() {     
        for(int i = 0; i < mGroupArrays.length; i++)
        {
        	List<friend> list = new ArrayList<friend>(); 
        	for(int j = 0; j < 2; j++)
        	{
        		friend item = new friend(mImageIds[i][j], mGroupArraysChild[i][j], mDetail[i][j]);
        		list.add(item);
        	}
        	mData.add(list);
        }
    } 
	
}