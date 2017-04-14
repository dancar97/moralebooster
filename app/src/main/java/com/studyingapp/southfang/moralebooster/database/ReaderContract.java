package com.studyingapp.southfang.moralebooster.database;

import android.provider.BaseColumns;

/**
 * Created by juandavid on 13/04/17.
 */

public final class ReaderContract {
	private ReaderContract(){}

	public static class FeedEntry implements BaseColumns{
		public static final String TABLE_NAME = "mensajes";
		public static final String COLUMN_DATE = "fecha";
		public static final String COLUMN_CONTENT = "contenido";
		public static final String COLUMN_TYPE = "tipo_conenido";
		public static final String COLUMN_IMAGE = "imagen";

		public static final String[] ALL_COLUMNS = {COLUMN_DATE, COLUMN_CONTENT, COLUMN_TYPE, COLUMN_IMAGE};
	}
}
