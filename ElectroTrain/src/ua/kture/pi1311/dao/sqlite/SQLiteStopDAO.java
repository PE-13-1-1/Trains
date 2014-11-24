package ua.kture.pi1311.dao.sqlite;

import android.database.sqlite.SQLiteDatabase;
import ua.kture.pi1311.dao.StopDAO;
import ua.kture.pi1311.entity.Stop;

public class SQLiteStopDAO implements StopDAO{
	private static final String SQL__FIND_STOP_BY_ID = "SELECT * FROM Stop WHERE stopId=?;";
	private static final String SQL__UPDATE_STOP = "UPDATE Stop SET [stationId]=? [timeArrival]=? [timeDeparture]=? [listId]=? [staying]=? WHERE [stopId]=?;";
	private static final String SQL__INSERT_STOP = "INSERT INTO Stop (stationId, timeArrival, timeDeparture, "
			+ "staying,listId) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL__DELETE_STOP = "DELETE FROM Stop WHERE stopId=?";
	private static final String SQL_TRUNCATE_STOP = "USE KharkovTrain;TRUNCATE TABLE dbo.Stop; ";
	private  SQLiteDatabase dbWrite;
	private  SQLiteDatabase dbRead;

	public SQLiteStopDAO (SQLiteDatabase dbWrite, SQLiteDatabase dbRead) {
		this.dbWrite = dbWrite;
		this.dbRead = dbRead;
	}
	@Override
	public boolean insertStop(Stop stop) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Stop findStop(int stopId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateStop(Stop stop) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteStop(int stopId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean truncateStop() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
