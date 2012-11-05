package aexp.explist;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.FloatMath;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.TestSegment.RSSFeed_Reader_Segment;
import com.android.TestSegment.RSSHandler_Reader_Segment;
import com.test.androidtest.ActionItem;
import com.test.androidtest.QuickAction;

public class showFullImage extends Activity implements OnTouchListener {
	private ProgressDialog progDailog;
	private RSSFeed_Reader_Segment myRssFeed_Segment = null;
	private final String imageUrl = "";
	private String fullimage;
	String t;
	String LinkDocument;
	ImageView comicview;
	float zoomfactor;
	Context context;
	Bitmap kangoo;
	private int scaledHeight;
	private int scaledWidth;
	private int widthAndroid;
	private int heightAndroid;
	float touchX = 0;
	float touchY = 0;
	float upX = 0;
	float upY = 0;
	float curx = 0;
	float cury = 0;
	float x = 1.0f;
	float y = 1.0f;
	float dx = 0;
	float dy = 0;
	private static final int INVALID_POINTER_ID = -1;
	private float mPosX;
	private float mPosY;
	private float mLastTouchX;
	private float mLastTouchY;
	String[] arInfo;
	private final int mActivePointerId = INVALID_POINTER_ID;
	private int setzoom;
	private final List<String> ListSmallImage = new ArrayList();
	private final List<String> ListFullImage = new ArrayList();
	private final List<String> ListLinkImage = new ArrayList();
	private String messageLinkProcess;
	private String messageLinkWB;
	private String messageDocName;
	private int setimage = 0;
	private int page;
	// TextView pageindex;
	private String TitleDoc;
	float downXValue;
	float currentX;
	float downYValue;
	float currentY;
	private String move;
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;

	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	// We can be in one of these 3 states
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;
	private String TextTest;
	private final List<String> SegmentX = new ArrayList();;
	private final List<String> SegmentY = new ArrayList();;
	private final List<String> SegmentWidth = new ArrayList();;
	private final List<String> SegmentHeight = new ArrayList();;
	private float dw, dh, newX, newY;
	private float spaceX = 0;
	private float spaceY = 0;
	private float deltaX;
	private float m, n, o, p;
	float scale;
	double SegmentX2;
	double SegmentY2;
	double SegmentH;
	double SegmentW;
	int minDelta;
	double ScaleWidth;
	double ScaleHeight;
	int dwidth;
	int dheight;
	int height;
	private String OCR;
	private static final int ID_CREDITOR = 1;
	private static final int ID_AMOUNT = 2;
	private static final int ID_CURRENCY = 3;
	private static final int ID_TYPE = 4;
	QuickAction mQuickAction;
	private EditText NewValueEditText;
	private String NewValue;
	private String IndexSelected;
	List<String> FirstListIndex = new ArrayList<String>();
	List<String> ValueListIndex = new ArrayList<String>();
	private int positionChange;
	private final double hs = 1.04;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.showfullimage);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage3;
		CharSequence textDocName = bundle.getCharSequence("messageDocName");
		messageDocName = "" + textDocName;
		readRss_Segment();
		LinearLayout layMain = (LinearLayout) this.findViewById(R.id.myScreen);
		setImage();
		layMain.setOnTouchListener(this);
		layMain.addView(new Mens(this));
		FirstListIndex.add("Coca Cola");
		FirstListIndex.add("100,00");
		FirstListIndex.add("Euro");
		FirstListIndex.add("bill");

		populateView();
		/*
		 * ActionItem creditorItem = new ActionItem(ID_CREDITOR,
		 * "Creditor :"+ValueListIndex.get(0),
		 * getResources().getDrawable(R.drawable.creditor_icon)); ActionItem
		 * amountItem = new ActionItem(ID_AMOUNT,
		 * "Amount :"+ValueListIndex.get(1),
		 * getResources().getDrawable(R.drawable.amount_icon)); ActionItem
		 * currencyItem = new ActionItem(ID_CURRENCY,
		 * "Currency :"+ValueListIndex.get(2),
		 * getResources().getDrawable(R.drawable.currency_icon)); ActionItem
		 * typeItem = new ActionItem(ID_TYPE, "Type :"+ValueListIndex.get(3),
		 * getResources().getDrawable(R.drawable.type_icon)); // mQuickAction =
		 * new QuickAction(this);
		 * 
		 * mQuickAction.addActionItem(creditorItem);
		 * mQuickAction.addActionItem(amountItem);
		 * mQuickAction.addActionItem(currencyItem);
		 * mQuickAction.addActionItem(typeItem);
		 */
		// setup the action item click listener

	}

	public class Mens extends View {
		Button undo_Btn;

		public Mens(Context context) {
			super(context);
			mScaleDetector = new ScaleGestureDetector(context,
					new ScaleListener());
			setFocusable(true);

			undo_Btn = new Button(context);

			undo_Btn.measure(150, 150); // size of view
			int width = undo_Btn.getMeasuredWidth();
			int height = undo_Btn.getMeasuredHeight();
			int right = 100;
			int top = 200;
			undo_Btn.layout(right, top, right + width, top + height);
			// img1=;
			undo_Btn.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.i_symbol));
			undo_Btn.setVisibility(VISIBLE);
			undo_Btn.setId(10);
			undo_Btn.setPadding(50, 50, 50, 50);
			undo_Btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

				}
			});
			/*
			 * tview= new TextView(context); tview.measure(400,400);
			 * tview.setText("AAAA");
			 */

		}

		public class ScaleListener extends
				ScaleGestureDetector.SimpleOnScaleGestureListener {
			@Override
			public boolean onScale(ScaleGestureDetector detector) {
				mScaleFactor *= detector.getScaleFactor();
				// Don't let the object get too small or too large.
				mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
				invalidate();
				return true;
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint = new Paint();
			canvas.drawColor(Color.BLACK);
			// Matrix zoommatrix=new Matrix();
			// zoommatrix.setScale(mScaleFactor, mScaleFactor);
			// canvas.translate(mPosX, mPosY);
			// canvas.scale(mScaleFactor, mScaleFactor);
			canvas.drawBitmap(kangoo, matrix, paint);
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(3);
			int x = myRssFeed_Segment.getList().size();
			double[] DeltaH = new double[x];
			Display display = getWindowManager().getDefaultDisplay();
			dwidth = display.getWidth();
			dheight = display.getHeight();
			// height=Math.round(dwidth*(809.0F/594));//828.652
			height = Math.round(dwidth * (2921.0F / 2115));// 828.652
			// dwidth=500;
			// dheight=500;
			// ScaleWidth =(594.0F/(dwidth*hs));//width=600 thi
			// ScaleWidth=3.525, = 320 thi ScaleWidth =6.609
			// ScaleHeight=(809.0F/(height));
			ScaleWidth = (2115.0F / (dwidth * hs));// width=600 thi
			// ScaleWidth=3.525, = 320
			// thi ScaleWidth =6.609
			ScaleHeight = (2921.0F / (height * hs));
			for (int i = 0; i < x; i++) {
				double a = Double.parseDouble(SegmentX.get(i));
				double b = Double.parseDouble(SegmentY.get(i));
				double c = Double.parseDouble(SegmentWidth.get(i));
				double d = Double.parseDouble(SegmentHeight.get(i));
				newY = touchY /* + dh - spaceY */;
				newX = touchX /* + dw - spaceX */;

				SegmentX2 = (a / ScaleWidth);
				SegmentY2 = (b / ScaleHeight);
				SegmentH = (d / ScaleHeight);
				SegmentW = (c / ScaleWidth);
				DeltaH[i] = Math.sqrt((newX - SegmentX2) * (newX - SegmentX2)
						+ (newY - SegmentY2) * (newY - SegmentY2));
			}
			minDelta = getMinValue(DeltaH);
			double a = Double.parseDouble(SegmentX.get(minDelta));
			double b = Double.parseDouble(SegmentY.get(minDelta));
			double c = Double.parseDouble(SegmentWidth.get(minDelta));
			double d = Double.parseDouble(SegmentHeight.get(minDelta));
			// SegmentX2 = (float) (a /(ImageWidth/dwidth)/(maxWidth/dWidth);
			SegmentX2 = (a / ScaleWidth) * 1.5;
			SegmentH = (d / ScaleHeight);
			SegmentW = (c / ScaleWidth);
			SegmentY2 = (b / ScaleHeight);
			/*
			 * if(minDelta==17||minDelta==18){ hs=1.02; SegmentY2 = (double) (b
			 * /ScaleWidth); } else if(minDelta==15||minDelta==16){ hs=1.025;
			 * SegmentY2 = (double) (b /ScaleWidth); } else
			 * if(minDelta==13||minDelta==14){ hs=1.03; SegmentY2 = (double) (b
			 * /ScaleWidth); } else if(minDelta==11||minDelta==12){ hs=1.035;
			 * SegmentY2 = (double) (b /ScaleWidth); } else
			 * if(minDelta==9||minDelta==10){ hs=1.040; SegmentY2 = (double) (b
			 * /ScaleWidth); } else if(minDelta==7||minDelta==8){ hs=1.045;
			 * SegmentY2 = (double) (b /ScaleWidth); } else { SegmentY2 =
			 * (double) (b / ScaleWidth); }
			 */
			canvas.drawRect((float) (SegmentX2 - SegmentW / 2),
					(float) (SegmentY2 - SegmentH / 2),
					(float) (SegmentX2 + SegmentW / 2),
					(float) (SegmentY2 + SegmentH / 2), paint);

			canvas.restore();
			invalidate();
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

	public void setImage() {
		Display display = getWindowManager().getDefaultDisplay();
		dwidth = display.getWidth();
		dheight = display.getHeight();
		// height=Math.round(dwidth*(809.0F/594));
		height = Math.round(dwidth * (2921.0F / 2115));
		kangoo = DecodeFile("https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600");
		// kangoo=DecodeFile("http://174.143.148.49/RssSample3.1/imgs/IMG.png");
		// kangoo=DecodeFile("https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor&image_output_format=png_gray&max_width=700&max_height=800");
		// kangoo = Bitmap.createScaledBitmap(kangoo,320,442, true);
		// kangoo = Bitmap.createScaledBitmap(kangoo,594,809, true);
		kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, height, true);

	}

	private void readRss_Segment() {

		try {
			URL rssUrl = new URL(
					"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor");
			/*
			 * URL rssUrl = new URL(
			 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor"
			 * );
			 */
			SAXParserFactory mySAXParserFactory = SAXParserFactory
					.newInstance();
			SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
			XMLReader myXMLReader = mySAXParser.getXMLReader();
			RSSHandler_Reader_Segment myRSSHandler_Segment = new RSSHandler_Reader_Segment();
			myXMLReader.setContentHandler(myRSSHandler_Segment);
			InputSource myInputSource = new InputSource(rssUrl.openStream());
			myXMLReader.parse(myInputSource);

			myRssFeed_Segment = myRSSHandler_Segment.getFeed();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (myRssFeed_Segment != null) {
			int x = myRssFeed_Segment.getList().size();
			// TextTest =
			// myRssFeed_Segment.getListImageData().get(0).getHeight();
			for (int i = 0; i < x; i++) {
				SegmentX.add((myRssFeed_Segment.getList().get(i).getX()));
				SegmentY.add((myRssFeed_Segment.getList().get(i).getY()));
				SegmentHeight.add((myRssFeed_Segment.getList().get(i).getH()));
				SegmentWidth.add((myRssFeed_Segment.getList().get(i).getW()));
			}

			/*
			 * Calendar c = Calendar.getInstance(); String strCurrentTiime =
			 * "\n(Time of Reading - " + c.get(Calendar.HOUR_OF_DAY) + " : " +
			 * c.get(Calendar.MINUTE) + ")\n";
			 * 
			 * feedTitle.setText(myRssFeed.getX() + strCurrentTiime);
			 * feedDescribtion.setText(myRssFeed.getY());
			 * feedPubdate.setText(myRssFeed.getW());
			 * feedLink.setText(myRssFeed.getH());
			 * 
			 * MyCustomAdapter adapter = new MyCustomAdapter(this,
			 * R.layout.rowreader, myRssFeed.getList());
			 * setListAdapter(adapter);
			 */

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {

			if (!(setimage == 0)) {

				setimage = setimage - 1;
				page = setimage + 1;
				// pageindex.setText("Page "+page);
				Toast.makeText(this, "Page " + page, Toast.LENGTH_SHORT).show();
				showProcessBar();
				// setImage();
			} else {

			}

			break;
		}
		case KeyEvent.KEYCODE_DPAD_RIGHT: {
			// zoomcontrols.setFocusable(true);
			if (setimage < ListFullImage.size() - 1) {

				setimage = setimage + 1;
				page = setimage + 1;
				// pageindex.setText("Page "+page);
				Toast.makeText(this, "Page " + page, Toast.LENGTH_SHORT).show();
				showProcessBar();

			} else {

			}
			break;
		}
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			CharSequence textmessageLinkProcess;
			textmessageLinkProcess = messageLinkProcess;
			bundle.putCharSequence("LinkProcess", textmessageLinkProcess);
			CharSequence textmessageLinkWB;
			textmessageLinkWB = messageLinkWB;
			CharSequence textDocName;
			textDocName = messageDocName;
			bundle.putCharSequence("messageDocName", textDocName);
			bundle.putCharSequence("messageLinkWB", textmessageLinkWB);
			intent.putExtras(bundle);
			intent.setClass(showFullImage.this, ViewImagesActivity.class);
			startActivity(intent);
			finish();
		}

		}
		return true;
	}

	private final OnClickListener onZoomOut = new OnClickListener() {
		public void onClick(View v) {
			setzoom = 1;
			x -= 0.2f;
			y -= 0.2f;

		}
	};

	private final OnClickListener onZoomIn = new OnClickListener() {
		public void onClick(View v) {
			setzoom = 1;
			x += 0.2f;
			y += 0.2f;
		}
	};

	public void reload() {

		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}

	public void showProcessBar() {
		progDailog = ProgressDialog.show(showFullImage.this, "Loading Page...",
				"please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					setImage();
					// progDailog.dismiss();
					// just doing some long operation
					sleep(300);

				} catch (Exception e) {
				}
				handler.sendEmptyMessage(0);
				progDailog.dismiss();
			}
		}.start();
	}

	public void sleep() {
		new Thread() {
			@Override
			public void run() {
				try {

					// progDailog.dismiss();
					// just doing some long operation
					sleep(10000);

				} catch (Exception e) {
				}
			}
		}.start();
	}

	private final Handler handler = new Handler() {
		// @Override
		@Override
		public void handleMessage(Message msg) {
			// setTitle("Processing Done");

		}
	};

	private void dumpEvent(MotionEvent event) {
		String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
				"POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
		StringBuilder sb = new StringBuilder();
		int action = event.getAction();
		int actionCode = action & MotionEvent.ACTION_MASK;
		sb.append("event ACTION_").append(names[actionCode]);
		if (actionCode == MotionEvent.ACTION_POINTER_DOWN
				|| actionCode == MotionEvent.ACTION_POINTER_UP) {
			sb.append("(pid ").append(
					action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
			sb.append(")");
		}
		sb.append("[");
		for (int i = 0; i < event.getPointerCount(); i++) {
			sb.append("#").append(i);
			sb.append("(pid ").append(event.getPointerId(i));
			sb.append(")=").append((int) event.getX(i));
			sb.append(",").append((int) event.getY(i));
			if (i + 1 < event.getPointerCount()) {
				sb.append(";");
			}
		}
		sb.append("]");
		// Log.d(TAG, sb.toString());
	}

	/** Determine the space between the first two fingers */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/** Calculate the mid point of the first two fingers */
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		dumpEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			Display display = getWindowManager().getDefaultDisplay();
			dwidth = display.getWidth();
			dheight = display.getHeight();
			touchX = (int) event.getX();
			touchY = (int) event.getY();

			if (touchX > 5 && touchX < 20 && touchY > 2 && touchY < 40) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				CharSequence textMessage;
				CharSequence textDocName;
				textMessage = "" + messageLinkWB;
				textDocName = "" + messageDocName;
				bundle.putCharSequence("messageLinkWB", textMessage);
				bundle.putCharSequence("messageDocName", textDocName);
				intent.putExtras(bundle);
				intent.setClass(showFullImage.this, IndexDoc.class);
				startActivity(intent);
				finish();
			}
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			if (minDelta == 7 || minDelta == 9 || minDelta == 11
					|| minDelta == 13 || minDelta == 15 || minDelta == 17) {
				OCR = "Oxseed";
			} else if (minDelta == 8 || minDelta == 10 || minDelta == 12
					|| minDelta == 14 || minDelta == 16 || minDelta == 18) {
				OCR = "Faxfunktionstest";
			} else {
				OCR = "Unknown";
			}
			setTitle("W=" + dwidth + "  H=" + dheight);
			mQuickAction.show(v);
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_UP:
			upX = (int) event.getX();
			upY = (int) event.getY();
			spaceX = spaceX + (upX - touchX);
			spaceY = spaceY + (upY - touchY);
			// setTitle(OCR);
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {

				/*
				 * float newDist = spacing(event); scale = newDist/oldDist;
				 * if(scale==1f){ if(event.getX() - start.x>0){
				 * setTitle("forward image"); } else{
				 * setTitle("backward image"); } } // ...
				 * matrix.set(savedMatrix); matrix.postTranslate(event.getX() -
				 * start.x, event.getY() - start.y);
				 */

			} else if (mode == ZOOM) {
				float newDist = spacing(event);
				// Log.d(TAG, "newDist=" + newDist);
				if (newDist > 10f) {
					matrix.set(savedMatrix);
					scale = newDist / oldDist;
					spaceX = spaceX * scale / 2;
					spaceY = spaceY * scale / 2;
					// matrix.postScale(scale, scale, mid.x, mid.y);
					/*
					 * if (scale < 1) { scale = 1f; setTitle("CANNOT ZOOM IN");
					 * } else { matrix.postScale(scale, scale, mid.x, mid.y); }
					 */

				}
			}
			break;
		}

		// view.setImageMatrix(matrix);
		return true; // indicate event was handled

	}

	public static int getMinValue(double[] numbers) {
		double minValue = numbers[0];
		int min = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] < minValue) {
				minValue = numbers[i];
				min = i;
			}
		}
		return min;
	}

	public static float getMaxValue(float[] numbers) {
		float maxValue = numbers[0];
		float maxi = 0;
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > maxValue) {
				maxValue = numbers[i];
				maxi = i;
			}
		}
		return maxValue;
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
		NewValueEditText.setText(IndexSelected);
		NewValueEditText.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NewValueEditText.setText("");
			}

		});
		Button addButton = (Button) dialogView.findViewById(R.id.add_button);
		Button cancelButton = (Button) dialogView
				.findViewById(R.id.cancel_button);

		addButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				NewValue = NewValueEditText.getText().toString();
				loginDialog.dismiss();
				FirstListIndex.remove(positionChange);
				FirstListIndex.add(positionChange, NewValue);
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
		ValueListIndex.removeAll(ValueListIndex);
		for (int i = 0; i < FirstListIndex.size(); i++) {
			ValueListIndex.add(FirstListIndex.get(i));
		}

		ActionItem creditorItem = new ActionItem(ID_CREDITOR, "Creditor :"
				+ ValueListIndex.get(0), getResources().getDrawable(
				R.drawable.creditor_icon));
		ActionItem amountItem = new ActionItem(ID_AMOUNT, "Amount :"
				+ ValueListIndex.get(1), getResources().getDrawable(
				R.drawable.amount_icon));
		ActionItem currencyItem = new ActionItem(ID_CURRENCY, "Currency :"
				+ ValueListIndex.get(2), getResources().getDrawable(
				R.drawable.currency_icon));
		ActionItem typeItem = new ActionItem(ID_TYPE, "Type :"
				+ ValueListIndex.get(3), getResources().getDrawable(
				R.drawable.type_icon));
		mQuickAction = new QuickAction(this);

		mQuickAction.addActionItem(creditorItem);
		mQuickAction.addActionItem(amountItem);
		mQuickAction.addActionItem(currencyItem);
		mQuickAction.addActionItem(typeItem);

		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction quickAction, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);
						positionChange = pos;
						IndexSelected = ValueListIndex.get(pos);
						showAddDialog();
						Toast.makeText(showFullImage.this,
								actionItem.getTitle(), Toast.LENGTH_SHORT)
								.show();
					}
				});

		// setup on dismiss listener, set the icon back to normal
		mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				// mMoreIv.setImageResource(R.drawable.ic_list_more);
				mQuickAction.dismiss();
			}
		});
	}

}
