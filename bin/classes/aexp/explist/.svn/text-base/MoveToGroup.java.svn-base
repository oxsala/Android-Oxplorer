package aexp.explist;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.mandant.protobuf.MandantData.MandantCOLLECTION;
import com.mandant.protobuf.MandantData.MandantDefinition;
import com.mandant.protobuf.MandantData.MandantDefinition.WorkBasket;
import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

import fr.marvinlabs.selectablelisttutorial.pojo.Item;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class MoveToGroup extends ExpandableListActivity {

	private static final String GROUP_NAME = "GroupName";
	private static final String WB_NAME = "workbname";
	private static final String CHECKBOX_MOVE = "CheckBox_Move";
	List<List<String>> ListAllListItems = new ArrayList<List<String>>();;
	private ProgressDialog progDailog;
	AlertDialog adSelectWB;
	AlertDialog adSelectMandant;
	AlertDialog adSelectgroup1;
	AlertDialog adSelectGroup;
	boolean[] isCheckedWBListfromServer;
	boolean[] isCheckedGroupListfromDB;
	private final List<String> test = new ArrayList();
	boolean isAllChecked = false;
	boolean isAllCheckedGroup = false;
	boolean isAllCheckedMandant = false;
	CharSequence[] arr_CS_allMandants;
	CharSequence[] arr_CS_allWBs;
	CharSequence[] arr_CS_allGroups;
	private TextView view;
	private TextView group;
	private TextView workbasket;
	private TextView data;
	private int whichbtn;
	private CheckBox cb;
	private String IdGroup;
	List<List<String>> ListAllListMandantFromMandantAdded = new ArrayList<List<String>>();;
	List<String> MandantListEnablefromDB = new ArrayList();
	List<String> MandantListfromServer = new ArrayList();
	List<String> ListWbChecked = new ArrayList();
	boolean[] isCheckedMandantListfromServer;
	private final List<String> MandantListSelectedfromDialog = new ArrayList();
	List<String> WBListfromServer = new ArrayList();
	List<String> WBListEnablefromDB = new ArrayList();
	List<String> GroupListEnablefromDB = new ArrayList();
	List<String> GroupListEnablefromDBInSpinner = new ArrayList();
	List<String> GroupListFilter = new ArrayList();
	List<String> TimeDateListfromServer = new ArrayList();
	List<String> TimeDateListEnablefromDB = new ArrayList();
	String[] ListItem;
	private EditText mGroupName;
	ExpandableListAdapter mAdapter;
	private String selectedGrouptoMove = "No_Group";
	private String selectedGrouptoRemove;
	private static final int CUSTOM_DIALOG = 1;
	private static final int DEFAULT_DIALOG = 2;
	private String newGroup;
	private static final String TAG = "ExpList";
	private int x, y;
	private String itemClicked;
	private String WBUriClicked;
	private String idgrouptoMove;
	private String idgrouptoRemove;
	private int numberOfGroup;
	private int numberOfWBenable;
	private String SetAddGroup;
	private String messagefromClicked;
	private ListView listChecked;
	private List<Item> DataChecked;
	List<String> ListItemChecked = new ArrayList();
	private final int size_group = 20;
	private final int size_workbasket = 18;
	private final int size_data = 16;
	private final int color_group = 0xffffff00;
	private final int color_workbasket = 0xFFFF0000;
	private final int color_data = 0xffffffff;
	String item;
	String val2;
	List<String> IndexGroupExpandable = new ArrayList();
	private final String[] MandantenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.MANDANT_NAME,
			BookProviderMetaData.BookTableMetaData.MANDANT_URI,
			BookProviderMetaData.BookTableMetaData.MANDANT_ENABLE };

	private final String[] WBenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.WB_NAME,
			BookProviderMetaData.BookTableMetaData.WB_URI,
			BookProviderMetaData.BookTableMetaData.WB_EANABLE,
			BookProviderMetaData.BookTableMetaData.TOTAL_COUNT,
			BookProviderMetaData.BookTableMetaData.UNREAD_COUNT,
			BookProviderMetaData.BookTableMetaData.TIME_DATE,
			BookProviderMetaData.BookTableMetaData.INGROUP_ID };
	private final String[] GroupenableProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.GROUP_NAME,
			BookProviderMetaData.BookTableMetaData.GROUP_ENABLE,
			BookProviderMetaData.BookTableMetaData.GROUP_ID,
			BookProviderMetaData.BookTableMetaData.EXPAND };
	private long[] expandedIds;
	private String GroupNameClicked;
	List<MandantDefinition> listMandant;
	List<WorkBasket> listWBs;
	MandantCOLLECTION PBF_mdCollection;
	private int postion;
	private int postion2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_main_move_group);
		updateNumberOfGroupinDB();
		/*
		 * if (numberOfGroup == 0) { newGroup = "No_Group";
		 * addGrouptoDB(newGroup); updateNumberOfGroupinDB();
		 * getListItemForEachGroup(); expandable(); } else {
		 */
		// updateMandantinDB();
		// updateNumberOfGroupinDB();

		// }
		// GroupListEnablefromDBInSpinner.clear();
		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			GroupListFilter.add(GroupListEnablefromDB.get(i));
			GroupListEnablefromDBInSpinner.add(GroupListEnablefromDB.get(i));
		}
		for (int i = 0; i < GroupListEnablefromDBInSpinner.size(); i++) {
			if (GroupListEnablefromDBInSpinner.get(i).equals("no_group")) {
				GroupListEnablefromDBInSpinner.remove(i);
				// postion2 = i;
			}
		}
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item,
				GroupListEnablefromDBInSpinner);
		adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			// postion = pos + 1;

			selectedGrouptoMove = parent.getItemAtPosition(pos).toString();
			for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
				if (GroupListEnablefromDB.get(i).equals(selectedGrouptoMove)) {
					postion = i;
				}
			}
			/*
			 * Toast.makeText(MoveToGroup.this, "You're Selecting Group: " +
			 * postion + selectedGrouptoMove, Toast.LENGTH_SHORT).show();
			 */
			// setTitle("You're Selecting Group: " + selectedGrouptoMove);
			getWBList();
			getListItemForEachGroup();
			expandable();

			GroupListFilter.clear();
			for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
				GroupListFilter.add(GroupListEnablefromDB.get(i));
			}

			/*
			 * if(selectedGrouptoMove.equals("No_Group")){
			 * 
			 * //getWBList(); //getListItemForEachGroup();
			 * GroupListEnablefromDB.remove(0); ListAllListItems.remove(0);
			 * setTitle("You're Selecting Group: " +
			 * GroupListEnablefromDB.get(0)); //expandable(); }
			 */
		}

		public void onNothingSelected(AdapterView parent) {

		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.ExitMove:
			CancelSave();
			break;
		case R.id.ApplyMove:
			ApplyMove();
			break;
		case R.id.OkMove:
			MoveNow();
			break;
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Object o1 = this.getExpandableListAdapter().getGroup(groupPosition);
		Object o2 = this.getExpandableListAdapter().getChild(groupPosition,
				childPosition);
		String GroupClicked = o1.toString();
		String ChildClicked = o2.toString();
		String[] arInfo;
		arInfo = ChildClicked.split(",");
		for (int i = 0; i < arInfo.length; i++) {
			if (arInfo[i].contains("workbname")) {
				item = arInfo[i].substring(11);
			} else {
			}
		}
		itemClicked = item.replace("}", "");
		messagefromClicked = o2.toString();
		return true;

	}

	public class MyExpandableListAdapter extends SimpleExpandableListAdapter {
		private final List<? extends Map<String, ?>> mGroupData;
		private final String[] mGroupFrom;
		private final int[] mGroupTo;
		private final List<? extends List<? extends Map<String, ?>>> mChildData;
		private final String[] mChildFrom;
		private final int[] mChildTo;
		private Activity context;

		public MyExpandableListAdapter(Context context,
				List<? extends Map<String, ?>> groupData, int groupLayout,
				String[] groupFrom, int[] groupTo,
				List<? extends List<? extends Map<String, ?>>> childData,
				int childLayout, String[] childFrom, int[] childTo) {
			super(context, groupData, groupLayout, groupFrom, groupTo,
					childData, childLayout, childFrom, childTo);
			mChildData = childData;
			mChildFrom = childFrom;
			mChildTo = childTo;
			mGroupData = groupData;
			mGroupFrom = groupFrom;
			mGroupTo = groupTo;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			View v;
			if (convertView == null) {
				v = newChildView(isLastChild, parent);
			} else {
				v = convertView;
			}
			bindView(v, mChildData.get(groupPosition).get(childPosition),
					mChildFrom, mChildTo, groupPosition, childPosition);
			return v;
		}

		private void bindView(View view, Map<String, ?> data, String[] from,
				int[] to, int groupPosition, int childPosition) {
			int len = to.length - 1;
			final int IdChild = childPosition;
			final int IdGroup = groupPosition + 1;
			final String Group;
			final String Child;
			final String GroupClicked = mGroupData.get(groupPosition)
					.toString();
			final String ChildClicked = mChildData.get(groupPosition).get(
					childPosition).toString();
			String[] GroupInfo;
			String[] ChildInfo;
			GroupInfo = GroupClicked.split(",");
			ChildInfo = ChildClicked.split(",");
			Group = GroupInfo[0].substring(11).replace("}", "");
			Child = ChildInfo[0].substring(11).replace("}", "");

			for (int i = 0; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null) {
					v.setText((String) data.get(from[i]));
				}
			}

			for (int i = 1; i < len; i++) {
				final CheckBox checkbox_move = (CheckBox) view
						.findViewById(to[i]);
				if (checkbox_move != null && to[i] == R.id.checkbox_move) {
					checkbox_move
							.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {
									if (isChecked) {
										ListWbChecked.add(Child);

									} else {
										ListWbChecked.remove(Child);
									}
								}
							});
				}
			}
		}
	}

	public void expandable() {
		GroupListFilter.remove(postion);
		String[] groups = new String[GroupListFilter.size()];
		for (int n = 0; n < GroupListFilter.size(); n++) {
			groups[n] = GroupListFilter.get(n);
		}
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		for (int j = 0; j < groups.length; ++j) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(GROUP_NAME, groups[j]);
		}

		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		ListAllListItems.remove(postion);
		for (int i = 0; i < 19; ++i) {

			populateDataExpandableListView(childData, ListAllListItems.get(i));
		}
		mAdapter = new MyExpandableListAdapter(this, groupData,
				R.layout.new_group_row_movetogroup,
				new String[] { GROUP_NAME }, new int[] { R.id.groupnamemove },
				childData, R.layout.new_child_row_movetogroup, new String[] {
						WB_NAME, CHECKBOX_MOVE }, new int[] { R.id.childmove,
						R.id.checkbox_move, R.id.r4 });
		setListAdapter(mAdapter);
		registerForContextMenu(getExpandableListView());
		for (int j = 0; j < numberOfGroup; j++) {
			getExpandableListView().expandGroup(j);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case 0:
			break;
		case 2:
			break;
		case 1:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		}
		return false;
	}

	/*
	 * public void moveToGroup() { Intent intent = new Intent();
	 * intent.setClass(MoveToGroup.this,
	 * fr.marvinlabs.selectablelisttutorial.MainActivity.class);
	 * startActivity(intent); finish(); setTitle("Going To Move To Group View");
	 * }
	 */

	public void renameExpandId(String ExpandOldId, String ExpandNewId,
			String GroupName) {
		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.EXPAND, ExpandNewId);
		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"expand = ? AND name = ?", new String[] { ExpandOldId,
						GroupName });
	}

	public void getWBListFromURI() {
		try {
			for (int i = 0; i < PBF_mdCollection.getMandantList().size(); i++) {
				for (int j = 0; j < PBF_mdCollection.getMandant(i)
						.getArrWbsList().size(); j++) {
					if (MandantListEnablefromDB.contains(PBF_mdCollection
							.getMandant(i).getId())) {
						WBListfromServer.add(""
								+ PBF_mdCollection.getMandant(i).getArrWbs(j)
										.getWbName());
					} else {
						view.setText("NO");
					}
				}
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}
	}

	public void getWBList() {
		getWBListFromURI();
	}

	public void addSelectedItems(DialogInterface dialog, int whichButton) {
		updateWBinDB();
		addWBtoDB();
		updateNumberOfGroupinDB();
		getListItemForEachGroup();
		expandable();
	}

	public void addWBtoDB() {
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		for (int j = 0; j < MandantListEnablefromDB.size(); j++) {
			for (int k = 0; k < PBF_mdCollection.getMandant(j).getArrWbsList()
					.size(); k++) {
				int count = PBF_mdCollection.getMandant(j).getArrWbs(k)
						.getArrProsCount();

				values.put(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT,
						"" + count);
			}
		}

		for (int i = 0; i < WBListfromServer.size(); i++) {
			if (WBListEnablefromDB.contains(WBListfromServer.get(i))) {
			} else {
				values.put(BookProviderMetaData.BookTableMetaData.WB_NAME,
						WBListfromServer.get(i));
				values.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID,
						"1");
				values.put(BookProviderMetaData.BookTableMetaData.WB_EANABLE,
						"1");
				for (int j = 0; j < MandantListEnablefromDB.size(); j++) {
					for (int k = 0; k < PBF_mdCollection.getMandant(j)
							.getArrWbsList().size(); k++) {
						int count = PBF_mdCollection.getMandant(j).getArrWbs(k)
								.getArrProsCount();
						values
								.put(
										BookProviderMetaData.BookTableMetaData.TOTAL_COUNT,
										"" + count);
					}
				}

				Uri uri = contentResolver.insert(
						BookProviderMetaData.BookTableMetaData.CONTENT_URI,
						values);
				updateWBinDB();
			}
		}
	}

	public void updateWBinDB() {
		WBListEnablefromDB.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				WBenableProj, "wbenable = ?", new String[] { "1" }, null);
		startManagingCursor(cursor);
		numberOfWBenable = cursor.getCount();
		for (int i = 0; i < numberOfWBenable; i++) {
			cursor.moveToPosition(i);
			int ColumnWBenable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.WB_NAME);
			String nameWBenable = cursor.getString(ColumnWBenable);
			WBListEnablefromDB.add(nameWBenable);

			int ColumnWBUrienable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.WB_URI);
			String nameWBUrienable = cursor.getString(ColumnWBUrienable);
			HashSet hs = new HashSet();
			hs.addAll(WBListEnablefromDB);
			WBListEnablefromDB.clear();
			WBListEnablefromDB.addAll(hs);
		}
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
		updateNumberOfGroupinDB();
	}

	public void updateNumberOfGroupinDB() {
		GroupListEnablefromDB.clear();
		IndexGroupExpandable.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor_Group = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				GroupenableProj, "groupenable = ?", new String[] { "1" }, null);

		startManagingCursor(cursor_Group);
		numberOfGroup = cursor_Group.getCount();
		for (int i = 0; i < numberOfGroup; i++) {
			int num = i + 1;
			Cursor cursor = contentResolver.query(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					GroupenableProj, "groupenable = ? AND groupid = ?",
					new String[] { "1", "" + num }, null);
			startManagingCursor(cursor);
			cursor.moveToFirst();
			int ColumnGroupenable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.GROUP_NAME);
			String nameGroupenable = cursor.getString(ColumnGroupenable);
			GroupListEnablefromDB.add(nameGroupenable);

			int ColumnExpandID = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.EXPAND);
			String nameExpandID = cursor.getString(ColumnExpandID);
			IndexGroupExpandable.add(nameExpandID);
		}
	}

	public void getListItemForEachGroup() {
		ListAllListItems.clear();
		for (int i = 1; i < 21; i++) {
			ContentResolver contentResolver = getContentResolver();
			Cursor cursor = contentResolver.query(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					WBenableProj, "wbenable = ? AND idgroup = ?", new String[] {
							"1", "" + i }, null);
			startManagingCursor(cursor);
			int x = cursor.getCount();
			List<String> ListItemGroup = new ArrayList();
			for (int j = 0; j < x; j++) {
				cursor.moveToPosition(j);
				int ColumnWBenable_InGroupID = cursor
						.getColumnIndex(BookProviderMetaData.BookTableMetaData.WB_NAME);
				String nameWBenable = cursor
						.getString(ColumnWBenable_InGroupID);
				ListItemGroup.add(nameWBenable);
			}
			ListAllListItems.add(ListItemGroup);

		}
	}

	public void updateData() {
		addWBtoDB();
		updateNumberOfGroupinDB();
		getListItemForEachGroup();
	}

	public void moveItemToGroup(String item, String idgroup) {
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID, idgroup);

		int count = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values,
				"wbname = ? AND wbenable = ?", new String[] { item, "1" });
	}

	public void MoveItemDialog() {
		String[] ssGroups = new String[GroupListEnablefromDB.size()];
		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			ssGroups[i] = GroupListEnablefromDB.get(i);
		}
		arr_CS_allGroups = ssGroups;
		adSelectgroup1 = new AlertDialog.Builder(this)
				.setTitle("Move to Group").setIcon(R.drawable.star_big_on)
				.setSingleChoiceItems(arr_CS_allGroups, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								selectedGrouptoMove = arr_CS_allGroups[whichButton]
										.toString();
								if (whichButton == -1) {
									whichbtn = 0;
								} else {
									whichbtn = 1;
								}
							}

						})

				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						setGroup();

					}
				}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}

	public void setGroup() {
		if (whichbtn == 0) {

		} else {

			for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
				if (GroupListEnablefromDB.get(i).equals(selectedGrouptoMove)) {
					int num = i + 1;
					idgrouptoMove = "" + num;
				}
			}
			showProcessBar();
			moveItemToGroup(itemClicked, idgrouptoMove);
			updateNumberOfGroupinDB();
			getListItemForEachGroup();
			expandable();
		}
	}

	public void showProcessBar() {
		progDailog = ProgressDialog.show(MoveToGroup.this,
				"Processing data...", "Refreshesing, please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					sleep(1500);
				} catch (Exception e) {
				}
				handler.sendEmptyMessage(0);
				progDailog.dismiss();
			}
		}.start();
	}

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
		}
	};

	public void getDataProtobuf() {
		try {
			InputStream is = new URL(
					"http://174.143.148.49/PBFDemo/getDataCOLLECTION.jsp")
					.openStream();
			PBF_mdCollection = MandantCOLLECTION.parseFrom(is);
			listMandant = PBF_mdCollection.getMandantList();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void populateDataExpandableListView(
			List<List<Map<String, String>>> childData,
			List<String> ListItemWbName) {
		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
		children = new ArrayList<Map<String, String>>();
		for (int j = 0; j < ListItemWbName.size(); ++j) {
			Map<String, String> curChildMap = new HashMap<String, String>();
			children.add(curChildMap);
			curChildMap.put(WB_NAME, ListItemWbName.get(j));
		}
		childData.add(children);
	}

	public void moveItemToGroup(List<String> ListItem, String idgroup) {
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID, idgroup);

		for (int i = 0; i < ListItem.size(); i++) {
			int count = contentResolver.update(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI, values,
					"wbname = ? AND wbenable = ?", new String[] {
							ListItem.get(i), "1" });
		}
		Toast.makeText(this,
				"Moved WB Selected To Group: " + selectedGrouptoMove,
				Toast.LENGTH_SHORT).show();
	}

	public void MoveNow() {

		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			if (GroupListEnablefromDB.get(i).equals(selectedGrouptoMove)) {
				int num = i + 1;
				idgrouptoMove = "" + num;
			}
		}
		moveItemToGroup(ListWbChecked, idgrouptoMove);
		Intent intent = new Intent();
		intent.setClass(MoveToGroup.this, ANDROID_RSS_READER.class);
		startActivity(intent);
		finish();
	}

	public void ApplyMove() {
		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			if (GroupListEnablefromDB.get(i).equals(selectedGrouptoMove)) {
				int num = i + 1;
				idgrouptoMove = "" + num;
			}
		}
		moveItemToGroup(ListWbChecked, idgrouptoMove);
		getListItemForEachGroup();
		expandable();
		ListWbChecked.clear();
		GroupListFilter.clear();
		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			GroupListFilter.add(GroupListEnablefromDB.get(i));
		}
	}

	private void CancelSave() {
		Intent intent = new Intent();
		intent.setClass(MoveToGroup.this, ANDROID_RSS_READER.class);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			intent.setClass(MoveToGroup.this, ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
		}
		}
		return true;
	}
}
