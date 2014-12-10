package ua.kture.pi1311.context;

import java.util.ArrayList;
import java.util.List;

import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.dao.sqlite.SQLiteDAOFactory;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;
import ua.kture.pi1311.entity.Train;

public class TrainContext implements ITrainContext
{

	@Override
	public String[][] getTrainsByStationName(String stationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Stop> getStopsByTrainId(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getTrainsByStationNames(String stationNameFirst,
			String stationNameSecond) {
		// TODO Auto-generated method stub
		return null;
	}

}
