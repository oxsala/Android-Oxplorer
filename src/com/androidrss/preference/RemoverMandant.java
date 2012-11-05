package com.androidrss.preference;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import protobuf.data.wbList.WbListData;
import protobuf.data.wbList.WbListData.Wb;
import aexp.explist.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.sskk.example.bookprovidertest.provider.NewDBAdapter;

//import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

public class RemoverMandant extends Activity {
	boolean isAllCheckedMandant_Remove = false;
	CharSequence[] arr_CS_allMandants_Remove;
	List<String> MandantListEnablefromDB_Remove = new ArrayList();
	AlertDialog adSelectMandant_Remove;
	boolean[] isCheckedMandantListfromDB_Remove;
	private final List<String> WBListRemovedfromMandantSelected_Remove = new ArrayList();
	private final List<String> MandantListSelectedfromDialog_Remove = new ArrayList();
	NewDBAdapter mDB;
	List<Wb> listWb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remove_mandant);
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();

		updateMandantinDB_Remove();
		prepareDialog_Remove();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {

		case KeyEvent.KEYCODE_BACK: {
			// finish();
			Intent intent = new Intent();
			intent.setClass(RemoverMandant.this,
					aexp.explist.ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
			mDB.closeDB();
		}

		}
		return false;
	}



	public void openDialogWithMandants_Remove() {

		adSelectMandant_Remove = new AlertDialog.Builder(this)
				.setTitle("Select Mandant to Remove :")
				.setIcon(R.drawable.star_big_on)
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
						})
				.setNeutralButton("Remove",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								
								removeSelectedMandants_Remove();
							}
						})
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								Intent intent = new Intent();
								intent.setClass(RemoverMandant.this,
										aexp.explist.ANDROID_RSS_READER.class);
								startActivity(intent);
								finish();
								mDB.closeDB();
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

	public void removeSelectedMandants_Remove() {
		// updateMandantinDB();
		for (int i = 0; i < MandantListSelectedfromDialog_Remove.size(); i++) {
			getWbDataProtobuf_Remove(MandantListSelectedfromDialog_Remove
					.get(i));
		}
		removeMandant_Remove();
		mDB.closeDB();
		//MandantListSelectedfromDialog_Remove.clear();
		// MandantListUriSelectedfromDialog.clear();
		Toast.makeText(this, "Remove Mandant Successfully !",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		intent.setClass(RemoverMandant.this,
				aexp.explist.ANDROID_RSS_READER.class);
		startActivity(intent);
		finish();
		mDB.closeDB();
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
		Cursor c = mDB.getMandantByAdded("1");
		if (c.moveToFirst()) {
			do {
				MandantListEnablefromDB_Remove.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();

	}

	public void getWbDataProtobuf_Remove(String Mandant) {
		try {
			InputStream is = new URL("https://my-ea.oxseed.net/" + Mandant
					+ "/ext/oxplorer?content=wbList&login=admin&pass=oxseed")
					.openStream();
			WbListData.WbList data = WbListData.WbList.parseFrom(is);
			listWb = data.getWbList();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (Wb wbLoop : listWb) {
				WBListRemovedfromMandantSelected_Remove
				.add("[" + Mandant + "]" + "_" + wbLoop.getId());
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}

	}

	public void removeMandant_Remove() {

		for (int i = 0; i < MandantListSelectedfromDialog_Remove.size(); i++) {
			mDB.deleteMandant(MandantListSelectedfromDialog_Remove.get(i));
		}
		for (int j = 0; j < WBListRemovedfromMandantSelected_Remove.size(); j++) {
			mDB.deleteWorkBasket(WBListRemovedfromMandantSelected_Remove.get(j));
		}
	}

}
