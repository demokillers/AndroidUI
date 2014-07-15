package com.example.history;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpHistoryAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<Map<String,Object>> listItems;
	private Context context;	
	private ViewHolder holder;
	
	public HelpHistoryAdapter(Context ctx, List<Map<String, Object>> lst){
		context = ctx;
		inflater = LayoutInflater.from(context);
		listItems = lst;
	}
	
	public void setData(List<Map<String,Object>> item)
	{
		listItems=item;
	}
	//���������ʾ��UIʱ��Ҫ��ȡ�ģ�������listview��ʾ������
	@Override
	public int getCount(){
		return listItems.size();
	}
		
	//����listview��λ�÷���view
	@Override
	public Object getItem(int position){
		return this.listItems.get(position);
		
	}
	//����listview��λ�õõ�����Դ���е�ID
	@Override
	public long getItemId(int position){
		return position;
	}
	
	//����Ҫ�ķ���������listview�������ʽ��
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		
		holder=null;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.pull_to_refreshlist_item,null);
			holder.image = (ImageView) convertView.findViewById(R.id.imageItem1);
			holder.name = (TextView) convertView.findViewById(R.id.nameItem1);
			holder.time = (TextView) convertView.findViewById(R.id.timeItem1);
			holder.content = (TextView) convertView.findViewById(R.id.contentItem1);
			holder.concern = (Button) convertView.findViewById(R.id.concernBut1);
			holder.assist = (Button) convertView.findViewById(R.id.assistBut1);
            convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.position=position;
		holder.image.setBackgroundResource((Integer) listItems.get(position).get("image"));
        holder.name.setText((String) listItems.get(position).get("name"));
        holder.time.setText((String) listItems.get(position).get("time"));
        holder.content.setText((String) listItems.get(position).get("content"));
        
        //Ϊlistview�ϵ�button���click����
        holder.concern.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v){
            }
        });
        
        holder.assist.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v){
        	}
        });
        
        return convertView;
	}
	public ViewHolder getViewHolder(View v){  
		  if (v.getTag() == null){  
		    return getViewHolder((View) v.getParent());  
		  }  
		  return (ViewHolder) v.getTag();  	
	}
	
	static class ViewHolder {
        TextView name,time,content;
        ImageView image;
        Button concern,assist;
        int position;
    }
}
