package client.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import communicate.PushSender;


public class SendHelpMsgActivity extends Activity{

	private static final String[] sen={"常用语句", "着火啦","有小偷啊","见鬼啦","被强奸啦","肚子饿了"};
	private EditText edt;
	private Spinner spinner;
	private Button sendmessage;
	private ArrayAdapter<String> adapter;
    private Send send;
    private ProgressBar pro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sendhelpmsg_activity);
		
		pro=(ProgressBar)findViewById(R.id.progressBar1);
		edt = (EditText) findViewById(R.id.send_msg);
		spinner = (Spinner) findViewById(R.id.common_sentence);
		sendmessage=(Button)findViewById(R.id.sendmessage);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sen);

		//设置下拉列表的风格
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    //将adapter 添加到spinner中
	    spinner.setAdapter(adapter);
	    
	    //添加事件Spinner事件监听 
	    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
	    
	    sendmessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pro.setVisibility(View.VISIBLE);
			    send=new Send();
			    send.execute();
				
			}
		});
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
	
	private class Send extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	Map<String,Object> a=new HashMap<String, Object>();
			a.put("kind", 1);
			a.put("content", "TestContent");
			a.put("assist", "TestAssist");
			a.put("latitude",23);
			a.put("longitude",23);
			Map<String,Object> b= new HashMap<String, Object>();
			b.put("username","test1");
			b.put("message", a);
			
            return PushSender.sendMessage("helpmessage", b);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	
        	if(result.equals("network error")){
        		Toast.makeText(SendHelpMsgActivity.this,"您还没有联网", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
        	if(result.equals("error")){
        		Toast.makeText(SendHelpMsgActivity.this,"连接服务器失败", Toast.LENGTH_SHORT).show();
            	//pro.setVisibility(View.INVISIBLE);
        	}
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(SendHelpMsgActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(SendHelpMsgActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
            	}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            pro.setVisibility(View.INVISIBLE);
            super.onPostExecute(result);
        }
    }

}
