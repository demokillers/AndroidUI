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
	private static final String[] sexs={"����","��","Ů"};
	private static final String[] ages={"����","15-22��","23-30��","31-45��","45������"};
	private static final String[] types={"����","ҽԺ","������֯","����","��"};
	
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
		//�Ա������б�
		//����ѡ������arrayadapter����
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sexs);
		//���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//��adapter��ӵ�spinner sex��
		sexSpinner.setAdapter(adapter);
		//���spinnerʱ�����
		sexSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//������ʾ��ǰѡ�����
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
		
		//���������б�
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ages);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ageSpinner.setAdapter(adapter);
		ageSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//text.setText("�Ա���"+ages[arg2]);
				//������ʾ��ǰѡ�����
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
		
		//�û����������б�
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		typeSpinner.setAdapter(adapter);
		typeSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			
			@Override
			public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3)
			{
				//text.setText("�Ա���"+ages[arg2]);
				//������ʾ��ǰѡ�����
				arg0.setVisibility(View.VISIBLE);
			}
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//TODO Auto-generated method stub
			}
		});
	}


}
