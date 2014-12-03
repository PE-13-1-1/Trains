/*package ua.kture.pi1311.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;
import ua.kture.pi1311.entity.Train;

public class SQLiteTrainDAO implements TrainDAO {

	private static final String SQL__FIND_TRAIN_BY_ID = "SELECT * FROM Train WHERE trainId=?;";
	private static final String SQL__UPDATE_TRAIN = "UPDATE Train SET [startPoint]=?, [finalPoint]=?, [status]=?,"
			+ "[trainNumber]=?,[trainURL]=?, [scheduleId]=? WHERE [trainId]=?;";
	private static final String SQL__INSERT_TRAIN = "INSERT INTO Train (startPoint, finalPoint, status, "
			+ "trainNumber,trainURL,scheduleId) VALUES (?, ?, ?, ?, ?,?);";
	private static final String SQL__DELETE_TRAIN = "DELETE FROM Train WHERE trainId=?";
	private static final String SQL_TRUNCATE_TRAIN = "USE KharkovTrain;TRUNCATE TABLE dbo.Train; ";
	private static final String SQL_GET_TRAIN_URL = "SELECT trainURL FROM KharkovTrain.dbo.Train";
	private static final String SQL_SELECT_ALL_TRAINS = "SELECT * FROM KharkovTrain.dbo.Train";

	public SQLiteTrainDAO() {

	}

	@Override
	public boolean insertTrain(Train train) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.TRAIN_ID, train.getTrainId());
			values.put(MapperParameters.TRAIN_START_POINT,
					train.getStartPoint());
			values.put(MapperParameters.TRAIN_FINAL_POINT,
					train.getFinalPoint());
			values.put(MapperParameters.TRAIN_NUMBER, train.getTrainNumber());
			values.put(MapperParameters.TRAIN_STATUS, train.getStatus());
			values.put(MapperParameters.TRAIN_URL, train.getTrainUrl());
			AndroidDB.dbWrite.insert("Train", null, values);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	@Override
	public Train findTrain(int trainId) {
		Cursor cursor = AndroidDB.dbRead.query("Train", new String[] {
				MapperParameters.TRAIN_ID, MapperParameters.TRAIN_START_POINT,
				MapperParameters.TRAIN_FINAL_POINT,
				MapperParameters.TRAIN_NUMBER, MapperParameters.TRAIN_STATUS,
				MapperParameters.TRAIN_URL }, MapperParameters.TRAIN_ID + "=?",
				new String[] { String.valueOf(trainId) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		Train train = null;
		try {
			train = unMapTrain(cursor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return train;

	}

	@Override
	public boolean updateTrain(Train train) {
		try {
			ContentValues values = new ContentValues();
			values.put(MapperParameters.TRAIN_ID, train.getTrainId());
			values.put(MapperParameters.TRAIN_START_POINT,
					train.getStartPoint());
			values.put(MapperParameters.TRAIN_FINAL_POINT,
					train.getFinalPoint());
			values.put(MapperParameters.TRAIN_NUMBER, train.getTrainNumber());
			values.put(MapperParameters.TRAIN_STATUS, train.getStatus());
			values.put(MapperParameters.TRAIN_URL, train.getTrainUrl());
			AndroidDB.dbWrite.update("Train", values, MapperParameters.TRAIN_ID
					+ " = ?",
					new String[] { String.valueOf(train.getTrainId()) });
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean deleteTrain(int trainId) {
		try {
		AndroidDB.dbWrite.delete("Train", MapperParameters.TRAIN_ID + " =?",
				new String[] { String.valueOf(trainId) });
		return true; }
		catch (Exception ex) 
		{
			return false;
		}
	}

	@Override
	public boolean truncateTrain() {
		try {
			AndroidDB.dbWrite.execSQL(SQL_TRUNCATE_TRAIN);
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Set<String> getTrainURL() {
		return null;
	}

	@Override
	public ArrayList<Train> findAllTrains() {
		ArrayList<Train> contactList = new ArrayList<Train>();
        String selectQuery = "SELECT  * FROM " + "Train"; 
        Cursor cursor = AndroidDB.dbRead.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Train contact = null;
				try {
					contact = unMapTrain(cursor);
				} catch (SQLException e) {
					e.printStackTrace();
				}
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
	}

	private Train unMapTrain(Cursor rs) throws SQLException {
		Train train = new Train();
		train.setStartPoint(rs.getString(1));
		train.setFinalPoint(rs.getString(2));
		train.setTrainId(rs.getInt(0));
		train.setStatus(rs.getString(4));
		train.setTrainNumber(rs.getInt(3));
		train.setTrainUrl(rs.getString(5));
		return train;
	}
}*/
