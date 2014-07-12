package client.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class SearchfriendActivity extends Activity {

	Spinner sexSpinner;
	Spinner ageSpinner;
	Spinner typeSpinner;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
