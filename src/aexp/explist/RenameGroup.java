package aexp.explist;

import java.util.ArrayList;
import java.util.List;

import com.sskk.example.bookprovidertest.provider.NewDBAdapter;

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

public class RenameGroup extends Activity {
	private Button mRename;
	List<String> GroupListEnablefromDB = new ArrayList();
	private EditText mGroupName;
	private String NewNameGroup;
	private String OldNameGroup;
	private String messageNameGroup;
	NewDBAdapter mDB;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.renamegroup);
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();
		mGroupName = (EditText) findViewById(R.id.namegroup);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage = bundle.getCharSequence("messageNameGroup");
		messageNameGroup = "" + textMessage;
		mGroupName.setText(messageNameGroup);
		mGroupName.setOnClickListener(new View.OnClickListener() {
			//@Override
			public void onClick(View v) {
				mGroupName.setText("");
			}

		});
		updateNumberOfGroupinDB();
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
	Cursor c = mDB.getGroupByEnable("1");
	if (c.moveToFirst()) {
	do {
	GroupListEnablefromDB.add(c.getString(1));
	} while (c.moveToNext());
	}
	c.close();
	GroupListEnablefromDB.remove(0);
	}

	public void renameGroup(String GroupOldName, String GroupNewName) {
		mDB.renameGroup(GroupOldName, GroupNewName);
		Toast.makeText(this, "Rename Successfully !", Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent();
		intent.setClass(RenameGroup.this, ANDROID_RSS_READER.class);
		startActivity(intent);
	//	finish();
		// reload();
	}


	public void reload() {
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
}
