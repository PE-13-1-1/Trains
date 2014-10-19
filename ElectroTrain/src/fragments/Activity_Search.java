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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;

import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.electrotrain.SearchActivity;

public class Activity_Search extends Fragment {
	
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
	    String stations[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
                "iPhone 4S", "Samsung Galaxy Note 800",
                "Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};

		lv_s = (ListView) rootView.findViewById(R.id.list_view_s);
		inputSearch_s = (EditText) rootView.findViewById(R.id.inputSearch_s);
		
		// Adding items to listview
		adapter_s = new ArrayAdapter<String>(rootView.getContext(), R.layout.list_item, R.id.lblListItem, stations);
		lv_s.setAdapter(adapter_s);       

		inputSearch_s.addTextChangedListener(new TextWatcher() {
		     
		    @Override
		    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		        // When user changed the Text
		        Activity_Search.this.adapter_s.getFilter().filter(cs);   
		    }
		     
		    @Override
		    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
		            int arg3) {
		        // TODO Auto-generated method stub
		         
		    }
		     
		    @Override
		    public void afterTextChanged(Editable arg0) {
		        // TODO Auto-generated method stub                          
		    }
		});
	    
        return rootView;
    }
}