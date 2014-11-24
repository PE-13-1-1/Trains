package ua.kture.pi1311.dao.sqlite;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.DirectionDAO;
import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.StopDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.dao.mssql.MSsqlDirectionDAO;
import ua.kture.pi1311.dao.mssql.MSsqlStationDAO;
import ua.kture.pi1311.dao.mssql.MSsqlStopDAO;
import ua.kture.pi1311.dao.mssql.MSsqlTrainDAO;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDAOFactory extends DAOFactory{

	private  SQLiteDatabase dbWrite;
	private  SQLiteDatabase dbRead;
	
	public SQLiteDAOFactory(SQLiteDatabase dbWrite, SQLiteDatabase dbRead) {
		this.dbWrite = dbWrite;
		this.dbRead = dbRead;
	}

	@Override
	public StationDAO getStationDAO() {
		return new SQLiteStationDAO(dbWrite, dbRead);
	}

	@Override
	public TrainDAO getTrainDAO() {
		return new SQLiteTrainDAO(dbWrite, dbRead);
	}

	@Override
	public DirectionDAO getDirectionDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StopDAO getStopDAO() {
		return new SQLiteStopDAO(dbWrite, dbRead);
	}
	
}
