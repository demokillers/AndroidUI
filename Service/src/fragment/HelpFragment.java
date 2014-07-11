package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import client.ui.R;

public class HelpFragment extends Fragment{
	View view;
	public HelpFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {       
		view=View.inflate(getActivity(),R.layout.fragment_1,null); 
        return view;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
}
