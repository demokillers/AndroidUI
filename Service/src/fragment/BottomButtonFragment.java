package fragment;

import routeplan.RoutePlan;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import client.ui.CloseActivity;
import client.ui.R;
import client.ui.SendHelpMsgActivity;

public class BottomButtonFragment extends Fragment {
	
	private View messageLayout;
	private TextView navigation; //导航按钮
	private TextView assist;    //援助按钮
	private TextView conclude;  //结束按钮
	
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
       
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		messageLayout = inflater.inflate(R.layout.messagedetail_bottom, container, false);  
		
		navigation=(TextView)messageLayout.findViewById(R.id.button_navigate);
		assist = (TextView)messageLayout.findViewById(R.id.button_assist);
		conclude = (TextView)messageLayout.findViewById(R.id.button_conclude);
		
		
		//监听事件
	    navigation.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),RoutePlan.class));
	        }
	    });
	    
	    assist.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),SendHelpMsgActivity.class));
	        }
	    });
	    
	    conclude.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),CloseActivity.class));
	        }
	    });
	    
        return messageLayout;
	}
	
    public void onPause()
    {
        // TODO Auto-generated method stub
    	
        super.onPause();
    }

}
