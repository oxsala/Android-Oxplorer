package aexp.explist;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import madant.data.protobuf.MandantData;
import madant.data.protobuf.MandantData.Mandant;
import protobuf.com.mandant.MandantData.MandantDefinition.WorkBasket;
import protobuf.data.wbList.WbListData;
import protobuf.data.wbList.WbListData.Wb;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import basketProcessIdInfo.data.protobuf.BasketProcessIdInfo;
import basketProcessIdInfo.data.protobuf.BasketProcessIdInfo.A_Basket_ProcessId_And_Date;

import com.androidrss.preference.RemoverMandant;
import com.sskk.example.bookprovidertest.provider.NewDBAdapter;
import com.test.androidtest.QuickAction;

public class ANDROID_RSS_READER extends ExpandableListActivity implements
		Runnable {
	private Handler mHandler = new Handler();
	private boolean keepRunning = true;
	private long interval = 30000;
	private int idgroupClicked = 1;
	private int id2groupClicked = 1;
	private int IDNOGROUP = 1;
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
	private TextView view2;
	private TextView group;
	private TextView workbasket;
	// private TextView data;
	private int whichbtn;
	private CheckBox cb;
	private String IdGroup;
	List<String> ListTimeDateGroup = new ArrayList();
	// List<List<String>> ListAllListItems = new ArrayList<List<String>>();;
	// List<List<String>> ListAllListTimeDate = new ArrayList<List<String>>();;
	// List<List<String>> ListAllListCount = new ArrayList<List<String>>();;
	// List<List<String>> ListAllListMandantFromMandantAdded = new
	// ArrayList<List<String>>();;
	List<String> MandantListEnablefromDB = new ArrayList();
	List<String> MandantListfromServer = new ArrayList();
	boolean[] isCheckedMandantListfromServer;
	List<String> MandantListSelectedfromDialog = new ArrayList();
	// List<String> WBListfromServer = new ArrayList();
	private final List<String> WbListFromServer = new ArrayList();
	List<String> WBListEnablefromDB = new ArrayList();
	List<String> GroupListEnablefromDB = new ArrayList();
	List<String> TimeDateListfromServer = new ArrayList();
	List<String> TimeDateListEnablefromDB = new ArrayList();
	List<String> ListProcess_PID_FromServer = new ArrayList();
	List<String> ListProcess_PID_FromDB = new ArrayList();
	List<String> ListProcess_PID_FromReaded = new ArrayList();
	List<String> ListProcess_PID_READED_FromDB = new ArrayList();
	String[] ListItem;
	private EditText mGroupName;
	ExpandableListAdapter mAdapter;
	private String selectedGrouptoMove;
	// private String selectedGrouptoRemove;
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
	//private List<Item> DataChecked;
	List<String> ListItemChecked = new ArrayList();
	private final int size_group = 20;
	private final int size_workbasket = 18;
	private final int size_data = 16;
	private int Color_WorkBasket = 0xFFFF0000;
	private int Color_TimeStamp = 0xffffffff;
	private int Color_GroupName = 0xffffff00;
	private int Size_GroupName = 18;
	private int Size_WorkBasket = 18;
	private int Size_TimeStamp = 18;
	String item;
	String val2;
	private int[] color = { 0xdedede, 0xfff, 0xfff, 0xfff, 0xFFFF0000 };
	List<String> IndexGroupExpandable = new ArrayList();
	private long[] expandedIds;
	private String GroupNameClicked;
	// MandantData.MandantCOLLECTION portfolio1;
	// List<MandantDefinition> listMandant;
	List<Mandant> listMandant;
	List<Wb> listWb;
	List<WorkBasket> listWBs;
	List<A_Basket_ProcessId_And_Date> listProcessIdAndDate;
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
	private ParserTask parserTask;
	private ProgressBar mProgressBar;
	private String atest = "a";
	private MainCountDown countDown;
	NewDBAdapter mDB;
	private final int[] RemoveGroup_Icon = { R.drawable.no_group,
			R.drawable.removegroupicon, R.drawable.removegroupicon,
			R.drawable.removegroupicon, R.drawable.removegroupicon,
			R.drawable.removegroupicon, R.drawable.removegroupicon,
			R.drawable.removegroupicon, R.drawable.removegroupicon,
			R.drawable.removegroupicon, R.drawable.removegroupicon,
			R.drawable.removegroupicon, R.drawable.removegroupicon };
	private final int[] SortGroup_Icon = { R.drawable.no_group,
			R.drawable.down_arrow, R.drawable.down_arrow,
			R.drawable.down_arrow, R.drawable.down_arrow,
			R.drawable.down_arrow, R.drawable.down_arrow,
			R.drawable.down_arrow, R.drawable.down_arrow,
			R.drawable.down_arrow, R.drawable.down_arrow,
			R.drawable.down_arrow, R.drawable.down_arrow };
	private final int[] RenameGroup_Icon = { R.drawable.no_group,
			R.drawable.edit, R.drawable.edit,
			R.drawable.edit, R.drawable.edit,
			R.drawable.edit, R.drawable.edit,
			R.drawable.edit, R.drawable.edit,
			R.drawable.edit, R.drawable.edit,
			R.drawable.edit, R.drawable.edit };
	MandantData.MandantList Mandantdata;
	WbListData.WbList WBdata;
	BasketProcessIdInfo.All_Baskets_ProcessId_And_Date Processdata;
	double testint = 0;
	String DoneConverted;
	String ItemNotConverted = new String();
	private String language = "en";
	private String StringWBClicked = "";
	int totalCountPId;
	List<String> ListTestProcessIdDate = new ArrayList();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getTestProcessDataProtobuf();
		Log.w(TAG, "ONCREATE.......");
		mDB = new NewDBAdapter(getApplicationContext());
		mDB.openDB();
		Cursor CurCountItemClicked = mDB.getItemClickByID();
		if (CurCountItemClicked.getCount() == 0) {
			mDB.insertItemClicked("itemClicked", "ItemNotConverted");
		} else {

		}
		CurCountItemClicked.close();
		Cursor curLanguage = mDB.getLanguageByID();
		if (curLanguage.getCount() == 0) {
			mDB.insertLanguageType("languageName", "en");
		} else {
			if (curLanguage.moveToFirst()) {
				do {
					language = curLanguage.getString(1);
				} while (curLanguage.moveToNext());

			}

		}
		curLanguage.close();
		view = (TextView) findViewById(R.id.tv);
		//view2 = (TextView) findViewById(R.id.tv2);
		// view2.setText(language);
		mGroupName = (EditText) findViewById(R.id.namegroup);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

		/*
		 * for (int i = 0; i < data.length; i++) {
		 * deleteGroupIcon[i]=BitmapFactory.decodeResource(this.getResources(),
		 * data[i]);
		 * deleteGroupIcon2[i]=Bitmap.createScaledBitmap(deleteGroupIcon[i], 32,
		 * 32, false); }
		 */
		/*
		 * if (checkInternetConnection() == true) { view2.setText("Co ket noi");
		 * } else { view2.setText("Khong co ket noi"); }
		 */
		getMandantDataProtobuf();
		getMandantListFromServer();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences myPreference = PreferenceManager
				.getDefaultSharedPreferences(this);
		String ColorGroupName = new String();
		ColorGroupName = ""
				+ myPreference.getString("listPrefTextColorGroup", "Black");
		if (ColorGroupName.equals("Yellow")) {
			Color_GroupName = 0xffffff00;
		} else if (ColorGroupName.equals("Red")) {
			Color_GroupName = 0xffff0000;
		} else if (ColorGroupName.equals("Black")) {
			Color_GroupName = 0xff000000;
		} else if (ColorGroupName.equals("White")) {
			Color_GroupName = 0xffffffff;
		} else if (ColorGroupName.equals("Green")) {
			Color_GroupName = 0xff00ff00;
		} else if (ColorGroupName.equals("Blue")) {
			Color_GroupName = 0xff0000ff;
		} else if (ColorGroupName.equals("Pink")) {
			Color_GroupName = 0xffff00ff;
		} else if (ColorGroupName.equals("Turquoise")) {
			Color_GroupName = 0xff00ffff;
		}

		String ColorWorkBasket = new String();
		ColorWorkBasket = ""
				+ myPreference.getString("listPrefTextColorWorkBasket", "Red");
		if (ColorWorkBasket.equals("Yellow")) {
			Color_WorkBasket = 0xffffff00;
		} else if (ColorWorkBasket.equals("Red")) {
			Color_WorkBasket = 0xffff0000;
		} else if (ColorWorkBasket.equals("Black")) {
			Color_WorkBasket = 0xff000000;
		} else if (ColorWorkBasket.equals("White")) {
			Color_WorkBasket = 0xffffffff;
		} else if (ColorWorkBasket.equals("Green")) {
			Color_WorkBasket = 0xff00ff00;
		} else if (ColorWorkBasket.equals("Blue")) {
			Color_WorkBasket = 0xff0000ff;
		} else if (ColorWorkBasket.equals("Pink")) {
			Color_WorkBasket = 0xffff00ff;
		} else if (ColorWorkBasket.equals("Turquoise")) {
			Color_WorkBasket = 0xff00ffff;
		}

		String ColorTimeStamp = new String();
		ColorTimeStamp = ""
				+ myPreference.getString("listPrefTextColorTimestamp", "Black");
		if (ColorTimeStamp.equals("Yellow")) {
			Color_TimeStamp = 0xffffff00;
		} else if (ColorTimeStamp.equals("Red")) {
			Color_TimeStamp = 0xffff0000;
		} else if (ColorTimeStamp.equals("Black")) {
			Color_TimeStamp = 0xff000000;
		} else if (ColorTimeStamp.equals("White")) {
			Color_TimeStamp = 0xffffffff;
		} else if (ColorTimeStamp.equals("Green")) {
			Color_TimeStamp = 0xff00ff00;
		} else if (ColorTimeStamp.equals("Blue")) {
			Color_TimeStamp = 0xff0000ff;
		} else if (ColorTimeStamp.equals("Pink")) {
			Color_TimeStamp = 0xffff00ff;
		} else if (ColorTimeStamp.equals("Turquoise")) {
			Color_TimeStamp = 0xff00ffff;
		}

		String textSizeGroupName = new String();
		textSizeGroupName = ""
				+ myPreference.getString("listPrefTextSizeGroup", "Small");
		// Size_GroupName=Integer.parseInt(textSizeGroupName);
		if (textSizeGroupName.equals("Medium")) {
			Size_GroupName = 20;
		} else if (textSizeGroupName.equals("Small")) {
			Size_GroupName = 16;
		} else if (textSizeGroupName.equals("Very small")) {
			Size_GroupName = 13;
		} else if (textSizeGroupName.equals("Large")) {
			Size_GroupName = 22;
		} else if (textSizeGroupName.equals("Xlarge")) {
			Size_GroupName = 25;
		}

		String textSizeWorkBasket = new String();
		textSizeWorkBasket = ""
				+ myPreference
						.getString("listPrefTextSizeWorkBasket", "Small");
		// Size_WorkBasket=Integer.parseInt(textSizeWorkBasket);
		if (textSizeWorkBasket.equals("Medium")) {
			Size_WorkBasket = 20;
		} else if (textSizeWorkBasket.equals("Small")) {
			Size_WorkBasket = 16;
		} else if (textSizeWorkBasket.equals("Very small")) {
			Size_WorkBasket = 13;
		} else if (textSizeWorkBasket.equals("Large")) {
			Size_WorkBasket = 22;
		} else if (textSizeWorkBasket.equals("Xlarge")) {
			Size_WorkBasket = 25;
		}

		String textSizeTimeStamp = new String();
		textSizeTimeStamp = ""
				+ myPreference.getString("listPrefTextSizeTimeStamp", "Small");
		// Size_TimeStamp=Integer.parseInt(textSizeTimeStamp);
		if (textSizeTimeStamp.equals("Medium")) {
			Size_TimeStamp = 20;
		} else if (textSizeTimeStamp.equals("Small")) {
			Size_TimeStamp = 16;
		} else if (textSizeTimeStamp.equals("Very small")) {
			Size_TimeStamp = 13;
		} else if (textSizeTimeStamp.equals("Large")) {
			Size_TimeStamp = 22;
		} else if (textSizeTimeStamp.equals("Xlarge")) {
			Size_TimeStamp = 25;
		}
		// run();
	}

	public void convertWBName(String listconvert, String WbConvert) {

		String[] arInfo;
		arInfo = listconvert.split(",");
		for (int i = 0; i < arInfo.length; i++) {
			if (arInfo[i].contains(WbConvert)) {
				item = arInfo[i].substring(11);
			} else {
			}
		}
		itemClicked = item.replace("}", "");
	}

	public String convertWBID(String listconvert) {
		String mandant = new String();
		String WB = new String();
		String MandantConverted = new String();

		String[] arInfo;
		arInfo = listconvert.split("_");
		for (int i = 0; i < arInfo.length; i++) {

			mandant = arInfo[0];
			WB = arInfo[1];
		}
		MandantConverted = mandant.replace("[", "");
		MandantConverted = MandantConverted.replace("]", "");
		DoneConverted = WB + "_" + MandantConverted;
		return DoneConverted;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Object o1 = this.getExpandableListAdapter().getGroup(groupPosition);
		Object o2 = this.getExpandableListAdapter().getChild(groupPosition,
				childPosition);
		String GroupClicked = o1.toString();
		String ChildClicked = o2.toString();
		// view2.setText(itemClicked);

		convertWBName(ChildClicked, "workbname");
		ItemNotConverted = itemClicked;
		convertWBID(itemClicked);
		itemClicked = DoneConverted;

		getProcessDataProtobuf(itemClicked);
		ListProcess_PID_FromReaded.clear();
		for (A_Basket_ProcessId_And_Date A_Basket_ProcessId_And_Date_Loop : listProcessIdAndDate) {
			totalCountPId = A_Basket_ProcessId_And_Date_Loop.getPIdCount();
			if (totalCountPId == 0) {
				Toast.makeText(ANDROID_RSS_READER.this,
						"Sorry, No Data For This Process", Toast.LENGTH_LONG)
						.show();
			} else {

				for (int i = 0; i < A_Basket_ProcessId_And_Date_Loop
						.getPIdCount(); i++) {
					ListProcess_PID_FromReaded
							.add(A_Basket_ProcessId_And_Date_Loop.getPId(i));
				}
				for (int i = 0; i < ListProcess_PID_FromReaded.size(); i++) {
					mDB.changeProcessIsReaded(ListProcess_PID_FromReaded.get(i));
				}

				String CountTemp = new String();
				int unread;
				int count = 0;

				for (int m = 0; m < A_Basket_ProcessId_And_Date_Loop
						.getPIdCount(); m++) {
					Cursor cur = mDB.getProcessByAdded(""
							+ A_Basket_ProcessId_And_Date_Loop.getPId(m));
					count = count + cur.getCount();
					cur.close();
				}

				unread = totalCountPId - count;
				CountTemp = ("[" + unread + "/"
						+ A_Basket_ProcessId_And_Date_Loop.getPIdCount() + "]");
				mDB.UpdateCountWB(CountTemp, ItemNotConverted);

				Cursor CurCountClicked = mDB
						.getCountClickedWbByName(ItemNotConverted);
				int countWBClicked = 0;
				if (CurCountClicked.moveToFirst()) {
					do {
						StringWBClicked = CurCountClicked.getString(0);
						countWBClicked = Integer.parseInt(StringWBClicked) + 1;
						// count2=count2+1;
					} while (CurCountClicked.moveToNext());

				}
				CurCountClicked.close();
				mDB.UpdateCountClickedWB("" + countWBClicked, ItemNotConverted);
				mDB.UpdateItemClicked(itemClicked, ItemNotConverted);

				Toast.makeText(ANDROID_RSS_READER.this, itemClicked,
						Toast.LENGTH_LONG).show();
				messagefromClicked = o2.toString();
				Intent intent = new Intent();
				intent.setClass(ANDROID_RSS_READER.this,
						ShowListProcessWithDocs.class);
				startActivity(intent);
				//finish();

				// view2.setText(""+ListProcess_PID_FromReaded.size());
			//	mDB.closeDB();
				setTitle("GOING TO LIST PROCESS FOR WB : " + itemClicked);
			}

		}
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
							v.setText(Html.fromHtml("<b>"
									+ (String) data.get(from[i]) + "</b>"));
						}
					} else {
						v.setText((String) data.get(from[i]));
					}

					if (to[i] == R.id.child) {
						v.setTextColor(Color_WorkBasket);
						v.setTextSize(Size_WorkBasket);
					}
					if (to[i] == R.id.r2) {
						v.setTextColor(Color_TimeStamp);
						v.setTextSize(Size_TimeStamp);
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
					v.setTextColor(Color_GroupName);
					v.setTextSize(Size_GroupName);
				}
			}

			for (int i = 1; i < len; i++) {

				final int t = groupPosition;
				final int x = t + 1;
				IdGroup = "" + x;
				Button Button_group = (Button) view.findViewById(to[i]);

				// Button_group.setBackgroundColor(color[groupPosition]);

				/*
				 * if(groupPosition==0&&mGroupData.get(groupPosition).containsValue
				 * ("No_Group")){ Button_group.setBackgroundColor(0xdedede); }
				 */

				if (Button_group != null && to[i] == R.id.delete_group) {
					Button_group.setBackgroundDrawable(getResources()
							.getDrawable(RemoveGroup_Icon[groupPosition]));
					// Button_group.(deleteGroupIcon2[groupPosition]);
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
					Button_group.setBackgroundDrawable(getResources()
							.getDrawable(RenameGroup_Icon[groupPosition]));
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
								//finish();
								//mDB.closeDB();
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

								// mDB.openDB();
								mDB.changeIDGroup("" + idgroupClicked, "100");
								mDB.changeIDGroup("" + id2groupClicked, ""
										+ idgroupClicked);
								mDB.changeIDGroup("100", "" + id2groupClicked);

								updateNumberOfGroupinDB();
								mDB.changeIDWB("" + idgroupClicked, "100");
								mDB.changeIDWB("" + id2groupClicked, ""
										+ idgroupClicked);
								mDB.changeIDWB("100", "" + id2groupClicked);
								// getListItemForEachGroup();
								// showProcessBar();
								expandable();

							}

						}
					});
				}
			}

		}
	}

	public void expandable() {
		long startTime = System.currentTimeMillis();
		if (numberOfGroup == 0) {
			view.setText("There is no group");
		} else {
			view.setText("There are " + numberOfGroup + " Groups");
		//	view.setText(""+ListTestProcessIdDate.size()+ListTestProcessIdDate.get(0)+ListTestProcessIdDate.get(1));
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

		List<List<String>> ListAllListItems = new ArrayList<List<String>>();
		;
		List<List<String>> ListAllListTimeDate = new ArrayList<List<String>>();
		;
		List<List<String>> ListAllListCount = new ArrayList<List<String>>();
		;
		int t = numberOfGroup + 1;
		for (int i = 1; i < t; i++) {
			Cursor c = mDB.getWbBy_InGroup_ID("" + i);
			List<String> ListItemGroup = new ArrayList();
			List<String> ListTimeDateGroup = new ArrayList();
			List<String> ListItemCountGroup = new ArrayList();
			if (c.moveToFirst()) {
				do {
					ListItemGroup.add(c.getString(1));
					ListTimeDateGroup.add(c.getString(5));
					ListItemCountGroup.add(c.getString(4));
				} while (c.moveToNext());

			}

			ListAllListItems.add(ListItemGroup);
			ListAllListTimeDate.add(ListTimeDateGroup);
			ListAllListCount.add(ListItemCountGroup);

			c.close();
		}

		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < numberOfGroup; i++) {
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
					// @Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, final int groupPosition, long id) {
						atest = "b";
						int NumExpandId = groupPosition + 1;
						String GroupClicked = getExpandableListView()
								.getExpandableListAdapter()
								.getGroup(groupPosition).toString();
						String[] arInfo;
						arInfo = GroupClicked.split(",");
						for (int i = 0; i < arInfo.length; i++) {
							if (arInfo[i].contains("GroupName")) {
								item = arInfo[i].substring(11);
							} else {

							}
						}
						GroupNameClicked = item.replace("}", "");
						// mDB.openDB();
						if (IndexGroupExpandable.get(groupPosition).equals("1")) {

							mDB.renameExpandId("1", "0", GroupNameClicked);
							updateNumberOfGroupinDB();
						} else if (IndexGroupExpandable.get(groupPosition)
								.equals("0")) {
							mDB.renameExpandId("0", "1", GroupNameClicked);
							updateNumberOfGroupinDB();
						}
						// mDB.closeDB();
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
		// mDB.closeDB();
		// view2.setText(""+ListProcess_PID_FromDB.size());
		// view2.setText(""+ListProcess_PID_FromDB.size()+ListProcess_PID_FromDB.get(0)+" "+ListProcess_PID_FromDB.get(1)+" "+ListProcess_PID_FromDB.get(2)+" "+ListProcess_PID_FromDB.get(3)+" "+ListProcess_PID_FromDB.get(4));

		long endTime = System.currentTimeMillis();
		System.out.println("expandable() took " + (endTime - startTime)
				+ " MiliSeconds");
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
			// RemoveGroupDialog();
			break;
		case 1:
			startActivity(new Intent(ANDROID_RSS_READER.this,
					com.androidrss.preference.Setting.class));
			// finish();
			//mDB.closeDB();
			break;

		case 3:
			
			updateMandantinDB();
			// MandantListfromServer.removeAll(MandantListEnablefromDB);
			String[] ssMandants = new String[MandantListfromServer.size()];
			isCheckedMandantListfromServer = new boolean[MandantListfromServer
					.size()];

			for (int i = 0; i < MandantListfromServer.size(); i++) {
				ssMandants[i] = MandantListfromServer.get(i);
				isCheckedMandantListfromServer[i] = false;
				// MandantListfromServer.removeAll(MandantListEnablefromDB);

			}
			arr_CS_allMandants = ssMandants;
			openDialogWithMandants();

			// populateQuickActionDialog();
			// mQuickAction.show(v);
			break;
		case 4:
			reload();
			break;
		case 5:
			moveToGroup();
			break;
		case 6:
			Intent intent = new Intent();
			intent.setClass(ANDROID_RSS_READER.this, RemoverMandant.class);
			startActivity(intent);
			//finish();
			// System.exit(0);
		//	mDB.closeDB();
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
		//mDB.closeDB();
		//finish();
		setTitle("Going To Add Group View");
	}

	public void moveToGroup() {
		Intent intent = new Intent();
		intent.setClass(ANDROID_RSS_READER.this, MoveToGroup.class);
		startActivity(intent);
		// finish();
	//	mDB.closeDB();
		setTitle("Going To Move To Group View");
	}

	public void openDialogWithMandants() {

		adSelectMandant = new AlertDialog.Builder(this)
				.setTitle("Select Mandant to Add :")
				.setIcon(R.drawable.star_big_on)
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
						})
				.setNeutralButton("Add", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						/*
						 * countDown = new MainCountDown(180, 180);
						 * countDown.start();
						 */
						/*
						 * mProgressBar.setVisibility(View.VISIBLE); countDown =
						 * new MainCountDown(180, 180); countDown.start();
						 */
						updateMandantinDB();
						addMandanttoDB();
						/*
						 * MandantListSelectedfromDialog.clear(); countDown =
						 * new MainCountDown(180, 180); countDown.start();
						 */

						// reload();

						updateNumberOfGroupinDB();
						updateMandantinDB();
						getWBListFromUri();
						updateWBinDB();
						addWBtoDB();
						expandable();

						// reload();
						/*
						 * updateMandantinDB(); getWBListFromUri();
						 * updateWBinDB(); addWBtoDB();
						 * getListItemForEachGroup(); expandable();
						 */
						MandantListSelectedfromDialog.clear();
					}
				})
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								MandantListSelectedfromDialog.clear();
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

	public void openDialogWithWBs() {
		test.clear();
		adSelectWB = new AlertDialog.Builder(this)
				.setTitle(R.string.alert_dialog_multi_choice)
				.setIcon(R.drawable.alert)
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
						})
				.setNeutralButton(R.string.alert_dialog_ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								addSelectedItems();
							}
						})
				.setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).show();
	}

	public void setCheckAllWB() {
		if (isAllChecked) {
			for (int i = 0; i < WbListFromServer.size(); i++) {
				isCheckedWBListfromServer[i] = true;
				test.add(WbListFromServer.get(i));
			}
		} else {
			for (int i = 0; i < WbListFromServer.size(); i++) {
				isCheckedWBListfromServer[i] = false;
			}
		}

		openDialogWithWBs();
	}

	public void getTestProcessDataProtobuf() {
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = new URL(
					"http://192.168.16.121:8080/moxseed/spring/basketapp?basketIds=inbox_ani23012012,storage_ani2301201&responseType=protobuf&action=getbasketsprocessidslist")
					.openStream();
			Processdata = BasketProcessIdInfo.All_Baskets_ProcessId_And_Date
					.parseFrom(is);
			listProcessIdAndDate = Processdata.getABasketProcessIdAndDateList();
			
			for (A_Basket_ProcessId_And_Date A_Basket_ProcessId_And_Date_Loop : listProcessIdAndDate) {
				ListTestProcessIdDate.add(A_Basket_ProcessId_And_Date_Loop.getBasketId());
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("getProcessDataProtobuf() took "
				+ (endTime - startTime) + " MiliSeconds");
	}
	
	
	public void getProcessDataProtobuf(String WorkBasket) {
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = new URL(
					"http://192.168.16.121:8080/moxseed/spring/basketapp?basketIds="
							+ WorkBasket
							+ "&responseType=protobuf&action=getbasketsprocessidslist")
					.openStream();
			Processdata = BasketProcessIdInfo.All_Baskets_ProcessId_And_Date
					.parseFrom(is);
			listProcessIdAndDate = Processdata.getABasketProcessIdAndDateList();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("getProcessDataProtobuf() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void getMandantDataProtobuf() {
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = new URL(
			// "https://my-ea.oxseed.net/oxseedadmin/ext/oxplorer?content=mandantList&login=admin&pass=oxseed")
					"http://192.168.16.121:8888/oxseedadmin/ext/oxplorer?content=mandantList&login=admin&pass=oxseed&format=pbf")
					.openStream();
			Mandantdata = MandantData.MandantList.parseFrom(is);
			listMandant = Mandantdata.getMandantList();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			// view2.setText("CONNECT TIME OUT");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getMandantDataProtobuf take : "
				+ (endTime - startTime) + " (MiliSeconds)");

	}

	public void getMandantListFromServer() {
		long startTime = System.currentTimeMillis();
		try {
			for (Mandant mandant : listMandant) {
				MandantListfromServer.add(mandant.getId());
			}
		} catch (Exception e) {
			Log.e("TRANG TRADER 1", "Exception getting ProtocolBuffer data", e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getMandantListFromServer() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void getWbDataProtobuf(String Mandant) {
		long startTime = System.currentTimeMillis();
		try {
			InputStream is = new URL("http://192.168.16.121:8888/" + Mandant
					+ "/ext/oxplorer?content=wbList&login=admin&pass=oxseed&format=pbf")
					.openStream();
			WBdata = WbListData.WbList.parseFrom(is);
			listWb = WBdata.getWbList();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (Wb wbLoop : listWb) {
				// WbListFromServer.add(wbLoop.getId() + "_" + Mandant);
				WbListFromServer
						.add("[" + Mandant + "]" + "_" + wbLoop.getId());

			}
		} catch (Exception e) {
			/*WbListFromServer
			.add("[" + Mandant + "]" + "_Null");*/
			Log.e("TRANG TRADER 2", "Exception getting ProtocolBuffer data", e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("getWbDataProtobuf() took " + (endTime - startTime)
				+ " MiliSeconds");

	}

	public void getWBListFromUri() {
		long startTime = System.currentTimeMillis();
		try {
			for (int i = 0; i < MandantListSelectedfromDialog.size(); i++) {

				getWbDataProtobuf(MandantListSelectedfromDialog.get(i));
			}

		} catch (Exception e) {
			Log.e("TRANG TRADER 3 ", "Exception getting ProtocolBuffer data", e);
			Log.w(TAG, "Wblist size : " + WbListFromServer.size());
		}

		long endTime = System.currentTimeMillis();
		System.out.println("getWBListFromUri() took " + (endTime - startTime)
				+ " MiliSeconds");
	}

	// ------------------------ADD WORKBASKET TO DB----------------------------

	public void addWBtoDB() {
		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		java.util.Date timestamp = null;
		String CountTemp = new String();
		for (int i = 0; i < WbListFromServer.size(); i++) {
			if (WBListEnablefromDB.contains(WbListFromServer.get(i))) {
			} else {

				convertWBID(WbListFromServer.get(i));
				getProcessDataProtobuf(DoneConverted);
				try {
					for (A_Basket_ProcessId_And_Date A_Basket_ProcessId_And_Date_Loop : listProcessIdAndDate) {
						timestamp = df.parse(A_Basket_ProcessId_And_Date_Loop
								.getLastBuildDate());
						int unread;
						int count = 0;
						int total = A_Basket_ProcessId_And_Date_Loop
								.getPIdCount();

						for (int m = 0; m < A_Basket_ProcessId_And_Date_Loop
								.getPIdCount(); m++) {
							Cursor cur = mDB.getProcessByAdded(""
									+ A_Basket_ProcessId_And_Date_Loop
											.getPId(m));
							count = count + cur.getCount();
							cur.close();
						}
						if (total<count) {
							unread=0;
						}
						else {
							unread = total - count;
						}
						

						CountTemp = ("["
								+ unread
								+ "/"
								+ A_Basket_ProcessId_And_Date_Loop
										.getPIdCount() + "]");
					}
				} catch (java.text.ParseException e) {
					e.printStackTrace();
				}
				getSecondsDifference(timestamp);

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

				mDB.insertNewWorkBasket(WbListFromServer.get(i), "1", "1",
						CountTemp, timerLoop);
				getListProcess_PID();
				for (int m = 0; m < ListProcess_PID_FromServer.size(); m++) {
					mDB.insertNewProcess(ListProcess_PID_FromServer.get(m),
							"type_id", "type_name", "0");
				}

				updateWBinDB();
			}
		}
	}

	public void getListProcess_PID() {
		ListProcess_PID_FromServer.clear();
		try {
			for (A_Basket_ProcessId_And_Date A_Basket_ProcessId_And_Date_Loop : listProcessIdAndDate) {
				for (int i = 0; i < A_Basket_ProcessId_And_Date_Loop
						.getPIdCount(); i++) {
					ListProcess_PID_FromServer
							.add(A_Basket_ProcessId_And_Date_Loop.getPId(i));
					// mDB.insertUnreadCountProcess(""+A_Basket_ProcessId_And_Date_Loop.getPIdCount());
				}

			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}
	}

	public void updateWBinDB() {
		WBListEnablefromDB.clear();
		// mDB.openDB();
		Cursor c = mDB.getWbByEnable("1");
		if (c.moveToFirst()) {
			do {
				WBListEnablefromDB.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();
		updateProcess_PIDinDB();
	}

	public void updateProcess_PIDinDB() {
		ListProcess_PID_FromDB.clear();
		Cursor c = mDB.getProcessByAdded("0");
		if (c.moveToFirst()) {
			do {
				ListProcess_PID_FromDB.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();
	}

	public void addSelectedItems() {
		updateWBinDB();
		addWBtoDB();
		updateNumberOfGroupinDB();
		// getListItemForEachGroup();
		expandable();
	}

	public void addGrouptoDB(String groupname) {
		int num = numberOfGroup + 1;
		mDB.insertNewGroup(groupname, "1", "1", "" + num);
		updateNumberOfGroupinDB();
	}

	public void updateNumberOfGroupinDB() {
		long startTime = System.currentTimeMillis();
		// mDB.openDB();
		GroupListEnablefromDB.clear();
		IndexGroupExpandable.clear();
		// mDB.openDB();
		Cursor cursor = mDB.getGroupByEnable("1");
		numberOfGroup = cursor.getCount();
		for (int i = 0; i < numberOfGroup; i++) {
			int num = i + 1;
			Cursor c = mDB.getGroupByPosition("1", "" + num);

			if (c.moveToFirst()) {
				do {
					GroupListEnablefromDB.add(c.getString(1));
				} while (c.moveToNext());
			}
			c.close();
		}
		cursor.close();
		Cursor cursorExpand = mDB.getExpand("1");
		if (cursorExpand.moveToFirst()) {
			do {
				IndexGroupExpandable.add(cursorExpand.getString(3));
			} while (cursorExpand.moveToNext());
		}
		cursorExpand.close();
		long endTime = System.currentTimeMillis();
		System.out.println("updateNumberOfGroupinDB() took "
				+ (endTime - startTime) + " MiliSeconds");
	}

	public void addMandanttoDB() {
		for (int x = 0; x < MandantListSelectedfromDialog.size(); x++) {
			mDB.insertNewMandant(MandantListSelectedfromDialog.get(x), "1");

		}

	}

	public void updateMandantinDB() {

		MandantListEnablefromDB.clear();
		// mDB.openDB();
		Cursor c = mDB.getMandantByAdded("1");
		if (c.moveToFirst()) {
			do {
				MandantListEnablefromDB.add(c.getString(1));
			} while (c.moveToNext());
		}
		c.close();
		MandantListfromServer.removeAll(MandantListEnablefromDB);
	}

	public void ChangeIdWB(int idgroup, int NumOfGroup) {
		int extra;
		extra = NumOfGroup - idgroup;
		for (int i = 0; i < extra; i++) {
			int j = i + 1;
			int OldID = idgroup + j;
			int NewID = idgroup + i;
			mDB.changeIDWB("" + OldID, "" + NewID);
			// mDB.closeDB();
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
	private int count;

	public void deleteAll() {
		mDB.DropDatabase(getApplicationContext());
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
		new AlertDialog.Builder(this)
				.setTitle("Report")
				.setIcon(R.drawable.dialog_warning)
				.setMessage("You cannot Remove No_Group")
				.setNegativeButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})

				.show();
	}

	private void openDialog2() {
		new AlertDialog.Builder(this)
				.setTitle("Report")
				.setIcon(R.drawable.dialog_warning)
				.setMessage("You cannot Rename No_Group")
				.setNegativeButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})

				.show();
	}

	private void openDialog_CheckConnection() {
		new AlertDialog.Builder(this)
				.setTitle("Report")
				.setIcon(R.drawable.dialog_warning)
				.setMessage("Problem with your Connection..Try again later")
				.setNegativeButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
							}
						})

				.show();
	}

	private void openDialogRemoveGroup() {
		new AlertDialog.Builder(this)
				.setTitle("Report")
				.setIcon(R.drawable.dialog_warning)
				.setMessage(
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
								// removeGroup(itemClicked, idgrouptoRemove);
								// mDB.openDB();
								/*
								 * mDB.removeGroup(selectedGrouptoRemove,
								 * idgrouptoRemove);
								 */
								int t = idgrouptoRemove + 1;
								int n = numberOfGroup + 1;
								/*
								 * for (int k = t; k < n; k++) { int m = k - 1;
								 * mDB.changeIDGroup("" + k, "" + m); }
								 */
								mDB.deleteGroup(itemClicked);

								/*
								 * ChangeIdWB(idgrouptoRemove,
								 * GroupListEnablefromDB.size());
								 */
								updateWBinDB();
								updateNumberOfGroupinDB();
								// getListItemForEachGroup();
								expandable();
								// reload();
							}
						})
				.setNegativeButton(R.string.alert_dialog_cancel,
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
			updateNumberOfGroupinDB();
			if (numberOfGroup == 0) {
				newGroup = "No_Group";
				addGrouptoDB(newGroup);
			} else {
				updateMandantinDB();
				getWBListFromUri();
				updateWBinDB();
				addWBtoDB();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Long arg0) {
			expandable();

			//setTitle("" + testint);
			Log.w(TAG, "MAndantInDB : " + MandantListEnablefromDB.size());
			// view2.setText("WBListEnablefromDB Size :"+
			// WBListEnablefromDB.size());
			Log.w(TAG, "numberOfGroup : " + numberOfGroup);

			mProgressBar.setVisibility(View.GONE);
			// mDB.closeDB();
		}
	}

	public class MainCountDown extends CountDownTimer {

		public MainCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			parserTask = new ParserTask();
			parserTask.execute();

		}

		@Override
		public void onTick(long millisUntilFinished) {
		}
	}

	public void reload() {
		mDB.closeDB();
		startActivity(getIntent());
		// finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK: {
			mDB.closeDB();
			// finish();
			System.exit(0);
		}
		}
		return false;
	}

	private Boolean isOnline() {

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo ni = cm.getActiveNetworkInfo();

		if (ni != null && ni.isConnected()) {

			return true;

		}

		return false;

	}

	private boolean checkInternetConnection() {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("ping fr.yahoo.com -c 1"); // other
																	// servers,
																	// for
																	// example
			proc.waitFor();
			int exit = proc.exitValue();
			if (exit == 0) { // normal exit
				return true;
			} else { // abnormal exit, so decide that the server is not
						// reachable

			}
		} catch (Exception e) {
		}
		return false;
	}

	/*
	 * public void loadtime() { testint =testint+ Math.random(); }
	 */

	public void run() {
		new Thread(new Runnable() {
			public void run() {
				while (keepRunning) {
					try {
						if (!keepRunning)
							return;
						Thread.sleep(interval);
						if (!keepRunning)
							return;
						mHandler.post(new Runnable() {
							// @Override
							public void run() {
								if (!keepRunning)
									return;
								// showProcessBar();
								expandable();
							}
						});
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();
		if (!keepRunning)
			return;
	}

	public synchronized void stopRunning() {
		this.keepRunning = false;

	}

	@Override
	protected void onDestroy() {
		stopRunning();
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		stopRunning();
		super.onStop();

	}

	@Override
	protected void onPause() {
		stopRunning();
		super.onPause();

	}
}