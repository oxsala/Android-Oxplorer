package aexp.explist;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.mandant.protobuf.MandantData;
import com.mandant.protobuf.MandantData.MandantCOLLECTION;
import com.sskk.example.bookprovidertest.provider.BookProviderMetaData;

import fr.marvinlabs.selectablelisttutorial.pojo.Item;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class ShowListProcessWithDocs extends ExpandableListActivity implements
		Runnable {
	List<String> ListFuncationProcess = new ArrayList();
	List<String> ListProcessClicked = new ArrayList();
	private int totalcount;
	private int unreadcount;
	boolean isCheckedGroup;
	private String SelectAllOrNot = "1";
	private String ButtonTextSelectAll = "Select All";
	private SparseBooleanArray checkedBoxes;
	ArrayList<Integer> expandedGroups = null;
	private static final String GROUP_NAME = "ProcessName";
	private static final String DOC_NAME = "docname";
	private static final String TIME_TEMP = "ItemName2";
	private static final String COUNT_TEMP = "ItemName3";
	private static final String THUMBNAIL_TEMP = "ItemName4";
	private static final String ENTRY_NAME = "ENTRY_NAME";
	private static final String GROUP_FWBUTTON = "GROUP_FWBUTTON";
	private static final String GROUP_SCBUTTON = "GROUP_SCBUTTON";
	private static final String GROUP_CHECKBOX = "GROUP_CHECKBOX";
	private static final String TABLE_LAYOUT = "TABLE_LAYOUT";
	List<String> ListEntryDateGroup = new ArrayList();
	private String messageLinkWB;
	private ProgressDialog progDailog;
	AlertDialog adSelectWB;
	AlertDialog adSelectMandant;
	AlertDialog adSelectFunctionProcess;
	AlertDialog adSelectGroup;
	boolean[] isCheckedWBListfromServer;
	boolean[] isCheckedGroupListfromDB;
	private final List<String> test = new ArrayList();
	boolean isAllChecked = false;
	boolean isAllCheckedGroup = false;
	boolean isAllCheckedMandant = false;
	CharSequence[] arr_All_FunctionProcess;
	CharSequence[] arr_CS_allWBs;
	CharSequence[] arr_CS_allGroups;
	private TextView view;
	private TextView group;
	private TextView workbasket;
	private TextView data;
	private int whichbtn;
	private CheckBox cb;
	List<List<String>> ListDocsTitleOfProcess = new ArrayList<List<String>>();;
	List<List<String>> ListAllListTimeDate = new ArrayList<List<String>>();;
	List<List<String>> ListAllListCount = new ArrayList<List<String>>();;
	List<List<String>> ListAllListMandantFromMandantAdded = new ArrayList<List<String>>();;
	List<String> MandantListEnablefromDB = new ArrayList();
	List<String> MandantListfromServer = new ArrayList();
	boolean[] isCheckedMandantListfromDB;
	boolean[] isCheckedMandantListfromServer;
	private final List<String> MandantListSelectedfromDialog = new ArrayList();
	// private List<String> MandantListUriSelectedfromDialog = new ArrayList();

	List<String> WBListfromServer = new ArrayList();
	// List<String> WBListUrifromServer = new ArrayList();
	List<String> WBListEnablefromDB = new ArrayList();
	// List<String> WBListUriEnablefromDB = new ArrayList();
	List<String> GroupListEnablefromDB = new ArrayList();
	List<String> TimeDateListfromServer = new ArrayList();
	List<String> TimeDateListEnablefromDB = new ArrayList();

	List<List<String>> ListDocsGroup = new ArrayList<List<String>>();
	List<List<String>> ListCreditorGroup = new ArrayList<List<String>>();
	List<List<String>> ListAmountGroup = new ArrayList<List<String>>();
	List<List<String>> ListTypeGroup = new ArrayList<List<String>>();
	private final List<String> ListProcessTitleInfo = new ArrayList();
	private final List<String> ListProcessTypeInfo = new ArrayList();
	private final List<String> ListProcessEntryDateInfo = new ArrayList();
	private final List<String> ListProcessCountdocInfo = new ArrayList();
	private final List<String> ListProcessStatusInfo = new ArrayList();
	private final List<String> ListDoccumentTitleInfo = new ArrayList();
	String[] ListItem;
	private EditText mGroupName;
	ExpandableListAdapter mAdapter;
	private String selectedFunctionProcess;
	private String selectedGrouptoRemove;
	private static final int CUSTOM_DIALOG = 1;
	private static final int DEFAULT_DIALOG = 2;
	private String newGroup;
	private static final String TAG = "ExpList";
	private int x, y;
	private String itemClicked;
	private String GroupItemClicked;
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
	private String item;
	private String GroupItem;
	String val2;
	private int NumProcess;
	private int NumDoccument;
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
	private final String[] ProcessClickedProj = new String[] {
			BookProviderMetaData.BookTableMetaData._ID,
			BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED,
			BookProviderMetaData.BookTableMetaData.STATUS_PROCESS };
	private long[] expandedIds;
	private String GroupNameClicked;
	MandantData.MandantCOLLECTION PBF_mdCollection;
	CheckBox checkbox_scr2;
	Button DoneFunctionButton;
	Button ForwardFunctionButton;
	Button CancelFunctionButton;
	private TextView backButton;
	private TextView txtTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen2);
		view = (TextView) findViewById(R.id.tv);
		DoneFunctionButton = (Button) findViewById(R.id.DoneFunction);
		ForwardFunctionButton = (Button) findViewById(R.id.ForwardFunction);
		CancelFunctionButton = (Button) findViewById(R.id.CancelFunction);
		backButton = (TextView) findViewById(R.id.albums_activity_btn_back);
		txtTitle = (TextView) findViewById(R.id.albums_activity_txt_title);
		final Button ExpandAllButton = (Button) findViewById(R.id.ExpandAll);
		final Button CollapseAllButton = (Button) findViewById(R.id.CollapseAll);
		final Button SortingButton = (Button) findViewById(R.id.Sorting);
		mGroupName = (EditText) findViewById(R.id.namegroup);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage;
		txtTitle.setText(messageLinkWB);
		getDataProtobuf();
		isCheckedGroup = false;
		UpdateProcessClickedInDb();
		expandable();

		ExpandAllButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < NumProcess; i++) {
					getExpandableListView().expandGroup(i);
				}

			}
		});
		CollapseAllButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < NumProcess; i++) {
					getExpandableListView().collapseGroup(i);
				}
			}
		});

		SortingButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				openDialogWithSortingProcess();
			}
		});

		CancelFunctionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				/*
				 * DoneFunctionButton.setVisibility(DoneFunctionButton.INVISIBLE)
				 * ; ForwardFunctionButton
				 * .setVisibility(ForwardFunctionButton.INVISIBLE);
				 * CancelFunctionButton
				 * .setVisibility(CancelFunctionButton.INVISIBLE);
				 */
			}
		});
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ShowListProcessWithDocs.this,
						ANDROID_RSS_READER.class);
				startActivity(intent);
				finish();
			}
		});

	}

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
	public boolean onOptionsItemSelected(MenuItem item) {
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
			final int t = groupPosition;
			final int k = childPosition;
			int len = to.length;
			for (int i = 0; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null) {
					v.setText((String) data.get(from[i]));
				}

				if (v != null
						&& (to[i] == R.id.childname_scr2
								|| to[i] == R.id.r2_scr2 || to[i] == R.id.r3_scr2)) {
					v.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							GroupItemClicked = mGroupData.get(t).toString();
							if (ListProcessClicked.contains(GroupItemClicked)) {
								Object o2 = mChildData.get(t).get(k).toString();
								String ChildClicked = o2.toString();
								String[] arInfo;
								arInfo = ChildClicked.split(",");
								for (int i = 0; i < arInfo.length; i++) {
									if (arInfo[i].contains("docname")) {
										item = arInfo[i].substring(9);
									} else {
									}
								}

								itemClicked = item.replace("}", "");
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								CharSequence textMessage;
								CharSequence textDocName;
								textMessage = "" + messageLinkWB;
								textDocName = "" + itemClicked;
								bundle.putCharSequence("messageLinkWB",
										textMessage);
								bundle.putCharSequence("messageDocName",
										textDocName);
								intent.putExtras(bundle);
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								finish();
							} else {
								Object o2 = mChildData.get(t).get(k).toString();
								String ChildClicked = o2.toString();
								String[] arInfo;
								arInfo = ChildClicked.split(",");
								for (int i = 0; i < arInfo.length; i++) {
									if (arInfo[i].contains("docname")) {
										item = arInfo[i].substring(9);
									} else {
									}
								}

								itemClicked = item.replace("}", "");
								addProcessClickedtoDB(GroupItemClicked);
								UpdateProcessClickedInDb();
								UpdateCountInDb();
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								CharSequence textMessage;
								CharSequence textDocName;
								textMessage = "" + messageLinkWB;
								textDocName = "" + itemClicked;
								bundle.putCharSequence("messageLinkWB",
										textMessage);
								bundle.putCharSequence("messageDocName",
										textDocName);
								intent.putExtras(bundle);
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								finish();
							}
						}
					});
				}
			}

			for (int i = 3; i < len; i++) {

				Button ThumbnailDoc = (Button) view.findViewById(to[i]);
				if (ThumbnailDoc != null && to[i] == R.id.ThumbnailDoc) {

					ThumbnailDoc.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							GroupItemClicked = mGroupData.get(t).toString();
							if (ListProcessClicked.contains(GroupItemClicked)) {
								Object o2 = mChildData.get(t).get(k).toString();
								String ChildClicked = o2.toString();
								String[] arInfo;
								arInfo = ChildClicked.split(",");
								for (int i = 0; i < arInfo.length; i++) {
									if (arInfo[i].contains("docname")) {
										item = arInfo[i].substring(9);
									} else {
									}
								}

								itemClicked = item.replace("}", "");
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								CharSequence textMessage;
								CharSequence textDocName;
								textMessage = "" + messageLinkWB;
								textDocName = "" + itemClicked;
								bundle.putCharSequence("messageLinkWB",
										textMessage);
								bundle.putCharSequence("messageDocName",
										textDocName);
								intent.putExtras(bundle);
								intent.setClass(ShowListProcessWithDocs.this,
										ViewImagesActivity.class);
								startActivity(intent);
								finish();
								/*
								 * Toast.makeText(getBaseContext(),
								 * ""+GroupItemClicked, Toast.LENGTH_SHORT)
								 * .show();
								 */
							} else {
								Object o2 = mChildData.get(t).get(k).toString();
								String ChildClicked = o2.toString();
								String[] arInfo;
								arInfo = ChildClicked.split(",");
								for (int i = 0; i < arInfo.length; i++) {
									if (arInfo[i].contains("docname")) {
										item = arInfo[i].substring(9);
									} else {
									}
								}

								itemClicked = item.replace("}", "");
								addProcessClickedtoDB(GroupItemClicked);
								UpdateProcessClickedInDb();
								UpdateCountInDb();
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								CharSequence textMessage;
								CharSequence textDocName;
								textDocName = "" + itemClicked;
								textMessage = "" + messageLinkWB;
								bundle.putCharSequence("messageLinkWB",
										textMessage);
								bundle.putCharSequence("messageDocName",
										textDocName);
								intent.putExtras(bundle);
								intent.setClass(ShowListProcessWithDocs.this,
										ViewImagesActivity.class);
								startActivity(intent);
								finish();
								setTitle("" + itemClicked);
								/*
								 * Toast.makeText(getBaseContext(),
								 * GroupItemClicked, Toast.LENGTH_SHORT)
								 * .show();
								 */
							}
						}
					});
				}
			}
			/*
			 * for (int i = 4; i < len; i++) { TableLayout layMain =
			 * (TableLayout) view.findViewById(to[i]); if (layMain != null &&
			 * to[i] == R.id.leftTbl) { layMain.setOnClickListener(new
			 * OnClickListener() { public void onClick(View v) {
			 * Toast.makeText(getBaseContext(), "hahaha",
			 * Toast.LENGTH_SHORT).show(); } }); } }
			 */
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View parentView = convertView;
			if (parentView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				parentView = vi.inflate(R.layout.group_row_screen2, null);
			}
			bindGroupView(parentView, mGroupData.get(groupPosition),
					mGroupFrom, mGroupTo, groupPosition);
			return parentView;
		}

		private void bindGroupView(View view, Map<String, ?> Groupdata,
				String[] from, int[] to, int groupPosition) {
			int len = to.length;
			final int IdGroup = groupPosition + 1;
			final String aaa;
			final String GroupClicked = mGroupData.get(groupPosition)
					.toString();
			String[] arInfo;
			arInfo = GroupClicked.split(",");
			aaa = arInfo[0].substring(13);

			for (int i = 0; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null && to[i] == R.id.groupnamescreen2) {
					v.setText((String) Groupdata.get(from[i]));
				} else if (v != null && to[i] == R.id.EntryDate) {
					// v.setText((String) Groupdata.get(from[i]));
					v.setText("[31.12]");
				}
			}
			for (int i = 2; i < len; i++) {
				final int t = groupPosition + 1;
				Button ForwardButton = (Button) view.findViewById(to[i]);
				if (ForwardButton != null && to[i] == R.id.forward) {
					ForwardButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							setTitle("Forward Button GROUP " + t);
							openDialogWithProcessFunction();
						}
					});

				} else if (ForwardButton != null && to[i] == R.id.scissors) {
					ForwardButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							setTitle("Scissors Button GROUP " + t);
						}
					});
				}
			}

			for (int i = 4; i < len; i++) {
				final Button SelectAllButton = (Button) findViewById(R.id.SelectAll);
				SelectAllButton.setText(ButtonTextSelectAll);

				checkbox_scr2 = (CheckBox) view.findViewById(to[i]);
				if (checkbox_scr2 != null && to[i] == R.id.checkbox_scr2) {
					checkbox_scr2.setChecked(isCheckedGroup);
					SelectAllButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							if (SelectAllOrNot.equals("0")) {
								isCheckedGroup = false;
								expandable();
								SelectAllOrNot = "1";
								ButtonTextSelectAll = "Select All";
							} else {
								isCheckedGroup = true;
								expandable();
								SelectAllOrNot = "0";
								ButtonTextSelectAll = "UnSelect All";
							}
						}
					});

					checkbox_scr2
							.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								@Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {

									// TODO Auto-generated method stub
									if (isChecked) {
										// isChecked=isCheckedGroupList[j];
										Toast.makeText(getBaseContext(),
												"Checked" + IdGroup,
												Toast.LENGTH_SHORT).show();
									} else {
										Toast
												.makeText(getBaseContext(),
														"UnChecked",
														Toast.LENGTH_SHORT)
												.show();
									}
								}
							});
				}
			}

		}
	}

	public void expandable() {
		String[] GroupsProcess = new String[NumProcess];
		for (int i = 0; i < NumProcess; i++) {
			GroupsProcess[i] = ListProcessTitleInfo.get(i);
			// isCheckedGroupList[i]=true;
		}

		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		for (int j = 0; j < GroupsProcess.length; ++j) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(GROUP_NAME, GroupsProcess[j]);
			curGroupMap.put(ENTRY_NAME, "[" + ListEntryDateGroup.get(j) + "]");
			curGroupMap.put(GROUP_FWBUTTON, "");
			curGroupMap.put(GROUP_SCBUTTON, "");
			curGroupMap.put(GROUP_CHECKBOX, "");
		}
		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < ListDocsGroup.size(); i++) {
			populateDataExpandableListView(childData, ListDocsGroup.get(i),
					ListCreditorGroup.get(i), ListTypeGroup.get(i),
					ListAmountGroup.get(i));
		}
		mAdapter = new MyExpandableListAdapter(this, groupData,
				R.layout.group_row_screen2, new String[] { GROUP_NAME,
						ENTRY_NAME, GROUP_FWBUTTON, GROUP_SCBUTTON,
						GROUP_CHECKBOX }, new int[] { R.id.groupnamescreen2,
						R.id.EntryDate, R.id.forward, R.id.scissors,
						R.id.checkbox_scr2 }, childData,
				R.layout.child_row_screen2, new String[] { DOC_NAME, TIME_TEMP,
						COUNT_TEMP, THUMBNAIL_TEMP }, new int[] {
						R.id.childname_scr2, R.id.r2_scr2, R.id.r3_scr2,
						R.id.ThumbnailDoc });
		setListAdapter(mAdapter);
		registerForContextMenu(getExpandableListView());
		for (int j = 0; j < NumProcess; j++) {

			// if(IndexGroupExpandable.get(j).equals("0")){
			// getExpandableListView().collapseGroup(j);
			// }
			// else {
			getExpandableListView().expandGroup(j);
			// }
		}

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

	public void getDataProtobuf() {
		try {
			InputStream is = new URL(
					"http://174.143.148.49/PBFDemo/getDataCOLLECTION.jsp")
					.openStream();
			PBF_mdCollection = MandantCOLLECTION.parseFrom(is);
			for (int i = 0; i < PBF_mdCollection.getMandantList().size(); i++) {
				for (int j = 0; j < PBF_mdCollection.getMandant(i)
						.getArrWbsList().size(); j++) {
					for (int k = 0; k < PBF_mdCollection.getMandant(i)
							.getArrWbs(j).getArrProsCount(); k++) {
						if (PBF_mdCollection.getMandant(i).getArrWbs(j)
								.getWbName().equals(messageLinkWB)) {
							NumProcess = PBF_mdCollection.getMandant(i)
									.getArrWbs(j).getArrProsCount();
							NumDoccument = PBF_mdCollection.getMandant(i)
									.getArrWbs(j).getArrPros(k).getNumDocs();
							ListProcessTitleInfo.add(""
									+ PBF_mdCollection.getMandant(i).getArrWbs(
											j).getArrPros(k).getProName());
							filterList(ListProcessTitleInfo);
							Collections.sort(ListProcessTitleInfo);
						} else {
						}
					}
				}
			}
			for (int i = 0; i < ListProcessTitleInfo.size(); i++) {
				getDataForListProcess(ListProcessTitleInfo.get(i));
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void filterList(List<String> st) {
		HashSet hs = new HashSet();
		hs.addAll(st);
		st.clear();
		st.addAll(hs);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			// finish();
			Intent intent = new Intent();
			intent.setClass(ShowListProcessWithDocs.this,
					ANDROID_RSS_READER.class);
			startActivity(intent);
			finish();
		}

		}
		return false;
	}

	public void populateDataExpandableListView(
			List<List<Map<String, String>>> childData,
			List<String> ListItemDocName, List<String> ListItemCreditorOfDoc,
			List<String> ListItemTypeOfDoc, List<String> ListItemAmountOfDoc) {
		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
		children = new ArrayList<Map<String, String>>();
		for (int j = 0; j < ListItemDocName.size(); ++j) {
			Map<String, String> curChildMap = new HashMap<String, String>();
			children.add(curChildMap);
			curChildMap.put(DOC_NAME, ListItemDocName.get(j));
			curChildMap.put(TIME_TEMP, "Creditor: "
					+ ListItemCreditorOfDoc.get(j));
			curChildMap.put(COUNT_TEMP, "Type: " + ListItemTypeOfDoc.get(j)
					+ ",  Amount: " + ListItemAmountOfDoc.get(j));
		}
		childData.add(children);
	}

	public void getDataForListProcess(String ProcessTitle) {
		try {
			List<String> ListDocs = new ArrayList();
			List<String> ListCreditor = new ArrayList();
			List<String> ListType = new ArrayList();
			List<String> ListAmount = new ArrayList();
			for (int i = 0; i < PBF_mdCollection.getMandantList().size(); i++) {
				for (int j = 0; j < PBF_mdCollection.getMandant(i)
						.getArrWbsList().size(); j++) {

					for (int k = 0; k < PBF_mdCollection.getMandant(i)
							.getArrWbs(j).getArrProsCount(); k++) {
						for (int m = 0; m < PBF_mdCollection.getMandant(i)
								.getArrWbs(j).getArrPros(k).getArrDocsCount(); m++) {
							for (int n = 0; n < PBF_mdCollection.getMandant(i)
									.getArrWbs(j).getArrPros(k).getArrDocs(m)
									.getArrPagesCount(); n++) {
								if (PBF_mdCollection.getMandant(i).getArrWbs(j)
										.getArrPros(k).getProName().equals(
												ProcessTitle)) {
									ListDocs.add(PBF_mdCollection.getMandant(i)
											.getArrWbs(j).getArrPros(k)
											.getArrDocs(m).getDocName());
									ListCreditor.add(PBF_mdCollection
											.getMandant(i).getArrWbs(j)
											.getArrPros(k).getArrDocs(m)
											.getDocCreditor());
									ListType.add(PBF_mdCollection.getMandant(i)
											.getArrWbs(j).getArrPros(k)
											.getArrDocs(m).getDocType());
									ListAmount.add(PBF_mdCollection.getMandant(
											i).getArrWbs(j).getArrPros(k)
											.getArrDocs(m).getDocAmount());
									/*
									 * ListThumnailGroup
									 * .add(PBF_mdCollection.getMandant(i)
									 * .getArrWbs(j).getArrPros(k)
									 * .getArrDocs(m).getArrPages( n)
									 * .getImgThumnailUrl());
									 */
									ListEntryDateGroup.add(PBF_mdCollection
											.getMandant(i).getArrWbs(j)
											.getArrPros(k).getProId());
									filterList(ListDocs);
									filterList(ListCreditor);
									filterList(ListType);
									filterList(ListAmount);
									// filterList(ListThumnailGroup);
								} else {

								}
							}
						}
					}

				}
			}
			ListDocsGroup.add(ListDocs);
			ListCreditorGroup.add(ListCreditor);
			ListTypeGroup.add(ListType);
			ListAmountGroup.add(ListAmount);
		} catch (Exception e) {
			Log.e("Protobuf", "Exception getting ProtocolBuffer data", e);
		}
	}

	public void CountReadinDB() {

		ContentResolver contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				WBenableProj, "wbenable = ? AND wbname = ?", new String[] {
						"1", messageLinkWB }, null);
		int numberOfWBenable = cursor.getCount();
		for (int i = 0; i < numberOfWBenable; i++) {
			cursor.moveToPosition(i);
			int Columntotalcount = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.TOTAL_COUNT);
			String nametotalcount = cursor.getString(Columntotalcount);
			totalcount = Integer.parseInt(nametotalcount);

			int Columnunreadcount = cursor
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT);
			String nameunreadcount = cursor.getString(Columnunreadcount);
			unreadcount = Integer.parseInt(nameunreadcount);

		}
	}

	public void UpdateCountInDb() {
		CountReadinDB();
		int total = totalcount;
		int unread = unreadcount - 1;
		if (unread <= 0) {
			unread = 0;
		}
		ContentResolver contentResolver = getContentResolver();
		ContentValues values1 = new ContentValues();
		ContentValues values2 = new ContentValues();
		ContentValues values3 = new ContentValues();
		values1.put(BookProviderMetaData.BookTableMetaData.TOTAL_COUNT, ""
				+ total);
		values2.put(BookProviderMetaData.BookTableMetaData.UNREAD_COUNT, ""
				+ unread);
		values3.put(BookProviderMetaData.BookTableMetaData.STATUS_PROCESS,
				"READ");
		int count1 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values1,
				"totalcount = ? AND wbname = ?", new String[] {
						"" + totalcount, messageLinkWB });
		int count2 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values2,
				"unreadcount = ? AND wbname = ?", new String[] {
						"" + unreadcount, messageLinkWB });
		int count3 = contentResolver.update(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values3,
				"statusprocess = ? AND processopenned = ?", new String[] {
						"UNREAD", GroupItemClicked });
		// Toast.makeText(this,"Rename Successfully !",
		// Toast.LENGTH_SHORT).show();

	}

	public void addProcessClickedtoDB(String ProcessClicked) {
		ContentResolver contentResolver = getContentResolver();
		ContentValues values = new ContentValues();
		values.put(BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED,
				ProcessClicked);
		// values.put(BookProviderMetaData.BookTableMetaData.STATUS_PROCESS,"READ");
		Uri uri = contentResolver.insert(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI, values);

	}

	public void UpdateProcessClickedInDb() {
		ListProcessClicked.clear();
		ListProcessStatusInfo.clear();
		ContentResolver contentResolver = getContentResolver();
		Cursor cursor1 = contentResolver.query(
				BookProviderMetaData.BookTableMetaData.CONTENT_URI,
				ProcessClickedProj, null, null, null);
		int x1 = cursor1.getCount();

		for (int i = 0; i < x1; i++) {

			cursor1.moveToPosition(i);
			int ColumnProcessClicked = cursor1
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.PROCESS_OPENNED);
			String ProcessClicked = cursor1.getString(ColumnProcessClicked);
			ListProcessClicked.add(ProcessClicked);

			int ColumnStatusProcess = cursor1
					.getColumnIndex(BookProviderMetaData.BookTableMetaData.STATUS_PROCESS);
			String StatusProcess = cursor1.getString(ColumnStatusProcess);
			ListProcessStatusInfo.add(StatusProcess);

		}
	}

	public void openDialogWithProcessFunction() {
		ListFuncationProcess.clear();
		ListFuncationProcess.add("Checked");
		ListFuncationProcess.add("1st Approval");
		ListFuncationProcess.add("2nd Approval");
		ListFuncationProcess.add("Booked");
		String[] ListFunctionProcess = new String[ListFuncationProcess.size()];
		for (int i = 0; i < ListFuncationProcess.size(); i++) {
			ListFunctionProcess[i] = ListFuncationProcess.get(i);
		}
		arr_All_FunctionProcess = ListFunctionProcess;
		adSelectFunctionProcess = new AlertDialog.Builder(this).setTitle(
				"Select Process Command :").setIcon(R.drawable.star_big_on)
				.setSingleChoiceItems(arr_All_FunctionProcess, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

								selectedFunctionProcess = arr_All_FunctionProcess[whichButton]
										.toString();
								if (whichButton == -1) {
									whichbtn = 0;
								} else {
									whichbtn = 1;
								}
							}

						}).setNeutralButton("Done",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {

							}
						}).setPositiveButton("Go",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								DoneFunctionButton
										.setVisibility(DoneFunctionButton.VISIBLE);
								ForwardFunctionButton
										.setVisibility(ForwardFunctionButton.VISIBLE);
								CancelFunctionButton
										.setVisibility(CancelFunctionButton.VISIBLE);
							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								/*
								 * DoneFunctionButton
								 * .setVisibility(DoneFunctionButton.INVISIBLE);
								 * ForwardFunctionButton
								 * .setVisibility(ForwardFunctionButton
								 * .INVISIBLE); CancelFunctionButton
								 * .setVisibility
								 * (CancelFunctionButton.INVISIBLE);
								 */
							}
						}).show();
	}

	public void openDialogWithSortingProcess() {
		ListFuncationProcess.clear();
		ListFuncationProcess.add("By Type");
		ListFuncationProcess.add("By Date");
		String[] ListFunctionProcess = new String[ListFuncationProcess.size()];
		for (int i = 0; i < ListFuncationProcess.size(); i++) {
			ListFunctionProcess[i] = ListFuncationProcess.get(i);
		}
		arr_All_FunctionProcess = ListFunctionProcess;
		adSelectFunctionProcess = new AlertDialog.Builder(this).setTitle(
				"Sorting Process :").setIcon(R.drawable.star_big_on)
				.setSingleChoiceItems(arr_All_FunctionProcess, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

							}

						})

				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
	}
}
