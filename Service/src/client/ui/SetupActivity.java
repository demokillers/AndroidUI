package client.ui;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;


public class SetupActivity extends Activity {
	
	RelativeLayout info;
	RelativeLayout about;
	RelativeLayout feedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setup);
		
		info = (RelativeLayout)findViewById(R.id.info);
		about = (RelativeLayout)findViewById(R.id.aboutus);
		feedback = (RelativeLayout)findViewById(R.id.feedback);
		
		info.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(SetupActivity.this, PersonalInfoActivity.class);
                startActivity(intent); 
			}
			
		});
		about.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(SetupActivity.this, aboutYiZhu.class);
                startActivity(intent); 
			}
			
		});
		feedback.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(SetupActivity.this, Feekback.class);
                startActivity(intent); 
			}
			
		});
	}
	

}
