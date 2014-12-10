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
        ArrayList<Train> tr2;
        ArrayList<Stop> stops;
        String[][] s;
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
                new DownloadWayTask().execute("http://178.150.137.228:8080/Server/");
 
            }
        });
        return rootView;
    }
       
    private class DownloadWayTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
             
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadWayUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) {
                t1.setText(tr.get(0).getStartPoint());
                t2.setText(tr.get(1).getStartPoint());
            b1.setText(result);
       }
    }
    private String downloadWayUrl(String myurl) throws IOException {
         HttpClient httpclient = new DefaultHttpClient();
     HttpPost httppost = new HttpPost("http://178.150.137.228:8080/Server/");
    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
    params.add(new BasicNameValuePair("method", "4"));
    params.add(new BasicNameValuePair("stationName" ,"Граково"));
    //params.add(new BasicNameValuePair("name_1" ,"Занки"));
    //params.add(new BasicNameValuePair("name_2" ,"Граково"));
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();
 
    ObjectInputStream in = new ObjectInputStream(entity.getContent());
    try {
    	s=(String[][])in.readObject();
                //tr = (ArrayList<Train>)in.readObject();
                //t1.setText((String)tr.getStartPoint());
        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    in.close();
    return "1";
    }
}