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
import android.widget.ListView;

import ua.kture.pi1311.context.FavouriteContext;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Station_screen extends Fragment {
	FragmentPagerAdapter adapterViewPager;
    ImageButton fav_but;
	ImageButton refresh_but;
	String[] stations;
    public Activity_Station_screen() {
    }

    public Activity_Station_screen(String stationName, ArrayList<ArrayList<String>> trainsList) 
    {
		stations = new String[trainsList.size()];
		for (int i = 0; i < trainsList.size(); i++)
		{
			stations[i] = trainsList.get(i).get(0);
		}
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_stations, container,
                false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.stations_list);
        Adapter_for_stations adapter = new Adapter_for_stations(this.getActivity(), stations);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
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
        fav_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
        refresh_but=(ImageButton) rootView.findViewById(R.id.refresh_but);
        refresh_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
        return rootView;
    }
}