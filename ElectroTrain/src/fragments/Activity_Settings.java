package fragments;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
 
import android.R.string;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
 

import ua.kture.pi1311.context.FavouriteContext;
import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;
 

public class Activity_Settings extends Fragment {
        Button b1;
        ArrayList<Train> tr;
        TextView t1;
        TextView t2;
 

	
    public Activity_Settings() {
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 

        View rootView = inflater.inflate(R.layout.activity_settings, container,
                false);
        b1 = (Button)rootView.findViewById(R.id.button1);
        t1 = (TextView)rootView.findViewById(R.id.textView1);
        t2 = (TextView)rootView.findViewById(R.id.textView2);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new DownloadWebpageTask().execute("http://178.150.137.228:8080/Server/");
            }
        });
        
//        Station station_kh = new Station();
//        station_kh.setStationName("Kharkov");
//        station_kh.setStationId(1);
//        Station station_ps = new Station();
//        station_ps.setStationName("Pesochin");
//        station_ps.setStationId(2);
//        Station station_lb = new Station();
//        station_lb.setStationName("Liubotin");
//        station_lb.setStationId(3);
//        
//        Train train1 = new Train();
//        train1.setStartPoint("Kharkov");
//        train1.setFinalPoint("Liubotin");
//        train1.setTrainNumber(1000);
//        train1.setTrainId(1);
//        Train train2 = new Train();
//        train2.setFinalPoint("Kharkov");
//        train2.setStartPoint("Liubotin");
//        train2.setTrainNumber(2000);
//        train2.setTrainId(2);
//        
//        Stop kh1 = new Stop();
//        kh1.setStationId(1);
//        kh1.setTimeArrival(new Time(10));
//        kh1.setTimeDeparture(new Time(11));
//        kh1.setTrainId(1);
//        
//        Stop ps1 = new Stop();
//        ps1.setStationId(2);
//        ps1.setTimeArrival(new Time(13));
//        ps1.setTimeDeparture(new Time(14));
//        ps1.setTrainId(1);
//        
//        Stop lb1 = new Stop();
//        lb1.setStationId(1);
//        lb1.setTimeArrival(new Time(16));
//        lb1.setTimeDeparture(new Time(17));
//        lb1.setTrainId(1);
//        
//        Stop kh2 = new Stop();
//        kh2.setStationId(1);
//        kh2.setTimeArrival(new Time(20));
//        kh2.setTimeDeparture(new Time(21));
//        kh2.setTrainId(2);
//        
//        Stop ps2 = new Stop();
//        ps2.setStationId(2);
//        ps2.setTimeArrival(new Time(23));
//        ps2.setTimeDeparture(new Time(24));
//        ps2.setTrainId(2);
//        
//        Stop lb2 = new Stop();
//        lb2.setStationId(1);
//        lb2.setTimeArrival(new Time(26));
//        lb2.setTimeDeparture(new Time(27));
//        lb2.setTrainId(2);
//        
//        ArrayList<Stop> l1 = new ArrayList<Stop>();
//        l1.add(kh1);
//        l1.add(ps1);
//        l1.add(lb1);
//        ArrayList<Stop> l2 = new ArrayList<Stop>();
//        l2.add(lb2);
//        l2.add(ps2);
//        l2.add(kh2);
//        
//        train1.setStops(l1);
//        train2.setStops(l2);
//        
//        ArrayList<Train> ltr = new ArrayList<Train>();
//        ltr.add(train1);
//        ltr.add(train2);
        
//        FavouriteContext context = new FavouriteContext(this.getActivity().getApplicationContext());
//        context.addStationToFauvorite(station_ps, ltr);
                
        return rootView;
    }
    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
             
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
                t1.setText((String)tr.get(0).getStartPoint());
            b1.setText(result);
       }
    }
    private static String convertStreamToString(InputStream is) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
        while ((line = reader.readLine()) != null) { sb.append((line + "\n")); }
        }
    catch (IOException e) {
        e.printStackTrace(); } finally { try { is.close();
        }
    catch (IOException e) {
        e.printStackTrace();
        } }
    return sb.toString(); }
    private String downloadUrl(String myurl) throws IOException {
         HttpClient httpclient = new DefaultHttpClient();
     HttpPost httppost = new HttpPost("http://178.150.137.228:8080/Server/");
     
    //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    //nameValuePairs.add(new BasicNameValuePair("method", "twoplustwo"));
    //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    //HttpParams n= new BasicHttpParams();
    //n.setParameter("method", 1);
    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
    params.add(new BasicNameValuePair("method", "2"));
    params.add(new BasicNameValuePair("number" ,"1"));
    //params.add(new BasicNameValuePair("method", "0"));
    //params.add(new BasicNameValuePair("name_1" ,"Харьков"));
    //params.add(new BasicNameValuePair("name_2" ,"Казачья Лопань"));
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    //httppost.setParams(n);
    //httppost.setHeader("method", "twoplustwo");
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();
 
    ObjectInputStream in = new ObjectInputStream(entity.getContent());
    //InputStream is = entity.getContent();
    //String s = convertStreamToString(is);
    try {
                tr = (ArrayList<Train>)in.readObject();
                //t1.setText((String)tr.getStartPoint());
        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    in.close();
    /*Header[] a = response.getAllHeaders();
    String s ="";
    for (int i =0;i<a.length;i++)
    {
        s+=a[i].getValue();
    }
    String s=response.getAllHeaders()[2].getName();*/
    //InputStream is = entity.getContent();
    //String s = convertStreamToString(is);
    return "1";
    }
    //public void postData() {
        //new DownloadWebpageTask().execute("http://178.150.137.228:8080/Server/");
        // Create a new HttpClient and Post Header
       /*
        try {
            // Add your data
                 HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost("http://178.150.137.228:8080/Server/");
 
            //List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            //nameValuePairs.add(new BasicNameValuePair("method", "twoplustwo"));
            //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            httppost.setHeader("method", "twoplustwo");
            HttpResponse response = httpclient.execute(httppost);
            String s=response.toString();  
            b1.setText(s);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        */
    //}

}