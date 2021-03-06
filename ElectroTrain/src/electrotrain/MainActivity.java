package ua.kture.pi1311.electrotrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fragments.Activity_About;
import fragments.Activity_Fav;
import fragments.Activity_Search;
import fragments.Activity_Search_Stations;
import fragments.Activity_Settings;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.dao.sqlite.AndroidDB;
import ua.kture.pi1311.entity.Train;
import android.support.v7.app.ActionBarActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private String[] mScreenTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTitle = mDrawerTitle = getTitle();
        mScreenTitles = getResources().getStringArray(R.array.screen_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mScreenTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
                ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
            	supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            	
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        // Initialize the first fragment when the application first loads.
        if (savedInstanceState == null) {
            selectItem(0);
        }
        try {
	    AndroidDB sqh = new AndroidDB(this);
	    DAOFactory dao = sqh.getSQLiteDAOFactory();
	    TrainDAO trainDAO = dao.getTrainDAO();
	    sqh.close();
        }
        catch (Exception ex) {
        	System.out.println(ex.getMessage());
        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
    	boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
    	menu.findItem(R.id.action_search).setVisible(false);
        //menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
         // Handle action buttons
        switch(item.getItemId()) {
        //case R.id.action_search:
            // Show toast about click.
        	//Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
            //return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
     /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new Activity_Fav();
            break;
        case 1:
        	fragment = new Activity_Search();
            break;
        case 2:
        	fragment = new Activity_Settings();
            break;
        case 3:
        	fragment = new Activity_About();
        default:
            break;
        }
 
        // Insert the fragment by replacing any existing fragment
        if (fragment != null) {
        	//FragmentTransaction tx = getSupportFragmentManager().beginTransation();
        	//tx.replace( R.id.content_frame, fragment ).addToBackStack( "tag" ).commit();
        	FragmentManager fragmentManager = getSupportFragmentManager();
        	//FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        	//fragmentTransaction.replace(R.id.content_frame, fragment);
        	//fragmentTransaction.addToBackStack(null);
        	//fragmentTransaction.commit();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
            // Highlight the selected item, update the title, and close the drawer
    	    mDrawerList.setItemChecked(position, true);
    	    setTitle(mScreenTitles[position]);
    	    mDrawerLayout.closeDrawer(mDrawerList);
    	    
        } else {
            // Error
            Log.e(this.getClass().getName(), "Error. Fragment is not created");
        }
    }
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
