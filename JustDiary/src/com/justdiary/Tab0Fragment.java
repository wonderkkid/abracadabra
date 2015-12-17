package com.justdiary;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab0Fragment extends Fragment {
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
//		View v = inflater.inflate(R.layout.layout_tab_0, container, false);
//		View tv = v.findViewById(R.id.title);
//		((TextView) tv).setText("hope is a good thing");
//		
//		return v;
 
		return (LinearLayout) inflater.inflate(R.layout.layout_tab_0, container, false);
	}
 
}
