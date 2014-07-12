package radialdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import client.ui.R;

/**
 * 
 * @author Arindam Nath
 *
 */
public class RadialMenuLife extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.layout_life, container, false);		
		return view;
	}
}
