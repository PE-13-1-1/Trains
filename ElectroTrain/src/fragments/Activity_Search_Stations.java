package fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Search_Stations extends Fragment {
	
	AutoCompleteTextView mAutoComplete;
	final String[]  stations = { "Belgium", "France", "Italy", "Germany", "Spain","Germ" };
    private ListView lv_s;
    ArrayAdapter<String> adapter_s;
    EditText inputSearch_s;
    ArrayList<HashMap<String, String>> trainList;
    Button butt;
	
    public Activity_Search_Stations() {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_search_stations, container,
                false);

		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
         AutoCompleteTextView textView = (AutoCompleteTextView)
        		 rootView.findViewById(R.id.search_station);
         textView.setAdapter(adapter);
         textView.setThreshold(0);
         butt=(Button) rootView.findViewById(R.id.search_but1);
         butt.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 //Fragment fragment = new Activity_Settings();
            	 //FragmentManager fragmentManager = rootView.getActivity().getSupportFragmentManager();
                 //fragmentManager.beginTransaction()
                         //.replace(R.id.content_frame, fragment).commit();
            	Fragment fragment = new Activity_Station_screen();
             	Fragment parent=getParentFragment();
             	FragmentManager fragmentManager2 = parent.getFragmentManager();
             	fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment).commit();
             	//fragmentTransaction2.addToBackStack("xyz");
             	//fragmentTransaction2.hide(MeinProfilFragment.this);
             	//fragmentTransaction2.add(android.R.id.content, fragment2);
             	//fragmentTransaction2.commit();
             }
         });
		

		
	    
        return rootView;
    }
}