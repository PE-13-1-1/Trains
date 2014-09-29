package ua.kture.pi1311.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LocalDataBase extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "localDB.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "Station";
	private static final String STATION_ID = "stationId";
	private static final String STATION_NAME = "stationName";
	private static final String STATION_DIRECTION_ID ="directionId";
	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ TABLE_NAME + " (" + STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ STATION_NAME + " VARCHAR(255)," + STATION_DIRECTION_ID+" INTEGER)";

	public LocalDataBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "ќбновление базы данных с версии " + oldVersion
				+ " до версии " + newVersion + ", которое удалит все старые данные");
		// ”дал€ем предыдущую таблицу при апгрейде
		db.execSQL(SQL_DELETE_ENTRIES);
		// —оздаЄм новый экземпл€р таблицы
		onCreate(db);

	}

}
