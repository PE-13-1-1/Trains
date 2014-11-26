package ua.kture.pi1311.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.electrotrain.parametres.*;

public class AndroidDB extends SQLiteOpenHelper {

	private static int DATABASE_VERSION = 1;
	public static SQLiteDatabase dbWrite;
	public static SQLiteDatabase dbRead;

	public AndroidDB(Context context) {
		super(context, MapperParameters.DB_NAME, null, DATABASE_VERSION);
	}

	public void createTables(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Station (" +
		MapperParameters.STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
		MapperParameters.STATION_NAME + " TEXT , " + 
		MapperParameters.STATION_URL + " TEXT);");
		
		
		db.execSQL("CREATE TABLE Train (" +
				MapperParameters.TRAIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
				MapperParameters.TRAIN_START_POINT + " TEXT , " +
				MapperParameters.TRAIN_FINAL_POINT + " TEXT , " +
				MapperParameters.TRAIN_STATUS + " TEXT , " +
				MapperParameters.TRAIN_NUMBER + " INTEGER , " + 
				MapperParameters.TRAIN_URL + " TEXT , " +
						");");
		
		db.execSQL("CREATE TABLE Stop (" +
				MapperParameters.STOP_STATION_ID + " INTEGER , " +
				MapperParameters.STOP_TIME_ARRIVAL + " TIME , " +
				MapperParameters.STOP_TIME_DEPARTURE + " TIME , " +
				MapperParameters.STOP_STAYING + " INTEGER , " +
				MapperParameters.STOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				MapperParameters.STOP_TRAIN_ID +  " INTEGER , " +
				"FOREIGN KEY (" + MapperParameters.STATION_ID + 
				") REFERENCES Station (" + MapperParameters.STATION_ID + ")" + 
				"FOREIGN KEY (" + MapperParameters.TRAIN_ID + 
				") REFERENCES Train (" + MapperParameters.TRAIN_ID + ")" + 
						");");
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbRead = this.getReadableDatabase();
		dbWrite = this.getWritableDatabase();
		createTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS Train");
		db.execSQL("DROP TABLE IF EXISTS Stop");
		db.execSQL("DROP TABLE IF EXISTS TrainSchedule");
		db.execSQL("DROP TABLE IF EXISTS Station");
		DATABASE_VERSION = newVersion;
		onCreate(db);
	}
	
	public void getSQLiteDAOFactory() {
//		SQLiteDatabase dbWrite = this.getWritableDatabase();
//		SQLiteDatabase dbRead = this.getReadableDatabase();
//		DAOFactory.getSQLiteDAOFactory(2, dbWrite, dbRead);
		dbRead = this.getReadableDatabase();
		dbWrite = this.getWritableDatabase();
	}

}
