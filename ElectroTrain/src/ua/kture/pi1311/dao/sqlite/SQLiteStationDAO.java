/*package ua.kture.pi1311.dao.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Train;

public class SQLiteStationDAO implements StationDAO {
	private static final String SQL__FIND_STATION_BY_ID = "SELECT * FROM Station WHERE stationId=?;";
	private static final String SQL__UPDATE_STATION = "UPDATE Station SET [stationName]=?, [stationURL]=? WHERE [stationId]=?;";
	private static final String SQL__INSERT_STATION = "INSERT INTO Station (stationName, stationURL) VALUES (?, ?);";
	private static final String SQL__DELETE_STATION = "DELETE FROM Station WHERE stationId=?";
	private static final String SQL_TRUNCATE_STATION = "USE KharkovTrain;TRUNCATE TABLE dbo.Station";
	private static final String SQL_FIND_ALL_STATIONS = "SELECT * FROM dbo.Station";


	public SQLiteStationDAO () {
		
	}
	@Override
	public boolean insertStation(Station station) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.STATION_ID, station.getStationId());
			values.put(MapperParameters.STATION_NAME,
					station.getStationName());
			values.put(MapperParameters.STATION_URL,
					station.getStationURL());
			AndroidDB.dbWrite.insert("Station", null, values);
			AndroidDB.dbWrite.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Station findStation(int stationId) {
		Cursor cursor = AndroidDB.dbRead.query("Station", new String[] {
				MapperParameters.STATION_ID, MapperParameters.STATION_NAME,
				MapperParameters.STATION_URL,
				}, MapperParameters.STATION_ID + "=?",
				new String[] { String.valueOf(stationId) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		Station station = null;
		try {
			station = unMapStation(cursor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return station;
	}

	@Override
	public boolean updateStation(Station station) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.STATION_ID, station.getStationId());
			values.put(MapperParameters.STATION_NAME,
					station.getStationName());
			values.put(MapperParameters.STATION_URL,
					station.getStationURL());
			AndroidDB.dbWrite.update("Station", values, MapperParameters.STATION_ID
					+ " = ?",
					new String[] { String.valueOf(station.getStationId()) });
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteStation(int stationId) {
		try {
			AndroidDB.dbWrite.delete("Station", MapperParameters.STATION_ID + " =?",
					new String[] { String.valueOf(stationId) });
			return true; }
			catch (Exception ex) 
			{
				return false;
			}
	}

	@Override
	public boolean truncateStation() {
		try {
			AndroidDB.dbWrite.execSQL(SQL_TRUNCATE_STATION);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}

	@Override
	public ArrayList<Station> findAllStations() {
		ArrayList<Station> contactList = new ArrayList<Station>();
        String selectQuery = "SELECT  * FROM " + "Station"; 
        Cursor cursor = AndroidDB.dbRead.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Station contact = null;
				try {
					contact = unMapStation(cursor);
				} catch (SQLException e) {
					e.printStackTrace();
				}
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
	}
	
	private void mapStationForInsert(Station station, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, station.getStationName());
		pstmt.setString(2, station.getStationURL());
	}
	private Station unMapStation(Cursor rs) throws SQLException {
		Station station = new Station();
		station.setStationId(rs.getInt(0));
		station.setStationURL(rs.getString(2));
		station.setStationName(rs.getString(1));
		return station;
	}

}*/
