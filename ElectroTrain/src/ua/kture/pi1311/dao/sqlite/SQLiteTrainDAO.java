package ua.kture.pi1311.dao.sqlite;

import java.util.ArrayList;
import java.util.Set;

import android.database.sqlite.SQLiteDatabase;

import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.entity.Train;

public class SQLiteTrainDAO implements TrainDAO{

	private static final String SQL__FIND_TRAIN_BY_ID = "SELECT * FROM Train WHERE trainId=?;";
	private static final String SQL__UPDATE_TRAIN = "UPDATE Train SET [startPoint]=?, [finalPoint]=?, [status]=?,"
			+ "[trainNumber]=?,[trainURL]=?, [scheduleId]=? WHERE [trainId]=?;";
	private static final String SQL__INSERT_TRAIN = "INSERT INTO Train (startPoint, finalPoint, status, "
			+ "trainNumber,trainURL,scheduleId) VALUES (?, ?, ?, ?, ?,?);";
	private static final String SQL__DELETE_TRAIN = "DELETE FROM Train WHERE trainId=?";
	private static final String SQL_TRUNCATE_TRAIN = "USE KharkovTrain;TRUNCATE TABLE dbo.Train; ";
	private static final String SQL_GET_TRAIN_URL = "SELECT trainURL FROM KharkovTrain.dbo.Train";
	private static final String SQL_SELECT_ALL_TRAINS = "SELECT * FROM KharkovTrain.dbo.Train";
	private  SQLiteDatabase dbWrite;
	private  SQLiteDatabase dbRead;

	public SQLiteTrainDAO (SQLiteDatabase dbWrite, SQLiteDatabase dbRead) {
		this.dbWrite = dbWrite;
		this.dbRead = dbRead;
	}
	@Override
	public boolean insertTrain(Train train) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Train findTrain(int trainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTrain(Train train) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTrain(int trainId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean truncateTrain() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getTrainURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Train> findAllTrains() {
		// TODO Auto-generated method stub
		return null;
	}

}
