package ua.kture.pi1311.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterStations 
{
	public static final String KEY_ID = "id";
	public static final String KEY_STATIONNAME = "stationname";
	public static final String KEY_TRAINNUMBER = "trainnumber";
	public static final String KEY_STATIONNAMEFROM = "stationnamefrom";
	public static final String KEY_STATIONNAMETO = "stationnameto";
	public static final String KEY_ARRIVAL = "arrival";
	public static final String KEY_DEPARTURE = "departure";
	public static final String KEY_STATUS = "status";
	
	private static final String DATABASE_NAME = "StationsDB";
	private static final String DATABASE_TABLE = "stations";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
					"create table if not exists stations ("
		          + "id integer primary key autoincrement," 
		          + "stationname text," 
		          + "trainnumber integer," 
		          + "stationnamefrom text,"
		          + "stationnameto text," 
		          + "arrival text," 
		          + "departure text,"
		          + "status text"+ ");";
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapterStations(Context context)
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
			db.execSQL("DROP TABLE IF EXISTS stations");
			onCreate(db);
		}
	}
	
	//---opens the database---
	public DBAdapterStations open() throws SQLException
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
	public long insertRecord(String stationName, int trainNumber, String stationNameFrom,
			String stationNameTo, String arrival, String departure, String status)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_STATIONNAME, stationName);
		initialValues.put(KEY_TRAINNUMBER, trainNumber);
		initialValues.put(KEY_STATIONNAMEFROM, stationNameFrom);
		initialValues.put(KEY_STATIONNAMETO, stationNameTo);
		initialValues.put(KEY_ARRIVAL, arrival);
		initialValues.put(KEY_DEPARTURE, departure);
		initialValues.put(KEY_STATUS, status);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//---deletes a particular record---
	public boolean deleteStation(String stationName)
	{
		return db.delete(DATABASE_TABLE, KEY_STATIONNAME + "= ?", new String[] { stationName }) > 0;
	}
	
	//---retrieves all the records---
	public Cursor getAllRecords()
	{
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_STATIONNAME, KEY_TRAINNUMBER,
				KEY_STATIONNAMEFROM, KEY_STATIONNAMETO, KEY_ARRIVAL, KEY_DEPARTURE, KEY_STATUS },
				null, null, null, null, null);
	}
	
	//---retrieves a particular record---
	public Cursor getStation(String stationName) throws SQLException
	{
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID, KEY_STATIONNAME,
				KEY_TRAINNUMBER, KEY_STATIONNAMEFROM, KEY_STATIONNAMETO, KEY_ARRIVAL, 
				KEY_DEPARTURE, KEY_STATUS },
				KEY_STATIONNAME + " = ?", new String[] { stationName }, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a record---
	public boolean updateStation(String stationName, int trainNumber, 
			String stationNameFrom, String stationNameTo, String arrival, String departure, String status)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_TRAINNUMBER, trainNumber);
		updatedValues.put(KEY_STATIONNAMEFROM, stationNameFrom);
		updatedValues.put(KEY_STATIONNAMETO, stationNameTo);
		updatedValues.put(KEY_ARRIVAL, arrival);
		updatedValues.put(KEY_DEPARTURE, departure);
		updatedValues.put(KEY_STATUS, status);
		return db.update(DATABASE_TABLE, updatedValues, KEY_STATIONNAME + "= ?", new String[] { stationName }) > 0;
	}
}