package aexp.explist;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.androidrss.preference.RemoverMandant;
import com.mandant.protobuf.MandantData.MandantCOLLECTION;
import com.mandant.protobuf.MandantData.MandantDefinition;
import com.mandant.protobuf.MandantData.MandantDefinition.WorkBasket;
import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;
import com.test.androidtest.QuickAction;

import fr.marvinlabs.selectablelisttutorial.pojo.Item;

public class ANDROID_RSS_READER extends ExpandableListActivity implements
		Runnable {
	private int idgroupClicked;
	private int id2groupClicked;
	private static final String GROUP_NAME = "GroupName";
	private static final String GROUP_DELETEBUTTON = "GROUP_DELETEBUTTON";
	private static final String GROUP_EDITBUTTON = "GROUP_EDITBUTTON";
	private static final String WB_NAME = "workbname";
	private static final String TIME_TEMP = "ItemName2";
	private static final String COUNT_TEMP = "ItemName3";
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
	List<List<String>> ListAllListItems = new ArrayList<List<String>>();;
	List<List<String>> ListAllListTimeDate = new ArrayList<List<String>>();;
	List<List<String>> ListAllListCount = new ArrayList<List<String>>();;
	List<List<String>> ListAllListMandantFromMandantAdded = new ArrayList<List<String>>();;
	List<String> MandantListEnablefromDB = new ArrayList();
	List<String> MandantListfromServer = new ArrayList();
	boolean[] isCheckedMandantListfromServer;
	private final List<String> MandantListSelectedfromDialog = new ArrayList();
	List<String> WBListfromServer = new ArrayList();
	List<String> WBListEnablefromDB = new ArrayList();
	List<String> GroupListEnablefromDB = new ArrayList();
	List<String> TimeDateListfromServer = new ArrayList();
	List<String> TimeDateListEnablefromDB = new ArrayList();
	String[] ListItem;
	private EditText mGroupName;
	ExpandableListAdapter mAdapter;
	private String selectedGrouptoMove;
	private String selectedGrouptoRemove;
	private static final int CUSTOM_DIALOG = 1;
	private static final int DEFAULT_DIALOG = 2;
	private String newGroup;
	private static final String TAG = "ExpList";
	private int x, y;
	private String itemClicked;
	private String WBUriClicked;
	private String idgrouptoMove;
	private int idgrouptoRemove;
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
	// MandantData.MandantCOLLECTION portfolio1;
	List<MandantDefinition> listMandant;
	List<WorkBasket> listWBs;
	MandantCOLLECTION PBF_mdCollection;
	QuickAction mQuickAction;
	private static final int ID_ADD_MANDANT = 1;
	private static final int ID_REMOVE_MANDANT = 2;
	boolean[] isCheckedMandantListfromDB;

	boolean isAllCheckedMandant_Remove = false;
	CharSequence[] arr_CS_allMandants_Remove;
	List<String> MandantListEnablefromDB_Remove = new ArrayList();
	AlertDialog adSelectMandant_Remove;
	boolean[] isCheckedMandantListfromDB_Remove;
	private final List<String> WBListRemovedfromMandantSelected_Remove = new ArrayList();
	private final List<String> MandantListSelectedfromDialog_Remove = new ArrayList();
	private long differenceSeconds;
	private long differenceSecondsMinutes;
	private long differenceSecondsHours;
	private long differenceSecondsDays;
	private long differenceSecondsMonths;
	private long differenceSecondsYears;
	private String timerLoop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		view = (TextView) findViewById(R.id.tv);
		mGroupName = (EditText) findViewById(R.id.namegroup);
		/*
		 * DataProtobuf DataPbf = new DataProtobuf();
		 * DataPbf.getDataProtobuf(PBF_mdCollection,listMandant);
		 */
		getDataProtobuf();

		getMandantListFromServer();
		updateNumberOfGroupinDB();
		if (numberOfGroup == 0) {
			newGroup = "No_Group";
			addGrouptoDB(newGroup);
			getListItemForEachGroup();
			expandable();
		} else {
			updateMandantinDB();
			getWBList();
			getListItemForEachGroup();
			expandable();
		}

	}

	/*
	 * private void startReadRss() { new RssLoadingTask().execute(); }
	 */

	/*
	 * private void readRss() { getMandantListFromServer();
	 * updateNumberOfGroupinDB(); if (numberOfGroup == 0) { newGroup =
	 * "no_group"; addGrouptoDB(newGroup); updateNumberOfGroupinDB();
	 * getListItemForEachGroup(); expandable(); } else { updateMandantinDB();
	 * getWBList(); getListItemForEachGroup(); expandable(); } }
	 */

	@Override
	protected void onStart() {
		super.onStart();
		if (this.expandedIds != null) {
			restoreExpandedState(expandedIds);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (this.expandedIds != null) {
			restoreExpandedState(expandedIds);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		expandedIds = getExpandedIds();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		this.expandedIds = getExpandedIds();
		outState.putLongArray("ExpandedIds", this.expandedIds);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		super.onRestoreInstanceState(state);
		long[] expandedIds = state.getLongArray("ExpandedIds");
		if (expandedIds != null) {
			restoreExpandedState(expandedIds);
		}
	}

	private long[] getExpandedIds() {
		ExpandableListView list = getExpandableListView();
		ExpandableListAdapter adapter = getExpandableListAdapter();
		if (adapter != null) {
			int length = adapter.getGroupCount();
			ArrayList<Long> expandedIds = new ArrayList<Long>();
			for (int i = 0; i < length; i++) {
				if (list.isGroupExpanded(i)) {
					expandedIds.add(adapter.getGroupId(i));
				}
			}
			return toLongArray(expandedIds);
		} else {
			return null;
		}
	}

	public void restoreExpandedState(long[] expandedIds) {
		this.expandedIds = expandedIds;
		if (expandedIds != null) {
			ExpandableListView list = getExpandableListView();
			ExpandableListAdapter adapter = getExpandableListAdapter();
			if (adapter != null) {
				for (int i = 0; i < adapter.getGroupCount(); i++) {
					long id = adapter.getGroupId(i);
					if (inArray(expandedIds, id)) {
						list.expandGroup(i);
					}
				}
			}
		}
	}

	private static boolean inArray(long[] array, long element) {
		for (long l : array) {
			if (l == element) {
				return true;
			}
		}
		return false;
	}

	private static long[] toLongArray(List<Long> list) {
		long[] ret = new long[list.size()];
		int i = 0;
		for (Long e : list) {
			ret[i++] = e.longValue();
		}
		return ret;
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
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		CharSequence textMessage;
		textMessage = "" + itemClicked;
		bundle.putCharSequence("messageLinkWB", textMessage);
		intent.putExtras(bundle);
		intent.setClass(ANDROID_RSS_READER.this, ShowListProcessWithDocs.class);
		startActivity(intent);
		finish();
		setTitle("GOING TO LIST PROCESS FOR WB : " + itemClicked);

		return true;

	}

	public void makeText() {
		Toast.makeText(ANDROID_RSS_READER.this, "" + idgroupClicked,
				Toast.LENGTH_LONG).show();
	}

	public void getWBUriClicked(String itemclicked) {
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				WBenableProj, "wbenable = ? AND wbname = ?", new String[] {
						"1", itemclicked }, null);
		startManagingCursor(cursor);
		int z = cursor.getCount();
		for (int i = 0; i < z; i++) {
			cursor.moveToPosition(i);
			int ColumnWBUrienable = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.WB_URI);
			String nameWBUrienableClicked = cursor.getString(ColumnWBUrienable);
			WBUriClicked = nameWBUrienableClicked;
		}
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
			for (int i = 0; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null) {
					if (to[i] == R.id.r3) {
						String UnreadCountString = ((String) data.get(from[i]))
								.substring(0, 2).replace("[", "");
						if (UnreadCountString.equals("0")) {
							v.setText((String) data.get(from[i]));
						} else {
							v.setTextColor(0xFFFF0000);
							v.setText(Html
									.fromHtml("<big><b>"
											+ (String) data.get(from[i])
											+ "</b></big>"));
						}
					} else {
						v.setText((String) data.get(from[i]));
					}
				}

			}
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View parentView = convertView;
			if (parentView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				parentView = vi.inflate(R.layout.group_row, null);
			}
			bindGroupView(parentView, mGroupData.get(groupPosition),
					mGroupFrom, mGroupTo, groupPosition);
			return parentView;
		}

		private void bindGroupView(View view, Map<String, ?> Groupdata,
				String[] from, int[] to, int groupPosition) {
			int len = to.length;
			for (int i = 0; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null && to[i] == R.id.groupname) {
					v.setText((String) Groupdata.get(from[i]));
				}
			}
			for (int i = 1; i < len; i++) {

				final int t = groupPosition;
				final int x = t + 1;
				IdGroup = "" + x;
				Button Button_group = (Button) view.findViewById(to[i]);

				if (Button_group != null && to[i] == R.id.delete_group) {
					if (groupPosition == 0) {
						Button_group.setBackgroundColor(0xdedede);

					}
					Button_group.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							String GroupClicked = mGroupData.get(t).toString();
							// setTitle(""+mGroupData.get(t).toString().substring(50));
							String[] arInfo;
							arInfo = GroupClicked.split(",");
							for (int i = 0; i < arInfo.length; i++) {
								if (arInfo[i].contains("GroupName")) {
									item = arInfo[i].substring(11);
								} else {

								}
							}
							itemClicked = item.replace("}", "");
							if (itemClicked.equals("No_Group")) {
								openDialog1();
							} else {
								openDialogRemoveGroup();

							}

						}
					});

				} else if (Button_group != null && to[i] == R.id.edit_group) {
					Button_group.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							String GroupClicked = mGroupData.get(t).toString();
							// setTitle(""+mGroupData.get(t).toString().substring(50));
							String[] arInfo;
							arInfo = GroupClicked.split(",");
							for (int i = 0; i < arInfo.length; i++) {
								if (arInfo[i].contains("GroupName")) {
									item = arInfo[i].substring(11);
								} else {

								}
							}
							itemClicked = item.replace("}", "");
							if (itemClicked.equals("no_group")) {
								openDialog2();
							} else {
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								CharSequence textMessage;
								textMessage = "" + itemClicked;
								bundle.putCharSequence("messageNameGroup",
										textMessage);
								intent.putExtras(bundle);
								intent.setClass(ANDROID_RSS_READER.this,
										RenameGroup.class);
								startActivity(intent);
								finish();
							}
						}
					});
				} else if (Button_group != null && to[i] == R.id.sort_group) {
					Button_group.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							String GroupClicked = mGroupData.get(t).toString();
							String[] arInfo;
							arInfo = GroupClicked.split(",");
							for (int i = 0; i < arInfo.length; i++) {
								if (arInfo[i].contains("GroupName")) {
									item = arInfo[i].substring(11);
								} else {

								}
							}
							itemClicked = item.replace("}", "");
							for (int j = 0; j < GroupListEnablefromDB.size(); j++) {
								if (GroupListEnablefromDB.get(j).equals(
										itemClicked)) {
									int num = j + 1;

									idgroupClicked = num;
									id2groupClicked = num + 1;
								}
							}
							if (idgroupClicked == numberOfGroup) {
							} else {
								changeIDGroup("" + idgroupClicked, "100");
								changeIDGroup("" + id2groupClicked, ""
										+ idgroupClicked);
								changeIDGroup("100", "" + id2groupClicked);
								updateNumberOfGroupinDB();
								changeIDWB("" + idgroupClicked, "100");
								changeIDWB("" + id2groupClicked, ""
										+ idgroupClicked);
								changeIDWB("100", "" + id2groupClicked);
								getListItemForEachGroup();
								showProcessBar();
								expandable();
							}

						}
					});
				}
			}

		}
	}

	public void expandable() {
		if (numberOfGroup == 0) {
			view.setText("There is no group");
		} else {
			view.setText("There are " + numberOfGroup + " Groups");
		}

		String[] groups = new String[numberOfGroup];
		for (int i = 0; i < numberOfGroup; i++) {
			groups[i] = GroupListEnablefromDB.get(i);
		}
		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		for (int j = 0; j < groups.length; ++j) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(GROUP_NAME, groups[j]);
			curGroupMap.put(GROUP_DELETEBUTTON, "");
			curGroupMap.put(GROUP_EDITBUTTON, "");
		}

		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < 20; i++) {
			populateDataExpandableListView(childData, ListAllListItems.get(i),
					ListAllListCount.get(i), ListAllListTimeDate.get(i));
		}
		mAdapter = new MyExpandableListAdapter(this, groupData,
				R.layout.group_row, new String[] { GROUP_NAME,
						GROUP_DELETEBUTTON, GROUP_EDITBUTTON }, new int[] {
						R.id.groupname, R.id.sort_group, R.id.delete_group,
						R.id.edit_group }, childData,
				R.layout.child_row_screen1, new String[] { WB_NAME, TIME_TEMP,
						COUNT_TEMP }, new int[] { R.id.child, R.id.r2, R.id.r3,
						R.id.r4 });
		setListAdapter(mAdapter);
		registerForContextMenu(getExpandableListView());
		getExpandableListView().setOnGroupClickListener(
				new OnGroupClickListener() {
					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, final int groupPosition, long id) {
						int NumExpandId = groupPosition + 1;
						String GroupClicked = getExpandableListView()
								.getExpandableListAdapter().getGroup(
										groupPosition).toString();
						String[] arInfo;
						arInfo = GroupClicked.split(",");
						for (int i = 0; i < arInfo.length; i++) {
							if (arInfo[i].contains("GroupName")) {
								item = arInfo[i].substring(11);
							} else {

							}
						}
						GroupNameClicked = item.replace("}", "");
						if (IndexGroupExpandable.get(groupPosition).equals("1")) {
							renameExpandId("1", "0", GroupNameClicked);
							updateNumberOfGroupinDB();
						} else if (IndexGroupExpandable.get(groupPosition)
								.equals("0")) {
							renameExpandId("0", "1", GroupNameClicked);
							updateNumberOfGroupinDB();
						}
						setTitle(GroupNameClicked);
						return false;
					}
				});
		for (int j = 0; j < numberOfGroup; j++) {

			if (IndexGroupExpandable.get(j).equals("0")) {
				getExpandableListView().collapseGroup(j);
			} else {
				getExpandableListView().expandGroup(j);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Create Group").setIcon(R.drawable.add_group);
		menu.add(0, 3, 0, "Add Mandant").setIcon(R.drawable.add_mandant);
		menu.add(0, 6, 0, "Remove Mandant").setIcon(R.drawable.recycle_icon);
		menu.add(1, 4, 1, "Refresh").setIcon(R.drawable.refresh);
		menu.add(1, 5, 1, "Move To Group").setIcon(R.drawable.move);
		menu.add(1, 1, 1, "More").setIcon(R.drawable.setting);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			addgroup();
			break;
		case 2:
			RemoveGroupDialog();
			break;
		case 1:
			startActivity(new Intent(ANDROID_RSS_READER.this,
					com.androidrss.preference.Setting.class));
			finish();

			break;

		case 3:
			updateMandantinDB();
			MandantListfromServer.removeAll(MandantListEnablefromDB);
			String[] ssMandants = new String[MandantListfromServer.size()];
			isCheckedMandantListfromServer = new boolean[MandantListfromServer
					.size()];

			for (int i = 0; i < MandantListfromServer.size(); i++) {
				ssMandants[i] = MandantListfromServer.get(i);
				isCheckedMandantListfromServer[i] = false;
				MandantListfromServer.removeAll(MandantListEnablefromDB);

			}
			arr_CS_allMandants = ssMandants;
			openDialogWithMandants();

			// populateQuickActionDialog();
			// mQuickAction.show(v);
			break;
		case 4:
			showProcessBar();
			break;
		case 5:
			moveToGroup();
			break;
		case 6:
			Intent intent = new Intent();
			intent.setClass(ANDROID_RSS_READER.this, RemoverMandant.class);
			startActivity(intent);
			finish();
			break;
		}
		return false;
	}

	public void addgroup() {
		int x = GroupListEnablefromDB.size();

		Intent intent = new Intent();
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < x; i++) {
			al.add(GroupListEnablefromDB.get(i));
		}
		setTitle("" + x);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("temp1", al);
		intent.putExtras(bundle);
		intent.setClass(ANDROID_RSS_READER.this, AddGroup.class);
		startActivity(intent);
		finish();
		setTitle("Going To Add Group View");
	}

	public void moveToGroup() {
		Intent intent = new Intent();
		intent.setClass(ANDROID_RSS_READER.this, MoveToGroup.class);
		startActivity(intent);
		finish();
		setTitle("Going To Move To Group View");
	}

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

	public void openDialogWithMandants() {

		adSelectMandant = new AlertDialog.Builder(this).setTitle(
				"Select Mandant to Add :").setIcon(R.drawable.star_big_on)
				.setMultiChoiceItems(arr_CS_allMandants,
						isCheckedMandantListfromServer,
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {
								// THEM Code:

								if (isCheckedMandantListfromServer[whichButton] = true) {
									MandantListSelectedfromDialog
											.add(arr_CS_allMandants[whichButton]
													.toString());
									// MandantListUriSelectedfromDialog.add(MandantListUrifromServer.get(whichButton));
								} else {
									isCheckedMandantListfromServer[whichButton] = isChecked;
								}
							}
						})

				.setPositiveButton(R.string.check_all,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								// THEM COde cho Check All

								if (isAllCheckedMandant) {
									isAllCheckedMandant = false;
								} else {
									isAllCheckedMandant = true;
								}

								setCheckAllMandant();
							}
						}).setNeutralButton("Add",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								addSelectedMandants(dialog, whichButton);
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}

	public void setCheckAllMandant() {
		if (isAllCheckedMandant) {
			for (int i = 0; i < MandantListfromServer.size(); i++) {
				isCheckedMandantListfromServer[i] = true;
				MandantListSelectedfromDialog.add(MandantListfromServer.get(i));
			}
		} else {
			for (int i = 0; i < MandantListfromServer.size(); i++) {
				isCheckedMandantListfromServer[i] = false;
			}
		}
		openDialogWithMandants();
	}

	public void addSelectedMandants(DialogInterface dialog, int whichButton) {
		updateMandantinDB();
		addMandanttoDB();
		getWBList();
		updateWBinDB();
		addWBtoDB();
		updateNumberOfGroupinDB();
		getListItemForEachGroup();
		expandable();
		MandantListSelectedfromDialog.clear();
	}

	public void openDialogWithWBs() {
		test.clear();
		adSelectWB = new AlertDialog.Builder(this).setTitle(
				R.string.alert_dialog_multi_choice).setIcon(R.drawable.alert)
				.setMultiChoiceItems(arr_CS_allWBs, isCheckedWBListfromServer,
						new DialogInterface.OnMultiChoiceClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton, boolean isChecked) {
								if (isCheckedWBListfromServer[whichButton] = true) {
									test.add(arr_CS_allWBs[whichButton]
											.toString());
								} else {
									isCheckedWBListfromServer[whichButton] = isChecked;
								}
							}
						})

				.setPositiveButton(R.string.check_all,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								if (isAllChecked) {
									isAllChecked = false;
								} else {
									isAllChecked = true;
								}

								setCheckAllWB();
							}
						}).setNeutralButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								addSelectedItems(dialog, whichButton);
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}

	public void setCheckAllWB() {
		if (isAllChecked) {
			for (int i = 0; i < WBListfromServer.size(); i++) {
				isCheckedWBListfromServer[i] = true;
				test.add(WBListfromServer.get(i));
			}
		} else {
			for (int i = 0; i < WBListfromServer.size(); i++) {
				isCheckedWBListfromServer[i] = false;
			}
		}

		openDialogWithWBs();
	}

	public void getMandantListFromServer() {
		try {
			for (MandantDefinition mandant : listMandant) {
				MandantListfromServer.add(mandant.getId());
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}

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

	public void addMandanttoDB() {
		for (int x = 0; x < MandantListSelectedfromDialog.size(); x++) {

			if (MandantListEnablefromDB.contains(MandantListSelectedfromDialog
					.get(x))) {

			} else {
				ContentResolver contentResolver = getContentResolver();
				ContentValues values = new ContentValues();
				values.put(BookProviderMetaData.BookTableMetaData.MANDANT_NAME,
						MandantListSelectedfromDialog.get(x));
				values.put(
						BookProviderMetaData.BookTableMetaData.MANDANT_ENABLE,
						"1");
				Uri uri = contentResolver.insert(
						BookProviderMetaData.BookTableMetaData.CONTENT_URI,
						values);
				updateMandantinDB();
			}
		}

	}

	public void updateMandantinDB() {

		MandantListEnablefromDB.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				MandantenableProj, "mandantenable = ?", new String[] { "1" },
				null);
		startManagingCursor(cursor);
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
			MandantListEnablefromDB.add(nameMandantenable);
		}
	}

	public void addWBtoDB() {
		String TimeStamp;
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		for (int j = 0; j < MandantListEnablefromDB.size(); j++) {
			for (int k = 0; k < PBF_mdCollection.getMandant(j).getArrWbsList()
					.size(); k++) {
				int count = PBF_mdCollection.getMandant(j).getArrWbs(k)
						.getArrProsCount();
				TimeStamp = PBF_mdCollection.getMandant(j).getArrWbs(k)
						.getLastBuildDate().toString();
				values.put(BookProviderMetaData.BookTableMetaData.TIME_DATE,
						TimeStamp);
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

		// startManagingCursor(cursor_Group);
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

	public void changeIDGroup(String OldIDGroup, String NewIDGroup) {

		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		values1
				.put(BookProviderMetaData.BookTableMetaData.GROUP_ID,
						NewIDGroup);
		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"groupenable = ? AND groupid = ?", new String[] { "1",
						OldIDGroup });
	}

	public void changeIDWB(String OldIDGroup, String NewIDGroup) {

		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID,
				NewIDGroup);
		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"wbenable = ? AND idgroup = ?",
				new String[] { "1", OldIDGroup });
	}

	public void getListItemForEachGroup() {
		ListAllListItems.clear();
		ListAllListTimeDate.clear();
		ListAllListCount.clear();
		for (int i = 1; i < 21; i++) {
			ContentResolver contentResolver = getContentResolver();
			Cursor cursor = contentResolver.query(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					WBenableProj, "wbenable = ? AND idgroup = ?", new String[] {
							"1", "" + i }, null);
			startManagingCursor(cursor);
			int x = cursor.getCount();
			List<String> ListItemGroup = new ArrayList();
			List<String> ListTimeDateGroup = new ArrayList();
			List<String> ListItemCountGroup = new ArrayList();
			for (int j = 0; j < x; j++) {
				cursor.moveToPosition(j);
				int ColumnWBenable_InGroupID = cursor
						.getColumnIndex(BookProviderMetaData.BookTableMetaData.WB_NAME);
				String nameWBenable = cursor
						.getString(ColumnWBenable_InGroupID);
				ListItemGroup.add(nameWBenable);

				int ColumnTimeDate = cursor
						.getColumnIndex(BookProviderMetaData.BookTableMetaData.TIME_DATE);
				String nameTimeDate = cursor.getString(ColumnTimeDate);
				SimpleDateFormat df = new SimpleDateFormat(
						"EEE, dd MMM yyyy HH:mm:ss zzz");
				df.setTimeZone(TimeZone.getTimeZone("GMT"));
				java.util.Date timestamp = null;

				try {
					timestamp = df.parse(nameTimeDate);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				getSecondsDifference(timestamp);

				if (differenceSecondsYears >= 1) {
					timerLoop = differenceSecondsYears + " Year ago";
				} else if (differenceSecondsMonths >= 1
						&& differenceSecondsMonths <= 12) {
					timerLoop = differenceSecondsMonths + " Months ago";
				} else if (differenceSecondsDays >= 1
						&& differenceSecondsDays <= 30) {
					timerLoop = differenceSecondsDays + " Days ago";
				} else if (differenceSecondsHours >= 1
						&& differenceSecondsHours <= 24) {
					timerLoop = differenceSecondsHours + " Hours ago";
				} else if (differenceSecondsMinutes >= 1
						&& differenceSecondsMinutes <= 60) {
					timerLoop = differenceSecondsMinutes + " Minutes ago";
				}

				ListTimeDateGroup.add(timerLoop);

				int ColumnTotalCount = cursor
						.getColumnIndex(BookProviderMetaData.BookTableMetaData.TOTAL_COUNT);
				String nameTotalCount = cursor.getString(ColumnTotalCount);
				int ColumnUnReadCount = cursor
						.getColumnIndex(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT);
				String nameUnReadCount = cursor.getString(ColumnUnReadCount);
				ListItemCountGroup.add("[" + nameUnReadCount + "/"
						+ nameTotalCount + "]");
			}
			ListAllListItems.add(ListItemGroup);
			ListAllListTimeDate.add(ListTimeDateGroup);
			ListAllListCount.add(ListItemCountGroup);
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

	public void removeGroup(String groupremoved, int idgroup) {

		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		ContentValues values2 = new ContentValues();
		ContentValues values3 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID, "1");
		values2.put(BookProviderMetaData.BookTableMetaData.GROUP_ENABLE, "0");

		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"wbenable = ? AND idgroup = ?", new String[] { "1",
						"" + idgroup });
		int count2 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values2,
				"name = ? AND groupenable = ?", new String[] { groupremoved,
						"1" });
		int t = idgroup + 1;
		int n = numberOfGroup + 1;
		for (int i = t; i < n; i++) {
			int m = i - 1;
			values3
					.put(BookProviderMetaData.BookTableMetaData.GROUP_ID, ""
							+ m);
			int count3 = contentResolver.update(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					values3, "groupenable = ? AND groupid = ?", new String[] {
							"1", "" + i });
		}

	}

	public void ChangeIdGroup(int idgroup, int NumOfGroup) {
		int extra;
		extra = NumOfGroup - idgroup;
		for (int i = 0; i < extra; i++) {
			int j = i + 1;
			int OldID = idgroup + j;
			int NewID = idgroup + i;
			ContentResolver contentResolver = getContentResolver();
			ContentValues values1 = new ContentValues();
			ContentValues values2 = new ContentValues();
			values1.put(BookProviderMetaData.BookTableMetaData.INGROUP_ID, ""
					+ NewID);
			int count1 = contentResolver.update(
					BookProviderMetaData.BookTableMetaData.CONTENT_URI,
					values1, "wbenable = ? AND idgroup = ?", new String[] {
							"1", "" + OldID });
		}

	}

	public void showDialog() {
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

								/* User clicked No so do some stuff */
							}
						}).show();
	}

	public void RemoveGroupDialog() {
		String[] ssGroups = new String[GroupListEnablefromDB.size() - 1];
		for (int i = 0; i < GroupListEnablefromDB.size() - 1; i++) {
			ssGroups[i] = GroupListEnablefromDB.get(i + 1);
		}

		arr_CS_allGroups = ssGroups;

		adSelectgroup1 = new AlertDialog.Builder(this).setTitle(
				"Select Group to Remove").setIcon(R.drawable.star_big_on)
				.setSingleChoiceItems(arr_CS_allGroups, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

								selectedGrouptoRemove = arr_CS_allGroups[whichButton]
										.toString();
							}

						})

				.setPositiveButton("REMOVE",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								setGrouptoRemove();
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								/* User clicked No so do some stuff */
							}
						}).show();
	}

	public void setGrouptoRemove() {

		for (int i = 0; i < GroupListEnablefromDB.size(); i++) {
			if (GroupListEnablefromDB.get(i).equals(selectedGrouptoRemove)) {
				int num = i + 1;
				idgrouptoRemove = num;
				showProcessBar();
				removeGroup(selectedGrouptoRemove, idgrouptoRemove);
				updateWBinDB();
				updateNumberOfGroupinDB();
				getListItemForEachGroup();
				expandable();
			} else {

			}
		}

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
		progDailog = ProgressDialog.show(ANDROID_RSS_READER.this,
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

	public void deleteAll() {
		ContentResolver contentResolver = getContentResolver();
		contentResolver.delete(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, null, null);
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, null, null,
				null, null);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	protected InputStream getData() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(new URI(
				"http://174.143.148.49/PBFDemo/getDataCOLLECTION.jsp"));
		request.addHeader("Accept-Encoding", "gzip");
		HttpResponse response = client.execute(request);
		InputStream content = response.getEntity().getContent();
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		if (contentEncoding != null
				&& contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			content = new GZIPInputStream(content);
		}
		return content;
	}

	public void getDataProtobuf() {
		try {
			InputStream is = new URL(
					"http://174.143.148.49/PBFDemo/getDataCOLLECTION.jsp")
					.openStream();
			PBF_mdCollection = MandantCOLLECTION.parseFrom(is);
			listMandant = PBF_mdCollection.getMandantList();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void populateDataExpandableListView(
			List<List<Map<String, String>>> childData,
			List<String> ListItemWbName, List<String> ListItemCountTemp,
			List<String> ListItemTimeTemp) {
		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
		children = new ArrayList<Map<String, String>>();

		for (int j = 0; j < ListItemWbName.size(); ++j) {
			Map<String, String> curChildMap = new HashMap<String, String>();
			children.add(curChildMap);
			curChildMap.put(WB_NAME, ListItemWbName.get(j));
			// curChildMap.put(TIME_TEMP, "9 months ago");
			curChildMap.put(TIME_TEMP, ListItemTimeTemp.get(j));
			curChildMap.put(COUNT_TEMP, ListItemCountTemp.get(j));
		}
		childData.add(children);
	}

	private void openDialog1() {
		new AlertDialog.Builder(this).setTitle("Report").setIcon(
				R.drawable.dialog_warning).setMessage(
				"You cannot Remove No_Group").setNegativeButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
					}
				})

		.show();
	}

	private void openDialog2() {
		new AlertDialog.Builder(this).setTitle("Report").setIcon(
				R.drawable.dialog_warning).setMessage(
				"You cannot Rename No_Group").setNegativeButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
					}
				})

		.show();
	}

	private void openDialogRemoveGroup() {
		new AlertDialog.Builder(this).setTitle("Report").setIcon(
				R.drawable.dialog_warning).setMessage(
				"Are you sure to remove group " + itemClicked + " ?")
				.setNeutralButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								for (int j = 0; j < GroupListEnablefromDB
										.size(); j++) {
									if (GroupListEnablefromDB.get(j).equals(
											itemClicked)) {
										int num = j + 1;
										idgrouptoRemove = num;
									}
								}
								removeGroup(itemClicked, idgrouptoRemove);
								ChangeIdGroup(idgrouptoRemove,
										GroupListEnablefromDB.size());
								updateWBinDB();
								updateNumberOfGroupinDB();
								getListItemForEachGroup();
								expandable();
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}

	public long getSecondsDifference(java.util.Date timestamp) {

		final Calendar calendar = Calendar.getInstance(Locale.getDefault());

		int offset = TimeZone.getDefault().getOffset(timestamp.getTime());

		if (TimeZone.getDefault().inDaylightTime(
				Calendar.getInstance().getTime())) {
			offset = offset - TimeZone.getDefault().getDSTSavings();
		}
		final long referenceSeconds = (timestamp.getTime() + offset) / 1000;

		final long currentTimeSeconds = (calendar.getTimeInMillis()) / 1000;
		differenceSeconds = (currentTimeSeconds - referenceSeconds);
		differenceSecondsMinutes = differenceSeconds / 60;
		differenceSecondsHours = differenceSecondsMinutes / 60;
		differenceSecondsDays = differenceSecondsHours / 24;
		differenceSecondsMonths = differenceSecondsDays / 30;
		differenceSecondsYears = differenceSecondsMonths / 12;
		return differenceSeconds;
	}
}