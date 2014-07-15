package com.example.chatpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class SendHelpMsgActivity extends Activity{

	private static final String[] sen={"常用语句", "着火啦","有小偷啊","见鬼啦","被强奸啦","肚子饿了"};
	private EditText edt;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sendhelpmsg_activity);
		
		edt = (EditText) findViewById(R.id.send_msg);
		spinner = (Spinner) findViewById(R.id.common_sentence);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sen);

		//设置下拉列表的风格
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    //将adapter 添加到spinner中
	    spinner.setAdapter(adapter);
	    
	    //添加事件Spinner事件监听 
	    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());


	}
	
	//使用数组形式操作

	class SpinnerSelectedListener implements OnItemSelectedListener{

	    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    	
	    	if (arg2 != 0)
	    		edt.setText(edt.getText().toString() + sen[arg2] + "...");
	    	
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {

	    }
	}


}
