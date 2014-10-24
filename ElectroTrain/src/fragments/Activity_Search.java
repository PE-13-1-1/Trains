package fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Search extends Fragment {
	
	AutoCompleteTextView mAutoComplete;
	final String[]  COUNTRIES = { "Belgium", "France", "Italy", "Germany", "Spain","Germ" };
	// List view
    private ListView lv_s;
    // Listview Adapter
    ArrayAdapter<String> adapter_s;
    // Search EditText
    EditText inputSearch_s;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> trainList;
	
    public Activity_Search() {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_search, container,
                false);
        TabHost tabs = (TabHost) rootView.findViewById(android.R.id.tabhost);
	    tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec("tag1");

		spec.setContent(R.id.tab1);
		spec.setIndicator("Станция");
		tabs.addTab(spec);

		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Маршрут");
		tabs.addTab(spec);
		
		tabs.setCurrentTab(0);
		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, COUNTRIES);
         AutoCompleteTextView textView = (AutoCompleteTextView)
        		 rootView.findViewById(R.id.search_way1);
         textView.setAdapter(adapter);
         textView.setThreshold(0);
		

		
	    
        return rootView;
    }
}