package ua.kture.pi1311.context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import fragments.Activity_Station_screen;

import android.os.AsyncTask;

import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.dao.sqlite.SQLiteDAOFactory;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;

public class TrainContext implements ITrainContext
{
	String StationName;
	String Station_way1;
	String Station_way2;
	String[][] trains_forStation;
	ArrayList<ArrayList<String>> trains_forWay;
	ArrayList<Stop> stops;
	String TrainNumber;
	
	@Override
	public String[][] getTrainsByStationName(String stationName) {
		StationName=stationName;
		try
		{
		String str_result=new DownloadStationTask().execute("http://monopoly.jvmhost.net/Train/").get();
		}
		catch(Exception e)
		{
			
		}
		return trains_forStation;
	}

	@Override
	public ArrayList<Stop> getStopsByTrainId(int trainId) {
		TrainNumber=Integer.toString(trainId);
		try
		{
		String str_result=new DownloadTrainTask().execute("http://monopoly.jvmhost.net/Train/").get();
		}
		catch(Exception e)
		{
			
		}
		return stops;
	}

	@Override
	public String[][] getTrainsByStationNames(String stationNameFirst,
			String stationNameSecond) {
		Station_way1=stationNameFirst;
		Station_way2=stationNameSecond;
		try
		{
		String str_result=new DownloadWayTask().execute("http://monopoly.jvmhost.net/Train/").get();
		}
		catch(Exception e)
		{
			
		}
		String[][] result = new String[trains_forWay.size()][6];
		for (int i = 0; i < trains_forWay.size(); i++)
		{
			if (trains_forWay.get(i).get(3) != null)
				result[i][0] = trains_forWay.get(i).get(3);
			else result[i][0] = "";
			if (trains_forWay.get(i).get(4) != null)
				result[i][1] = trains_forWay.get(i).get(4);
			else result[i][1] = "";
			result[i][2] = "������ ����";
			if (trains_forWay.get(i).get(0) != null)
				result[i][3] = trains_forWay.get(i).get(0);
			else result[i][3] = "";
			if (trains_forWay.get(i).get(1) != null)
				result[i][4] = trains_forWay.get(i).get(1);
			else result[i][4] = "";
			if (trains_forWay.get(i).get(2) != null)
				result[i][5] = trains_forWay.get(i).get(2);
			else result[i][5] = "";
		}
		return result;
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
               
       }
    }
    private String downloadStationUrl(String myurl) throws IOException 
    {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://monopoly.jvmhost.net/Train/");
	    List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	    params.add(new BasicNameValuePair("method", "1"));
	    params.add(new BasicNameValuePair("stationName", StationName));
	    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity entity = response.getEntity();
 
	    ObjectInputStream in = new ObjectInputStream(entity.getContent());
	    try 
	    {
               trains_forStation = (String[][])in.readObject();
               //t1.setText((String)tr.getStartPoint());
        } 
	    catch (ClassNotFoundException e) 
	    {
               e.printStackTrace();
        }
	    in.close();
	    return "1";
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
               
       }
    }
    private String downloadWayUrl(String myurl) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://monopoly.jvmhost.net/Train/");
   List<NameValuePair> params = new ArrayList<NameValuePair>(2);
   params.add(new BasicNameValuePair("method", "3"));
   params.add(new BasicNameValuePair("name_1" ,Station_way1));
   params.add(new BasicNameValuePair("name_2" ,Station_way2));
   httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
   HttpResponse response = httpclient.execute(httppost);
   HttpEntity entity = response.getEntity();
 
   ObjectInputStream in = new ObjectInputStream(entity.getContent());
   try {
               trains_forWay = (ArrayList<ArrayList<String>>)in.readObject();
               //t1.setText((String)tr.getStartPoint());
       } catch (ClassNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
       }
   in.close();
   return "1";
   }
    private class DownloadTrainTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
             
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadTrainUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) {
               
       }
    }
    private String downloadTrainUrl(String myurl) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://monopoly.jvmhost.net/Train/");
   List<NameValuePair> params = new ArrayList<NameValuePair>(2);
   params.add(new BasicNameValuePair("method", "2"));
   params.add(new BasicNameValuePair("number" ,TrainNumber));
   httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
   HttpResponse response = httpclient.execute(httppost);
   HttpEntity entity = response.getEntity();
 
   ObjectInputStream in = new ObjectInputStream(entity.getContent());
   try {
	   		stops = (ArrayList<Stop>)in.readObject();
	   } 
   catch (ClassNotFoundException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
       }
   in.close();
   return "1";
   }
}
