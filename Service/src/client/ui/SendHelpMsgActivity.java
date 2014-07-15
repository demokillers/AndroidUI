package client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class SendHelpMsgActivity extends Activity{

	private static final String[] sen={"�������", "�Ż���","��С͵��","������","��ǿ����","���Ӷ���"};
	private EditText edt;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sendhelpmsg_activity);
		
		edt = (EditText) findViewById(R.id.send_msg);
		spinner = (Spinner) findViewById(R.id.common_sentence);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sen);

		//���������б�ķ��
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	    //��adapter ��ӵ�spinner��
	    spinner.setAdapter(adapter);
	    
	    //����¼�Spinner�¼����� 
	    spinner.setOnItemSelectedListener(new SpinnerSelectedListener());


	}
	
	//ʹ��������ʽ����

	class SpinnerSelectedListener implements OnItemSelectedListener{

	    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	    	
	    	if (arg2 != 0)
	    		edt.setText(edt.getText().toString() + sen[arg2] + "...");
	    	
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {

	    }
	}


}
