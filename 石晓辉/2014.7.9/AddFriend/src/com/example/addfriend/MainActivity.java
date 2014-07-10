package com.example.addfriend;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

		Spinner sexSpinner;
		TextView text;
		ArrayAdapter<String> adapter;
		Spinner ageSpinner;
		private static final String[] sexs={"不限","男","女"};
		private static final String[] ages={"不限","15-22岁","23-30岁","31-45岁","45岁以上"};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		text=(TextView)findViewById(R.id.textView4);
		sexSpinner=(Spinner)findViewById(R.id.sex);
		ageSpinner=(Spinner)findViewById(R.id.age);
		
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
				//text.setText("性别是"+sexs[arg2]);
				//设置显示当前选择的项
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
		
		
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ages);
		//设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//将adapter添加到spinner sex中
		ageSpinner.setAdapter(adapter);
		//添加spinner时间监听
		sexSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
