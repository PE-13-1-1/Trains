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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import ua.kture.pi1311.context.FavouriteContext;
import ua.kture.pi1311.context.TrainContext;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Stop;

public class Activity_Station_screen extends Fragment 
{
	FragmentPagerAdapter adapterViewPager;
    ImageButton fav_but;
	ImageButton refresh_but;
	
	String[] stationsFrom;
	String[] stationsTo;
	String[] arrivals;
	String[] depatures;
	String[] trainsIds;
	
	String stationName;
	
	public Activity_Station_screen() 
    {
		stationsFrom = new String[1];
		stationsFrom[0] = "stationfrom1";
		stationsTo = new String[1];
		stationsTo[0] = "stationto1";
		arrivals = new String[1];
		arrivals[0] = "arrival1";
		depatures = new String[1];
		depatures[0] = "depature1";
		trainsIds = new String[1];
		trainsIds[0] = "1";
		
		stationName = "������� ��������";
    }
	
    public Activity_Station_screen(String stationName, String[][] trainsInfo) 
    {
    	int size = trainsInfo.length;
    	stationsFrom = new String[size];
    	stationsTo = new String[size];
    	stationsFrom = new String[size];
    	stationsFrom = new String[size];
    	trainsIds = new String[size];
    	
    	for (int i = 0; i < size; i++)
    	{
    		stationsFrom[i] = trainsInfo[i][0];
    		stationsTo[i] = trainsInfo[i][1];
    		arrivals[i] = trainsInfo[i][2];
    		depatures[i] = trainsInfo[i][3];
    		trainsIds[i] = trainsInfo[i][4];
    	}
    	
    	this.stationName = stationName;
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	
        View rootView = inflater.inflate(R.layout.activity_stations, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.textViewTitle);
        title.setText(stationName);
        
        ListView lvMain = (ListView) rootView.findViewById(R.id.stations_list);
        Adapter_for_stations adapter = new Adapter_for_stations(this.getActivity(),
        		stationsFrom, stationsTo, arrivals, depatures, trainsIds);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener
        (
	        new OnItemClickListener()
	        {
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	            {	
	            	LinearLayout row = (LinearLayout) view;
	            	TextView trainID = (TextView) row.getChildAt(4);
	            	String train = trainID.getText().toString();
	            	int trainId = Integer.parseInt(train);
	            	
	            	TrainContext context = new TrainContext();
	            	ArrayList<Stop> stopList = context.getStopsByTrainId(trainId);
	            	
	            	Fragment fragment = new Activity_Train();
	             	FragmentManager fragmentManager2 = getFragmentManager();
	             	FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
	            	fragmentTransaction.replace(R.id.content_frame, fragment);
	            	fragmentTransaction.addToBackStack(null);
	            	fragmentTransaction.commit();
	             	//fragmentManager2.beginTransaction()
	                //.replace(R.id.content_frame, fragment).commit();
	            }
	        }
        );
        
        fav_but=(ImageButton) rootView.findViewById(R.id.fav_but);
        fav_but.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            { }
        });
        
        refresh_but=(ImageButton) rootView.findViewById(R.id.refresh_but);
        refresh_but.setOnClickListener
        (
    		new View.OnClickListener() 
	        {
	            public void onClick(View v) { }
	        }
    	);
        return rootView;
    }
} // end of class