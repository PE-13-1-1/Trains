package ua.kture.pi1311.electrotrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import ua.kture.pi1311.dao.mssql.MSsqlStationDAO;
import ua.kture.pi1311.entity.Station;

public class FavActivity extends Activity 
{
	ExpandableListAdapter listAdapter_s;
    ExpandableListView expListView_s;
    List<String> listDataHeader_s;
    HashMap<String, List<String>> listDataChild_s;
    
    ExpandableListAdapter listAdapter_w;
    ExpandableListView expListView_w;
    List<String> listDataHeader_w;
    HashMap<String, List<String>> listDataChild_w;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_fav);
	    
		expListView_s = (ExpandableListView) findViewById(R.id.Stations_List);
	    prepareListData_s();
	    listAdapter_s = new ExpandableListAdapter(this, listDataHeader_s, listDataChild_s);
	    expListView_s.setAdapter(listAdapter_s);
	    
	    expListView_w = (ExpandableListView) findViewById(R.id.Ways_List);
	    prepareListData_w();
	    listAdapter_w = new ExpandableListAdapter(this, listDataHeader_w, listDataChild_w);
	    expListView_w.setAdapter(listAdapter_w);	         
	}
	
	private void prepareListData_s() 
	{
        listDataHeader_s = new ArrayList<String>();
        listDataChild_s = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader_s.add("Станции");
        // Adding child data
        
        MSsqlStationDAO worker = new MSsqlStationDAO();
        
        List<String> stationNames = new ArrayList<String>();
        ArrayList<Station> stations = worker.findAllStations();
        
        for (Station station : stations)
        {
        	stationNames.add(station.getStationName());
        }
        
        listDataChild_s.put(listDataHeader_s.get(0), stationNames);
    }
	
	private void prepareListData_w() 
	{
        listDataHeader_w = new ArrayList<String>();
        listDataChild_w = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader_w.add("Маршрут");
        // Adding child data
        List<String> ways = new ArrayList<String>();
        ways.add("Маршрут 1");
        ways.add("Маршрут 2");
        ways.add("Маршрут 3");
        ways.add("Маршрут 4");
        ways.add("Маршрут 5");
        
        listDataChild_w.put(listDataHeader_w.get(0), ways);
    }	
}
