package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kture.pi1311.electrotrain.MyPagerAdapter;
import ua.kture.pi1311.electrotrain.R;

public class Activity_Search extends Fragment {
	FragmentPagerAdapter adapterViewPager;
    public Activity_Search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_search, container,
                false);
        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        return rootView;
    }

}