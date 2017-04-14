package com.studyingapp.southfang.moralebooster.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.studyingapp.southfang.moralebooster.database.ReaderContract.FeedEntry;

/**
 * Created by juandavid on 13/04/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FeedReader.db";

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
					FeedEntry._ID + " INTEGER PRIMARY KEY," +
					FeedEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_CONTENT + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_TYPE + TEXT_TYPE + COMMA_SEP +
					FeedEntry.COLUMN_IMAGE + TEXT_TYPE + " )";

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
		onCreate(sqLiteDatabase);
	}
}