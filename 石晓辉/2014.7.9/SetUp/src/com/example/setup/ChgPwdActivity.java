package com.example.setup;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChgPwdActivity extends Activity {
	//RelativeLayout mLayout=new RelativeLayout(this);
	
	
	Button button1;
	Button button2;

	
	TextView oldPwd;
	TextView newPwd;
	TextView confirmPwd;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//调用父类方法，该句代码自动生成
        setContentView(R.layout.activity_chg_pwd);//通过布局文件的id调用该Activity所使用的布局文件
        
        oldPwd=(TextView)findViewById(R.id.oldPwd);
        newPwd=(TextView)findViewById(R.id.newPwd);
        confirmPwd=(TextView)findViewById(R.id.confirmPwd);
        
        button1=(Button)findViewById(R.id.button01);
        button2=(Button)findViewById(R.id.button02);
        
        //点击确认或取消实现跳转到上一个页面
        button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ChgPwdActivity.this, SetUpActivity.class);
				startActivity(intent);
			}
		});
        button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ChgPwdActivity.this, SetUpActivity.class);
				startActivity(intent);
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.set_up, menu);
        return true;
    }  
}
