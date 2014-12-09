package fragments;

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
import android.widget.ListView;

import ua.kture.pi1311.electrotrain.Adapter_for_ways;
import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.Adapter_for_stations;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Way_screen extends Fragment {
	FragmentPagerAdapter adapterViewPager;
    ImageButton fav_but;
	ImageButton refresh_but;
    public Activity_Way_screen() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_ways, container,
                false);
        ListView lvMain = (ListView) rootView.findViewById(R.id.stations_list);
        String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис","Костя", "Игорь", "Анна", "Денис", "Андрей" };
        Adapter_for_ways adapter = new Adapter_for_ways(this.getActivity(), names);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //   R.layout.trains_item,R.id.label, names);
        // присваиваем адаптер списку
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
        fav_but.setTag(1);
        fav_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	ImageButton b=(ImageButton) v.findViewById(R.id.fav_but);
            	Drawable empty = getResources().getDrawable(R.drawable.emptystar);
            	Drawable fill = getResources().getDrawable(R.drawable.filledstar);
            	b.setImageDrawable(empty);
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