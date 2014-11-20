package fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

import ua.kture.pi1311.dao.mssql.MSsqlStationDAO;
import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Station;

public class Activity_Fav extends Fragment {

	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    
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
        
        return rootView;
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        
        listDataHeader.add("Станции");
        
        MSsqlStationDAO worker = new MSsqlStationDAO();
        
        List<String> stationNames = new ArrayList<String>();
        ArrayList<Station> stations = worker.findAllStations();
        
        for (int i = 0; i < 5; i++)
        {
        	stationNames.add(stations.get(i).getStationName());
        }
        
        listDataHeader.add("Маршрут");
        List<String> ways = new ArrayList<String>();
        
        ways.add("Маршрут 1");
        ways.add("Маршрут 2");
        ways.add("Маршрут 3");
        ways.add("Маршрут 4");
        ways.add("Маршрут 5");
        ways.add("Маршрут 6");
        
        listDataChild.put(listDataHeader.get(0), stationNames);
        listDataChild.put(listDataHeader.get(1), ways);}
	

}