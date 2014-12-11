package ua.kture.pi1311.context;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;
import ua.kture.pi1311.localdb.DBAdapterStations;
import ua.kture.pi1311.localdb.DBAdapterTrains;
import ua.kture.pi1311.localdb.DBAdapterWays;

public class FavouriteContext 
{
	private DBAdapterStations adapterStations;
	private DBAdapterWays adapterWays;
	private DBAdapterTrains adapterTrains;
	
	public FavouriteContext(Context context)
	{
		adapterStations = new DBAdapterStations(context);
		adapterTrains = new DBAdapterTrains(context);
		adapterWays = new DBAdapterWays(context);
	}
	
	public boolean addStationToFavourite(String stationName, String[] trainNumbers, String[] stationNamesFrom,
			String[] stationNamesTo, String[] arrivals, String[] departures, String[] statuses)
	{
		try
		{
			adapterStations.open();
			
			int size = trainNumbers.length;
			for (int i = 0; i < size; i++)
				adapterStations.insertRecord(stationName, Integer.parseInt(trainNumbers[i]), stationNamesFrom[i],
					stationNamesTo[i], arrivals[i], departures[i], statuses[i]);
			
			adapterStations.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	
	public boolean addWayToFavourite(String[] firstStationNames, String[] secondStationNames, String[] trainNumbers,
			String[] stationNamesFrom, String[] stationNamesTo, String[] arrivalsToFirst, String[] arrivalsToSecond, String[] statuses)
	{
		try
		{
			adapterWays.open();
			
			int size = trainNumbers.length;
			for (int i = 0; i < size; i++)
				adapterWays.insertWay(firstStationNames[i], secondStationNames[i], Integer.parseInt(trainNumbers[i]),
						stationNamesFrom[i], stationNamesTo[i], arrivalsToFirst[i], arrivalsToSecond[i], statuses[i]);
			
			adapterWays.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	
	public boolean addTrainToFavourite(int trainNumber, ArrayList<Stop> stops)
	{
		try
		{
			adapterTrains.open();
			
			for (Stop stop : stops)
			{
				adapterTrains.insertTrain(trainNumber, stop.getStationName(),
						stop.getTimeArrival().toString(), stop.getTimeDeparture().toString());
			}
			
			adapterTrains.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	
	public ArrayList<String> getFavouriteStationsNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		adapterStations.open();
		
		Cursor cur = adapterStations.getAllRecords();
		
		
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
				adapterStations.close();
				return null;
			}

		adapterStations.close();
		return result;
	}

	public ArrayList<Integer> getFavouriteTrainsNumbers()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		adapterTrains.open();
		
		Cursor cur = adapterTrains.getAllRecords();
		
		
		if (cur.moveToFirst())
		{
			do
			{
				String trainNumber = cur.getString(cur.getColumnIndex("trainnumber"));
				if (!result.contains(Integer.parseInt(trainNumber)))
					result.add(Integer.parseInt(trainNumber));
			}
			while (cur.moveToNext());
		}
		else 
			{
				adapterTrains.close();
				return null;
			}

		adapterTrains.close();
		return result;
	}
	
	public ArrayList<String> getFavouriveWaysNames()
	{
		ArrayList<String> result = new ArrayList<String>();
		
		adapterWays.open();
		
		Cursor cur = adapterWays.getAllRecords();
		
		if (cur.moveToFirst())
		{
			do
			{
				String firstStation = cur.getString(cur.getColumnIndex("firststationname"));
				String secondStation = cur.getString(cur.getColumnIndex("secondstationname"));
				String way = firstStation + " - " + secondStation;
				if (!result.contains(way))
					result.add(way);
			}
			while (cur.moveToNext());
		}
		else 
		{
			adapterWays.close();
			return null;
		}

		adapterWays.close();
		return result;
	}

	public ArrayList<ArrayList<String>> getTrainsForStation(String stationName)
	{
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		adapterStations.open();
		
		Cursor cur = adapterStations.getStation(stationName);
		
		if (cur.moveToFirst())
		{
			do
			{
				ArrayList<String> train = new ArrayList<String>();
				train.add(cur.getString(cur.getColumnIndex("stationnamefrom")));
				train.add(cur.getString(cur.getColumnIndex("stationnameto")));
				train.add(cur.getString(cur.getColumnIndex("status")));
				train.add(cur.getString(cur.getColumnIndex("arrival")));
				train.add(cur.getString(cur.getColumnIndex("departure")));
				train.add(String.valueOf(cur.getInt(cur.getColumnIndex("trainnumber"))));
				if (!result.contains(train))
					result.add(train);
			}
			while (cur.moveToNext());
		}
		else
		{
			adapterStations.close();
			return null;
		}

		adapterStations.close();
		return result;
	}

	public ArrayList<ArrayList<String>> getStopsForTrain(int trainNumber)
	{
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		adapterTrains.open();
		
		Cursor cur = adapterTrains.getTrain(trainNumber);
		
		if (cur.moveToFirst())
		{
			do
			{
				ArrayList<String> train = new ArrayList<String>();
				train.add(cur.getString(cur.getColumnIndex("stationname")));
				train.add(cur.getString(cur.getColumnIndex("srrival")));
				train.add(cur.getString(cur.getColumnIndex("departure")));
				if (!result.contains(train))
					result.add(train);
			}
			while (cur.moveToNext());
		}
		else
		{
			adapterTrains.close();
			return null;
		}

		adapterTrains.close();
		return result;
	}
	
	public ArrayList<ArrayList<String>> getTrainsForWay(String stationNameFirst, String stationNameSecond)
	{
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		adapterWays.open();
		
		Cursor cur = adapterWays.getWay(stationNameFirst, stationNameSecond);
		
		if (cur.moveToFirst())
		{
			do
			{
				ArrayList<String> train = new ArrayList<String>();
				train.add(cur.getString(cur.getColumnIndex("stationnamefrom")));
				train.add(cur.getString(cur.getColumnIndex("stationnameto")));
				train.add(cur.getString(cur.getColumnIndex("status")));
				train.add(cur.getString(cur.getColumnIndex("arrivaltofirst")));
				train.add(cur.getString(cur.getColumnIndex("arrivaltosecond")));
				train.add(String.valueOf(cur.getInt(cur.getColumnIndex("trainnumber"))));
				if (!result.contains(train))
					result.add(train);
			}
			while (cur.moveToNext());
		}
		else
		{
			adapterWays.close();
			return null;
		}

		adapterWays.close();
		return result;
	}

	public boolean deleteStationFromFavourite(String stationName)
	{
		try
		{
			adapterStations.open();
			adapterStations.deleteStation(stationName);
			adapterStations.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	
	public boolean deleteTrainFromFavourite(int trainNumber)
	{
		try
		{
			adapterTrains.open();
			adapterTrains.deleteTrain(trainNumber);
			adapterTrains.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	
	public boolean deleteWayFromFauvorite(String stationNameFirst, String stationNameSecond)
	{
		try
		{
			adapterWays.open();
			adapterWays.deleteWay(stationNameFirst, stationNameSecond);
			adapterWays.close();
			return true;
		}
		catch (Exception exception)
		{
			return false;
		}
	}

	public boolean isFavouriteStation(String stationName)
	{
		adapterStations.open();
		Cursor cur = adapterStations.getStation(stationName);
		adapterStations.close();
		return cur == null;
	}
	
	public boolean isFavouriteTrain(int trainNumber)
	{
		adapterTrains.open();
		Cursor cur = adapterTrains.getTrain(trainNumber);
		adapterTrains.close();
		return cur == null;
	}
	
	public boolean isFavouriteWay(String stationNameFirst, String stationNameSecond)
	{
		adapterWays.open();
		Cursor cur = adapterWays.getWay(stationNameFirst, stationNameSecond);
		adapterWays.close();
		return cur == null;
	}
}
