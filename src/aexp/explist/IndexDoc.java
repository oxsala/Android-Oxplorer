package aexp.explist;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class IndexDoc extends ListActivity implements OnTouchListener {
	private TextView backButton;
	private Button i_symbol_button;
	private String messageLinkWB;
	private String messageDocName;
	List<String> contentList = new ArrayList<String>();
	List<String> valueList = new ArrayList<String>();
	List<String> FirstValueList = new ArrayList<String>();
	List<String> TypeDocList = new ArrayList<String>();
	List<String> CurrencyList = new ArrayList<String>();
	private final List<Object> listItems = new ArrayList<Object>();
	private ListItemsAdapter adapter = null;
	private final String Type = "Type";
	private final String Creditor = "Creditor";
	private final String Amount = "Amount";
	private final String Currency = "Currency";
	private String NewValue;
	private EditText NewValueEditText;
	private int positionChange;
	PopupWindow popUp;
	LinearLayout layout;
	boolean click = true;
	TextView tv;
	Button btn;
	LayoutParams params;
	ListView lv;
	private Gallery gallery;
	private final Integer[] Imgid = { R.drawable.i1, R.drawable.i2,
			R.drawable.i3 };
	int position;
	CharSequence[] arr_CS_TypeDocList;
	AlertDialog adSelectTypeDoc;
	private MainCountDown countDown;
	private ProgressBar mProgressBar;
	private ParserTask parserTask;
	Bitmap kangoo;
	private final String[] LinkImage = {
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I1.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I3.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I2.png" };
	private TextView DocTitle;
	private TextView NumPage;
	private final int[] data = { R.drawable.i1, R.drawable.i2, R.drawable.i3,
			R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i1,
			R.drawable.i2, R.drawable.i3, R.drawable.i1, R.drawable.i2,
			R.drawable.i3 };
	private final String[] name = { "1", "2" };
	LinearLayout layMain;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indexdoc);
		// loadCanvas();
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		TypeDocList.add("Doc Type 1");
		TypeDocList.add("Doc Type 2");
		TypeDocList.add("Doc Type 3");
		CurrencyList.add("EURO");
		CurrencyList.add("USD");
		CurrencyList.add("VND");
		layout = new LinearLayout(this);
		tv = new TextView(this);
		lv = new ListView(this);
		btn = new Button(this);

		popUp = new PopupWindow(this);
/*		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage3;
		CharSequence textDocName = bundle.getCharSequence("messageDocName");
		messageDocName = "" + textDocName;*/

		DocTitle = (TextView) findViewById(R.id.title_doc);
		DocTitle.setText(messageDocName);

		NumPage = (TextView) findViewById(R.id.num_page);
		NumPage.setText("Number Of Page : " + data.length);

		backButton = (TextView) findViewById(R.id.index_doc_btn_back);
		backButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(IndexDoc.this, ShowListProcessWithDocs.class);
				startActivity(intent);
			//	finish();
			}
		});

		i_symbol_button = (Button) findViewById(R.id.i_symbol);
		i_symbol_button.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				CharSequence textMessage;
				CharSequence textDocName;
				textMessage = "" + messageLinkWB;
				textDocName = "" + messageDocName;
				bundle.putCharSequence("messageLinkWB", textMessage);
				bundle.putCharSequence("messageDocName", textDocName);
				intent.putExtras(bundle);
				intent.setClass(IndexDoc.this, test2.class);
				startActivity(intent);
				//finish();
			}
		});

		FirstValueList.add("bill");
		FirstValueList.add("Coca Cola");
		FirstValueList.add("100,00");
		FirstValueList.add("Euro");

	}

/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {

			break;
		}
		case KeyEvent.KEYCODE_DPAD_RIGHT: {

			break;
		}
		case KeyEvent.KEYCODE_DPAD_UP: {
			break;
		}
		case KeyEvent.KEYCODE_DPAD_DOWN: {

			break;
		}
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			CharSequence textmessageLinkWB;
			textmessageLinkWB = messageLinkWB;
			bundle.putCharSequence("messageLinkWB", textmessageLinkWB);
			intent.putExtras(bundle);
			intent.setClass(IndexDoc.this, ShowListProcessWithDocs.class);
			startActivity(intent);
		//	finish();
		}

		}
		return false;
	}*/

	private class ListItemsAdapter extends ArrayAdapter<Object> {
		private int size;

		public ListItemsAdapter(List<Object> items) {
			super(IndexDoc.this, R.layout.row, items);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final int t = position;
			ViewHolder holder;
			ViewHolder Value;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				convertView = inflater.inflate(R.layout.row, parent, false);
			}
			LayoutInflater inflater = getLayoutInflater();
			convertView = inflater.inflate(R.layout.row, null);
			holder = new ViewHolder();
			Value = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.TextView01);
			;
			holder.text.setText(contentList.get(position));

			Value.text = (TextView) convertView.findViewById(R.id.TextView02);
			Value.text.setText(valueList.get(position));
			Button holder2 = (Button) convertView.findViewById(R.id.Change);
			holder2.setOnClickListener(new View.OnClickListener() {

				//@Override
				public void onClick(View v) {
					positionChange = t;
					if (contentList.get(positionChange).equals("Type:")) {
						ShowTypeDocListDialog();
					} else if (contentList.get(positionChange).equals(
							"Currency:")) {
						ShowCurrencyListDialog();
					} else {
						showAddDialog();
					}

					/*
					 * Toast.makeText(getBaseContext(), "CHANGE"+positionChange,
					 * Toast.LENGTH_SHORT).show();
					 */
				}
			});
			return convertView;
		}

		private class ViewHolder {
			TextView text;
		}
	}

	private void showAddDialog() {

		final String TAG = "test";
		final Dialog loginDialog = new Dialog(this);
		loginDialog.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
				WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		loginDialog.setTitle("Change Value");

		LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = li.inflate(R.layout.add_dailog, null);
		loginDialog.setContentView(dialogView);

		loginDialog.show();

		NewValueEditText = (EditText) dialogView.findViewById(R.id.newValue);
		/*
		 * ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
		 * .showSoftInput(NewValueEditText, 0);
		 */
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

		NewValueEditText.setOnClickListener(new View.OnClickListener() {
			//@Override
			public void onClick(View v) {
				NewValueEditText.setText("");

			}

		});
		if (contentList.get(positionChange).equals("Amount:")) {
			NewValueEditText.setRawInputType(Configuration.KEYBOARD_12KEY);

			/*
			 * NumberFormat format = NumberFormat.getNumberInstance(); String
			 * numAsStr = "1,000,000"; numAsStr =
			 * format.format(valueList.get(positionChange));
			 * NewValueEditText.setText(numAsStr);
			 */

			/*
			 * DecimalFormat twoDecimalPlaces = new DecimalFormat("#,##0.00");
			 * double val = Double.parseDouble(valueList.get(positionChange));
			 * NewValueEditText.setText(twoDecimalPlaces.format(val));
			 */

		} else {

		}
		NewValueEditText.setText(valueList.get(positionChange));
		Button addButton = (Button) dialogView.findViewById(R.id.add_button);
		Button cancelButton = (Button) dialogView
				.findViewById(R.id.cancel_button);

		addButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
				setNullAsyncTask();
				NewValue = NewValueEditText.getText().toString();
				loginDialog.dismiss();
				FirstValueList.remove(positionChange);
				FirstValueList.add(positionChange, NewValue);
				mProgressBar.setVisibility(View.VISIBLE);
				countDown = new MainCountDown(180, 180);
				countDown.start();

				Toast
						.makeText(
								getBaseContext(),
								"You've changed succesfully to new value : "
										+ NewValue, Toast.LENGTH_LONG).show();
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				loginDialog.dismiss();
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
			}
		});

	}

	public void populateView() {
		listItems.removeAll(listItems);
		contentList.removeAll(contentList);
		valueList.removeAll(valueList);
		for (int i = 0; i < FirstValueList.size(); i++) {
			valueList.add(FirstValueList.get(i));
		}
		contentList.add(Type + ":");
		contentList.add(Creditor + ":");
		contentList.add(Amount + ":");
		contentList.add(Currency + ":");

		for (int i = 0; i < 4; i++) {
			Object object = new Object();
			listItems.add(object);
		}
		adapter = new ListItemsAdapter(listItems);
		this.setListAdapter(adapter);
	}

	/*
	 * public class AddImgAdp extends BaseAdapter { int GalItemBg; private
	 * Context cont; private final LayoutInflater inflater = null;
	 * 
	 * public AddImgAdp(Context c) { cont = c; TypedArray typArray =
	 * obtainStyledAttributes(R.styleable.GalleryTheme); GalItemBg =
	 * typArray.getResourceId(
	 * R.styleable.GalleryTheme_android_galleryItemBackground, 0);
	 * typArray.recycle(); }
	 * 
	 * public AddImgAdp(Mens mens) { // TODO Auto-generated constructor stub }
	 * 
	 * public int getCount() { return Imgid.length; }
	 * 
	 * public Object getItem(int position) { return position; }
	 * 
	 * public long getItemId(int position) { return position; }
	 * 
	 * public View getView(int position, View convertView, ViewGroup parent) {
	 * 
	 * ImageView imgView = new ImageView(cont);
	 * 
	 * imgView.setImageResource(Imgid[position]); imgView.setLayoutParams(new
	 * Gallery.LayoutParams(80, 70));
	 * imgView.setScaleType(ImageView.ScaleType.FIT_XY);
	 * imgView.setBackgroundResource(GalItemBg);
	 * 
	 * return imgView;
	 * 
	 * View vi = convertView; ViewHolder holder; if (convertView == null) { vi =
	 * inflater.inflate(R.layout.custom_gallery, null); holder = new
	 * ViewHolder(); holder.text = (TextView)
	 * vi.findViewById(R.id.textViewGallery); holder.image = (ImageView)
	 * vi.findViewById(R.id.imageGallery); vi.setTag(holder); } else holder =
	 * (ViewHolder) vi.getTag(); holder.text.setText(name[position]); final int
	 * stub_id = data[position]; holder.image.setImageResource(stub_id);
	 * 
	 * holder.image.setLayoutParams(new Gallery.LayoutParams(80, 70));
	 * holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
	 * 
	 * return vi; } }
	 */
	public class ImageAdapter2 extends BaseAdapter {
		int GalItemBg;
		private final Activity activity;
		private LayoutInflater inflater = null;

		public ImageAdapter2(Activity a) {
			activity = a;
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			TypedArray typArray = obtainStyledAttributes(R.styleable.GalleryTheme);
			GalItemBg = typArray.getResourceId(
					R.styleable.GalleryTheme_android_galleryItemBackground, 0);
			typArray.recycle();
		}

		public int getCount() {
			return data.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public class ViewHolder {
			public TextView text;
			public ImageView image;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View vi = convertView;
			final int t = position + 1;
			final ViewHolder holder;
			if (convertView == null) {
				vi = inflater.inflate(R.layout.custom_gallery, null);
				holder = new ViewHolder();
				holder.text = (TextView) vi.findViewById(R.id.textView1);
				holder.image = (ImageView) vi.findViewById(R.id.image);
				vi.setTag(holder);
			} else {
				holder = (ViewHolder) vi.getTag();
			}
			holder.text.setText("" + t);
			final int stub_id = data[position];
			holder.image.setImageResource(stub_id);
			holder.image.setBackgroundResource(GalItemBg);

			// holder.image.setLayoutParams(new Gallery.LayoutParams(80, 70));
			holder.image.setScaleType(ImageView.ScaleType.FIT_XY);

			/*
			 * holder.image.setOnClickListener(new OnClickListener() { //
			 * 
			 * @Override public void onClick(View v) { holder.text.setText("a");
			 * Toast.makeText(getBaseContext(), "TEST CLICK IMAGE",
			 * Toast.LENGTH_SHORT).show();
			 * 
			 * } });
			 */
			return vi;
		}

	}

	public void ShowTypeDocListDialog() {
		String[] ssTypeDocList = new String[TypeDocList.size()];
		for (int i = 0; i < TypeDocList.size(); i++) {
			ssTypeDocList[i] = TypeDocList.get(i);
		}

		arr_CS_TypeDocList = ssTypeDocList;

		adSelectTypeDoc = new AlertDialog.Builder(this).setTitle(
				"Select Document Type").setIcon(R.drawable.type_icon)
				.setSingleChoiceItems(arr_CS_TypeDocList, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								NewValue = arr_CS_TypeDocList[whichButton]
										.toString();

							}
						}).setPositiveButton("Change Process Type",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								ShowTypeProcessDialog();
							}
						}).setNeutralButton("Select",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								setNullAsyncTask();
								FirstValueList.remove(positionChange);
								FirstValueList.add(positionChange, NewValue);
								mProgressBar.setVisibility(View.VISIBLE);
								countDown = new MainCountDown(180, 180);
								countDown.start();
								Toast.makeText(
										getBaseContext(),
										"You've changed succesfully to new value : "
												+ NewValue, Toast.LENGTH_LONG)
										.show();

							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).show();
	}

	public void ShowTypeProcessDialog() {
		String[] ssTypeDocList = new String[TypeDocList.size()];
		for (int i = 0; i < TypeDocList.size(); i++) {
			ssTypeDocList[i] = TypeDocList.get(i);
		}

		arr_CS_TypeDocList = ssTypeDocList;

		adSelectTypeDoc = new AlertDialog.Builder(this).setTitle(
				"Select Document Type").setIcon(R.drawable.type_icon)
				.setSingleChoiceItems(arr_CS_TypeDocList, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								NewValue = arr_CS_TypeDocList[whichButton]
										.toString();

							}
						}).setNeutralButton("Select",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								setNullAsyncTask();
								FirstValueList.remove(positionChange);
								FirstValueList.add(positionChange, NewValue);
								mProgressBar.setVisibility(View.VISIBLE);
								countDown = new MainCountDown(180, 180);
								countDown.start();
								Toast.makeText(
										getBaseContext(),
										"You've changed succesfully to new value : "
												+ NewValue, Toast.LENGTH_LONG)
										.show();
							}
						}).setPositiveButton("Back",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								ShowTypeDocListDialog();

							}
						}).setNegativeButton(R.string.alert_dialog_cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).show();
	}

	public void ShowCurrencyListDialog() {
		String[] ssTypeDocList = new String[CurrencyList.size()];
		for (int i = 0; i < CurrencyList.size(); i++) {
			ssTypeDocList[i] = CurrencyList.get(i);
		}

		arr_CS_TypeDocList = ssTypeDocList;

		adSelectTypeDoc = new AlertDialog.Builder(this).setTitle(
				"Select Currency :").setSingleChoiceItems(arr_CS_TypeDocList,
				-1, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int whichButton) {
						NewValue = arr_CS_TypeDocList[whichButton].toString();

					}
				})

		.setPositiveButton("Select", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				setNullAsyncTask();
				FirstValueList.remove(positionChange);
				FirstValueList.add(positionChange, NewValue);
				mProgressBar.setVisibility(View.VISIBLE);
				countDown = new MainCountDown(180, 180);
				countDown.start();
				Toast
						.makeText(
								getBaseContext(),
								"You've changed succesfully to new value : "
										+ NewValue, Toast.LENGTH_LONG).show();

			}
		}).setNegativeButton(R.string.alert_dialog_cancel,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				}).show();
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
			kangoo = DecodeFile(LinkImage[0]);
			// kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight,
			// true);
			return null;
		}

		@Override
		protected void onPostExecute(Long arg0) {
			loadGallery();
			populateView();
			mProgressBar.setVisibility(View.GONE);

		}
	}

	public Bitmap DecodeFile(String Url) {
		try {
			URL aURL = new URL(Url);
			URLConnection conn = aURL.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			Bitmap bm = BitmapFactory.decodeStream(bis);
			bis.close();
			is.close();
			return bm;
		} catch (Exception e) {
			return null;
		}
	}

	public void loadGallery() {
		gallery = (Gallery) findViewById(R.id.examplegallery);
		gallery.setAdapter(new ImageAdapter2(this));
		// gallery.setAdapter(new AddImgAdp(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				final int pos = position + 1; // setImage(position);
				IndexDoc.this.position = position;

				/*
				 * Toast.makeText(getBaseContext(), "GOING TO PAGE " + pos,
				 * Toast.LENGTH_SHORT).show();
				 */
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				CharSequence textMessage;
				CharSequence textDocName;
				textMessage = "" + messageLinkWB;
				textDocName = "" + messageDocName;
				bundle.putCharSequence("messageLinkWB", textMessage);
				bundle.putCharSequence("messageDocName", textDocName);
				intent.putExtras(bundle);
				intent.setClass(IndexDoc.this, test2.class);
				startActivity(intent);
				//finish();

			}
		});
		gallery.setOnItemLongClickListener(new OnItemLongClickListener() {
			//@Override
			public boolean onItemLongClick(AdapterView parent, View v,
					int position, long id) {
				/*
				 * final int pos = position + 1;
				 * Toast.makeText(getBaseContext(), "GOING TO PAGE " + pos,
				 * Toast.LENGTH_SHORT).show(); Intent intent = new Intent();
				 * Bundle bundle = new Bundle(); CharSequence textMessage;
				 * CharSequence textDocName; textMessage = "" + messageLinkWB;
				 * textDocName = "" + messageDocName;
				 * bundle.putCharSequence("messageLinkWB", textMessage);
				 * bundle.putCharSequence("messageDocName", textDocName);
				 * intent.putExtras(bundle); intent.setClass(IndexDoc.this,
				 * ViewImagesActivity.class); startActivity(intent); finish();
				 */
				return false;
			}
		});
	}

	private void setNullAsyncTask() {
		if (parserTask != null) {
			parserTask.cancel(true);
			parserTask = null;
		}
	}

	public void reload() {
		startActivity(getIntent());
		finish();
	}

	public class Mens extends View {
		Button undo_Btn;

		public Mens(Context context) {
			super(context);
			setFocusable(true);
			undo_Btn = new Button(context);
			undo_Btn.measure(100, 100); // size of view
			int width = undo_Btn.getMeasuredWidth();
			int height = undo_Btn.getMeasuredHeight();
			int left = 170;
			int top = 200;
			undo_Btn.layout(left, top, left + width, top + height);
			undo_Btn.setBackgroundDrawable(getResources().getDrawable(
					R.xml.i_symbol));
			undo_Btn.setVisibility(VISIBLE);
			undo_Btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

				}
			});
		}

		@Override
		protected void onDraw(Canvas canvas) {
			undo_Btn.draw(canvas);
			canvas.restore();
			invalidate();

		}
	}

	public void loadCanvas() {

		layMain = (LinearLayout) this.findViewById(R.id.indexdoc);
		layMain.setOnTouchListener(this);
		layMain.addView(new Mens(this));

	}

	//@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
