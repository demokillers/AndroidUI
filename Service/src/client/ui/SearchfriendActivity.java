package client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchfriendActivity extends Activity {
	
	private static final String[] m_sextype = {"不限","男","女" } ;
	private static final String[] m_old = {"不限","16-22岁","23-30岁","31-45岁","46-60岁","60岁以上" } ;
	
	private Spinner sex_spinner;
	private Spinner old_spinner;
	private Button near_btn;
	private Button find_btn;
	
	private ArrayAdapter<String> sextype_Adapter;
	private ArrayAdapter<String> oldrange_Adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchfriend);
		
		sex_spinner = (Spinner)findViewById(R.id.sextype_spinner);
		old_spinner = (Spinner)findViewById(R.id.oldrange_spinner);
		near_btn = (Button)findViewById(R.id.nearpeople);
		find_btn = (Button)findViewById(R.id.find);
		
		sextype_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m_sextype);
		oldrange_Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m_old);
		
		sextype_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		oldrange_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sex_spinner.setAdapter(sextype_Adapter);
		old_spinner.setAdapter(oldrange_Adapter);
		
		near_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(SearchfriendActivity.this, Nearpeople.class);
                startActivity(intent); 
			}
			
		});
		
		find_btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(); 
                intent.setClass(SearchfriendActivity.this, Findfriendresult.class);
                startActivity(intent); 
			}
			
		});
	}


}
