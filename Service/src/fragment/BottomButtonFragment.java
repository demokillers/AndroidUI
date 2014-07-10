package fragment;

import client.ui.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomButtonFragment extends Fragment {
	
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		View messageLayout = inflater.inflate(R.layout.bottom_button_fragment, container, false);  
        return messageLayout;
	}
	
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
    }

}
