package client.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import communicate.PushSender;

public class LoginActivity extends Activity implements OnClickListener{
	Button loginBtn,forgetPasswdBtn;
	EditText userEdit,passwdEdit;
	RelativeLayout loginLayout;
	Login login;
	String userEditStr,passwdEditStr;
	ProgressBar pro;
	private Map<String,Object> data=new HashMap<String,Object>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);            

        loginBtn = (Button)findViewById(R.id.login_login_btn);
        pro=(ProgressBar)findViewById(R.id.progressBar1);
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
		case R.id.login_login_btn://点击登录按钮   进行判断  用户名和密码是否为空
			userEditStr = userEdit.getText().toString().trim();
			passwdEditStr = passwdEdit.getText().toString().trim();
			/*if(("".equals(userEditStr) || null == userEditStr) || 
					("".equals(passwdEditStr) || null == passwdEditStr)){//只要用户名和密码有一个为空
				new AlertDialog.Builder(LoginActivity.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("登录失败")
				.setMessage("微信账号或密码不能为空，请输入微信账号或密码")
				.create().show();
			}
			else{*/
				pro.setVisibility(View.VISIBLE);
				login=new Login();
				login.execute();
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
	
	private class Login extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	data.put("username", userEditStr); 
            data.put("password", passwdEditStr);
            return PushSender.sendMessage("login",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	if(result.equals("network error")){
        		Toast.makeText(LoginActivity.this,"您还没有联网", Toast.LENGTH_SHORT).show();
            	pro.setVisibility(View.INVISIBLE);
        	}
        	if(result.equals("error")){
        		Toast.makeText(LoginActivity.this,"连接服务器失败", Toast.LENGTH_SHORT).show();
            	pro.setVisibility(View.INVISIBLE);
        	}
            super.onPostExecute(result);
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            		break;
            	case 2:
            		Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            		break;
            	case 3:
            		Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(LoginActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
            	}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            super.onPostExecute(result);
        }
    }
}
