package client.ui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Feekback extends Activity {
	
	
	Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feekback);
		
		btn1 = (Button)findViewById(R.id.quxiao);
		
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
                finish();
			}
			
		});
	}

}
