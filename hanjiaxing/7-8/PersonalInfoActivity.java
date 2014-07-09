package com.example.helper;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoActivity extends Activity {
	
	private ImageView portrait;

	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// ����
	private static final int PHOTO_REQUEST_GALLERY = 2;// �������ѡ��
	private static final int PHOTO_REQUEST_CUT = 3;// ���
	// ����һ���Ե�ǰʱ��Ϊ���Ƶ��ļ�
	File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView name = (TextView)findViewById(R.id.conten_name);
		final TextView sex = (TextView)findViewById(R.id.content_sex);
		final TextView age = (TextView)findViewById(R.id.content_age);
		final TextView phone = (TextView)findViewById(R.id.content_phone);
		final TextView address = (TextView)findViewById(R.id.content_address);
		final TextView disease = (TextView)findViewById(R.id.content_disease);
		portrait = (ImageView)findViewById(R.id.imageview);
		
		name.setText("�����ǡ���¡");
		sex.setText("��");
		age.setText("20");
		phone.setText("0000");
		address.setText("����-˪�´�");
		disease.setText("·��");
		
		TextView label_portrait = (TextView)findViewById(R.id.label_portrait);
		label_portrait.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(PersonalInfoActivity.this)
				       .setTitle("ͷ������")
				       .setPositiveButton("����", new DialogInterface.OnClickListener() {		
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								// ����ϵͳ�����չ���
								Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
								// ָ������������պ���Ƭ�Ĵ���·��
								intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
								startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
							}
				       	})
				       	.setNegativeButton("���", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								Intent intent = new Intent(Intent.ACTION_PICK, null);
								intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
								startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
						}}).show();
			}	
		});
		
		TextView label_name = (TextView)findViewById(R.id.lable_name);
		label_name.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(20)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("�ף�ԭ�����ǽ����ϴ�˵�е�")
					  .setView(et)
					  .setIcon(R.drawable.user)
					  .setPositiveButton("ȷ��", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "�ף��������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
								else{
									name.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}				  
					  })
					  .setNegativeButton("ȡ��",null)
					  .show();
			}	
		});
		
		TextView label_sex = (TextView)findViewById(R.id.label_sex);
		label_sex.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String[] items = new String[]{"��","Ů"};
				new AlertDialog.Builder(PersonalInfoActivity.this)
				      .setIcon(R.drawable.sex)
				      .setTitle("�ף������Ա���")
				      .setSingleChoiceItems(items,0,new AlertDialog.OnClickListener(){
						  @Override
						  public void onClick(DialogInterface dialog, int which) {
							  // TODO Auto-generated method stub
							  String mysex = items[which];
							  sex.setText(mysex);
						  }			
					  })
				     .setPositiveButton("ȷ��", null)
				     .show();
			}		
		});
		
		TextView label_age = (TextView)findViewById(R.id.label_age);
		label_age.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(3)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("�ף����ķ�����")
					  .setIcon(R.drawable.age_tree)
					  .setView(et)
					  .setPositiveButton("ȷ��", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "�ף��������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
								else{
									age.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}				  
					  })
					  .setNegativeButton("ȡ��",null)
					  .show();
			}		
		});
		
		TextView label_phone = (TextView)findViewById(R.id.label_phone);
		label_phone.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(11)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("�ף�����������绰����")
					  .setIcon(R.drawable.phone_call)
					  .setView(et)
					  .setPositiveButton("ȷ��", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "�ף��������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
								else{
									phone.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}				  
					  })
					  .setNegativeButton("ȡ��",null)
					  .show();
			}		
		});
		
		TextView label_address = (TextView)findViewById(R.id.label_address);
		label_address.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setSingleLine();
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("�ף���ס���ﰡ")
					  .setView(et)
					  .setIcon(R.drawable.house)
					  .setPositiveButton("ȷ��", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "�ף��������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
								else{
									address.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}				  
					  })
					  .setNegativeButton("ȡ��",null)
					  .show();
			}	
		});
		
		TextView label_disease = (TextView)findViewById(R.id.label_disease);
		label_disease.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setSingleLine();
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("�ף�ע������Ŷ")
					  .setIcon(R.drawable.cross)
					  .setView(et)
					  .setPositiveButton("ȷ��", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))	
									Toast.makeText(getApplicationContext(), "�ף��������ݲ���Ϊ��", Toast.LENGTH_SHORT).show();
								else{
									disease.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}				
						}				  
					  })
					  .setNegativeButton("ȡ��",null)
					  .show();
			}	
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub

		switch (requestCode) {
			case 	PHOTO_REQUEST_TAKEPHOTO://��ѡ������ʱ����
					startPhotoZoom(Uri.fromFile(tempFile), 150);
					break;

			case 	PHOTO_REQUEST_GALLERY://��ѡ��ӱ��ػ�ȡͼƬʱ
					//���ǿ��жϣ������Ǿ��ò����������¼��õ�ʱ��㲻�ᱨ�쳣����ͬ
					if (data != null)
						startPhotoZoom(data.getData(), 150);
						break;

			case 	PHOTO_REQUEST_CUT://���صĽ��
					if (data != null) 
						setPicToView(data);
					break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	//�����м��ú��ͼƬ��ʾ��UI������
	private void setPicToView(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			Bitmap bitmap = bundle.getParcelable("data");
		Drawable drawable = new BitmapDrawable(bitmap);
		portrait.setBackgroundDrawable(drawable);
		}
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
	
	// ʹ��ϵͳ��ǰ���ڼ��Ե�����Ϊ��Ƭ������
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}	
	
}
