package client.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchfriendActivity extends Activity {

	Spinner sexSpinner;
	Spinner ageSpinner;
	Spinner typeSpinner;
	TextView exactsearch,fuzzysearch;
	LinearLayout ll;
	ArrayAdapter<String> adapter;
	private static final String[] sexs={"不限","男","女"};
	private static final String[] ages={"不限","15-22岁","23-30岁","31-45岁","45岁以上"};
	private static final String[] types={"不限","医院","公益组织","警局","火警"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchfriend);
		
		sexSpinner=(Spinner)findViewById(R.id.sex);
		ageSpinner=(Spinner)findViewById(R.id.age);
		typeSpinner=(Spinner)findViewById(R.id.userType);
		ll=(LinearLayout)findViewById(R.id.layout);
		exactsearch=(TextView)findViewById(R.id.textView8);
		fuzzysearch=(TextView)findViewById(R.id.textView9);
		exactsearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		exactsearch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_UP)
					exactsearch.setTextColor(Color.BLACK);
				if(event.getAction()==MotionEvent.ACTION_DOWN)
					exactsearch.setTextColor(Color.BLUE);
				return false;
			}
		});
		
		fuzzysearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		fuzzysearch.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction()==MotionEvent.ACTION_UP)
					fuzzysearch.setTextColor(Color.BLACK);
				if(event.getAction()==MotionEvent.ACTION_DOWN)
					fuzzysearch.setTextColor(Color.BLUE);
				return false;
			}
		});
		//性别下拉列表
		//将可选内容与arrayadapter连接
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sexs);
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//将adapter添加到spinner sex中
		sexSpinner.setAdapter(adapter);
		//添加spinner时间监听
		sexSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//设置显示当前选择的项
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
		
		//年龄下拉列表
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ages);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ageSpinner.setAdapter(adapter);
		ageSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//text.setText("性别是"+ages[arg2]);
				//设置显示当前选择的项
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
		
		//用户类型下拉列表
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeSpinner.setAdapter(adapter);
		typeSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//text.setText("性别是"+ages[arg2]);
				//设置显示当前选择的项
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
	}


}
