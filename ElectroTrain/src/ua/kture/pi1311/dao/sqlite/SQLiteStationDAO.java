package ua.kture.pi1311.dao.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;
import ua.kture.pi1311.entity.Station;

public class SQLiteStationDAO implements StationDAO {
	private static final String SQL__FIND_STATION_BY_ID = "SELECT * FROM Station WHERE stationId=?;";
	private static final String SQL__UPDATE_STATION = "UPDATE Station SET [stationName]=?, [stationURL]=? WHERE [stationId]=?;";
	private static final String SQL__INSERT_STATION = "INSERT INTO Station (stationName, stationURL) VALUES (?, ?);";
	private static final String SQL__DELETE_STATION = "DELETE FROM Station WHERE stationId=?";
	private static final String SQL_TRUNCATE_STATION = "USE KharkovTrain;TRUNCATE TABLE dbo.Station";
	private static final String SQL_FIND_ALL_STATIONS = "SELECT * FROM dbo.Station";
	private  SQLiteDatabase dbWrite;
	private  SQLiteDatabase dbRead;

	public SQLiteStationDAO (SQLiteDatabase dbWrite, SQLiteDatabase dbRead) {
		this.dbWrite = dbWrite;
		this.dbRead = dbRead;
	}
	@Override
	public boolean insertStation(Station station) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Station findStation(int stationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStation(Station station) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStation(int stationId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean truncateStation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Station> findAllStations() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void mapStationForInsert(Station station, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, station.getStationName());
		pstmt.setString(2, station.getStationURL());
	}
	private Station unMapStation(ResultSet rs) throws SQLException {
		Station station = new Station();
		station.setStationId(rs.getInt(MapperParameters.STATION_ID));
		station.setStationURL(rs.getString(MapperParameters.STATION_URL));
		station.setStationName(rs.getString(MapperParameters.STATION_NAME));
		return station;
	}

}
