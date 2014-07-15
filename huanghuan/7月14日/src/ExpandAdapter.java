package com.example.expandablelv;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//�½�һ���࣬������дBaseExpandableListAdapter�Լ��ṩ������Դ
public class ExpandAdapter extends BaseExpandableListAdapter {
	
	private Context mContext;
	private LayoutInflater mInflater = null;
	private String[] mGroupStrings = null;
	private List<List<Item>> mData = null; //��ʾ�б���ÿһ�������
	
	//���캯��
	public ExpandAdapter(Context ctx, List<List<Item>> list, String groups[])
	{
		mContext = ctx;
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//mGroupStrings = mContext.getResources().getStringArray(R.array.groups);
		mGroupStrings = groups;
		mData = list;
	}
	
	public void setData(List<List<Item>>list)
	{
		mData = list;
	}
	
	//������д��������
	@Override
	public Item getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	//��ȡ�б����������������ͼ����ͼƬ�Լ�����˵��
	@Override
	public View getChildView(int groupPosition, int childPosition, 
            boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) { 
            convertView = mInflater.inflate(R.layout.child_item_layout, null); 
        } 
        ChildViewHolder holder = new ChildViewHolder(); 
        holder.mIcon = (ImageView) convertView.findViewById(R.id.img); 
        //holder.mIcon.setBackgroundResource(getChild(groupPosition, childPosition).getImageId()); 
        //ͼƬԲ�ǻ�����
        holder.mIcon.setBackgroundDrawable(getRoundCornerDrawable(  
                getChild(groupPosition, childPosition).getImageId(), 100));  
        holder.mChildName = (TextView) convertView.findViewById(R.id.item_name); 
        holder.mChildName.setText(getChild(groupPosition, childPosition).getName()); 
        holder.mDetail = (TextView) convertView.findViewById(R.id.item_detail); 
        holder.mDetail.setText(getChild(groupPosition, childPosition).getDetail()); 
        return convertView;
	}
	
	//ͼƬԲ�ǻ�������
	private Drawable getRoundCornerDrawable(int resId, float roundPX /* Բ�ǵİ뾶 */) {  
        Drawable drawable = mContext.getResources().getDrawable(resId);  
        //int w = mContext.getResources().getDimensionPixelSize(R.dimen.image_width);
        int w = 500;
        int h = w;  
  
        Bitmap bitmap = Bitmap  
                .createBitmap(w,h,  
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                                : Bitmap.Config.RGB_565);  
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0, 0, w, h);  
        drawable.draw(canvas);  
  
        int width = bitmap.getWidth();  
        int height = bitmap.getHeight();  
        Bitmap retBmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);  
        Canvas can = new Canvas(retBmp);  
  
        final int color = 0xff424242;  
        final Paint paint = new Paint();  
        final Rect rect = new Rect(0, 0, width, height);  
        final RectF rectF = new RectF(rect);  
  
        paint.setColor(color);  
        paint.setAntiAlias(true);  
        can.drawARGB(0, 0, 0, 0);  
        can.drawRoundRect(rectF, roundPX, roundPX, paint);  
  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        can.drawBitmap(bitmap, rect, rect, paint);  
        return new BitmapDrawable(retBmp);  
    }  

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mData.get(groupPosition).size();
	}

	@Override
	public List<Item> getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	//��ȡ�б������ͼ������һ�����֣�Ҳ����Ӧ���еĺ��ѷ���
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.group_item_layout, null);
		}
		GroupViewHolder holder = new GroupViewHolder(); 
        holder.mGroupName = (TextView) convertView.findViewById(R.id.group_name); 
        holder.mGroupName.setText(mGroupStrings[groupPosition]); 
        holder.mGroupCount = (TextView) convertView.findViewById(R.id.group_count); 
        holder.mGroupCount.setText("[" + mData.get(groupPosition).size() + "]"); 
        return convertView; 
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		//ʵ��ChildView����¼������뷵��true
		return true;
	}
	
	private class GroupViewHolder { 
        TextView mGroupName; 
        TextView mGroupCount; 
    } 
 
    private class ChildViewHolder { 
        ImageView mIcon; 
        TextView mChildName; 
        TextView mDetail; 
    }
	
}
