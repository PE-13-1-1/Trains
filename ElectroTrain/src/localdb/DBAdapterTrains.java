package ua.kture.pi1311.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterTrains 
{
	public static final String KEY_ID = "id";
	public static final String KEY_TRAINNUMBER = "trainnumber";
	public static final String KEY_STATIONNAME = "stationname";
	public static final String KEY_ARRIVAL = "arrival";
	public static final String KEY_DEPARTURE = "departure";
	
	private static final String DATABASE_NAME = "TrainsDB";
	private static final String DATABASE_TABLE = "trains";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
					"create table if not exists trains ("
		          + "id integer primary key autoincrement," 
		          + "trainnumber integer,"
		          + "stationname text,"
		          + "arrival text,"
		          + "deparuture text" + ");";
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapterTrains(Context context)
	{
		this.context = context;
		DBHelper = new DatabaseHelper(this.context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		}
		
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS trains");
			onCreate(db);
		}
	}
	
	//---opens the database---
	public DBAdapterTrains open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	//---closes the database---
	public void close()
	{
		DBHelper.close();
	}
	
	//---insert a record into the database---
	public long insertTrain(int trainNumber, String stationName, String arrival, String departure)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TRAINNUMBER, trainNumber);
		initialValues.put(KEY_STATIONNAME, stationName);
		initialValues.put(KEY_ARRIVAL, arrival);
		initialValues.put(KEY_DEPARTURE, departure);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//---deletes a particular record---
	public boolean deleteTrain(int trainNumber)
	{
		return db.delete(DATABASE_TABLE, KEY_TRAINNUMBER + "= ?", new String[] { String.valueOf(trainNumber) }) > 0;
	}
	
	//---retrieves all the records---
	public Cursor getAllRecords()
	{
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_TRAINNUMBER, KEY_STATIONNAME,
				KEY_ARRIVAL, KEY_DEPARTURE },
				null, null, null, null, null);
	}
	
	//---retrieves a particular record---
	public Cursor getTrain(int trainNumber) throws SQLException
	{
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID, KEY_TRAINNUMBER, KEY_STATIONNAME,
				KEY_ARRIVAL, KEY_DEPARTURE },
				KEY_TRAINNUMBER + "= ?", new String[] { String.valueOf(trainNumber) }, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a record---
	public boolean updateTrain(int trainNumber, String stationName, String arrival, String departure)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_STATIONNAME, stationName);
		updatedValues.put(KEY_ARRIVAL, arrival);
		updatedValues.put(KEY_DEPARTURE, departure);
		return db.update(DATABASE_TABLE, updatedValues, KEY_TRAINNUMBER + "= ?", new String[] { String.valueOf(trainNumber) }) > 0;
	}
}
