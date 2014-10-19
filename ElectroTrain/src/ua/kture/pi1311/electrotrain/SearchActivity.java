package ua.kture.pi1311.electrotrain;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

public class SearchActivity extends Activity {

	// List view
    private ListView lv_s;
    // Listview Adapter
    ArrayAdapter<String> adapter_s;
    // Search EditText
    EditText inputSearch_s;
    // ArrayList for Listview
    ArrayList<HashMap<String, String>> trainList;
    
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_search);
	    

	    TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
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

		lv_s = (ListView) findViewById(R.id.list_view_s);
		inputSearch_s = (EditText) findViewById(R.id.inputSearch_s);
		
		// Adding items to listview
		adapter_s = new ArrayAdapter<String>(this, R.layout.list_item, R.id.lblListItem, stations);
		lv_s.setAdapter(adapter_s);       

		inputSearch_s.addTextChangedListener(new TextWatcher() {
		     
		    @Override
		    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		        // When user changed the Text
		        SearchActivity.this.adapter_s.getFilter().filter(cs);   
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
	    
	    
	  }
}
