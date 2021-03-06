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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;

import ua.kture.pi1311.context.StoredStationsContext;
import ua.kture.pi1311.context.TrainContext;
import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Search_Ways extends Fragment {
	
	AutoCompleteTextView mAutoCompleteFirst;
	AutoCompleteTextView mAutoCompleteSecond;
	String[]  stations;
    private ListView lv_s;
    ArrayAdapter<String> adapter_s;
    EditText inputSearch_s;
    ArrayList<HashMap<String, String>> trainList;
    ImageButton search_but;
	ImageButton swap_but;
    public Activity_Search_Ways() 
    {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
            {
    	 StoredStationsContext context = new StoredStationsContext(this.getActivity().getApplicationContext());
    	 if (context.isEmpty())
    		 context.fillDatabase();
    	 ArrayList<String> list = context.getStationNames();
    	 stations = list.toArray(new String[list.size()]);
    	
         View rootView = inflater.inflate(R.layout.activity_search_ways, container,
                 false);

         mAutoCompleteFirst = (AutoCompleteTextView) rootView.findViewById(R.id.search_way1);
         mAutoCompleteSecond = (AutoCompleteTextView) rootView.findViewById(R.id.search_way2);
         
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
		 mAutoCompleteFirst.setAdapter(adapter);
		 mAutoCompleteFirst.setThreshold(0);

         adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
         mAutoCompleteSecond.setAdapter(adapter);
         mAutoCompleteSecond.setThreshold(0);
		
         search_but=(ImageButton) rootView.findViewById(R.id.search_but);
         search_but.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	String stationNameFirst = mAutoCompleteFirst.getText().toString();
            	String stationNameSecond = mAutoCompleteSecond.getText().toString();
             	TrainContext context = new TrainContext();
             	//ArrayList<ArrayList<String>> trainInfo = context.getTrainsByStationNames(stationNameFirst, stationNameSecond);
            	 
            	Fragment fragment = new Activity_Way_screen();
             	Fragment parent=getParentFragment();
             	FragmentManager fragmentManager2 = parent.getFragmentManager();
             	FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
            	fragmentTransaction.replace(R.id.content_frame, fragment);
            	fragmentTransaction.addToBackStack(null);
            	fragmentTransaction.commit();
             	//fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment).commit();

             }
         });
         swap_but=(ImageButton) rootView.findViewById(R.id.swap_but);
         swap_but.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 AutoCompleteTextView t1 = (AutoCompleteTextView)getActivity().findViewById(R.id.search_way1);
            	 AutoCompleteTextView t2 = (AutoCompleteTextView)getActivity().findViewById(R.id.search_way2);
            	 //AutoCompleteTextView temp=t1;
            	 //t1=t2;
            	 //t2=temp;
            	 Editable temp = t1.getText();
            	 //String s=temp.toString();
            	 //Editable temp2 = t2.getText();
            	 //String s2=temp.toString();
            	 t1.setText(t2.getText());
            	 t2.setText(temp);
            	 t2.clearFocus();
            	 t1.clearFocus();

             }
         });
        return rootView;
    }
}