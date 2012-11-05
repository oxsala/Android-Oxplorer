package com.androidrss.preference;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import aexp.explist.ANDROID_RSS_READER;
import aexp.explist.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mandant.protobuf.MandantData.MandantCOLLECTION;
import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class Setting extends Activity {
	private final String[] GroupenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.GROUP_NAME,
			BookProviderMetaData.BookTableMetaData.GROUP_ENABLE,
			BookProviderMetaData.BookTableMetaData.GROUP_ID };

	boolean isAllCheckedMandant_Remove = false;
	CharSequence[] arr_CS_allMandants_Remove;
	List<String> MandantListEnablefromDB_Remove = new ArrayList();
	AlertDialog adSelectMandant_Remove;
	boolean[] isCheckedMandantListfromDB_Remove;
	private final List<String> WBListRemovedfromMandantSelected_Remove = new ArrayList();
	private final List<String> MandantListSelectedfromDialog_Remove = new ArrayList();
	private TextView backButton;
	private final String[] MandantenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.MANDANT_NAME,
			BookProviderMetaData.BookTableMetaData.MANDANT_URI,
			BookProviderMetaData.BookTableMetaData.MANDANT_ENABLE };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		updateMandantinDB_Remove();
		backButton = (TextView) findViewById(R.id.albums_activity_btn_back);
		Button buttonDeleteAll = (Button) findViewById(R.id.deleteAll);
		buttonDeleteAll.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openDialog2();
			}
		});

		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Setting.this, ANDROID_RSS_READER.class);
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
		case KeyEvent.KEYCODE_DPAD_CENTER: {

			break;
		}
		case KeyEvent.KEYCODE_BACK: {
			// finish();
			Intent intent = new Intent();
			intent
					.setClass(Setting.this,
							aexp.explist.ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
		}

		}
		return false;
	}

	private void openDialog2() {
		new AlertDialog.Builder(this).setTitle("Warning").setIcon(
				R.drawable.dialog_warning).setMessage(
				"Are you sure to delete everything ?").setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {

						deleteAll();
						addGrouptoDB("no_group");
					}
				}).setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
					}
				})

		.show();
	}

	public void deleteAll() {

		ContentResolver contentResolver = getContentResolver();
		contentResolver.delete(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, null, null);
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, null, null,
				null, null);
		Toast.makeText(this, "Deleted Successfully !", Toast.LENGTH_SHORT)
				.show();
	}

	public void addGrouptoDB(String groupname) {
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				GroupenableProj, "groupenable = ?", new String[] { "1" }, null);
		int numberOfGroup = cursor.getCount();

		int num = numberOfGroup + 1;
		ContentValues values = new ContentValues();
		values
				.put(BookProviderMetaData.BookTableMetaData.GROUP_NAME,
						groupname);
		values.put(BookProviderMetaData.BookTableMetaData.GROUP_ENABLE, "1");
		values.put(BookProviderMetaData.BookTableMetaData.GROUP_ID, "" + num);
		Uri uri = contentResolver.insert(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values);
		// updateNumberOfGroupinDB();
		// getContentResolver().notifyChange(BookProviderMetaData.BookTableMetaData.CONTENT_URI,
		// null);
	}

	public void openDialogWithMandants_Remove() {

		adSelectMandant_Remove = new AlertDialog.Builder(this).setTitle(
				"Select Mandant to Remove :").setIcon(R.drawable.star_big_on)
				.setMultiChoiceItems(arr_CS_allMandants_Remove,
						isCheckedMandantListfromDB_Remove,
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {
								// THEM Code:

								if (isCheckedMandantListfromDB_Remove[whichButton] = true) {
									MandantListSelectedfromDialog_Remove
											.add(arr_CS_allMandants_Remove[whichButton]
													.toString());
									// MandantListUriSelectedfromDialog.add(MandantListUriEnablefromDB.get(whichButton));
								} else {
									isCheckedMandantListfromDB_Remove[whichButton] = isChecked;
								}
							}
						})

				.setPositiveButton(R.string.check_all,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// THEM COde cho Check All

								if (isAllCheckedMandant_Remove) {
									isAllCheckedMandant_Remove = false;
								} else {
									isAllCheckedMandant_Remove = true;
								}

								setCheckAllMandant_Remove();
							}
						}).setNeutralButton("Remove",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								removeSelectedMandants_Remove(dialog,
										whichButton);
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent = new Intent();
								intent.setClass(Setting.this,
										aexp.explist.ANDROID_RSS_READER.class);
								startActivity(intent);
								finish();
							}
						}).show();
	}

	public void setCheckAllMandant_Remove() {
		if (isAllCheckedMandant_Remove) {
			for (int i = 0; i < MandantListEnablefromDB_Remove.size(); i++) {
				isCheckedMandantListfromDB_Remove[i] = true;
				MandantListSelectedfromDialog_Remove
						.add(MandantListEnablefromDB_Remove.get(i));
				// MandantListUriSelectedfromDialog.add(MandantListUriEnablefromDB.get(i));
			}
		} else {
			for (int i = 0; i < MandantListEnablefromDB_Remove.size(); i++) {
				isCheckedMandantListfromDB_Remove[i] = false;
			}
		}
		openDialogWithMandants_Remove();
	}

	public void removeSelectedMandants_Remove(DialogInterface dialog,
			int whichButton) {
		// updateMandantinDB();
		getWBListFromURI_Remove();
		removeMandant_Remove();
		MandantListSelectedfromDialog_Remove.clear();
		// MandantListUriSelectedfromDialog.clear();
		Toast.makeText(this, "Remove Mandant Successfully !",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.setClass(Setting.this, aexp.explist.ANDROID_RSS_READER.class);
		startActivity(intent);
		finish();
	}

	public void prepareDialog_Remove() {
		String[] ssMandants = new String[MandantListEnablefromDB_Remove.size()];
		isCheckedMandantListfromDB_Remove = new boolean[MandantListEnablefromDB_Remove
				.size()];

		for (int i = 0; i < MandantListEnablefromDB_Remove.size(); i++) {
			ssMandants[i] = MandantListEnablefromDB_Remove.get(i);
			isCheckedMandantListfromDB_Remove[i] = false;
		}
		arr_CS_allMandants_Remove = ssMandants;
		openDialogWithMandants_Remove();
	}

	public void updateMandantinDB_Remove() {
		MandantListEnablefromDB_Remove.clear();
		// MandantListUriEnablefromDB.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				MandantenableProj, "mandantenable = ?", new String[] { "1" },
				null);
		int numberOfMandantenable = cursor.getCount();
		for (int i = 0; i < numberOfMandantenable; i++) {
			cursor.moveToPosition(i);
			int ColumnMandantenable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.MANDANT_NAME);
			int ColumnMandantUrienable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.MANDANT_URI);
			String nameMandantenable = cursor.getString(ColumnMandantenable);
			String nameMandanUritenable = cursor
					.getString(ColumnMandantUrienable);
			MandantListEnablefromDB_Remove.add(nameMandantenable);
			// MandantListUriEnablefromDB.add(nameMandanUritenable);
		}
	}

	public void getWBListFromURI_Remove() {

		try {
			InputStream is = new URL(
					"http://174.143.148.49/PBFDemo/getDataCOLLECTION.jsp")
					.openStream();
			MandantCOLLECTION PBF_mdCollection = MandantCOLLECTION
					.parseFrom(is);
			for (int i = 0; i < PBF_mdCollection.getMandantList().size(); i++) {
				for (int j = 0; j < MandantListSelectedfromDialog_Remove.size(); j++) {
					for (int k = 0; k < PBF_mdCollection.getMandant(k)
							.getArrWbsList().size(); k++) {
						if (PBF_mdCollection.getMandant(i).getId().equals(
								MandantListSelectedfromDialog_Remove.get(j))) {
							WBListRemovedfromMandantSelected_Remove.add(""
									+ PBF_mdCollection.getMandant(i).getArrWbs(
											k).getWbName());
							// setTitle(""+WBListRemovedfromMandantSelected_Remove.size());
						} else {

						}
					}
				}
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}
	}

	public void removeMandant_Remove() {

		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		ContentValues values2 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.MANDANT_ENABLE, "0");
		values2.put(BookProviderMetaData.BookTableMetaData.WB_EANABLE, "0");
		for (int i = 0; i < MandantListSelectedfromDialog_Remove.size(); i++) {
			// values2.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID,"0");
			int count1 = contentResolver.update(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					values1, "mandantname = ? AND mandantenable = ?",
					new String[] { MandantListSelectedfromDialog_Remove.get(i),
							"1" });
		}
		for (int j = 0; j < WBListRemovedfromMandantSelected_Remove.size(); j++) {
			int count2 = contentResolver
					.update(BookProviderMetaData.BookTableMetaData.CONTENT_URI,
							values2, "wbname = ? AND wbenable = ?",
							new String[] {
									WBListRemovedfromMandantSelected_Remove
											.get(j), "1" });
		}

	}

}
