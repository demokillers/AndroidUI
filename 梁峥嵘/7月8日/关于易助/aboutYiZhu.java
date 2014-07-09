package com.example.setting;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class aboutYiZhu extends Activity {
	
	
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutyizhu);
		
		btn1 = (Button)findViewById(R.id.back);
		
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(aboutYiZhu.this, MainActivity.class);
                startActivity(intent); 
                finish();
			}
			
		});
	}

}
