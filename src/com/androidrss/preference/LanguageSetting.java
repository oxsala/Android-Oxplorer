package com.androidrss.preference;

import java.util.ArrayList;
import java.util.List;

import aexp.explist.ANDROID_RSS_READER;
import aexp.explist.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sskk.example.bookprovidertest.provider.NewDBAdapter;

public class LanguageSetting extends Activity {

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
		setContentView(R.layout.setlanguage);
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();
		Button buttonSetGermany= (Button) findViewById(R.id.SetGermany);
		Button buttonsetEnglish = (Button) findViewById(R.id.setEnglish);
		backButton = (TextView) findViewById(R.id.albums_activity_btn_back);
		backButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LanguageSetting.this, Setting.class);
				startActivity(intent);
				finish();
				mDB.closeDB();
			}
		});
		
		buttonsetEnglish.setOnClickListener(new OnClickListener() {
		   @Override
			public void onClick(View v) {
			   makeText1();
			   mDB.UpdateLanguageDB("en");
			   Intent intent = new Intent();
				intent
						.setClass(LanguageSetting.this,
								aexp.explist.ANDROID_RSS_READER.class);
				startActivity(intent);
				finish();
				mDB.closeDB();
			}
		});
		
		buttonSetGermany.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDB.UpdateLanguageDB("ge");
				makeText2();
				Intent intent = new Intent();
				intent
						.setClass(LanguageSetting.this,
								aexp.explist.ANDROID_RSS_READER.class);
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
					.setClass(LanguageSetting.this,
							Setting.class);
			startActivity(intent);
			finish();
			mDB.closeDB();
		}

		}
		return false;
	}


 public void makeText1(){
	 Toast.makeText(this, "You've changed Language To English !",
				Toast.LENGTH_SHORT).show();
 }
 
 
 public void makeText2(){
	 Toast.makeText(this, "You've changed Language To Germany !",
				Toast.LENGTH_SHORT).show();
 }
}
