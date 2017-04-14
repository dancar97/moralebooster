package com.studyingapp.southfang.moralebooster;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.studyingapp.southfang.moralebooster.database.DatabaseHelper;
import com.studyingapp.southfang.moralebooster.database.ReaderContract.FeedEntry;
import com.studyingapp.southfang.moralebooster.fragments.MessageFragment;

import java.text.SimpleDateFormat;
import java.util.Date;


public class writing_panel11 extends Activity {

	public static String location;
	public Spinner spinner;
	public ImageView Image;
	public EditText texts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.writing_panel1);


		Image = (ImageView) findViewById(R.id.imageView3);
		texts = (EditText) findViewById(R.id.editText);
		final Spinner spnLocale = (Spinner) findViewById(R.id.spinner);
		spinner = (Spinner) findViewById(R.id.spinner2);

		spnLocale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				Image.setImageResource(getImageId(spnLocale.getSelectedItem().toString()));
			}

			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});


		Button clickButton = (Button) findViewById(R.id.button3);
		clickButton.setOnClickListener(new View.OnClickListener() {

			                               @Override
			                               public void onClick(View v) {
				                               SQLiteDatabase db = (new DatabaseHelper(getBaseContext())).getWritableDatabase();
				                               ContentValues values = new ContentValues();
				                               CharSequence s  = DateFormat.format("d/MM/yy", new Date().getTime() + 1);

				                               values.put(FeedEntry.COLUMN_DATE,s.toString());
				                               values.put(FeedEntry.COLUMN_CONTENT, texts.getText().toString());
				                               values.put(FeedEntry.COLUMN_IMAGE, getImageId(spnLocale.getSelectedItem().toString()));
				                               values.put(FeedEntry.COLUMN_TYPE,
						                               MessageItem.TipoMensaje.getTipoMensaje(spinner.getSelectedItem()
								                               .toString()).toString());
				                               db.insert(FeedEntry.TABLE_NAME, null, values);
				                               MessageFragment.getInstance().refreshItems();
				                               finish();
			                               }
		                               }
		);


	}

	private int getImageId(String text) {
		switch (text) {
			case "Estoy conforme":
				location = ".sprite_0";
				return R.drawable.sprite_0;
			case "Estoy feliz":
				location = ".sprite_4";
				return R.drawable.sprite_4;
			case "Estoy triste":
				location = ".sprite_1";
				return R.drawable.sprite_1;
			case "Tengo disgusto":
				location = ".sprite_2";
				return R.drawable.sprite_2;
			case "Tengo curiosidad":
				location = ".sprite_3";
				return R.drawable.sprite_3;
		}

		return 0;
	}
}
