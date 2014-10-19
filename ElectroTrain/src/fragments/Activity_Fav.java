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

import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;

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
        
        listDataHeader.add("�������");
        List<String> stations1 = new ArrayList<String>();
        
        stations1.add("������� 1");
        stations1.add("������� 2");
        stations1.add("������� 3");
        stations1.add("������� 4");
        stations1.add("������� 5");
        stations1.add("������� 6");
        stations1.add("������� 7");
        stations1.add("������� 5");
        stations1.add("������� 1");
        stations1.add("������� 2");
        stations1.add("������� 3");
        stations1.add("������� 4");
        stations1.add("������� 5");
        stations1.add("������� 6");
        stations1.add("������� 7");
        
        listDataHeader.add("�������");
        List<String> ways = new ArrayList<String>();
        
        ways.add("������� 1");
        ways.add("������� 2");
        ways.add("������� 3");
        ways.add("������� 4");
        ways.add("������� 5");
        ways.add("������� 6");
        
        listDataChild.put(listDataHeader.get(0), stations1);
        listDataChild.put(listDataHeader.get(1), ways);}
	

}