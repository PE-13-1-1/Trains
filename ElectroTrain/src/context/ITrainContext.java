package ua.kture.pi1311.context;

import java.util.ArrayList;

import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;

public interface ITrainContext
{
	public String[][] getTrainsByStationName(String stationName);
	
	public ArrayList<Stop> getStopsByTrainId(int trainId);
	
	public String[][] getTrainsByStationNames(String stationNameFirst, String stationNameSecond);
}
