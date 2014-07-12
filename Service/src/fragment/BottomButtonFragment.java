package fragment;

import routeplan.RoutePlan;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import client.ui.R;

public class BottomButtonFragment extends Fragment {
	private View messageLayout;
	private Button navigation;
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
       
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		messageLayout = inflater.inflate(R.layout.messagedetail_bottom, container, false);  
		navigation=(Button)messageLayout.findViewById(R.id.button_navigate);
	    navigation.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v){
	        	startActivity(new Intent(getActivity(),RoutePlan.class));
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
