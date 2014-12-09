package fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import ua.kture.pi1311.context.FavouriteContext;
import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Station;

public class Activity_Fav extends Fragment {

	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    FavouriteContext context;
    
    public Activity_Fav() {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_fav, container,
                false);
        expListView = (ExpandableListView) rootView.findViewById(R.id.Stations_List);
        prepareListData();
        listAdapter = new ExpandableListAdapter(rootView.getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
        
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expListView.collapseGroup(previousItem );
                previousItem = groupPosition;
            }
        });
        expListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition,   int childPosition, long id) {
            	if (groupPosition == 0)
            	{
            		String stationName = (String) parent.getExpandableListAdapter().getChild(groupPosition, groupPosition);
                	Fragment fragment = new Activity_Station_screen(stationName, context.getTrainsForStation(stationName));
            		FragmentManager fragmentManager2 = getFragmentManager();
                 	fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment).commit();
            	}
            	if (groupPosition == 1)
            	{
                	Fragment fragment = new Activity_Way_screen();
            		FragmentManager fragmentManager2 = getFragmentManager();
                 	fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment).commit();
            	}
                 return false;
                }
              });
        return rootView;
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        
        listDataHeader.add("Станции");
        List<String> stations = new ArrayList<String>();
        
        context = new FavouriteContext(this.getActivity().getApplicationContext());
        for (String station : context.getFavouriteStationsNames())
        	stations.add(station);
        
        listDataChild.put(listDataHeader.get(0), stations);
        
        listDataHeader.add("Маршрут");
        List<String> ways = new ArrayList<String>();
        
        ways.add("Маршрут 1");
        ways.add("Маршрут 2");
        ways.add("Маршрут 3");
        ways.add("Маршрут 4");
        ways.add("Маршрут 5");
        ways.add("Маршрут 6");
        listDataChild.put(listDataHeader.get(1), ways);
        }
	

}