package com.example.helper;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ScoreAdapter extends BaseAdapter{
	
	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater listContainer;
	private ViewHolder holder;
	
	public ScoreAdapter(Context ctx, List<Map<String, Object>> lst){
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
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		holder=null;
		if (convertView == null){
			holder=new ViewHolder();
			convertView = listContainer.inflate(R.layout.score_item, null);		
			holder.image = (ImageView)convertView.findViewById(R.id.score_image);
			holder.name = (TextView)convertView.findViewById(R.id.score_name);
			holder.submit = (Button)convertView.findViewById(R.id.score_submit);
			holder.radioGroup = (RadioGroup)convertView.findViewById(R.id.score_radiogroup);       
            convertView.setTag(holder);
		}
		else
			holder=(ViewHolder)convertView.getTag();
		
		
		holder.image.setBackgroundResource((Integer) listItems.get(   
                position).get("image"));   
		holder.name.setText((String) listItems.get(position).get("name"));   
		holder.submit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i=0;i<holder.radioGroup.getChildCount();i++){
					RadioButton r=(RadioButton)holder.radioGroup.getChildAt(i);
					if(r.isChecked())
						r.getText();
				}
			}
        });
        
		return convertView;
	}
	
	public ViewHolder getViewHolder(View v){  
		if (v.getTag() == null)
		    return getViewHolder((View) v.getParent());   
		return (ViewHolder) v.getTag();  	
	}
	
	static class ViewHolder {
	    TextView name;
	    ImageView image;
	    Button submit;
	    RadioGroup radioGroup;
	    int position;
	}
}
