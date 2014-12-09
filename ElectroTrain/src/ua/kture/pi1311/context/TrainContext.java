package ua.kture.pi1311.context;

import java.util.ArrayList;
import java.util.List;

import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.dao.sqlite.SQLiteDAOFactory;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;

public class TrainContext implements ITrainContext {

	@Override
	public ArrayList<Train> GetTrainsWithinStations(Station station1, Station station2) 
	{
		boolean st1 = false, st2 = false;
		ArrayList<Train> result = new ArrayList<Train>();
		
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		TrainDAO trainDAO = fact.getTrainDAO();
		List<Train> trainList = trainDAO.findAllTrains();
		
		for (Train train : trainList)
		{
			ArrayList<Stop> stops = train.getStops();
			for (Stop stop : stops)
			{
				if (!st1 && stop.getStationId() == station1.getStationId())
					st1 = true;
				else if (!st2 && stop.getStationId() == station2.getStationId())
					st2 = true;
			}
			if (st1&&st2)
				result.add(train);
			st1 = false;
			st2 = false;
		}
		return result;
	}
	
	@Override
	public ArrayList<Train> GetTrainsByStation(Station station) 
	{
		ArrayList<Train> result = new ArrayList<Train>();
		
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		TrainDAO trainDAO = fact.getTrainDAO();
		List<Train> trainList = trainDAO.findAllTrains();
		
		for (Train train : trainList)
		{
			ArrayList<Stop> stops = train.getStops();
			for (Stop stop : stops)
			{
				if (stop.getStationId() == station.getStationId())
					result.add(train);
			}
		}
		return result;
	}
	
	@Override
	public ArrayList<Station> GetStationsByTrain(Train train) 
	{
		ArrayList<Station> result = new ArrayList<Station>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for (Stop stop : train.getStops())
			ids.add(stop.getStationId());
		
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		StationDAO stationDAO = fact.getStationDAO();
		List<Station> stationList = stationDAO.findAllStations();
		
		for (Station station : stationList)
			if (ids.contains(station.getStationId()))
					result.add(station);
			
		return result;
	}
	
	@Override
	public Stop GetStopByTrainAndStation(Train train, Station station) 
	{
		for (Stop stop : train.getStops())
			if (stop.getStationId() == station.getStationId())
				return stop;

		return null;
	}
	
	@Override
	public Station GetStationByStop(Stop stop) 
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		StationDAO stationDAO = fact.getStationDAO();
		return stationDAO.findStation(stop.getStationId());
	}
	
	@Override
	public Train GetTrainByStop(Stop stop)
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		TrainDAO trainDAO = fact.getTrainDAO();
		return trainDAO.findTrain(stop.getStationId());
	}

	@Override
	public Station GetStationByName(String stationName) 
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		StationDAO stationDAO = fact.getStationDAO();
		List<Station> stationList = stationDAO.findAllStations();
		
		for (Station station : stationList)
			if (station.getStationName() == stationName)
				return station;
		
		return null;
	}

	@Override
	public Train GetTrainByNumber(int number) 
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		TrainDAO trainDAO = fact.getTrainDAO();
		List<Train> trainList = trainDAO.findAllTrains();
		
		for (Train train : trainList)
			if (train.getTrainNumber() == number)
				return train;
		
		return null;
	}

	@Override
	public ArrayList<Station> GetStationsByPartialName(String partialName) 
	{
		ArrayList<Station> result = new ArrayList<Station>();
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		StationDAO stationDAO = fact.getStationDAO();
		List<Station> stationList = stationDAO.findAllStations();
		
		for (Station station : stationList)
			if (station.getStationName().contains(partialName))
				result.add(station);
		
		return result;
	}
	
	public ArrayList<Station> GetAllStations()
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		StationDAO stationDAO = fact.getStationDAO();
		return stationDAO.findAllStations();
	}
	
	public ArrayList<Train> GetAllTrains()
	{
		SQLiteDAOFactory fact = new SQLiteDAOFactory();
		TrainDAO trainDAO = fact.getTrainDAO();
		return trainDAO.findAllTrains();
	}
}
