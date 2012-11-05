package aexp.explist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class RenameGroup extends Activity {
	private Button mRename;
	List<String> GroupListEnablefromDB = new ArrayList();
	private EditText mGroupName;
	private String NewNameGroup;
	private String OldNameGroup;
	private String messageNameGroup;
	private final String[] GroupenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.GROUP_NAME,
			BookProviderMetaData.BookTableMetaData.GROUP_ENABLE,
			BookProviderMetaData.BookTableMetaData.GROUP_ID };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.renamegroup);
		mGroupName = (EditText) findViewById(R.id.namegroup);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage = bundle.getCharSequence("messageNameGroup");
		messageNameGroup = "" + textMessage;
		mGroupName.setText(messageNameGroup);
		mGroupName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mGroupName.setText("");
			}

		});

		updateNumberOfGroupinDB();
		/*
		 * Spinner spinner = (Spinner) findViewById(R.id.spinner); ArrayAdapter
		 * adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
		 * GroupListEnablefromDB);
		 * adapter.setDropDownViewResource(android.R.layout
		 * .simple_spinner_dropdown_item); spinner.setAdapter(adapter);
		 * spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		 */

		mRename = (Button) findViewById(R.id.RenameButton);
		mRename.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NewNameGroup = mGroupName.getText().toString();
				renameGroup(messageNameGroup, NewNameGroup);
			}
		});
	}

	public void updateNumberOfGroupinDB() {
		GroupListEnablefromDB.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				GroupenableProj, "groupenable = ?", new String[] { "1" }, null);
		int numberOfGroup = cursor.getCount();
		for (int i = 0; i < numberOfGroup; i++) {
			cursor.moveToPosition(i);
			int ColumnGroupenable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.GROUP_NAME);
			String nameGroupenable = cursor.getString(ColumnGroupenable);
			GroupListEnablefromDB.add(nameGroupenable);

		}

		GroupListEnablefromDB.remove(0);

	}

	public void renameGroup(String GroupOldName, String GroupNewName) {

		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.GROUP_NAME,
				GroupNewName);
		// values2.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID,"0");
		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"name = ?", new String[] { GroupOldName });
		Toast.makeText(this, "Rename Successfully !", Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent();
		intent.setClass(RenameGroup.this, ANDROID_RSS_READER.class);
		startActivity(intent);
		finish();
		// reload();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			intent.setClass(RenameGroup.this, ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
		}
		}
		return false;
	}

	public void reload() {

		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
}
