package fragments;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
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

import ua.kture.pi1311.context.TrainContext;
import ua.kture.pi1311.electrotrain.Adapter_for_ways;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Stop;

public class Activity_Way_screen extends Fragment {
	FragmentPagerAdapter adapterViewPager;
    ImageButton fav_but;
	ImageButton refresh_but;
	boolean favouriteFlag = false;
	
	String[] stationsFrom;
	String[] stationsTo;
	String[] arrivalsFirst;
	String[] arrivalsSecond;
	String[] trainsIds;

	String[][] trainsInfo;
	String stationNameFirst;
	String stationNameSecond;
	
    public Activity_Way_screen() 
    {
    	stationsFrom = new String[2];
		stationsFrom[0] = "stationfrom1";
		stationsFrom[1] = "stationfrom2";
		stationsTo = new String[2];
		stationsTo[0] = "stationto1";
		stationsTo[1] = "stationto2";
		arrivalsFirst = new String[2];
		arrivalsFirst[0] = "arrivalFirst1";
		arrivalsFirst[1] = "arrivalFirst2";
		arrivalsSecond = new String[2];
		arrivalsSecond[0] = "arrivalSecond1";
		arrivalsSecond[1] = "arrivalSecond2";
		trainsIds = new String[2];
		trainsIds[0] = "1";
		trainsIds[1] = "2";
		
		stationNameFirst = "Песочин";
		stationNameSecond = "Люботин";
    	trainsInfo = new String[0][0];
    }
    
    public Activity_Way_screen(String stationNameFirst, String stationNameSecond, String[][] trainsInfo) 
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
    		arrivalsFirst[i] = trainsInfo[i][2];
    		arrivalsSecond[i] = trainsInfo[i][3];
    		trainsIds[i] = trainsInfo[i][4];
    	}
    	
    	this.trainsInfo = trainsInfo;
    	this.stationNameFirst = stationNameFirst;
    	this.stationNameSecond = stationNameSecond;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_ways, container,
                false);
        
        TextView title = (TextView) rootView.findViewById(R.id.textViewTitle);
        title.setText(stationNameFirst + " - " + stationNameSecond);
        
        ListView lvMain = (ListView) rootView.findViewById(R.id.stations_list);
        Adapter_for_ways adapter = new Adapter_for_ways(this.getActivity(),
        		stationsFrom, stationsTo, arrivalsFirst, arrivalsSecond, trainsIds);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //   R.layout.trains_item,R.id.label, names);
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
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
          });
        fav_but=(ImageButton) rootView.findViewById(R.id.fav_but);
        fav_but.setTag(1);
        fav_but.setOnClickListener(
        	new View.OnClickListener() 
        	{
	            public void onClick(View v) 
	            {
	            	ImageButton b=(ImageButton) v.findViewById(R.id.fav_but);
	            	if(favouriteFlag)
	            	{
	            		Drawable emptyStar = getResources().getDrawable(R.drawable.emptystar);
	            		b.setImageDrawable(emptyStar);
	            		favouriteFlag = false;
	            		// place for RUD	            		            		
	            	}
	            	else
	            	{
	            		Drawable fillStar = getResources().getDrawable(R.drawable.filledstar);
	            		b.setImageDrawable(fillStar);
	            		favouriteFlag = true;
	            		//place for RUD	
	            	}
	            }
        	}
        );
        refresh_but=(ImageButton) rootView.findViewById(R.id.refresh_but);
        refresh_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
        return rootView;
    }
}