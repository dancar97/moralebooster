package com.studyingapp.southfang.moralebooster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studyingapp.southfang.moralebooster.database.ReaderContract.FeedEntry;

public class DetailActivity extends AppCompatActivity {

	private static MessageItem messageItem;
	private static String typeText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		messageItem = new MessageItem(bundle.get(FeedEntry.COLUMN_DATE).toString(),
				bundle.get(FeedEntry.COLUMN_CONTENT).toString(),
				MessageItem.TipoMensaje.getTipoMensaje(bundle.get(FeedEntry.COLUMN_TYPE).toString()),
				bundle.getInt(FeedEntry.COLUMN_IMAGE));
		typeText = intent.getStringExtra(FeedEntry.COLUMN_TYPE);

		if (savedInstanceState == null) getSupportFragmentManager().beginTransaction()
				.add(R.id.container, new PlaceholderFragment())
				.commit();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

			TextView date = (TextView) container.findViewById(R.id.detail_date);
			TextView content = (TextView) container.findViewById(R.id.detail_content);
			TextView type = (TextView) container.findViewById(R.id.detail_type);
			ImageView image = (ImageView) container.findViewById(R.id.detail_image);

			date.setText(messageItem.getFecha());
			content.setText(messageItem.getContenido());
			type.setText(messageItem.getTipoMensaje().toString());
			image.setImageResource(messageItem.getImagen());


			return rootView;
		}
	}
}
