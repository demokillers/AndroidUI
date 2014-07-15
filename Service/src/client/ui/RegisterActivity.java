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
	
	private ImageView img_view; //ͷ����ͼ
	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// ����
	private static final int PHOTO_REQUEST_GALLERY = 2;// �������ѡ��
	private static final int PHOTO_REQUEST_CUT = 3;// ���
	// ����һ���Ե�ǰʱ��Ϊ���Ƶ��ļ�
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
	
	//��ʼ���ؼ�
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
		//ΪImageView��Ӽ����¼�
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
	
	//����¼�
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
	
		case R.id.portrait:			
			showDialog();
			break;
		}

	}

	//��ʾ�Ի��򷽷�
	private void showDialog() {
		
		new AlertDialog.Builder(this)
		.setTitle("ͷ������")
		.setPositiveButton("����", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				// ����ϵͳ�����չ���
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// ָ������������պ���Ƭ�Ĵ���·��
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
				startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
			}
		})
		
		.setNegativeButton("���", new DialogInterface.OnClickListener() {

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
		case PHOTO_REQUEST_TAKEPHOTO://��ѡ������ʱ����
			startPhotoZoom(Uri.fromFile(tempFile), 150);
			break;
	
		case PHOTO_REQUEST_GALLERY://��ѡ��ӱ��ػ�ȡͼƬʱ
			//���ǿ��жϣ������Ǿ��ò����������¼��õ�ʱ��㲻�ᱨ�쳣����ͬ
			if (data != null)
			startPhotoZoom(data.getData(), 150);
			break;
	
		case PHOTO_REQUEST_CUT://���صĽ��
			if (data != null) 
			setPicToView(data);
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	private void startPhotoZoom(Uri uri, int size) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// cropΪtrue�������ڿ�����intent��������ʾ��view���Լ���
		intent.putExtra("crop", "true");
	
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
	
		// outputX,outputY �Ǽ���ͼƬ�Ŀ��
		intent.putExtra("outputX", size);
		intent.putExtra("outputY", size);
		intent.putExtra("return-data", true);
	
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	//�����м��ú��ͼƬ��ʾ��UI������
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap photo = bundle.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			img_view.setBackgroundDrawable(drawable);
		}
	}

	// ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}
	
	private class Regis extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) { 
        	data.put("username","���"); 
            data.put("password","���");
            data.put("kind","���");
            data.put("cardid","���");
            data.put("realname","���");
            data.put("sex","1");
            data.put("age","1");
            data.put("address","���");
            data.put("illness","���");
            
            return PushSender.sendMessage("register",data);
        }
        @Override
        protected void onPreExecute() {   
        	
        }
        @Override
        protected void onPostExecute(String result) {   	
        	if(result.equals("network error")){
        		Toast.makeText(RegisterActivity.this,"����û������", Toast.LENGTH_SHORT).show();
        	}
        	if(result.equals("error")){
        		Toast.makeText(RegisterActivity.this,"���ӷ�����ʧ��", Toast.LENGTH_SHORT).show();
        	}
            super.onPostExecute(result);
            try {
            	switch (new JSONObject(result).getInt("state")) {
            	case 1:
            		Toast.makeText(RegisterActivity.this, "�û����Ѿ�����", Toast.LENGTH_SHORT).show();
            		break;
            	case 2:
            		Toast.makeText(RegisterActivity.this, "���֤�Ѿ�����", Toast.LENGTH_SHORT).show();
            		break;
            	default:
            		Toast.makeText(RegisterActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
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

