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

import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Train extends Fragment {
	FragmentPagerAdapter adapterViewPager;
	
    public Activity_Train() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_train, container,
                false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.stops_list);
        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис","Костя", "Игорь", "Анна", "Денис", "Андрей" };
        Adapter_for_stations adapter = new Adapter_for_stations(this.getActivity(), names);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //   R.layout.trains_item,R.id.label, names);
        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);
        return rootView;
    }
}