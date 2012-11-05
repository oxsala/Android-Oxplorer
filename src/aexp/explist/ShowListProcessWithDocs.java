package aexp.explist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import basketProcessIdInfo.data.protobuf.BasketProcessIdInfo;

import com.sskk.example.bookprovidertest.provider.NewDBAdapter;
import commandlist.data.protobuf.CommandListData;
import commandlist.data.protobuf.CommandListData.Command;

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
	private static final String TYPE_TEMP = "ItemName2";
	private static final String COUNT_TEMP = "ItemName3";
	private static final String NUMPAGE_TEMP = "ItemName5";
	private static final String THUMBNAIL_TEMP = "ItemName4";
	private static final String ENTRY_NAME = "ENTRY_NAME";
	private static final String GROUP_FWBUTTON = "GROUP_FWBUTTON";
	private static final String GROUP_SCBUTTON = "GROUP_SCBUTTON";
	private static final String GROUP_CHECKBOX = "GROUP_CHECKBOX";
	private static final String LEFT_TABLE_LAYOUT = "LEFT_TABLE_LAYOUT";
	private static final String RIGHT_TABLE_LAYOUT = "RIGHT_TABLE_LAYOUT";
	List<String> ListEntryDateGroup = new ArrayList();
	List<String> ListOrderIDProcess = new ArrayList();
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

	List<List<String>> ListDocsTypeIDForEachGroup = new ArrayList<List<String>>();
	List<List<String>> ListNumberOfIndexForEachGroup = new ArrayList<List<String>>();
	List<List<String>> ListValueOfIndexForEachGroup = new ArrayList<List<String>>();
	// List<List<String>> ListTypeGroup = new ArrayList<List<String>>();
	private final List<String> ListProcessIDInfo = new ArrayList();

	// private final List<String> ListProcessEntryDateInfo = new ArrayList();
	private final List<String> ListProcessCountdocInfo = new ArrayList();
	private final List<String> ListProcessStatusInfo = new ArrayList();
	// private final List<String> ListDoccumentTitleInfo = new ArrayList();
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
	//private List<Item> DataChecked;
	List<String> ListItemChecked = new ArrayList();
	private int Size_ProcessName = 18;
	private int Size_DocName = 18;
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
	List<basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Process> listProcess;
	List<List<basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Process>> listAllProcess;
	List<Command> listCommand;
	private final List<String> CommandListFromServer = new ArrayList();
	private final List<String> CommandListFromDB = new ArrayList();
	private ExpandableListView listView;
	ArrayList<String> chkState = new ArrayList<String>();
	List<basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Index> listIndexDocument;
	List<basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Document> listDocument;
	private long[] expandedIds;
	private String GroupNameClicked;
	CheckBox checkbox_scr2;
	Button DoneFunctionButton;
	Button ForwardFunctionButton;
	Button CancelFunctionButton;
	Button SelectAllButton;
	private TextView backButton;
	private TextView txtTitle;
	private ProgressBar mProgressBar;

	private ParserTask parserTask;
	private int Color_ProcessName = 0xffffff00;
	private int Color_DocName = 0xffffff00;
	String[] arInfo;
	//List<List<OptionItem>> children = new ArrayList<List<OptionItem>>();
	private String DocTypeID;
	private String IndexTypeID = "";
	private String IndexTypeValue = "";
	private String IndexContent = "";
	private int NumberOfIndex;
	InputStream is;
	BasketProcessIdInfo.Document Indexdata;
	String ProcessNeedToTranslated = "";
	String IndexNeedToTranslated = "";
	String[] DoctypeIDSpilit;
	String[] ProcessIDSpilit;
	String[] StringSpilit2;
	String[] IndexSpilit;
	String[] IndexSpilitForEachGroup;
	String listtextTranslated;
	NewDBAdapter mDB;

	private final List<String> ListProcessTypeGetFromProtobuf = new ArrayList();
	private final List<String> ListNewProcessTypeNeedToTranslated = new ArrayList();
	private final List<String> ListProcessTypeTranslatedValueFromServer = new ArrayList();
	private final List<String> ListProcessTypeTranslatedWordFromDB = new ArrayList();
	private final List<String> ListProcessTypeTranslatedValueFromDB = new ArrayList();

	private final List<String> ListNewDocTypeIDNeedToTranslated = new ArrayList();
	private final List<String> ListDocTypeIdGetFromProtobuf = new ArrayList();
	private final List<String> ListDocTypeIdTranslatedValueFromServer = new ArrayList();
	private final List<String> ListDocTypeIDFromDB = new ArrayList();
	private final List<String> ListDocTypeIdFromProtoBufFilter = new ArrayList();

	private final List<String> ListIndexGetFromProtobuf = new ArrayList();
	private final List<String> ListIndexIDTranslatedValueFromServer = new ArrayList();
	private final List<String> ListIndexIDFromDB = new ArrayList();
	private final List<String> ListNewIndexIDNeedToTranslated = new ArrayList();
	private final List<String> ListIndexIdFromProtoBufFilter = new ArrayList();
	private final List<String> ListIndexIDFromDBFilter = new ArrayList();
	private int CountWbClicked;
	private String ItemNotConverted;
	int count;
	Drawable thumbail;
	Drawable thumbail2;
	private long differenceSeconds;
	private long differenceSecondsMinutes;
	private long differenceSecondsHours;
	private long differenceSecondsDays;
	private long differenceSecondsMonths;
	private long differenceSecondsYears;
	private String timerLoop;
	boolean[] isCheckedCheckBoxGroup;
	private int ClickCheckBox;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen2);
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		view = (TextView) findViewById(R.id.tv);
		DoneFunctionButton = (Button) findViewById(R.id.DoneFunction);
		ForwardFunctionButton = (Button) findViewById(R.id.ForwardFunction);
		CancelFunctionButton = (Button) findViewById(R.id.CancelFunction);
		backButton = (TextView) findViewById(R.id.albums_activity_btn_back);
		txtTitle = (TextView) findViewById(R.id.albums_activity_txt_title);
		SelectAllButton = (Button) findViewById(R.id.SelectAllButton);
		final Button ExpandAllButton = (Button) findViewById(R.id.ExpandAll);
		final Button CollapseAllButton = (Button) findViewById(R.id.CollapseAll);
		final Button SortingButton = (Button) findViewById(R.id.Sorting);
		mGroupName = (EditText) findViewById(R.id.namegroup);

	
		
		Cursor CurCountItemClicked = mDB.getItemClickByID();
		if (CurCountItemClicked.moveToFirst()) {
			do {
				messageLinkWB = CurCountItemClicked.getString(0);
				ItemNotConverted = CurCountItemClicked.getString(1);
			} while (CurCountItemClicked.moveToNext());

		}
		
		getProcessInfoProtobuf(messageLinkWB);
		CurCountItemClicked.close();

		Cursor CurCountClicked = mDB.getCountClickedWbByName(ItemNotConverted);
		if (CurCountClicked.moveToFirst()) {
			do {
				CountWbClicked = Integer.parseInt(CurCountClicked.getString(0));
			} while (CurCountClicked.moveToNext());

		}
		CurCountClicked.close();

		txtTitle.setText(messageLinkWB);
		arInfo = messageLinkWB.split("_");

		
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
			// @Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(ShowListProcessWithDocs.this,
						ANDROID_RSS_READER.class);
				startActivity(intent);
				mDB.closeDB();
				// finish();

			}
		});
		// expandable();
		
	}

	/*
	 * @Override protected void onStart() { super.onStart(); if
	 * (this.expandedIds != null) { restoreExpandedState(expandedIds); } }
	 */

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences myPreference = PreferenceManager
				.getDefaultSharedPreferences(this);
		String ColorProcessName = new String();
		ColorProcessName = ""
				+ myPreference.getString("listPrefTextColorProcessName", "Red");
		if (ColorProcessName.equals("Yellow")) {
			Color_ProcessName = 0xffffff00;
		} else if (ColorProcessName.equals("Red")) {
			Color_ProcessName = 0xffff0000;
		} else if (ColorProcessName.equals("Black")) {
			Color_ProcessName = 0xff000000;
		} else if (ColorProcessName.equals("White")) {
			Color_ProcessName = 0xffffffff;
		} else if (ColorProcessName.equals("Green")) {
			Color_ProcessName = 0xff00ff00;
		} else if (ColorProcessName.equals("Blue")) {
			Color_ProcessName = 0xff0000ff;
		} else if (ColorProcessName.equals("Pink")) {
			Color_ProcessName = 0xffff00ff;
		} else if (ColorProcessName.equals("Turquoise")) {
			Color_ProcessName = 0xff00ffff;
		}

		String ColorDocName = new String();
		ColorDocName = ""
				+ myPreference.getString("listPrefTextColorDocName", "Black");
		if (ColorDocName.equals("Yellow")) {
			Color_DocName = 0xffffff00;
		} else if (ColorDocName.equals("Red")) {
			Color_DocName = 0xffff0000;
		} else if (ColorDocName.equals("Black")) {
			Color_DocName = 0xff000000;
		} else if (ColorDocName.equals("White")) {
			Color_DocName = 0xffffffff;
		} else if (ColorDocName.equals("Green")) {
			Color_DocName = 0xff00ff00;
		} else if (ColorDocName.equals("Blue")) {
			Color_DocName = 0xff0000ff;
		} else if (ColorDocName.equals("Pink")) {
			Color_DocName = 0xffff00ff;
		} else if (ColorDocName.equals("Turquoise")) {
			Color_DocName = 0xff00ffff;
		}

		String textSizeProcessName = new String();
		textSizeProcessName = ""
				+ myPreference.getString("listPrefTextSizeProcessName",
						"Small");
		// Size_GroupName=Integer.parseInt(textSizeGroupName);
		if (textSizeProcessName.equals("Medium")) {
			Size_ProcessName = 16;
		} else if (textSizeProcessName.equals("Small")) {
			Size_ProcessName = 13;
		}

		String textSizeDocName = new String();
		textSizeDocName = ""
				+ myPreference.getString("listPrefTextSizeDocName", "Medium");
		// Size_GroupName=Integer.parseInt(textSizeGroupName);
		if (textSizeDocName.equals("Medium")) {
			Size_DocName = 20;
		} else if (textSizeDocName.equals("Small")) {
			Size_DocName = 16;
		} else if (textSizeDocName.equals("Very small")) {
			Size_DocName = 13;
		} else if (textSizeDocName.equals("Large")) {
			Size_DocName = 22;
		} else if (textSizeDocName.equals("Xlarge")) {
			Size_DocName = 25;
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
			final int t = groupPosition;
			final int k = childPosition;
			int len = to.length;
			for (int i = 2; i < len; i++) {
				TextView v = (TextView) view.findViewById(to[i]);
				if (v != null) {
					v.setText((String) data.get(from[i]));
					if (to[i] == R.id.childname_scr2) {
						v.setTextColor(Color_DocName);
						v.setTextSize(Size_DocName);
					}
				}
			}

			/*for (int i = 6; i < len; i++) {
			
			}*/
			
			for (int i = 1; i < 2; i++) {
				int j = i+5;
				int numpage=0;
				TextView v = (TextView) view.findViewById(to[j]);
				if (v != null) {
					if (to[j] == R.id.numpage) {
						v.setText((String) data.get(from[j]));
					}
					String numpageString = ((String) data.get(from[j]));
					numpage=Integer.parseInt(numpageString);
				}
				
				
				RelativeLayout relativeMain = (RelativeLayout) view
						.findViewById(to[i]);
				if (relativeMain != null && to[i] == R.id.RightTbl) {
					if(numpage>1){
						relativeMain.setBackgroundDrawable(getResources()
								.getDrawable(R.drawable.nav_black));
					}
					else {
						relativeMain.setBackgroundDrawable(thumbail2);
						
					}
					relativeMain.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
						
						}
					});
				}
			}

			for (int i = 5; i < 6; i++) {

				Button ThumbnailDoc = (Button) view.findViewById(to[i]);
				if (ThumbnailDoc != null && to[i] == R.id.ThumbnailDoc) {
					ThumbnailDoc.setBackgroundDrawable(thumbail2);
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
								intent.setClass(ShowListProcessWithDocs.this,
										test2.class);
								startActivity(intent);
								// finish();

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
								Intent intent = new Intent();						
								intent.setClass(ShowListProcessWithDocs.this,
										test2.class);
								startActivity(intent);
								// finish();
								setTitle("" + itemClicked);

							}
						}
					});
				}
			}

			for (int i = 0; i < 1; i++) {
				TableLayout layMain = (TableLayout) view.findViewById(to[i]);
				if (layMain != null && to[i] == R.id.leftTbl) {
					layMain.setOnClickListener(new OnClickListener() {
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
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								// finish();
								mDB.closeDB();
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
								Intent intent = new Intent();
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								// finish();
								mDB.closeDB();
							}
						}
					});
				}
			}



			for (int i = 4; i < 5; i++) {
				TextView indexValue = (TextView) view.findViewById(to[i]);
				if (indexValue != null && to[i] == R.id.count_temp) {
					indexValue.setOnClickListener(new OnClickListener() {
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
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								// finish();
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
								Intent intent = new Intent();				
								intent.setClass(ShowListProcessWithDocs.this,
										IndexDoc.class);
								startActivity(intent);
								// finish();
							}
						}
					});
				}
			}

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

		private void bindGroupView(View convertView, Map<String, ?> Groupdata,
				String[] from, int[] to, final int groupPosition) {
			int len = to.length;
			final int IdGroup = groupPosition + 1;
			final String aaa;
			final String GroupClicked = mGroupData.get(groupPosition)
					.toString();
			String[] arInfo;
			arInfo = GroupClicked.split(",");
			aaa = arInfo[0].substring(13);
			for (int i = 0; i < len; i++) {
				TextView v = (TextView) convertView.findViewById(to[i]);
				if (v != null && to[i] == R.id.groupnamescreen2) {
					v.setText((String) Groupdata.get(from[i]));
					v.setTextColor(Color_ProcessName);
					v.setTextSize(Size_ProcessName);

				} else if (v != null && to[i] == R.id.EntryDate) {
					// v.setText("[31.12]");
					v.setText((String) Groupdata.get(from[i]));
					// v.setTextColor(Color_ProcessName);
					v.setTextSize(12);
				}
			}
			
			for (int i = 3; i < len; i++) {

				SelectAllButton.setText(ButtonTextSelectAll);
				checkbox_scr2 = (CheckBox) convertView.findViewById(to[i]);
				if (checkbox_scr2 != null && to[i] == R.id.checkbox_scr2) {
				checkbox_scr2.setChecked(isCheckedCheckBoxGroup[groupPosition]);
					SelectAllButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {
							if (SelectAllOrNot.equals("0")) {
								for (int i = 0; i < isCheckedCheckBoxGroup.length; i++) {
									isCheckedCheckBoxGroup[i]=false;
								}
								expandable();
								SelectAllOrNot = "1";
								ButtonTextSelectAll = "Select All";
							} else {
								for (int i = 0; i < isCheckedCheckBoxGroup.length; i++) {
									isCheckedCheckBoxGroup[i]=true;
								}
								expandable();
								SelectAllOrNot = "0";
								ButtonTextSelectAll = "DeSelect All";
							}
						}
					});

					checkbox_scr2
							.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								// @Override
								public void onCheckedChanged(
										CompoundButton buttonView,
										boolean isChecked) {

								}
							});
				}
			}
	
			for (int i = 2; i < len; i++) {
				final int t = groupPosition + 1;
				final int ClickGroup =groupPosition;
				Button ForwardButton = (Button) convertView.findViewById(to[i]);
				if (ForwardButton != null && to[i] == R.id.forward) {
					ForwardButton.setOnClickListener(new OnClickListener() {
						public void onClick(View v) {

							
							String[] ListFunctionProcess = new String[CommandListFromDB.size()];
							for (int i = 0; i < CommandListFromDB.size(); i++) {
								ListFunctionProcess[i] = CommandListFromDB.get(i);
							}
							arr_All_FunctionProcess = ListFunctionProcess;
							adSelectFunctionProcess = new AlertDialog.Builder(ShowListProcessWithDocs.this)
									.setTitle("Select Process Command :")
									.setIcon(R.drawable.star_big_on)
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

											})
									.setNeutralButton("Done",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialoginterface, int i) {													
													for (int j = 0; j < isCheckedCheckBoxGroup.length; j++) {
														isCheckedCheckBoxGroup[j]=true;
													}
													expandable();
													SelectAllOrNot = "0";
													ButtonTextSelectAll = "DeSelect All";

												}
											})
									.setPositiveButton("Go", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int whichButton) {
											
											for (int i = 0; i < isCheckedCheckBoxGroup.length; i++) {
												isCheckedCheckBoxGroup[i]=false;
											}
											isCheckedCheckBoxGroup[ClickGroup]=true;
									
											for (int j = 0; j < NumProcess; j++) {
												getExpandableListView().collapseGroup(j);
											}
										    checkbox_scr2.setChecked(isCheckedCheckBoxGroup[groupPosition]);
											
											for (int j = 0; j < NumProcess; j++) {
												getExpandableListView().expandGroup(j);
											}
											ClickCheckBox=ClickGroup;
											
											
										}
									})
									.setNegativeButton(R.string.alert_dialog_cancel,
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,
														int whichButton) {

												}
											}).show();
							
						}
					});

				}
			}

	

		}
	}


	public void expandable() {
		String[] GroupsProcess = new String[NumProcess];
		for (int i = 0; i < NumProcess; i++) {
			GroupsProcess[i] = ListProcessTypeTranslatedValueFromDB.get(i);
			// isCheckedGroupList[i]=true;
		}

		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		for (int j = 0; j < GroupsProcess.length; ++j) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(GROUP_NAME, "" + GroupsProcess[j]);

			SimpleDateFormat df = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			java.util.Date timestamp = null;
			try {
				timestamp = df.parse(ListEntryDateGroup.get(j));
				getSecondsDifference(timestamp);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			if (differenceSeconds <= 0) {
				timerLoop = "Just Now";
			} else if (differenceSecondsYears >= 1) {
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

			curGroupMap.put(ENTRY_NAME,"["+timerLoop+"]");
			curGroupMap.put(GROUP_FWBUTTON, "");
			curGroupMap.put(GROUP_SCBUTTON, "");
			curGroupMap.put(GROUP_CHECKBOX, "");
		}

		List<List<String>> ListDocsTypeIDForEachGroup = new ArrayList<List<String>>();
		List<List<String>> ListNumberOfIndexForEachGroup = new ArrayList<List<String>>();
		List<List<String>> ListValueOfIndexForEachGroup = new ArrayList<List<String>>();
		int t = NumProcess + 1;
		for (int i = 1; i < t; i++) {

			Cursor DocCur = mDB.getDocTypeIdNameByGroupIdAndWbID(messageLinkWB,
					"" + i);

			List<String> ListDocTypeID = new ArrayList();
			List<String> ListDocTypeIdTranslated = new ArrayList();
			List<String> ListCountIndex = new ArrayList();
			List<String> ListValueIndex = new ArrayList();

			String[] IndexIDSpilit;
			String[] IndexValueSpilit;
			if (DocCur.moveToFirst()) {
				do {

					Cursor DocTranslateCur = mDB
							.getTranslateByTypeAndWordAndWb("Document",
									DocCur.getString(1), messageLinkWB);
					if (DocTranslateCur.moveToFirst()) {
						do {
							ListDocTypeID.add(DocTranslateCur.getString(3));
						} while (DocTranslateCur.moveToNext());
					}

					DocTranslateCur.close();

					// ListDocTypeID.add(DocCur.getString(1));
					ListCountIndex.add(DocCur.getString(2));
					// ListValueIndex.add("value");

					Cursor indexCur = mDB.getIndexTypeIdByGroupIdAndWbID(
							messageLinkWB, "" + i);
					if (indexCur.moveToFirst()) {
						do {
							List<String> ListIndexValue = new ArrayList();
							List<String> ListIndexID = new ArrayList();

							IndexIDSpilit = indexCur.getString(1).split(",");
							for (int k = 0; k < IndexIDSpilit.length; k++) {
								ListIndexID.add(IndexIDSpilit[k]);
							}

							IndexValueSpilit = indexCur.getString(2).split(",");
							for (int k = 0; k < IndexValueSpilit.length; k++) {
								ListIndexValue.add(IndexValueSpilit[k]);
							}

							List<String> ListIndexIDTranslated = new ArrayList();
							for (int m = 0; m < ListIndexID.size(); m++) {
								Cursor IndexTranslateCur = mDB
										.getTranslateByTypeAndWordAndWb(
												"Index", ListIndexID.get(m),
												messageLinkWB);
								if (IndexTranslateCur.moveToFirst()) {
									do {
										ListIndexIDTranslated
												.add(IndexTranslateCur
														.getString(3));

									} while (IndexTranslateCur.moveToNext());
								}
								IndexTranslateCur.close();
							}

							String NewIndexContent = new String();
							for (int n = 0; n < ListIndexValue.size(); n++) {
								NewIndexContent += ListIndexIDTranslated.get(n)
										+ ": " + ListIndexValue.get(n) + ". ";
							}
							ListValueIndex.add(NewIndexContent);

						} while (indexCur.moveToNext());
					}
					indexCur.close();

				}

				while (DocCur.moveToNext());
			}

			ListDocsTypeIDForEachGroup.add(ListDocTypeID);
			ListNumberOfIndexForEachGroup.add(ListCountIndex);
			ListValueOfIndexForEachGroup.add(ListValueIndex);

			DocCur.close();
		}
		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < NumProcess; i++) {
			populateDataExpandableListView(childData,
					ListDocsTypeIDForEachGroup.get(i),
					ListNumberOfIndexForEachGroup.get(i),
					ListValueOfIndexForEachGroup.get(i));
		}
		mAdapter = new MyExpandableListAdapter(this, groupData,
				R.layout.group_row_screen2, new String[] { GROUP_NAME,
						ENTRY_NAME, GROUP_FWBUTTON, GROUP_CHECKBOX },
				new int[] { R.id.groupnamescreen2, R.id.EntryDate,
						R.id.forward, R.id.checkbox_scr2 }, childData,
				R.layout.child_row_screen2, new String[] { LEFT_TABLE_LAYOUT,
						RIGHT_TABLE_LAYOUT, DOC_NAME, TYPE_TEMP, COUNT_TEMP,
						THUMBNAIL_TEMP, NUMPAGE_TEMP }, new int[] {
						R.id.leftTbl, R.id.RightTbl, R.id.childname_scr2,
						R.id.type_temp, R.id.count_temp, R.id.ThumbnailDoc,
						R.id.numpage });
		setListAdapter(mAdapter);

		registerForContextMenu(getExpandableListView());
		for (int j = 0; j < NumProcess; j++) {
			getExpandableListView().expandGroup(j);

			/*
			 * if (IndexGroupExpandable.get(j).equals("0")) {
			 * getExpandableListView( ).collapseGroup(j); } else {
			 * getExpandableListView().expandGroup(j); }
			 */
		}
		listView = getExpandableListView();
		listView.setItemsCanFocus(false);
		// txtTitle.setText(""+ListIndexIdFromProtoBufFilter.size()+ListIndexIdFromProtoBufFilter.get(0)+ListIndexIdFromProtoBufFilter.get(1));
		// ListDocTypeIDFromDB.size()+" CountWbClicked="+CountWbClicked);

	}

	public void getTranslationProcessFromUri(String mandant, List<String> ID) {
		long startTime = System.currentTimeMillis();
		try {
			if (ID.size() > 0) {
				for (int i = 0; i < ID.size(); i++) {
					ProcessNeedToTranslated += ID.get(i) + ",";
				}

				URL url = new URL(
						"http://192.168.16.121:8888/"
								+ mandant
								+ "/ext/oxplorer?content=translation&language=en&login=admin&pass=oxseed&processTypeId="
								+ ProcessNeedToTranslated
										.replaceAll(" ", "%20"));
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				String str = new String();
				while ((str = in.readLine()) != null) {
					ProcessIDSpilit = str.split(",");
				}
				// ListProcessTypeTranslatedValueFromServer.clear();
				for (int j = 0; j < ProcessIDSpilit.length; j++) {
					if (ProcessIDSpilit[j].equals("?")) {
						ListProcessTypeTranslatedValueFromServer.add(ID.get(j));
					} else {
						ListProcessTypeTranslatedValueFromServer
								.add(ProcessIDSpilit[j]);
					}
				}

				in.close();
			} else {

			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getTranslationProcessFromUri() took "
				+ (endTime - startTime) + " MiliSeconds");

	}

	public void getTranslateDocTypeIDFromUri(String mandant,
			List<String> ListDocTypeID) {
		long startTime = System.currentTimeMillis();
		try {
			String DocTypeIDNeedToTranslated = new String();
			if (ListDocTypeID.size() > 0) {
				for (int i = 0; i < ListDocTypeID.size(); i++) {
					DocTypeIDNeedToTranslated += ListDocTypeID.get(i) + ",";
					DocTypeIDNeedToTranslated = DocTypeIDNeedToTranslated
							.replaceAll(" ", "%20");
				}
				URL url = new URL(
						"http://192.168.16.121:8888/"
								+ mandant
								+ "/ext/oxplorer?content=translation&language=en&login=admin&pass=oxseed&docTypeId="
								+ DocTypeIDNeedToTranslated);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				String str = new String();
				while ((str = in.readLine()) != null) {
					DoctypeIDSpilit = str.split(",");
				}
				// ListNewDocTypeIdTranslatedFromServer.clear();
				for (int j = 0; j < DoctypeIDSpilit.length; j++) {
					if (DoctypeIDSpilit[j].equals("?")) {
						ListDocTypeIdTranslatedValueFromServer
								.add(ListDocTypeID.get(j));
					} else {
						ListDocTypeIdTranslatedValueFromServer
								.add(DoctypeIDSpilit[j]);
					}
				}

				in.close();

			} else {

			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getTranslateDocTypeIDFromUri() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void getTranslationIndexFromUri(String mandant,
			List<String> ListStringIndex) {
		long startTime = System.currentTimeMillis();
		try {
			String IndexNeedToTranslated = new String();
			if (ListStringIndex.size() > 0) {
				for (int i = 0; i < ListStringIndex.size(); i++) {
					IndexNeedToTranslated += ListStringIndex.get(i) + ",";
				}
				URL url = new URL(
						"http://192.168.16.121:8888/"
								+ mandant
								+ "/ext/oxplorer?content=translation&language=en&login=admin&pass=oxseed&indexId="
								+ IndexNeedToTranslated);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				String str = new String();
				while ((str = in.readLine()) != null) {
					StringSpilit2 = str.split(",");
				}
				ListIndexIDTranslatedValueFromServer.clear();
				for (int i = 0; i < StringSpilit2.length; i++) {
					ListIndexIDTranslatedValueFromServer.add(StringSpilit2[i]);
				}

				in.close();
			}

			else {

			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {

		}
		long endTime = System.currentTimeMillis();
		System.out.println("getTranslationIndexFromUri() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void UpdateProcessIDFromDB() {
		long startTime = System.currentTimeMillis();
		Cursor cur = mDB.getTranslateByWbAndType(messageLinkWB, "Process");
		if (cur.moveToFirst()) {
			do {
				ListProcessTypeTranslatedValueFromDB.add(cur.getString(2));
			} while (cur.moveToNext());
		}
		cur.close();
		long endTime = System.currentTimeMillis();
		System.out.println("UpdateProcessIDFromDB() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void getProcessIDTranslated() {
		long startTime = System.currentTimeMillis();
		getTranslationProcessFromUri(arInfo[1], ListProcessTypeGetFromProtobuf);
		if (ListProcessTypeGetFromProtobuf.size() > 0) {
			for (int i = 0; i < ListProcessTypeGetFromProtobuf.size(); i++) {
				mDB.insertNewTranslate(messageLinkWB, "Process",
						ListProcessTypeGetFromProtobuf.get(i),
						ListProcessTypeTranslatedValueFromServer.get(i));
			}
		} else {

		}
		long endTime = System.currentTimeMillis();
		System.out.println("insertProcessIDTranslatedToDB()() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	/*
	 * public void updateDocTypeIdInDB() { long startTime =
	 * System.currentTimeMillis(); Cursor cur =
	 * mDB.getDocTypeIdNameByWbID(messageLinkWB); if (cur.moveToFirst()) { do {
	 * ListDocTypeIDFromDB.add(cur.getString(2)); } while (cur.moveToNext()); }
	 * cur.close(); long endTime = System.currentTimeMillis();
	 * System.out.println("updateDocTypeIdInDB() took " + (endTime - startTime)
	 * + " MiliSeconds"); }
	 */

	public void getDocTypeIdTranslated() {
		long startTime = System.currentTimeMillis();
		try {
			for (int i = 0; i < listProcess.size(); ++i) {
				int t = i + 1;
				listDocument = listProcess.get(i).getDocumentList()
						.getDocumentList();
				for (int j = 0; j < listDocument.size(); ++j) {
					String IndexTypeID = new String();
					String IndexTypeValue = new String();
					listIndexDocument = listDocument.get(j).getIndexList()
							.getIndexList();

					for (int m = 0; m < listIndexDocument.size(); m++) {
						IndexContent += listIndexDocument.get(m).getIndexId()
								+ ",";
						IndexTypeID += listIndexDocument.get(m).getIndexId()
								.replaceAll("_str", "")
								+ ",";
						if (listIndexDocument.get(m).getIndexValue().equals("")) {
							IndexTypeValue += "null" + ",";
						} else {
							IndexTypeValue += listIndexDocument.get(m)
									.getIndexValue() + ",";
						}

					}

					mDB.insertNewIndexTypeID(messageLinkWB, IndexTypeID,
							IndexTypeValue, "" + t);

					NumberOfIndex = listProcess.get(i).getDocumentList()
							.getDocument(j).getPageCount();
					DocTypeID = listProcess.get(i).getDocumentList()
							.getDocument(j).getTypeId();
					ListDocTypeIdGetFromProtobuf.add(DocTypeID);
					mDB.insertNewDocTypeID(messageLinkWB, DocTypeID, ""
							+ NumberOfIndex, "" + t);
				}

			}

			HashSet hs = new HashSet();
			hs.addAll(ListDocTypeIdGetFromProtobuf);
			ListDocTypeIdFromProtoBufFilter.addAll(hs);

			getTranslateDocTypeIDFromUri(arInfo[1],
					ListDocTypeIdFromProtoBufFilter);
			if (ListDocTypeIdFromProtoBufFilter.size() > 0) {
				for (int x = 0; x < ListDocTypeIdFromProtoBufFilter.size(); x++) {
					mDB.insertNewTranslate(messageLinkWB, "Document",
							ListDocTypeIdFromProtoBufFilter.get(x),
							ListDocTypeIdTranslatedValueFromServer.get(x));
				}
			} else {

			}

		} catch (Exception e) {
			Log.e("Protobuf", "Exception getting ProtocolBuffer data", e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("GetDocTypeIdTranslated() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void getIndexIDTranslated() {
		long startTime = System.currentTimeMillis();

		IndexSpilit = IndexContent.split(",");
		for (int q = 0; q < IndexSpilit.length; q++) {
			ListIndexGetFromProtobuf.add(IndexSpilit[q].replaceAll(" ", "%20")
					.replaceAll("_str", ""));
		}
		HashSet hs = new HashSet();
		hs.addAll(ListIndexGetFromProtobuf);
		ListIndexIdFromProtoBufFilter.addAll(hs);

		getTranslationIndexFromUri(arInfo[1], ListIndexIdFromProtoBufFilter);

		for (int i = 0; i < ListIndexIdFromProtoBufFilter.size(); i++) {
			mDB.insertNewTranslate(messageLinkWB, "Index",
					ListIndexIdFromProtoBufFilter.get(i),
					ListIndexIDTranslatedValueFromServer.get(i));
		}

		long endTime = System.currentTimeMillis();
		System.out.println("getIndexIDTranslated() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	// @Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void filterList(List<String> st) {
		HashSet hs = new HashSet();
		hs.addAll(st);
		st.clear();
		st.addAll(hs);
	}

/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			intent.setClass(ShowListProcessWithDocs.this,
					ANDROID_RSS_READER.class);
			startActivity(intent);
			mDB.closeDB();
			// finish();
		}

		}
		return false;
	}*/

	public void populateDataExpandableListView(
			List<List<Map<String, String>>> childData,
			List<String> ListItemDocName, List<String> ListItemCountOfIndex,
			List<String> ListValueOfIndex) {
		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
		children = new ArrayList<Map<String, String>>();
		for (int j = 0; j < ListItemDocName.size(); ++j) {
			Map<String, String> curChildMap = new HashMap<String, String>();
			children.add(curChildMap);
			curChildMap.put(DOC_NAME, ListItemDocName.get(j));
			// curChildMap.put(TYPE_TEMP, "Type: " + ListItemTypeOfDoc.get(j));
			curChildMap.put(COUNT_TEMP, "" + ListValueOfIndex.get(j));
			curChildMap.put(NUMPAGE_TEMP, "" + ListItemCountOfIndex.get(j));
		}
		childData.add(children);
	}

	public void getProcessInfoProtobuf(String WorkBasket) {
		long startTime = System.currentTimeMillis();
		try {
			is = new URL(
					"http://192.168.16.121:8080/moxseed/spring/basketapp?basketIds="
							+ WorkBasket
							+ "&responseType=protobuf&action=getbasketprocessinfolist&format=pbf")
					.openStream();
			BasketProcessIdInfo.ProcessList data = BasketProcessIdInfo.ProcessList
					.parseFrom(is);

			listProcess = data.getProcessList();
			NumProcess = listProcess.size();
			isCheckedCheckBoxGroup = new boolean[NumProcess];
			for (int i = 0; i < NumProcess; ++i) {
				isCheckedCheckBoxGroup[i]=false;
			}
	
			
			
			for (basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Process ProcessLoop : listProcess) {
				ListEntryDateGroup.add(ProcessLoop.getEntryDate());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.Process ProcessLoop : listProcess) {
				ListProcessTypeGetFromProtobuf.add(ProcessLoop.getTypeId());
			}
		}
		catch (Exception e) {
			Log.e("Oxplorer Exception", "Exception getProcessInfoProtobuf", e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getProcessInfoProtobuf() took "
				+ (endTime - startTime) + " MiliSeconds");

	}

	//........................GET COMMAND LIST FROM PROTOCOL BUFFER......................
	public void getCommandListProtobuf(String mandant) {
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = new URL(
					"http://192.168.16.121:8888/"
							+ mandant
							+ "/ext/oxplorer?content=wbTypeCommands&wbType=workflow&login=admin&pass=oxseed&format=pbf")
					.openStream();
			CommandListData.CommandList data = CommandListData.CommandList
					.parseFrom(is);
			listCommand = data.getCommandList();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			for (Command commandtLoop : listCommand) {
				CommandListFromServer.add(commandtLoop.getId());
			}
		} catch (Exception e) {
			Log.e("CommandList Get Error",
					"Exception getting CommandList ProtocolBuffer data", e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getCommandListProtobuf() took "
				+ (endTime - startTime) + " MiliSeconds");
	}
	
	
	//........................INSERT COMMAND LIST TO DATA BASE......................
	public void insertCommandListToDB(){
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < CommandListFromServer.size(); ++i) {
			mDB.insertCommand(messageLinkWB, CommandListFromServer.get(i));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("insertCommandListToDB() took "
				+ (endTime - startTime) + " MiliSeconds");
	}
	
	//........................GET COMMAND LIST FROM DATA BASE......................
	public void getCommandListFromDB(){
		long startTime = System.currentTimeMillis();
		Cursor CommandListCur = mDB.getCommandNameByWb(messageLinkWB);
		if (CommandListCur.moveToFirst()) {
			do {
				CommandListFromDB.add(CommandListCur.getString(1));
			} while (CommandListCur.moveToNext());

		}
		CommandListCur.close();
		long endTime = System.currentTimeMillis();
		System.out.println("getCommandListFromDB() took "
				+ (endTime - startTime) + " MiliSeconds");
	}
	
	
	public void openDialogWithProcessFunction() {

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
		adSelectFunctionProcess = new AlertDialog.Builder(this)
				.setTitle("Sorting Process :")
				.setIcon(R.drawable.star_big_on)
				.setSingleChoiceItems(arr_All_FunctionProcess, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

							}

						})

				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				})
				.setNegativeButton(R.string.alert_dialog_cancel,
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

	@Override
	protected void onStart() {
		super.onStart();
		parserTask = new ParserTask();
		parserTask.execute();
	}

	private class ParserTask extends AsyncTask<String, Integer, Long> {

		public ParserTask() {

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected Long doInBackground(String... params) {	
			thumbail = downloadFile("http://192.168.16.121:8080/services/ocr-archive?action=show_image&document_id=20111102140426916000838C968AF0BC78AB0FDAE5E4208961D7F00000000982d4a566c&mandant=ashok2210&max_width=64&max_height=89");
			thumbail2 = downloadFile("http://192.168.16.121:8080/services/ocr-archive?action=show_image&document_id=20111102140426916000838C968AF0BC78AB0FDAE5E4208961D7F00000000982d4a566c&mandant=ashok2210&max_width=64&max_height=89");
			
			
			
			if (CountWbClicked == 1) {
				getCommandListProtobuf(arInfo[1]);
				insertCommandListToDB();
				getCommandListFromDB();
				getProcessIDTranslated();
				UpdateProcessIDFromDB();
				getDocTypeIdTranslated();
				getIndexIDTranslated();
			} else {
				UpdateProcessIDFromDB();
				getCommandListFromDB();
				// updateIndexIdFromDB();

				// getIndexIDTranslated();
				// updateIndexIdFromDB();

			}
			return null;
		}

		@Override
		protected void onPostExecute(Long arg0) {
			expandable();
			mProgressBar.setVisibility(View.GONE);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Refresh").setIcon(R.drawable.refresh);
		menu.add(0, 1, 0, "Setting").setIcon(R.drawable.setting);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			reload();
			// startActivity(new Intent(ShowListProcessWithDocs.this,
			// test2.class));
			// //finish();
			break;
		case 1:
			startActivity(new Intent(ShowListProcessWithDocs.this,
					com.androidrss.preference.Setting.class));
			// finish();
			break;

		}
		return false;
	}

	public void reload() {
		mDB.closeDB();
		startActivity(getIntent());
		// finish();
	}

	static class ViewHolader {

		CheckBox checkbox_scr2;
	}

	private Drawable downloadFile(String fileUrl) {
		long startTime = System.currentTimeMillis();
		URL myFileUrl = null;
		try {
			myFileUrl = new URL(fileUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream is = (InputStream) new URL(fileUrl).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// mScreen.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg1));

		}

		long endTime = System.currentTimeMillis();
		System.out.println("downloadFileImage() took " + (endTime - startTime)
				+ " MiliSeconds");
		return null;

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