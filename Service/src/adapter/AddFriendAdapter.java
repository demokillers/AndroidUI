package adapter;

import java.util.ArrayList;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import client.ui.R;

//新建一个类，用来重写BaseExpandableListAdapter以及提供的数据源
public class AddFriendAdapter extends BaseAdapter {

	
	private LayoutInflater mInflater;  
	private ArrayList<Map<String,Object>> listItem;
	Context con;
	

	public AddFriendAdapter(Context context, ArrayList<Map<String,Object>> list) {  

         this.mInflater = LayoutInflater.from(context);  
         listItem = list;
         con = context;
    }  

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
        ViewHolder holder = null;  
            if (convertView == null) {  
                  
                holder=new ViewHolder();    
                  
                //可以理解为从vlist获取view  之后把view返回给ListView   
                  
                convertView = mInflater.inflate(R.layout.findfriend_item, null);
                holder.img = (ImageView)convertView.findViewById(R.id.add_image);
                holder.name = (TextView)convertView.findViewById(R.id.item_name);  
                holder.info = (TextView)convertView.findViewById(R.id.item_detail);  
                holder.add_Btn = (ImageButton)convertView.findViewById(R.id.addfriend);  
                convertView.setTag(holder);               
            }else {               
                holder = (ViewHolder)convertView.getTag();  
            }         
            
            holder.img.setTag(position);
            holder.name.setText((String)listItem.get(position).get("name"));  
            holder.info.setText((String)listItem.get(position).get("detail"));  
            holder.add_Btn.setTag(position);  
            //给Button添加单击事件  添加Button之后ListView将失去焦点  需要的直接把Button的焦点去掉   
            holder.add_Btn.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                    showInfo(position);                   
                }  
            }); 
            
            holder.img.setOnClickListener(new View.OnClickListener() {  
                  
                @Override  
                public void onClick(View v) {  
                    showInfo(position);                   
                }  
            }); 
             
            //holder.viewBtn.setOnClickListener(MyListener(position));   
                      
            return convertView; 
	}
	
	
	//提取出来方便点   
	    public final class ViewHolder {  
	    	public ImageView img;
	        public TextView name;  
	        public TextView info;  
	        public ImageButton add_Btn;  
	    }  
	    
	    public void showInfo(int position){  
	    	          
			LayoutInflater inflater = mInflater;
			   View layout = inflater.inflate(R.layout.add_dialog, null);
			   AlertDialog a = new AlertDialog.Builder(con)
			   .setTitle("添加好友").setView(layout)
			   .setPositiveButton("确定", null)
			   .setNegativeButton("取消", null).show();
	   } 
	
}
