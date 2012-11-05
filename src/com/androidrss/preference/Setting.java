package com.androidrss.preference;

import java.util.ArrayList;
import java.util.List;

import aexp.explist.ANDROID_RSS_READER;
import aexp.explist.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sskk.example.bookprovidertest.provider.NewDBAdapter;

public class Setting extends Activity {

	boolean isAllCheckedMandant_Remove = false;
	CharSequence[] arr_CS_allMandants_Remove;
	List<String> MandantListEnablefromDB_Remove = new ArrayList();
	AlertDialog adSelectMandant_Remove;
	boolean[] isCheckedMandantListfromDB_Remove;
	private final List<String> WBListRemovedfromMandantSelected_Remove = new ArrayList();
	private final List<String> MandantListSelectedfromDialog_Remove = new ArrayList();
	private TextView backButton;
	NewDBAdapter mDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();
		backButton = (TextView) findViewById(R.id.albums_activity_btn_back);
		Button buttonDeleteAll = (Button) findViewById(R.id.deleteAll);
		Button buttonSetColor = (Button) findViewById(R.id.settingColor);
		Button buttonSetLanguage = (Button) findViewById(R.id.languagesetting);
		buttonDeleteAll.setOnClickListener(new Button.OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				openDialog2();
			}
		});
		
		buttonSetColor.setOnClickListener(new Button.OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Setting.this, SetPreference.class));
				finish();
				mDB.closeDB();
			}
		});

		
		buttonSetLanguage.setOnClickListener(new Button.OnClickListener() {

			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Setting.this, LanguageSetting.class));
				finish();
				mDB.closeDB();
			}
		});
		
		backButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Setting.this, ANDROID_RSS_READER.class);
				startActivity(intent);
				finish();
				mDB.closeDB();
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			intent
					.setClass(Setting.this,
							aexp.explist.ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
			mDB.closeDB();
		}

		}
		return false;
	}

	private void openDialog2() {
		new AlertDialog.Builder(this).setTitle("Warning").setIcon(
				R.drawable.dialog_warning).setMessage(
				"Are you sure to Reset Data ?").setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						mDB.DropDatabase(getApplicationContext());
						mDB.closeDB();
						makeText();
						Intent intent = new Intent();
						intent
								.setClass(Setting.this,
										aexp.explist.ANDROID_RSS_READER.class);
						startActivity(intent);
						finish();
						mDB.closeDB();
						//addGrouptoDB("no_group");
					}
				}).setNegativeButton("No",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
					}
				})

		.show();
	}
 public void makeText(){
	 Toast.makeText(this, "Reset Data Successfully !",
				Toast.LENGTH_SHORT).show();
 }

}
