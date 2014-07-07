package client.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class LoginActivity extends Activity implements OnClickListener{
	Button rebackBtn,loginBtn,forgetPasswdBtn;
	EditText userEdit,passwdEdit;
	RelativeLayout loginLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        rebackBtn = (Button)findViewById(R.id.login_reback_btn);
        rebackBtn.setOnClickListener(this);//注册监听器  一定不能忘
        loginBtn = (Button)findViewById(R.id.login_login_btn);
        loginBtn.setOnClickListener(this);//注册监听器  一定不能忘
        passwdEdit = (EditText)findViewById(R.id.login_passwd_edit);
        userEdit = (EditText)findViewById(R.id.login_user_edit);
        forgetPasswdBtn = (Button)findViewById(R.id.forget_passwd);
        forgetPasswdBtn.setOnClickListener(this);
        loginLayout = (RelativeLayout)findViewById(R.id.login_layout);
    }
	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.login_reback_btn://返回按钮
			LoginActivity.this.finish();//关闭这个Activity  返回上一个Activity
			break;
		case R.id.login_login_btn://点击登录按钮   进行判断  用户名和密码是否为空
			String userEditStr = userEdit.getText().toString().trim();
			String passwdEditStr = passwdEdit.getText().toString().trim();
			/*if(("".equals(userEditStr) || null == userEditStr) || 
					("".equals(passwdEditStr) || null == passwdEditStr)){//只要用户名和密码有一个为空
				new AlertDialog.Builder(LoginActivity.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("登录失败")
				.setMessage("微信账号或密码不能为空，请输入微信账号或密码")
				.create().show();
			}
			else{*/
				Intent intent=new Intent(LoginActivity.this,ControlActivity.class);
				startActivity(intent);
			//}
			break;
		case R.id.forget_passwd://点击  “忘记密码” 这个文本
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
