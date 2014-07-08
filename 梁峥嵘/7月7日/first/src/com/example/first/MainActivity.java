package com.example.first;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		setContentView(R.layout.activity_main);  
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);  
		setContentView(R.layout.activity_main);
		Button Btn1 = (Button)findViewById(R.id.queding);
		Btn1.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DisplayToast("按下了确定");

			}
			
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_0:
			DisplayToast("按下了0");
			break;
		
		
		}
		return super.onKeyDown(keyCode, event);
	}

	public void DisplayToast(String str)
	{
		Toast.makeText(this, str , Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
