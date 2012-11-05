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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.FloatMath;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.TestSegment.RSSFeed_Reader_Segment;
import com.android.TestSegment.RSSHandler_Reader_Segment;

public class ShowListPageWithImage extends Activity implements OnTouchListener {
	private ProgressDialog progDailog;
	private RSSFeed_Reader_Segment myRssFeed_Segment = null;
	private final String imageUrl = "";
	// private ImageView imgView;
	private String fullimage;
	String t;
	// DemoView demoview;
	String LinkDocument;
	// ZoomControls zoomcontrols;
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
	private final int setimage = 0;
	private String setZoom = "0";
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
	float tl = 1;
	float oldwidth, OldHeight;
	float newwidth;
	float dodai;
	String text1 = "Creditor : Coca cola";
	String text2 = "Type : bill";
	String text3 = "Currency : USD";
	String text4 = "Amount: 100,00€";
	int img1 = R.drawable.i_symbol;
	Drawable img2;
	String modeview = "1";
	private final Integer[] Imgid = { R.drawable.i1, R.drawable.i2,
			R.drawable.i3 };
	private final String[] LinkImage = {
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I1.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I3.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I2.png" };
	int position;
	private Gallery gallery;
	private ImageView imgView;
	private int HeightGallery;
	private Button Option;
	String extStorageDirectory;
	Bitmap bm;
	private final RSSFeed_Reader_Segment myRssFeed = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showfullimage);
		readRss_Segment();
		LinearLayout layMain = (LinearLayout) this.findViewById(R.id.myScreen);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage3;
		position = 0;
		// imgView = (ImageView) findViewById(R.id.ImageView01);
		// imgView.setImageResource(Imgid[0]);
		gallery = (Gallery) findViewById(R.id.examplegallery);
		gallery.setAdapter(new AddImgAdp(this));
		HeightGallery = gallery.getHeight();
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				setImage(position);
				ShowListPageWithImage.this.position = position;
			}
		});
		extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();
		kangoo = DecodeFile(LinkImage[position]);
		/*
		 * Bundle bundle = this.getIntent().getExtras(); CharSequence
		 * textMessage1 = bundle.getCharSequence("LinkDocument");
		 * LinkDocument=""+textMessage1; CharSequence textTitleDoc =
		 * bundle.getCharSequence("TitleDoc"); TitleDoc=""+textTitleDoc;
		 * setTitle(TitleDoc); // setTitle( TextTest); CharSequence textMessage2
		 * = bundle.getCharSequence("LinkProcess");
		 * messageLinkProcess=""+textMessage2;
		 * 
		 * CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		 * messageLinkWB=""+textMessage3;
		 * 
		 * getLinkThumnailForEachDocument(LinkDocument); for(int
		 * i=0;i<ListLinkImage.size();i++){ String h;
		 * h=ListLinkImage.get(i).replace("[","-");
		 * //h=LinkProcess.get(i).replace("]",""); arInfo = h.split("-");
		 * ListSmallImage.add(arInfo[0]);
		 * ListFullImage.add(arInfo[1].replace("]","")); page=setimage+1;
		 * 
		 * }
		 */
		// layMain.addView(gallery);
		// layMain.addView(Option);
		setImage(position);
		layMain.setOnTouchListener(this);
		layMain.addView(new Mens(this));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "Setting").setIcon(R.drawable.setting);
		/*
		 * menu.add(0, 2, 0,"Remove Group") .setIcon(R.drawable.icon_delete);
		 */
		menu.add(0, 0, 0, "Add Group").setIcon(R.drawable.add_group);
		menu.add(1, 3, 1, "Add Mandant").setIcon(R.drawable.add_mandant);
		menu.add(1, 4, 1, "Refresh").setIcon(R.drawable.refresh);
		menu.add(1, 5, 1, "Move To Group").setIcon(R.drawable.move);
		// .setIcon(R.drawable.add_mandant);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case 0:

			break;
		}
		return false;
	}

	public void getLinkThumnailForEachDocument(String uri) {
		for (int i = 0; i < LinkImage.length; i++) {
			ListLinkImage.add(LinkImage[i]);
		}
	}

	public class Mens extends View implements OnTouchListener {
		Button undo_Btn;
		TextView tview;
		Paint textPaint;

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
			undo_Btn.setBackgroundDrawable(getResources().getDrawable(img1));
			undo_Btn.setVisibility(VISIBLE);
			undo_Btn.setId(10);
			undo_Btn.setPadding(50, 50, 50, 50);
			undo_Btn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					setTitle("QUICK ACTION");
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
			Paint paint2 = new Paint();
			paint.isFilterBitmap();
			canvas.drawColor(Color.BLACK);

			// Matrix zoommatrix=new Matrix();
			// zoommatrix.setScale(mScaleFactor, mScaleFactor);
			// canvas.translate(mPosX, mPosY);
			// canvas.scale(mScaleFactor, mScaleFactor);

			canvas.drawBitmap(kangoo, matrix, paint);
			undo_Btn.draw(canvas);
			Paint paint3 = new Paint();
			paint3.setColor(Color.BLACK);
			paint3.setTextSize(20);
			canvas.drawText(text1, 0, 80, paint3);

			Paint paint4 = new Paint();
			paint4.setColor(Color.BLACK);
			paint4.setTextSize(20);
			canvas.drawText(text2, 0, 100, paint4);

			Paint paint5 = new Paint();
			paint5.setColor(Color.BLACK);
			paint5.setTextSize(20);
			canvas.drawText(text3, 0, 120, paint5);

			Paint paint6 = new Paint();
			paint6.setColor(Color.BLACK);
			paint6.setTextSize(20);
			canvas.drawText(text4, 0, 140, paint6);
			// canvas.scale(1, 1);
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(3);
			paint2.setColor(Color.BLUE);
			paint2.setStyle(Paint.Style.STROKE);
			paint2.setStrokeWidth(3);
			int x = myRssFeed_Segment.getList().size();
			for (int i = 0; i < x; i++) {
				/*
				 * float a=Math.round(Float.parseFloat(SegmentX.get(i))/4.33);
				 * float b=Math.round(Float.parseFloat(SegmentY.get(i))/4.42);
				 * float
				 * c=Math.round(Float.parseFloat(SegmentWidth.get(i))/4.33);
				 * float
				 * d=Math.round(Float.parseFloat(SegmentHeight.get(i))/4.42);
				 */
				float a = Float.parseFloat(SegmentX.get(i));
				float b = Float.parseFloat(SegmentY.get(i));
				float c = Float.parseFloat(SegmentWidth.get(i));
				float d = Float.parseFloat(SegmentHeight.get(i));
				newY = touchY + dh - spaceY;
				newX = touchX + dw - spaceX;
				if ((newX > a) && (newX < (a + c)) && (newY > b)
						&& (newY < (b + d)))

				{
					// canvas.drawRect(touchX,touchY-50,touchX+100,touchY+120,
					// paint);
					// RectF rect = new RectF(0,40,40,40);
					// canvas.drawRect(rect, paint);
					canvas.drawRect(newX - a, d - (newY - b), c - (newX - a),
							newY - b, paint);
					m = a;
					n = b;
					o = c;
					p = d;
					// canvas.drawRect(touchX-80,touchY-60,40+touchX,touchY-20,
					// paint2);
					// canvas.drawRect(Float.parseFloat(SegmentWidth.get(i))/2,Float.parseFloat(SegmentHeight.get(i))/2,Float.parseFloat(SegmentWidth.get(i))/2,Float.parseFloat(SegmentHeight.get(i))/2,
					// paint);
				} else {
					// setTitle("K VE DUOC ");
				}
			}
			// canvas.drawRect(touchX-40,touchY+40,40+touchX,touchY-40, paint);
			canvas.restore();
			invalidate();
			int tx = Math.round(newX);
			int ty = Math.round(newY);
			// setTitle(""+SegmentX.get(0).toString());
			// setTitle("m= "+m+"; n= "+n+"; o="+o+";p= "+p);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// ImageView view = (ImageView) v;

			// Dump touch event to log
			dumpEvent(event);
			final View v = null;
			// Handle touch events here...
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				touchX = (int) event.getX();
				touchY = (int) event.getY();
				savedMatrix.set(matrix);
				start.set(event.getX(), event.getY());
				// setTitle(""+touchX+"--"+touchY);
				if (modeview.equals("1")) {
					if (touchX > 5 && touchX < 20 && touchY > 2 && touchY < 40) {
						// img1=R.drawable.white_small;
						// setTitle("BBBBB");
						showProcessBar4();
						// text1="Amount : 100,000$";
						// tview.setText("AAAA");
					}
				} else if (modeview.equals("2")) {
					if (touchX > 5 && touchX < 20 && touchY > 2 && touchY < 40) {
						showProcessBar();
					}
				}
				// Log.d(TAG, "mode=DRAG");
				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDist = spacing(event);
				// Log.d(TAG, "oldDist=" + oldDist);
				if (oldDist > 10f) {
					savedMatrix.set(matrix);
					midPoint(mid, event);
					mode = ZOOM;
					// Log.d(TAG, "mode=ZOOM");
				}
				break;
			case MotionEvent.ACTION_UP:
				upX = (int) event.getX();
				upY = (int) event.getY();
				spaceX = spaceX + (upX - touchX);
				spaceY = spaceY + (upY - touchY);

			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				// Log.d(TAG, "mode=NONE");
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == DRAG) {
					if (setZoom.equals("0")) {
						// if(scale==1f){
						if (event.getX() - start.x > 0) {
							// setImage3();
							// showProcessBar3();
							setTitle("Backward image");
						} else {
							setTitle("Forward image");
							// setImage2();
							// showProcessBar2();
						}
						// }
					} else {
						// ...
						matrix.set(savedMatrix);
						matrix.postTranslate(event.getX() - start.x, event
								.getY()
								- start.y);
						setTitle("move DRAG");
					}
				} else if (mode == ZOOM) {
					float newDist = spacing(event);
					tl = newwidth / oldwidth;
					if (newDist > 10f) {
						matrix.set(savedMatrix);
						scale = newDist / oldDist;
						spaceX = spaceX * scale / 2;
						spaceY = spaceY * scale / 2;
						if (setZoom.equals("0") && scale < 1) {
							setTitle("You cannot Zoom");
						} else {
							setZoom = "1";
							matrix.postScale(scale, scale, mid.x, mid.y);
							newwidth = kangoo.getWidth() * scale;
							// setTitle("New Width "+newwidth+"oldDist: "+oldDist);
						}
					}
				}
				break;
			}

			// view.setImageMatrix(matrix);
			return true; // indicate event was handled
		}

		//@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			return false;
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

	public void setImage(int position) {
		Display display = getWindowManager().getDefaultDisplay();
		int dwidth = display.getWidth();
		int dheight = display.getHeight() - 70;
		kangoo = DecodeFile(LinkImage[position]);
		kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);
		oldwidth = kangoo.getWidth();
		OldHeight = kangoo.getHeight();
		modeview = "1";
		text1 = "";
		text2 = "";
		text3 = "";
		text4 = "";
		dw = 0;
		dh = 0;
		// setTitle("dwidth :" + dwidth + "dheight: " + dheight);
	}

	public void setImage2() {
		Display display = getWindowManager().getDefaultDisplay();
		int dwidth = display.getWidth();
		int dheight = display.getHeight();
		kangoo = DecodeFile("http://174.143.148.49/RssSample3.1/mandant/imgs/I1.png");
		kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);
		dw = 0;
		dh = 0;
	}

	public void setImage3() {
		Display display = getWindowManager().getDefaultDisplay();
		int dwidth = display.getWidth();
		int dheight = display.getHeight();
		kangoo = DecodeFile("http://174.143.148.49/RssSample3.1/mandant/imgs/I2.png");
		kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);
		dw = 0;
		dh = 0;
	}

	public void setImage4() {

		Display display = getWindowManager().getDefaultDisplay();
		int dwidth = display.getWidth();
		int dheight = display.getHeight();
		Drawable board = getResources().getDrawable(R.drawable.quickaction_background);
		Bitmap bitmap2 = ((BitmapDrawable) board).getBitmap();
		kangoo = Bitmap.createBitmap(bitmap2);
		kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);
		modeview = "2";

		text1 = "Creditor : Coca cola";
		text2 = "Type : bill";
		text3 = "Currency : USD";
		text4 = "Amount: 100,00€";
		oldwidth = kangoo.getWidth();
	}

	private void readRss_Segment() {

		try {
			URL rssUrl = new URL(
					"http://174.143.148.49/RssSample3.1/mandant/workbasket/process/document/segment/ORG-segments.xml");
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
			TextTest = myRssFeed_Segment.getList().get(0).getH().toString();
			for (int i = 0; i < x; i++) {
				SegmentX.add((myRssFeed_Segment.getList().get(i).getX()));
				SegmentY.add((myRssFeed_Segment.getList().get(i).getY()));
				SegmentHeight.add((myRssFeed_Segment.getList().get(i).getH()));
				SegmentWidth.add((myRssFeed_Segment.getList().get(i).getW()));
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {

			Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
			showProcessBar2();

			break;
		}
		case KeyEvent.KEYCODE_DPAD_RIGHT: {

			Toast.makeText(this, "RIGHT" + page, Toast.LENGTH_SHORT).show();
			showProcessBar3();

			break;
		}
		case KeyEvent.KEYCODE_DPAD_UP: {
			break;
		}
		case KeyEvent.KEYCODE_DPAD_DOWN: {
			break;
		}
		case KeyEvent.KEYCODE_DPAD_CENTER: {
			break;
		}
		case KeyEvent.KEYCODE_BACK: {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			// CharSequence textmessageLinkProcess;
			// textmessageLinkProcess=messageLinkProcess;
			// bundle.putCharSequence("LinkProcess",textmessageLinkProcess);
			CharSequence textmessageLinkWB;
			textmessageLinkWB = messageLinkWB;
			bundle.putCharSequence("messageLinkWB", textmessageLinkWB);
			intent.putExtras(bundle);
			intent.setClass(ShowListPageWithImage.this,
					ShowListProcessWithDocs.class);
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
		progDailog = ProgressDialog.show(ShowListPageWithImage.this, "",
				"please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					setImage(position);
					sleep(300);
				} catch (Exception e) {
				}
				handler.sendEmptyMessage(0);
				progDailog.dismiss();
			}
		}.start();
	}

	public void showProcessBar3() {
		progDailog = ProgressDialog.show(ShowListPageWithImage.this, "",
				"please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					setImage3();
					sleep(300);
				} catch (Exception e) {
				}
				handler.sendEmptyMessage(0);
				progDailog.dismiss();
			}
		}.start();
	}

	public void showProcessBar2() {
		progDailog = ProgressDialog.show(ShowListPageWithImage.this, "",
				"please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					setImage2();
					sleep(300);
				} catch (Exception e) {
				}
				handler.sendEmptyMessage(0);
				progDailog.dismiss();
			}
		}.start();
	}

	public void showProcessBar4() {
		progDailog = ProgressDialog.show(ShowListPageWithImage.this, "",
				"please wait....", true);
		new Thread() {
			@Override
			public void run() {
				try {
					setImage4();
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
					sleep(10000);
				} catch (Exception e) {
				}
			}
		}.start();
	}

	private final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
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
			if (i + 1 < event.getPointerCount())
				sb.append(";");
		}
		sb.append("]");
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

	//@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public class AddImgAdp extends BaseAdapter {
		int GalItemBg;
		private Context cont;

		public AddImgAdp(Context c) {
			cont = c;
			TypedArray typArray = obtainStyledAttributes(R.styleable.GalleryTheme);
			GalItemBg = typArray.getResourceId(
					R.styleable.GalleryTheme_android_galleryItemBackground, 0);
			typArray.recycle();
		}

		public AddImgAdp(Mens mens) {
			// TODO Auto-generated constructor stub
		}

		public int getCount() {
			return Imgid.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView = new ImageView(cont);

			imgView.setImageResource(Imgid[position]);
			imgView.setLayoutParams(new Gallery.LayoutParams(80, 70));
			imgView.setScaleType(ImageView.ScaleType.FIT_XY);
			imgView.setBackgroundResource(GalItemBg);

			return imgView;
		}
	}

}
