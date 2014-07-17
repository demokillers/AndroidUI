package client.ui;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalInfoActivity extends Activity {
	
	private ImageView portrait;

	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果
	// 创建一个以当前时间为名称的文件
	File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal_info);
		
		final TextView nameTV = (TextView)findViewById(R.id.personal_info_name);
		final TextView sexTV = (TextView)findViewById(R.id.personal_info_sex);
		final TextView ageTV = (TextView)findViewById(R.id.personal_info_age);
		final TextView phoneTV = (TextView)findViewById(R.id.personal_info_phone);
		final TextView addressTV = (TextView)findViewById(R.id.personal_info_address);
		final TextView diseaseTV = (TextView)findViewById(R.id.personal_info_disease);
		RatingBar creditRB = (RatingBar)findViewById(R.id.personal_info_ratingBar);
		TextView pointTV = (TextView)findViewById(R.id.personal_info_point);
		portrait = (ImageView)findViewById(R.id.personal_info_portrait);
		
		String name="罗罗亚·索隆";
		String sex="男";
		String age="20";
		String phone="0000";
		String address="东海-霜月村";
		String disease="路痴";
		String rating="4";
		String point="0";
		
		nameTV.setText(name);
		sexTV.setText(sex);
		ageTV.setText(age);
		phoneTV.setText(phone);
		addressTV.setText(address);
		diseaseTV.setText(disease);
		creditRB.setRating(Float.parseFloat(rating));
		pointTV.setText(point);
		//portrait.setImageResource(R.drawable.bb_boy);
		
		TextView label_portrait = (TextView)findViewById(R.id.personal_info_label_portrait);
		label_portrait.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(PersonalInfoActivity.this)
				       .setTitle("头像设置")
				       .setPositiveButton("拍照", new DialogInterface.OnClickListener() {		
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();
								// 调用系统的拍照功能
								Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
								// 指定调用相机拍照后照片的储存路径
								intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(tempFile));
								startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
							}
				       	})
				       	.setNegativeButton("相册", new DialogInterface.OnClickListener() {
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
		
		TextView label_name = (TextView)findViewById(R.id.personal_info_lable_name);
		label_name.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(20)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("亲，原来您是江湖上传说中的")
					  .setView(et)
					  .setIcon(R.drawable.user)
					  .setPositiveButton("确定", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "亲，输入内容不能为空", Toast.LENGTH_SHORT).show();
								else{
									nameTV.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}				  
					  })
					  .setNegativeButton("取消",new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog,true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}					  
					  })
					  .show();
			}	
		});
		
		TextView label_sex = (TextView)findViewById(R.id.personal_info_label_sex);
		label_sex.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String[] items = new String[]{"男","女"};
				new AlertDialog.Builder(PersonalInfoActivity.this)
				      .setIcon(R.drawable.sex)
				      .setTitle("亲，您的性别是")
				      .setSingleChoiceItems(items,0,new AlertDialog.OnClickListener(){
						  @Override
						  public void onClick(DialogInterface dialog, int which) {
							  // TODO Auto-generated method stub
							  String mysex = items[which];
							  sexTV.setText(mysex);
						  }			
					  })
				     .setPositiveButton("确定", null)
				     .show();
			}		
		});
		
		TextView label_age = (TextView)findViewById(R.id.personal_info_label_age);
		label_age.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(3)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("亲，您的芳龄是")
					  .setIcon(R.drawable.age_tree)
					  .setView(et)
					  .setPositiveButton("确定", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "亲，输入内容不能为空", Toast.LENGTH_SHORT).show();
								else{
									ageTV.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}				  
					  })
					  .setNegativeButton("取消",new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog,true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}					  
					  })
					  .show();
			}		
		});
		
		TextView label_phone = (TextView)findViewById(R.id.personal_info_label_phone);
		label_phone.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(11)});
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("亲，方便就留个电话号码")
					  .setIcon(R.drawable.phone_call)
					  .setView(et)
					  .setPositiveButton("确定", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "亲，输入内容不能为空", Toast.LENGTH_SHORT).show();
								else{
									phoneTV.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}				  
					  })
					  .setNegativeButton("取消",new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog,true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}					  
					  })
					  .show();
			}		
		});
		
		TextView label_address = (TextView)findViewById(R.id.personal_info_label_address);
		label_address.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setSingleLine();
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("亲，您住哪里啊")
					  .setView(et)
					  .setIcon(R.drawable.house)
					  .setPositiveButton("确定", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))
									Toast.makeText(getApplicationContext(), "亲，输入内容不能为空", Toast.LENGTH_SHORT).show();
								else{
									addressTV.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}	
						}				  
					  })
					  .setNegativeButton("取消",new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog,true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}					  
					  })
					  .show();
			}	
		});
		
		TextView label_disease = (TextView)findViewById(R.id.personal_info_label_disease);
		label_disease.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText et = new EditText(PersonalInfoActivity.this);
				et.setSingleLine();
				new AlertDialog.Builder(PersonalInfoActivity.this)
					  .setTitle("亲，注意身体哦")
					  .setIcon(R.drawable.cross)
					  .setView(et)
					  .setPositiveButton("确定", new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String input = et.getText().toString();
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog, false);
								if(input.equals(""))	
									Toast.makeText(getApplicationContext(), "亲，输入内容不能为空", Toast.LENGTH_SHORT).show();
								else{
									diseaseTV.setText(input);
									field.set(dialog,true);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}				
						}				  
					  })
					  .setNegativeButton("取消",new AlertDialog.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
								field.setAccessible(true);
								field.set(dialog,true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}					  
					  })
					  .show();
			}	
		});
	}


	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub

		switch (requestCode) {
			case 	PHOTO_REQUEST_TAKEPHOTO://当选择拍照时调用
					startPhotoZoom(Uri.fromFile(tempFile), 150);
					break;

			case 	PHOTO_REQUEST_GALLERY://当选择从本地获取图片时
					//做非空判断，当我们觉得不满意想重新剪裁的时候便不会报异常，下同
					if (data != null)
						startPhotoZoom(data.getData(), 150);
						break;

			case 	PHOTO_REQUEST_CUT://返回的结果
					if (data != null) 
						setPicToView(data);
					break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	//将进行剪裁后的图片显示到UI界面上
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
	
	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}	
	
}
