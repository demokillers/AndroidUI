package com.example.detailmessage;

import java.util.List;
import java.util.Map;

import com.example.detailmessage.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AssistListViewAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater listContainer;
	
	//item���
	private final int TYPE_COUNT = 2;
	private final int FIRST_TYPE = 0;
	private final int SECOND_TYPE = 1;
	
	
	//������ͼ
	public final class FirstItemView{
		public ImageView image;
		public TextView name;
		public TextView time;
		public TextView content;
		public Button concern;
		public Button assist;
	}
	
	//Ԯ����ͼ
	public final class ListItemView{
		public ImageView image;
		public TextView name;
		public TextView time;
		public TextView content;
	}
	
	public AssistListViewAdapter(Context ctx, List<Map<String, Object>> lst){
		context = ctx;
		listContainer = LayoutInflater.from(context);
		listItems = lst;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getViewTypeCount(){
		return TYPE_COUNT;
	}
	
	public int getItemViewType(int position){
		if(position == 0)
		{
			return FIRST_TYPE;
		}
		else{
			return SECOND_TYPE;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ListItemView listItemView = null;  //Ԯ����Ϣ��ͼ
		FirstItemView firstItemView = null; //������Ϣ��ͼ
		
		int currentType = getItemViewType(position);
		
		if (currentType == FIRST_TYPE){   //��һ��������Ϣ
			if (convertView == null){
				firstItemView = new FirstItemView();
				convertView = listContainer.inflate(R.layout.first_item, null);
				
				firstItemView.image = (ImageView)convertView.findViewById(R.id.imageItem1);
				firstItemView.name = (TextView)convertView.findViewById(R.id.nameItem1);   
				firstItemView.time = (TextView)convertView.findViewById(R.id.timeItem1);   
				firstItemView.content= (TextView)convertView.findViewById(R.id.contentItem1);   
				firstItemView.concern = (Button)convertView.findViewById(R.id.concernBut1);
				firstItemView.assist = (Button)convertView.findViewById(R.id.assistBut1);
	            
	            convertView.setTag(firstItemView);
			}
			else{
				firstItemView = (FirstItemView)convertView.getTag();
			}
			
			
			firstItemView.image.setBackgroundResource((Integer) listItems.get(   
	                position).get("image"));   
			firstItemView.name.setText((String) listItems.get(position).get("name"));   
			firstItemView.time.setText((String) listItems.get(position).get("time"));   
			firstItemView.content.setText((String) listItems.get(position).get("content"));   
			firstItemView.concern.setText((String) listItems.get(position).get("concern"));
			firstItemView.assist.setText((String) listItems.get(position).get("assist"));
		}
		else{   //Ԯ����Ϣ
			if (convertView == null){
				listItemView = new ListItemView();
				convertView = listContainer.inflate(R.layout.assist_item, null);
				
				listItemView.image = (ImageView)convertView.findViewById(R.id.imageItem);
				listItemView.name = (TextView)convertView.findViewById(R.id.nameItem);   
	            listItemView.time = (TextView)convertView.findViewById(R.id.timeItem);   
	            listItemView.content= (TextView)convertView.findViewById(R.id.contentItem);   
	             
	            
	            convertView.setTag(listItemView);
			}
			else{
				listItemView = (ListItemView)convertView.getTag();
			}
			
			
			listItemView.image.setBackgroundResource((Integer) listItems.get(   
	                position).get("image"));   
	        listItemView.name.setText((String) listItems.get(position).get("name"));   
	        listItemView.time.setText((String) listItems.get(position).get("time"));   
	        listItemView.content.setText((String) listItems.get(position).get("content"));   
	        
		}
		
		
		
		return convertView;
	}

}
