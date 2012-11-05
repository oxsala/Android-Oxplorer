package aexp.explist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class AddGroup extends Activity {
	private int numberOfGroup;
	private EditText mGroupName;
	private Button mSave;
	private Button mDiscard;
	private String NameGroup;
	private String SetAddGroup = "0";
	private ArrayList<String> listName_Group;
	private final String[] GroupenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.GROUP_NAME,
			BookProviderMetaData.BookTableMetaData.GROUP_ENABLE,
			BookProviderMetaData.BookTableMetaData.GROUP_ID };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addgroup);
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				GroupenableProj, "groupenable = ?", new String[] { "1" }, null);
		numberOfGroup = cursor.getCount();
		mGroupName = (EditText) findViewById(R.id.namegroup);
		mGroupName.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mGroupName.setText("");
			}

		});
		Bundle bundle = this.getIntent().getExtras();
		listName_Group = bundle.getStringArrayList("temp1");
		// setTitle(""+listName_Group.get(0));
		mSave = (Button) findViewById(R.id.saveButton);
		mDiscard = (Button) findViewById(R.id.discardButton);

		// add a click listener to the button
		mSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				NameGroup = mGroupName.getText().toString();
				setTitle(NameGroup);
				if (listName_Group.contains(NameGroup)) {
					openDialog2();
				} else if (NameGroup.equals("")) {
					openDialog3();
				} else {
					Bundle bundle = new Bundle();
					CharSequence textName_Group;
					textName_Group = NameGroup;
					addGrouptoDB(NameGroup);
					bundle.putCharSequence("bNameGroup", textName_Group);
					SetAddGroup = "1";
					CharSequence textSetAddGroup;
					textSetAddGroup = "" + SetAddGroup;
					bundle.putCharSequence("bSetAddGroup", textSetAddGroup);
					Intent intent = new Intent();
					intent.putExtras(bundle);
					intent.setClass(AddGroup.this, ANDROID_RSS_READER.class);
					startActivity(intent);
					finish();
				}

			}
		});

		mDiscard.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				CharSequence textName_Group;
				textName_Group = "";
				bundle.putCharSequence("bNameGroup", textName_Group);
				SetAddGroup = "0";
				CharSequence textSetAddGroup;
				textSetAddGroup = "" + SetAddGroup;
				bundle.putCharSequence("bSetAddGroup", textSetAddGroup);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(AddGroup.this, ANDROID_RSS_READER.class);
				startActivity(intent);
				finish();

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {

			break;
		}
		case KeyEvent.KEYCODE_DPAD_RIGHT: {

			break;
		}
		case KeyEvent.KEYCODE_DPAD_UP: {

			// openDialog2();
			break;
		}
		case KeyEvent.KEYCODE_DPAD_DOWN: {

			break;
		}
			/*
			 * case KeyEvent.KEYCODE_DPAD_CENTER: {
			 * 
			 * break; }
			 */
		case KeyEvent.KEYCODE_BACK: {
			Bundle bundle = new Bundle();
			CharSequence textName_Group;
			textName_Group = "";
			bundle.putCharSequence("bNameGroup", textName_Group);
			SetAddGroup = "0";
			CharSequence textSetAddGroup;
			textSetAddGroup = "" + SetAddGroup;
			bundle.putCharSequence("bSetAddGroup", textSetAddGroup);
			Intent intent = new Intent();
			intent.putExtras(bundle);
			intent.setClass(AddGroup.this, ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
		}

		}
		return false;
	}

	private void openDialog2() {
		new AlertDialog.Builder(this).setTitle("Add group error").setIcon(
				R.drawable.dialog_warning).setMessage(
				"This Group is already exist, please try again")
				.setNegativeButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})

				.show();
	}

	private void openDialog3() {
		new AlertDialog.Builder(this).setTitle("Add group error").setIcon(
				R.drawable.dialog_warning).setMessage("Group Name cannot null")
				.setNegativeButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})

				.show();
	}

	public void addGrouptoDB(String groupname) {
		int num = numberOfGroup + 1;
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		values
				.put(BookProviderMetaData.BookTableMetaData.GROUP_NAME,
						groupname);
		values.put(BookProviderMetaData.BookTableMetaData.GROUP_ENABLE, "1");
		values.put(BookProviderMetaData.BookTableMetaData.GROUP_ID, "" + num);
		Uri uri = contentResolver.insert(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values);
		// updateNumberOfGroupinDB();
	}

	/*
	 * public void updateNumberOfGroupinDB() { ContentResolver contentResolver =
	 * getContentResolver(); Cursor cursor =
	 * contentResolver.query(BookProviderMetaData.BookTableMetaData.CONTENT_URI,
	 * GroupenableProj, "groupenable = ?", new String[] { "1"}, null);
	 * numberOfGroup= cursor.getCount(); for ( int i = 0; i <numberOfGroup ; i++
	 * ){2 cursor.moveToPosition(i ); int ColumnGroupenable =
	 * cursor.getColumnIndex(BookProviderMetaData.BookTableMetaData.GROUP_NAME);
	 * String nameGroupenable = cursor.getString(ColumnGroupenable);
	 * GroupListEnablefromDB.add(nameGroupenable); HashSet hs = new HashSet();
	 * hs.addAll(GroupListEnablefromDB); GroupListEnablefromDB.clear();
	 * GroupListEnablefromDB.addAll(hs); }
	 * //getContentResolver().notifyChange(BookProviderMetaData
	 * .BookTableMetaData.CONTENT_URI, null); }
	 */
}
