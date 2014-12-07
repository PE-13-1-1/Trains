package ua.kture.pi1311.context;

import java.util.ArrayList;

import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;

public interface ITrainContext
{
	public ArrayList<Train> GetTrainsWithinStations(Station station1, Station station2);
	
	public ArrayList<Train> GetTrainsByStation(Station station);
	
	public ArrayList<Station> GetStationsByTrain(Train train);
	
	public Stop GetStopByTrainAndStation(Train train, Station station);
	
	public Station GetStationByStop(Stop stop);
	
	public Train GetTrainByStop(Stop stop);
	
	public Station GetStationByName(String stationName);
	
	public ArrayList<Station> GetStationsByPartialName(String partialName);
	
	public Train GetTrainByNumber(int number);
}
