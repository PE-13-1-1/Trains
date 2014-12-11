package ua.kture.pi1311.dao.sqlite;

import java.sql.SQLException;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ua.kture.pi1311.dao.StopDAO;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Stop;

public class SQLiteStopDAO implements StopDAO {
	private static final String SQL__FIND_STOP_BY_ID = "SELECT * FROM Stop WHERE stopId=?;";
	private static final String SQL__UPDATE_STOP = "UPDATE Stop SET [stationId]=? [timeArrival]=? [timeDeparture]=? [listId]=? [staying]=? WHERE [stopId]=?;";
	private static final String SQL__INSERT_STOP = "INSERT INTO Stop (stationId, timeArrival, timeDeparture, "
			+ "staying,listId) VALUES (?, ?, ?, ?, ?);";
	private static final String SQL__DELETE_STOP = "DELETE FROM Stop WHERE stopId=?";
	private static final String SQL_TRUNCATE_STOP = "USE KharkovTrain;TRUNCATE TABLE dbo.Stop; ";

	public SQLiteStopDAO() {
	}

	@Override
	public boolean insertStop(Stop stop) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.STOP_ID, stop.getStopId());
			values.put(MapperParameters.STOP_TIME_ARRIVAL, stop
					.getTimeArrival().toString());
			values.put(MapperParameters.STOP_TIME_DEPARTURE, stop
					.getTimeDeparture().toString());
			values.put(MapperParameters.STOP_STAYING, stop.getStaying());
			values.put(MapperParameters.STOP_TRAIN_ID, stop.getTrainId());
			values.put(MapperParameters.STOP_STATION_ID, stop.getStationId());
			AndroidDB.dbWrite.insert("Station", null, values);
			AndroidDB.dbWrite.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Stop findStop(int stopId) {
		Cursor cursor = AndroidDB.dbRead.query("Stop",
				new String[] { MapperParameters.STOP_ID,
						MapperParameters.STOP_TIME_ARRIVAL,
						MapperParameters.STOP_TIME_DEPARTURE,
						MapperParameters.STOP_STAYING,
						MapperParameters.STOP_STATION_ID,
						MapperParameters.STOP_TRAIN_ID},
				MapperParameters.STOP_ID + "=?",
				new String[] { String.valueOf(stopId) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		Stop stop = null;
		try {
			stop = unMapStop(cursor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stop;
	}

	@Override
	public boolean updateStop(Stop stop) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.STOP_ID, stop.getStopId());
			values.put(MapperParameters.STOP_TIME_ARRIVAL, stop
					.getTimeArrival().toString());
			values.put(MapperParameters.STOP_TIME_DEPARTURE, stop
					.getTimeDeparture().toString());
			values.put(MapperParameters.STOP_STAYING, stop.getStaying());
			values.put(MapperParameters.STOP_TRAIN_ID, stop.getTrainId());
			values.put(MapperParameters.STOP_STATION_ID, stop.getStationId());
			AndroidDB.dbWrite.update("Stop", values, MapperParameters.STOP_ID
					+ " = ?",
					new String[] { String.valueOf(stop.getStopId()) });
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteStop(int stopId) {
		try {
			AndroidDB.dbWrite.delete("Stop", MapperParameters.STOP_ID + " =?",
					new String[] { String.valueOf(stopId) });
			return true; }
			catch (Exception ex) 
			{
				return false;
			}
	}

	@Override
	public boolean truncateStop() {
		try {
			AndroidDB.dbWrite.execSQL(SQL_TRUNCATE_STOP);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	private Stop unMapStop(Cursor rs) throws SQLException {
		Stop station = new Stop();
		station.setStopId(rs.getInt(0));
		station.setStaying(rs.getInt(3));
		station.setTrainId(rs.getInt(4));
		station.setStationId(rs.getInt(5));
		return station;
	}

}
