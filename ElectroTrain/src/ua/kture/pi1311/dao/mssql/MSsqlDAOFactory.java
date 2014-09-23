package ua.kture.pi1311.dao.mssql;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;


public class MSsqlDAOFactory extends DAOFactory
{

	@Override
	public StationDAO getStationDAO() {
		return new MSsqlStationDAO();
	}

	@Override
	public TrainDAO getTrainDAO() {
		return new MSsqlTrainDAO();
	}

}
