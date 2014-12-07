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
import java.util.ArrayList;
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
 

import ua.kture.pi1311.electrotrain.R;
import ua.kture.pi1311.entity.Train;
 

public class Activity_Settings extends Fragment {
        Button b1;
        Train tr;
        TextView t1;
 

	
    public Activity_Settings() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 

        View rootView = inflater.inflate(R.layout.activity_settings, container,
                false);
        b1=(Button)rootView.findViewById(R.id.button1);
        t1=(TextView)rootView.findViewById(R.id.textView1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postData();
            }
        });
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
                t1.setText((String)tr.getStartPoint());
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
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    //httppost.setParams(n);
    //httppost.setHeader("method", "twoplustwo");
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();
 
    ObjectInputStream in = new ObjectInputStream(entity.getContent());
    //InputStream is = entity.getContent();
    //String s = convertStreamToString(is);
    try {
                tr = (Train)in.readObject();
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
    public void postData() {
        new DownloadWebpageTask().execute("http://178.150.137.228:8080/Server/");
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
    }

}
