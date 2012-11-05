package com.androidrss.preference;

import aexp.explist.R;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;

public class SetPreference extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.mypreference);
	/*	Button buttonSavePreference = (Button)findViewById(R.id.savepreference);
		  buttonSavePreference.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					startActivity(new Intent(SetPreference.this, AndroidPreference.class));
				}});*/
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

		//openDialog2();
	break;
	}
	case KeyEvent.KEYCODE_DPAD_DOWN: {

	break;
	}
	case KeyEvent.KEYCODE_DPAD_CENTER: {
    
	break;
	}
	case KeyEvent.KEYCODE_BACK: {
		//finish();
		Intent intent = new Intent();
    	intent.setClass(SetPreference.this,Setting.class);
        startActivity(intent);
        finish();
	}
	
	}
	return false;
	}
	}


