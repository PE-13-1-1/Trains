package fragments;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import ua.kture.pi1311.context.FavouriteContext;
import ua.kture.pi1311.context.StoredStationsContext;
import ua.kture.pi1311.context.TrainContext;
import ua.kture.pi1311.electrotrain.ExpandableListAdapter;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Train;

public class Activity_Search_Stations extends Fragment {
	
	AutoCompleteTextView mAutoComplete;
	String[]  stations;
    private ListView lv_s;
    ArrayAdapter<String> adapter_s;
    EditText inputSearch_s;
    ArrayList<HashMap<String, String>> trainList;
    ImageButton search_but;
    ArrayList<Train> trains;
    
	
    public Activity_Search_Stations() 
    {
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
    	 StoredStationsContext context = new StoredStationsContext(this.getActivity().getApplicationContext());
    	 if (context.isEmpty())
     	 	 context.fillDatabase();
    	 ArrayList<String> list = context.getStationNames();
    	 stations = list.toArray(new String[list.size()]);
    	
    	
         View rootView = inflater.inflate(R.layout.activity_search_stations, container,
                 false);
         
         mAutoComplete = (AutoCompleteTextView) rootView.findViewById(R.id.search_station);

		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                 android.R.layout.simple_dropdown_item_1line, stations);
		 mAutoComplete.setAdapter(adapter);
		 mAutoComplete.setThreshold(0);
         
         search_but=(ImageButton) rootView.findViewById(R.id.search_but);
         search_but.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	String stationName = mAutoComplete.getText().toString();
            	TrainContext context = new TrainContext();
            	String[][] trainInfo = context.getTrainsByStationName(stationName);
            	
            	Fragment fragment = new Activity_Station_screen();
             	Fragment parent=getParentFragment();
             	FragmentManager fragmentManager2 = parent.getFragmentManager();
             	FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
            	fragmentTransaction.replace(R.id.content_frame, fragment);
            	fragmentTransaction.addToBackStack(null);
            	fragmentTransaction.commit();
             	//fragmentManager2.beginTransaction().replace(R.id.content_frame, fragment).commit();
             }
         });
	    
        return rootView;
    }
    private class DownloadStationTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
             
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadStationUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) {
               
                Fragment fragment = new Activity_Station_screen();
                Fragment parent=getParentFragment();
                FragmentManager fragmentManager2 = parent.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager2.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
       }
    }
    private String downloadStationUrl(String myurl) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://178.150.137.228:8080/Server/");
   List<NameValuePair> params = new ArrayList<NameValuePair>(2);
   params.add(new BasicNameValuePair("method", "1"));
   params.add(new BasicNameValuePair("stationName" ,"Занки"));
   httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
   HttpResponse response = httpclient.execute(httppost);
   HttpEntity entity = response.getEntity();
 
   ObjectInputStream in = new ObjectInputStream(entity.getContent());
   try {
               trains = (ArrayList<Train>)in.readObject();
               //t1.setText((String)tr.getStartPoint());
       } catch (ClassNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
       }
   in.close();
   return "1";
   }
 
}