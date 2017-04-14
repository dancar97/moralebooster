package com.studyingapp.southfang.moralebooster.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studyingapp.southfang.moralebooster.MessageItem;
import com.studyingapp.southfang.moralebooster.R;
import com.studyingapp.southfang.moralebooster.database.DatabaseHelper;
import com.studyingapp.southfang.moralebooster.database.ReaderContract.FeedEntry;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

	private OnListFragmentInteractionListener mListener;
	private static MessageFragment instance = new MessageFragment();
	public static final List<MessageItem> ITEMS = new ArrayList<>();

	public MessageFragment() {
	}

	public static MessageFragment getInstance(){
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_messageitem_list, container, false);

		// Set the adapter
		if (view instanceof RecyclerView) {
			Context context = view.getContext();
			RecyclerView recyclerView = (RecyclerView) view;
			recyclerView.setLayoutManager(new LinearLayoutManager(context));

			refreshItems();

			recyclerView.setAdapter(new MessageItemRecyclerViewAdapter(ITEMS, mListener));
		}
		return view;
	}


	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnListFragmentInteractionListener) {
			mListener = (OnListFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnListFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnListFragmentInteractionListener {
		void onListFragmentInteraction(MessageItem item);
	}

	public void refreshItems(){
		ITEMS.clear();git
		SQLiteDatabase db = (new DatabaseHelper(getContext())).getReadableDatabase();
		Cursor c = db.query(FeedEntry.TABLE_NAME,
				FeedEntry.ALL_COLUMNS, null, null, null, null, null);

		c.moveToFirst();
		MessageItem item;
		if(c.getCount()!= 0 ) {
			item = new MessageItem(c.getString(c.getColumnIndex(FeedEntry.COLUMN_DATE)),
					c.getString(c.getColumnIndex(FeedEntry.COLUMN_CONTENT)),
					MessageItem.TipoMensaje.getTipoMensaje(c.getString(c.getColumnIndex(FeedEntry.COLUMN_TYPE))),
					Integer.parseInt(c.getString(c.getColumnIndex(FeedEntry.COLUMN_IMAGE))));
			ITEMS.add(item);
		}
		while(c.moveToNext()){
			item = new MessageItem(c.getString(c.getColumnIndex(FeedEntry.COLUMN_DATE)),
					c.getString(c.getColumnIndex(FeedEntry.COLUMN_CONTENT)),
					MessageItem.TipoMensaje.getTipoMensaje(c.getString(c.getColumnIndex(FeedEntry.COLUMN_TYPE))),
					Integer.parseInt(c.getString(c.getColumnIndex(FeedEntry.COLUMN_IMAGE))));
			ITEMS.add(item);
		}
		c.close();
	}
}
