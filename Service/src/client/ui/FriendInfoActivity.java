package client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class FriendInfoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.friend_info);
		
		TextView userTV = (TextView)findViewById(R.id.friend_info_user);
		TextView nameTV = (TextView)findViewById(R.id.friend_info_name);
		TextView ageTV = (TextView)findViewById(R.id.friend_info_age);
		TextView sexTV = (TextView)findViewById(R.id.friend_info_sex);
		TextView phoneTV = (TextView)findViewById(R.id.friend_info_phone);
		TextView addressTV = (TextView)findViewById(R.id.friend_info_address);
		TextView diseaseTV = (TextView)findViewById(R.id.friend_info_disease);
		RatingBar creditRB = (RatingBar)findViewById(R.id.friend_info_ratingBar);
		TextView pointTV = (TextView)findViewById(R.id.friend_info_point);
		Button back = (Button)findViewById(R.id.friend_info_back);
		
		String user="Monkey·D·Luffy";
		String name="路飞";
		String age="18";
		String sex="bb-girl";
		String phone="0000";
		String address="风车村";
		String disease="hungry";
		String rating="4";
		String point="0";
		
		userTV.setText("昵称  "+user);
		nameTV.setText("姓名  "+name);
		ageTV.setText(age);
		sexTV.setText(sex);
		phoneTV.setText(phone);
		addressTV.setText(address);
		diseaseTV.setText(disease);
		creditRB.setRating(Float.parseFloat(rating));
		pointTV.setText(point);
		
		back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
