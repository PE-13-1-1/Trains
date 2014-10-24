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

public class Activity_Search_Ways extends Fragment {
	
	AutoCompleteTextView mAutoComplete;
	final String[]  stations = { "Belgium", "France", "Italy", "Germany", "Spain","Germ" };
    private ListView lv_s;
    ArrayAdapter<String> adapter_s;
    EditText inputSearch_s;
    ArrayList<HashMap<String, String>> trainList;
	
    public Activity_Search_Ways() {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_search_ways, container,
                false);

		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
         AutoCompleteTextView textView = (AutoCompleteTextView)
        		 rootView.findViewById(R.id.search_way1);
         textView.setAdapter(adapter);
         textView.setThreshold(0);

         adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
         textView = (AutoCompleteTextView)
        		 rootView.findViewById(R.id.search_way2);
         textView.setAdapter(adapter);
         textView.setThreshold(0);
		
        return rootView;
    }
}