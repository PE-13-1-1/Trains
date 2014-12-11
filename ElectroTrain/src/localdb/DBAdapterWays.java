package ua.kture.pi1311.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterWays 
{
	public static final String KEY_ID = "id";
	public static final String KEY_FIRSTSTATIONNAME = "firststationname";
	public static final String KEY_SECONDSTATIONNAME = "secondstationname";
	public static final String KEY_TRAINNUMBER = "trainnumber";
	public static final String KEY_STATIONNAMEFROM = "stationnamefrom";
	public static final String KEY_STATIONNAMETO = "stationnameto";
	public static final String KEY_ARRIVALTOFIRST = "arrivaltofirst";
	public static final String KEY_ARRIVALTOSECOND = "arrivaltosecond";
	public static final String KEY_STATUS = "status";
	
	private static final String DATABASE_NAME = "WaysDB";
	private static final String DATABASE_TABLE = "ways";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
					"create table if not exists ways ("
		          + "id integer primary key autoincrement," 
		          + "firststationname text," 
		          + "secondstationname text," 
		          + "trainnumber integer,"
		          + "stationnamefrom text,"
		          + "stationnameto text," 
		          + "arrivaltofirst text,"
		          + "arrivaltosecond text,"
		          + "status text" + ");";
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapterWays(Context context)
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
			db.execSQL("DROP TABLE IF EXISTS ways");
			onCreate(db);
		}
	}
	
	//---opens the database---
	public DBAdapterWays open() throws SQLException
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
	public long insertWay(String firstStationName, String secondStationName, int trainNumber,
			String stationNameFrom, String stationNameTo, String arrivalToFirst, String arrivalToSecond, String status)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_FIRSTSTATIONNAME, firstStationName);
		initialValues.put(KEY_SECONDSTATIONNAME, secondStationName);
		initialValues.put(KEY_TRAINNUMBER, trainNumber);
		initialValues.put(KEY_STATIONNAMEFROM, stationNameFrom);
		initialValues.put(KEY_STATIONNAMETO, stationNameTo);
		initialValues.put(KEY_ARRIVALTOFIRST, arrivalToFirst);
		initialValues.put(KEY_ARRIVALTOSECOND, arrivalToSecond);
		initialValues.put(KEY_STATUS, status);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	//---deletes a particular record---
	public boolean deleteWay(String stationNameFirst, String stationNameSecond)
	{
		return db.delete(DATABASE_TABLE, KEY_FIRSTSTATIONNAME + "= ? AND " + KEY_SECONDSTATIONNAME + "= ?", new String[] { stationNameFirst, stationNameSecond }) > 0;
	}
	
	//---retrieves all the records---
	public Cursor getAllRecords()
	{
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_FIRSTSTATIONNAME, 
				KEY_FIRSTSTATIONNAME, KEY_TRAINNUMBER, KEY_STATIONNAMEFROM, KEY_STATIONNAMETO, 
				KEY_ARRIVALTOFIRST, KEY_ARRIVALTOSECOND, KEY_STATUS },
				null, null, null, null, null);
	}
	
	//---retrieves a particular record---
	public Cursor getWay(String stationNameFirst, String stationNameSecond) throws SQLException
	{
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID, KEY_FIRSTSTATIONNAME, 
				KEY_FIRSTSTATIONNAME, KEY_TRAINNUMBER, KEY_STATIONNAMEFROM, KEY_STATIONNAMETO, 
				KEY_ARRIVALTOFIRST, KEY_ARRIVALTOSECOND, KEY_STATUS },
				KEY_FIRSTSTATIONNAME + "= ? AND " + KEY_SECONDSTATIONNAME + "= ?",
				new String[] { stationNameFirst, stationNameSecond }, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a record---
	public boolean updateWay(long rowId, String firstStationName, String secondStationName, int trainNumber,
			String stationNameFrom, String stationNameTo, String arrivalToFirst, String arrivalToSecond, String status)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_TRAINNUMBER, trainNumber);
		updatedValues.put(KEY_STATIONNAMEFROM, stationNameFrom);
		updatedValues.put(KEY_STATIONNAMETO, stationNameTo);
		updatedValues.put(KEY_ARRIVALTOFIRST, arrivalToFirst);
		updatedValues.put(KEY_ARRIVALTOSECOND, arrivalToSecond);
		updatedValues.put(KEY_STATUS, arrivalToSecond);
		return db.update(DATABASE_TABLE, updatedValues, KEY_FIRSTSTATIONNAME + "= ? AND " + KEY_SECONDSTATIONNAME + "= ?", new String[] { firstStationName, secondStationName }) > 0;
	}
}
