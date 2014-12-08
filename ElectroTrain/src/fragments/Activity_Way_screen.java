package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ua.kture.pi1311.electrotrain.Adapter_for_ways;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Way_screen extends Fragment {
	FragmentPagerAdapter adapterViewPager;
	
    public Activity_Way_screen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_ways, container,
                false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.stations_list);
        String[] names = { "����", "�����", "����", "�����", "����", "�����","�����", "�����", "����", "�����", "������" };
        Adapter_for_ways adapter = new Adapter_for_ways(this.getActivity(), names);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //   R.layout.trains_item,R.id.label, names);
        // ����������� ������� ������
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            	Fragment fragment = new Activity_Train();
             	FragmentManager fragmentManager2 = getFragmentManager();
             	//FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
             	fragmentManager2.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
            }
          });
        return rootView;
    }
}