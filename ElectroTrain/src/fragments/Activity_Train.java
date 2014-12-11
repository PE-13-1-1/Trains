package fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import ua.kture.pi1311.context.TrainContext;
import ua.kture.pi1311.electrotrain.Adapter_for_trains;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Stop;

public class Activity_Train extends Fragment {
	FragmentPagerAdapter adapterViewPager;
	
	String[] stopsNames;
	String[] arrivals;
	String[] departures; 
	
	String startStation;
	String finalStation;
			
    public Activity_Train() 
    {
    	stopsNames = new String[1];
    	stopsNames[0] = "Песочин";
    	arrivals = new String[1];
    	arrivals[0] = "11:15";
    	departures = new String[1];
    	departures[0] = "11:16";
    	
    	startStation = "Харьков";
    	finalStation = "Мерчик";
    }
    
    public Activity_Train(ArrayList<Stop> stops)
    {
    	int size = stops.size();
    	stopsNames = new String[size];
    	arrivals = new String[size];
    	departures = new String[size];
    	
    	for (int i = 0; i < size; i++)
    	{
    		if (stops.get(i).getStationName() != null)
    			stopsNames[i] = stops.get(i).getStationName();
    		else stopsNames[i] = "";
    		if (stops.get(i).getTimeArrival().toString() != null)
    			arrivals[i] = stops.get(i).getTimeArrival().toString();
    		else arrivals[i] = "";
    		if (stops.get(i).getTimeDeparture().toString() != null)
    			departures[i] = stops.get(i).getTimeDeparture().toString();
    		else departures[i] = "";
    	}
    	
    	this.startStation = stops.get(0).getStationName();
    	this.finalStation = stops.get(stops.size()-1).getStationName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_train, container,
                false);
        
        TextView title = (TextView) rootView.findViewById(R.id.textViewTitle);
        title.setText(startStation + " - " + finalStation);
        
        ListView lvMain = (ListView) rootView.findViewById(R.id.stops_list);
        Adapter_for_trains adapter = new Adapter_for_trains(this.getActivity(),
        		stopsNames, arrivals, departures);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //   R.layout.trains_item,R.id.label, names);
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener
        (
        		new OnItemClickListener()
    	        {
    	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    	            {
    	            	LinearLayout row = (LinearLayout) view;
    	            	TextView trainID = (TextView) row.getChildAt(0);
    	            	String stationName = trainID.getText().toString();
    	            	
    	            	TrainContext context = new TrainContext();
    	            	String[][] trainInfo = context.getTrainsByStationName(stationName);
    	            	
    	            	Fragment fragment = new Activity_Station_screen();
    	             	FragmentManager fragmentManager2 = getFragmentManager();
    	             	FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
    	            	fragmentTransaction.replace(R.id.content_frame, fragment);
    	            	fragmentTransaction.addToBackStack(null);
    	            	fragmentTransaction.commit();
    	            }
    	        }
        );
        return rootView;
    }
}