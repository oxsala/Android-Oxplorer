package aexp.explist;

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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class IndexDoc extends ListActivity {
	private TextView backButton;
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
	private final int[] data = { R.drawable.i1, R.drawable.i2 };
	private final String[] name = { "1", "2" };
	CharSequence[] arr_CS_TypeDocList;
	AlertDialog adSelectTypeDoc;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indexdoc);
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
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage3;
		CharSequence textDocName = bundle.getCharSequence("messageDocName");
		messageDocName = "" + textDocName;
		backButton = (TextView) findViewById(R.id.index_doc_btn_back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				CharSequence textmessageLinkWB;
				textmessageLinkWB = messageLinkWB;
				bundle.putCharSequence("messageLinkWB", textmessageLinkWB);
				intent.putExtras(bundle);
				intent.setClass(IndexDoc.this, ShowListProcessWithDocs.class);
				startActivity(intent);
				finish();

				/*
				 * if (click) { popUp.showAtLocation(layout, Gravity.BOTTOM, 10,
				 * 10); popUp.update(100, 50,400,200); click = false; } else {
				 * popUp.dismiss(); click = true; }
				 */
			}
		});

		gallery = (Gallery) findViewById(R.id.examplegallery);
		gallery.setAdapter(new ImageAdapter2(this));
		// gallery.setAdapter(new AddImgAdp(this));
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				final int pos = position + 1;
				// setImage(position);
				IndexDoc.this.position = position;
				Toast.makeText(getBaseContext(), "PAGE " + pos,
						Toast.LENGTH_SHORT).show();
			}
		});
		gallery.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView parent, View v,
					int position, long id) {
				final int pos = position + 1;
				Toast.makeText(getBaseContext(), "GOING TO PAGE " + pos,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				CharSequence textMessage;
				CharSequence textDocName;
				textMessage = "" + messageLinkWB;
				textDocName = "" + messageDocName;
				bundle.putCharSequence("messageLinkWB", textMessage);
				bundle.putCharSequence("messageDocName", textDocName);
				intent.putExtras(bundle);
				intent.setClass(IndexDoc.this, ViewImagesActivity.class);
				startActivity(intent);
				finish();
				return false;
			}
		});

		/*
		 * params = new LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT);
		 * layout.setOrientation(LinearLayout.VERTICAL);
		 * tv.setText("Hi this is a sample text for popup window");
		 * btn.setText("TEST"); layout.addView(tv, params); layout.addView(btn,
		 * params); popUp.setContentView(layout);
		 */

		FirstValueList.add("bill");
		FirstValueList.add("Coca Cola");
		FirstValueList.add("100,00");
		FirstValueList.add("Euro");
		populateView();

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
			finish();
		}

		}
		return false;
	}

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

				@Override
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
		NewValueEditText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NewValueEditText.setText("");
			}

		});
		if (contentList.get(positionChange).equals("Amount:")) {
			NewValueEditText.setRawInputType(Configuration.KEYBOARD_12KEY);
		} else {

		}
		NewValueEditText.setText(valueList.get(positionChange));
		Button addButton = (Button) dialogView.findViewById(R.id.add_button);
		Button cancelButton = (Button) dialogView
				.findViewById(R.id.cancel_button);

		addButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				NewValue = NewValueEditText.getText().toString();
				loginDialog.dismiss();
				FirstValueList.remove(positionChange);
				FirstValueList.add(positionChange, NewValue);
				populateView();
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

		private final int[] data = { R.drawable.i1, R.drawable.i2,
				R.drawable.i3 };
		private final String[] name = { "1", "2" };
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
						})

				.setPositiveButton("Select",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								FirstValueList.remove(positionChange);
								FirstValueList.add(positionChange, NewValue);
								populateView();
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

	public void ShowCurrencyListDialog() {
		String[] ssTypeDocList = new String[CurrencyList.size()];
		for (int i = 0; i < CurrencyList.size(); i++) {
			ssTypeDocList[i] = CurrencyList.get(i);
		}

		arr_CS_TypeDocList = ssTypeDocList;

		adSelectTypeDoc = new AlertDialog.Builder(this).setTitle(
				"Select Currency :").setIcon(R.drawable.currency_icon)
				.setSingleChoiceItems(arr_CS_TypeDocList, -1,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								NewValue = arr_CS_TypeDocList[whichButton]
										.toString();

							}
						})

				.setPositiveButton("Select",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								FirstValueList.remove(positionChange);
								FirstValueList.add(positionChange, NewValue);
								populateView();
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
}
