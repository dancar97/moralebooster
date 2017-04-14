package com.studyingapp.southfang.moralebooster.fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.studyingapp.southfang.moralebooster.MessageItem;
import com.studyingapp.southfang.moralebooster.R;
import com.studyingapp.southfang.moralebooster.database.DatabaseHelper;
import com.studyingapp.southfang.moralebooster.database.ReaderContract.FeedEntry;


public class tab3 extends Fragment {

	private DataPoint[] points;
	private String itemCount;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.tab3, container, false);

		GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
		getData();
		LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);

		graph.addSeries(series);
		((TextView)rootView.findViewById(R.id.tab3_count)).setText(itemCount);

		return rootView;
	}

	private void getData() {
		SQLiteDatabase db = (new DatabaseHelper(getContext())).getReadableDatabase();

		Cursor c = db.query(FeedEntry.TABLE_NAME,
				new String[]{FeedEntry.COLUMN_DATE, "COUNT(" + FeedEntry.COLUMN_DATE + ")"},
				null, null, FeedEntry.COLUMN_DATE, null,
				FeedEntry.COLUMN_DATE + " ASC", "100");

		c.moveToFirst();
		points = new DataPoint[c.getCount()];
		int i = 0;
		if (c.getCount() != 0)
			points[i] = new DataPoint(i++, c.getInt(1));
		while (c.moveToNext())
			points[i] = new DataPoint(i++, c.getInt(1));

		c = db.query(FeedEntry.TABLE_NAME,
				new String[]{"COUNT(" + FeedEntry.COLUMN_TYPE + ")"},
				FeedEntry.COLUMN_TYPE + " = ?",
				new String[]{MessageItem.TipoMensaje.MEDIJERON.toString()},
				null, null, null, "1");
		c.moveToFirst();
		if (c.getCount() != 0)
			itemCount = c.getString(0);

		c.close();
	}

}
