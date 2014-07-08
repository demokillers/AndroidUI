package com.example.changepassword;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangePassword extends Activity {
	//RelativeLayout mLayout=new RelativeLayout(this);
	
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	
	TextView editText1;
	TextView editText2;
	TextView editText3;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//调用父类方法，该句代码自动生成
        setContentView(R.layout.activity_change_password);//通过布局文件的id调用该Activity所使用的布局文件
        
       // mLayout.setPadding(activity_horizontal_margin, activity_vertical_margin, activity_horizontal_margin, activity_vertical_margin);
        //mLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT));
        
        //通过findViewById（）方法拿到布局文件中添加的控件
        //不过在布局文件中添加控件的时候必须定义id号
        //android:id="@+id/myTextView" 
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        
        editText1=(TextView)findViewById(R.id.editText1);
        editText2=(TextView)findViewById(R.id.editText2);
        editText3=(TextView)findViewById(R.id.editText3);
        
        //向控件上制定显示文字 
        button1.setText("旧密码");
        button2.setText("新密码");
        button3.setText("确认新密码");
        button4.setText("取消");
        button5.setText("确认");
        
        /*RelativeLayout.LayoutParams mParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mParams.topMargin=(int) 26;
        button1.setLayoutParams(mParams); 
         
        RelativeLayout.LayoutParams mParams1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mParams1.addRule(RelativeLayout.ALIGN_LEFT, button1);*/
         	
        //mLayout.addView(button1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.change_password, menu);
        return true;
    }
    
}
