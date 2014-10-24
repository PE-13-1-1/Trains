package ua.kture.pi1311.electrotrain;

import fragments.Activity_About;
import fragments.Activity_Fav;
import fragments.Activity_Settings;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;

        public MyPagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new Activity_Settings();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new Activity_Fav();
            
            default:
                return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
        	if (position==0){
        		return "Станции";
        	}
        	else{
                return "Направление";
        	}

        }

    }