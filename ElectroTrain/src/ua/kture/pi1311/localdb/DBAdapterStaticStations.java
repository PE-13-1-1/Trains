package ua.kture.pi1311.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterStaticStations 
{
	public static final String KEY_ID = "id";
	public static final String KEY_STATIONNAME = "stationname";
	
	private static final String DATABASE_NAME = "StaticStationsDB";
	private static final String DATABASE_TABLE = "stations";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = 
					"create table if not exists stations ("
		          + "id integer primary key autoincrement," 
		          + "stationname text" + ");";
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public DBAdapterStaticStations(Context context)
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
	public DBAdapterStaticStations open() throws SQLException
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
	public long insertRecord(String stationName)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_STATIONNAME, stationName);
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
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_STATIONNAME },
				null, null, null, null, null);
	}
	
	//---retrieves a particular record---
	public Cursor getStation(int id) throws SQLException
	{
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] { KEY_ID, KEY_STATIONNAME },
				KEY_ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a record---
	public boolean updateStation(int id, String stationName)
	{
		ContentValues updatedValues = new ContentValues();
		updatedValues.put(KEY_STATIONNAME, stationName);
		return db.update(DATABASE_TABLE, updatedValues, KEY_ID + "= ?", new String[] { String.valueOf(id) }) > 0;
	}
}
