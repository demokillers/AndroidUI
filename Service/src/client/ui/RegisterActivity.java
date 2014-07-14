package client.ui;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import communicate.PushSender;

public class RegisterActivity extends Activity implements OnClickListener{
	
	private ImageView img_view; //头像视图
	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	// 创建一个以当前时间为名称的文件
	File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
	private EditText userId,name,phone,rephone,idcard,address,sickness,age,password,password1;
	private Button cancel,register;
	private RadioGroup gender;
	private Map<String,Object> data=new HashMap<String,Object>();
	private Regis regis;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		init();
		
		
	}
	
	//初始化控件
	private void init() {
		img_view = (ImageView) findViewById(R.id.portrait);
		userId=(EditText)findViewById(R.id.user);
		name=(EditText)findViewById(R.id.name);
		phone=(EditText)findViewById(R.id.idNumber);
		rephone=(EditText)findViewById(R.id.relative);
		idcard=(EditText)findViewById(R.id.teleNumber);
		address=(EditText)findViewById(R.id.address);
		sickness=(EditText)findViewById(R.id.sickness);
		age=(EditText)findViewById(R.id.age);
		password=(EditText)findViewById(R.id.password);
		password1=(EditText)findViewById(R.id.password2);
		cancel=(Button)findViewById(R.id.cancel);
		register=(Button)findViewById(R.id.register);
		//为ImageView添加监听事件
		img_view.setOnClickListener(this);
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regis=new Regis();
				regis.execute();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	//点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
	
		case R.id.portrait:			
			showDialog();
			break;
		}

	}

	//提示对话框方法
	private void showDialog() {
		
		new AlertDialog.Builder(this)
		.setTitle("头像设置")
		.setPositiveButton("拍照", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				// 调用系统的拍照功能
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 指定调用相机拍照后照片的储存路径
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
				startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
			}
		})
		
		.setNegativeButton("相册", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
			}
		}).show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub

		switch (requestCode) {
		case PHOTO_REQUEST_TAKEPHOTO://当选择拍照时调用
			startPhotoZoom(Uri.fromFile(tempFile), 150);
			break;
	
		case PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
			//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
			if (data != null)
			startPhotoZoom(data.getData(), 150);
			break;
	
		case PHOTO_REQUEST_CUT://返回的结果
			if (data != null) 
			setPicToView(data);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	private void startPhotoZoom(Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
	
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
	
		// outputX,outputY 是剪裁图片的宽高
		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);
	
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	//将进行剪裁后的图片显示到UI界面上
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			img_view.setBackgroundDrawable(drawable);
		}
	}

	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}
	
	private class Regis extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	data.put("username","你好"); 
            data.put("password","你好");
            data.put("kind","你好");
            data.put("cardid","你好");
            data.put("realname","你好");
            data.put("sex","1");
            data.put("age","1");
            data.put("address","你好");
            data.put("illness","你好");
            
            return PushSender.sendMessage("register",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	if(result.equals("network error")){
        		Toast.makeText(RegisterActivity.this,"您还没有联网", Toast.LENGTH_SHORT).show();
        	}
        	if(result.equals("error")){
        		Toast.makeText(RegisterActivity.this,"连接服务器失败", Toast.LENGTH_SHORT).show();
        	}
            super.onPostExecute(result);
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(RegisterActivity.this, "用户名已经存在", Toast.LENGTH_SHORT).show();
            		break;
            	case 2:
            		Toast.makeText(RegisterActivity.this, "身份证已经存在", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
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

