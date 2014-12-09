package ua.kture.pi1311.context;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import ua.kture.pi1311.localdb.DBAdapterStaticStations;

public class StoredStationsContext 
{
	private DBAdapterStaticStations adapter;
	
	public StoredStationsContext(Context context)
	{
		adapter = new DBAdapterStaticStations(context);
	}

	public ArrayList<String> getStationNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		adapter.open();
		Cursor cur = adapter.getAllRecords();
		if (cur.moveToFirst())
		{
			do
			{
				String station = cur.getString(cur.getColumnIndex("stationname"));
				if (!result.contains(station))
					result.add(station);
			}
			while (cur.moveToNext());
		}
		else
		{
			adapter.close();
			return null;
		}
		
		adapter.close();
		return result;
		
	}
	
	public boolean addStation(String stationName)
	{
		try
		{
			adapter.open();
			adapter.insertRecord(stationName);
			adapter.close();
			return true;
		}
		catch (Exception ex)
		{
			adapter.close();
			return false;
		}
	}
	
	public boolean deleteStation(String stationName)
	{
		try
		{
			adapter.open();
			adapter.deleteStation(stationName);
			adapter.close();
			return true;
		}
		catch (Exception ex)
		{
			adapter.close();
			return false;
		}
	}

	public boolean isEmpty()
	{
		boolean empty;
		adapter.open();
		Cursor cur = adapter.getStation(1);
		empty = cur == null || cur.getCount() == 0;
		adapter.close();
		return empty;
	}

	public boolean fillDatabase()
	{
		try
		{
			adapter.open();
			if (!adapter.InsertInfo())
				return false;
			adapter.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public boolean clearDatabase()
	{
		try
		{
			adapter.open();
			if (!adapter.deleteAllStations())
				return false;
			adapter.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
}
