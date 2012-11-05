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
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.TestSegment.RSSFeed_Reader_Segment;
import com.android.TestSegment.RSSHandler_Reader_Segment;
import com.hots.util.GlobalVariable;
import com.hots.zoom.AspectQuotient;
import com.hots.zoom.ImageZoomView;
import com.hots.zoom.TouchZoomListener;
import com.hots.zoom.ZoomControl;
import com.hots.zoom.ZoomState;
import com.test.androidtest.ActionItem;
import com.test.androidtest.QuickAction;

public class ViewImagesActivity extends Activity implements OnTouchListener {
	private Bundle extra;
	// private TextView txtTitle;
	// private TextView txtTitleImage;
	// private TextView txtNumberPage;
	// private TextView btnBack;
	// private ImageButton btnLeft;
	// private ImageButton btnRight;
	// private ImageButton btnCoverFlow;
	private ProgressBar mProgressBar;
	// private RelativeLayout layoutNavigation;
	// private RelativeLayout layoutNavigationTop;
	// private RelativeLayout layoutNavigationBottom;

	private LinearLayout layoutZoomView;
	private ImageZoomView mZoomView;
	private ZoomControl mZoomControl;
	private TouchZoomListener mZoomListener;
	private Context mContext;

	private MainCountDown countDown;
	private ParserTask parserTask;
	private Animation animRightToLeft;
	private Animation animLeftToRight;
	private Animation animRightToLeft1;
	private Animation animLeftToRight1;
	private Animation animBottomToTop;
	private Animation animTopToBottom;
	private Animation animShowNavbar;
	Bitmap kangoo;
	private String strMaxSize;
	private String filename;
	// String extStorageDirectory;
	private int count = 0;
	private boolean isLeftorRight = true;
	private boolean isBottomToTop = true;
	private String messageLinkWB;
	private String messageDocName;
	private final AspectQuotient mAspectQuotient = new AspectQuotient();
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;
	private float mX;
	private float mY;
	/** X-coordinate of latest down event */
	private float mDownX;
	/** Y-coordinate of latest down event */
	private float mDownY;
	/** Velocity tracker for touch events */
	private VelocityTracker mVelocityTracker;
	/** Duration in ms before a press turns into a long press */
	private int mLongPressTimeout;
	/** Vibrator for tactile feedback */
	private Vibrator mVibrator;
	/** Maximum velocity for fling */
	private int mScaledMaximumFlingVelocity;
	private int width2, height2, value;
	// private boolean isNavigation = true;
	private float currentX, currentX1;
	private float currentY, currentY1;
	private float spaceTemp = 0;
	private boolean isZoomMutilPoint = false;
	private float sX, sY;
	private boolean isMove = true;
	float ScaleZoom=1;
	private final String[] LinkImage = {
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600",
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor&image_output_format=png_gray&max_width=700&max_height=800",
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600" };

	private final String[] LinkSegment = {
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=xml",
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor",
	"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=xml" };

	/*
	 * private final String[] LinkImage = {
	 * "http://174.143.148.49/RssSample3.1/mandant/imgs/I1.png",
	 * "http://174.143.148.49/RssSample3.1/mandant/imgs/I2.png",
	 * "http://174.143.148.49/RssSample3.1/mandant/imgs/I3.png" };
	 */

	/*
	 * private final String[] LinkImage = {
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600"
	 * ,
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600"
	 * ,
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600"
	 * };
	 */

	private static final int CAMERA_REQUEST = 1888;
	private static final int ID_SAVE = 1;
	private static final int ID_EMAIL = 2;
	private static final int ID_INDEX = 3;
	Matrix matrix = new Matrix();
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
	float touchX = 0;
	float touchY = 0;
	Matrix savedMatrix = new Matrix();
	PointF start = new PointF();
	float oldDist = 1f;
	PointF mid = new PointF();
	private float scale;
	private String setCanvas = "1";
	LinearLayout layMain;
	int dwidth;
	int dheight;

	private String TextTest;
	private final List<String> SegmentX = new ArrayList();;
	private final List<String> SegmentY = new ArrayList();;
	private final List<String> SegmentWidth = new ArrayList();;
	private final List<String> SegmentHeight = new ArrayList();;
	int minDelta;
	private double dw, dh, touchXimage, touchYImage;
	private float spaceX = 0;
	private float spaceY = 0;
	private final double hs = 1.04;
	int height;
	private RSSFeed_Reader_Segment myRssFeed_Segment = null;
	QuickAction mQuickAction;
	private EditText NewValueEditText;
	private String NewValue;
	private String IndexSelected;
	List<String> FirstListIndex = new ArrayList<String>();
	List<String> ValueListIndex = new ArrayList<String>();
	private static final int ID_CREDITOR = 1;
	private static final int ID_AMOUNT = 2;
	private static final int ID_CURRENCY = 3;
	private static final int ID_TYPE = 4;
	private int positionChange;
	double dis;
	double zoomscale = 1;
	private final ZoomState mState = new ZoomState();
	float scaleHPerW;
	  float leftImage = 0;
	  float topImage = 0;

	// private float zoomscale;
	private float ImageX, ImageY;
	private float deltaX, deltaY;
	private float DeviceWidth, DeviceHeight;
	private float FirstImageWidth, FirstImageHeight;
	private float FitImageWidth, FitImageHeight;
	private float SegmentXDraw, SegmentYDraw, SegmentHDraw, SegmentWDraw;
	private float ScaleWidth, ScaleHeight;
	private float ScaleFit;
	private float SegmentXLoop, SegmentYLoop, SegmentHLoop, SegmentWLoop;
	private float test;
	float upX = 0;
	float upY = 0;
	float dxx = 0;
	float dxy = 0;
	float dx;
	float dy;
	float top, left;

	// Rect mImagePosition;
	// Region mImageRegion;
	// boolean canImageMove;
	// float prevX;
	// float prevY;

	// PointF start = new PointF();

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.view_image_activity);
	// kangoo = DecodeFile(LinkImage[count]);

	readRss_Segment(LinkSegment[0]);
	loadCanvas();

	kangoo = DecodeFile(LinkImage[0]);
	Display display = getWindowManager().getDefaultDisplay();
	dwidth = display.getWidth();
	dheight = display.getHeight();
	height = Math.round(dwidth * (2921.0F / 2115));
	kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, height, true);
	
	GlobalVariable.config = getResources().getConfiguration();
	mContext = this;
	layoutZoomView = (LinearLayout) findViewById(R.id.zoomview);
	FirstListIndex.add("Coca Cola");
	FirstListIndex.add("100,00");
	FirstListIndex.add("Euro");
	FirstListIndex.add("bill");
	populateView();
	/*
	 * layoutNavigation = (RelativeLayout) findViewById(R.id.navigation);
	 * layoutNavigationTop = (RelativeLayout)
	 * findViewById(R.id.navigation_top); layoutNavigationBottom =
	 * (RelativeLayout) findViewById(R.id.navigation_bottom); txtTitle =
	 * (TextView) findViewById(R.id.view_image_txt_title); txtTitleImage =
	 * (TextView) findViewById(R.id.view_image_txt_title_image);
	 * txtNumberPage = (TextView)
	 * findViewById(R.id.view_image_txt_number_page); btnBack = (TextView)
	 * findViewById(R.id.view_image_btn_back_top); btnLeft = (ImageButton)
	 * findViewById(R.id.view_image_btn_back); btnRight = (ImageButton)
	 * findViewById(R.id.view_image_btn_next); btnCoverFlow = (ImageButton)
	 * findViewById(R.id.view_image_btn_list);
	 */
	mProgressBar = (ProgressBar) findViewById(R.id.view_image_progress_bar);
	animRightToLeft = AnimationUtils.loadAnimation(mContext,
	R.anim.popup_right_to_left);
	animLeftToRight = AnimationUtils.loadAnimation(mContext,
	R.anim.popup_left_to_right);
	animRightToLeft1 = AnimationUtils.loadAnimation(mContext,
	R.anim.popup_right_to_left1);
	animLeftToRight1 = AnimationUtils.loadAnimation(mContext,
	R.anim.popup_left_to_right1);
	animBottomToTop = AnimationUtils.loadAnimation(mContext,
	R.anim.grow_from_bottom);
	animTopToBottom = AnimationUtils.loadAnimation(mContext,
	R.anim.grow_from_top);

	animShowNavbar = AnimationUtils.loadAnimation(mContext,
	R.anim.popup_show_navbar);
	/*Bundle bundle = this.getIntent().getExtras();
	CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
	messageLinkWB = "" + textMessage3;
	CharSequence textDocName = bundle.getCharSequence("messageDocName");
	messageDocName = "" + textDocName;*/
	strMaxSize = "/3";
	// deltaY = (GlobalVariable.PANTOP);
	// deltaX = (GlobalVariable.PANLEFT);
	// count = 0;

	// ((ViewImagesActivity) mContext).visibilityLayoutNav(true);
	// kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);
	// setValue(count);
	/*
	 * txtTitle.setText(messageDocName); // ((ViewImagesActivity)
	 * mContext).visibilityLayoutNav(false); btnLeft.setOnClickListener(new
	 * OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { goLeft(); } });
	 * btnRight.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { goRight(); } });
	 */

	/*
	 * layoutNavigationTop.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { } });
	 */

	/*
	 * layoutNavigationBottom.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { } });
	 */

	/*
	 * ActionItem addItem = new ActionItem(ID_SAVE, "Save File",
	 * getResources().getDrawable(R.drawable.save_file)); final ActionItem
	 * acceptItem = new ActionItem(ID_EMAIL, "Send to email",
	 * getResources().getDrawable(R.drawable.send_email)); ActionItem
	 * uploadItem = new ActionItem(ID_INDEX, "Setting Index",
	 * getResources().getDrawable(R.drawable.doc)); final QuickAction
	 * mQuickAction = new QuickAction(this);
	 * 
	 * mQuickAction.addActionItem(addItem);
	 * mQuickAction.addActionItem(acceptItem);
	 * mQuickAction.addActionItem(uploadItem);
	 * 
	 * // setup the action item click listener mQuickAction
	 * .setOnActionItemClickListener(new
	 * QuickAction.OnActionItemClickListener() {
	 * 
	 * @Override public void onItemClick(QuickAction quickAction, int pos,
	 * int actionId) { ActionItem actionItem =
	 * quickAction.getActionItem(pos);
	 * 
	 * if (actionId == ID_SAVE) { // Add item selected
	 * Toast.makeText(ViewImagesActivity.this, "Saving Image...",
	 * Toast.LENGTH_SHORT) .show(); OutputStream outStream = null; String
	 * extStorageDirectory = Environment
	 * .getExternalStorageState().toString(); kangoo =
	 * DecodeFile(LinkImage[count]); File file = new
	 * File(extStorageDirectory, "er.PNG"); String filepath = Environment
	 * .getExternalStorageDirectory() .getAbsolutePath(); String extraPath =
	 * "/Pic" + count + ".png"; filepath += extraPath; FileOutputStream fos
	 * = null; try { int t = count + 1; fos = new
	 * FileOutputStream(filepath);
	 * kangoo.compress(Bitmap.CompressFormat.PNG, 75, fos); fos.flush();
	 * fos.close(); Toast.makeText( ViewImagesActivity.this,
	 * "Saved Image Pic" + t + ".png to SDCard", Toast.LENGTH_LONG).show();
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); Toast.makeText(ViewImagesActivity.this,
	 * e.toString(), Toast.LENGTH_LONG).show(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace();
	 * Toast.makeText(ViewImagesActivity.this, e.toString(),
	 * Toast.LENGTH_LONG).show(); }
	 * 
	 * } else if (actionId == ID_EMAIL) { acceptItem.setSelected(true);
	 * Toast.makeText(ViewImagesActivity.this, "Going To Email Box",
	 * Toast.LENGTH_SHORT) .show(); Intent picMessageIntent = new Intent(
	 * android.content.Intent.ACTION_SEND);
	 * picMessageIntent.setType("image/png"); File downloadedPic = new File(
	 * Environment
	 * .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
	 * "/Pic1.png"); picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri
	 * .fromFile(downloadedPic)); startActivity(picMessageIntent); } else if
	 * (actionId == ID_INDEX) { Toast.makeText(ViewImagesActivity.this,
	 * "Going To Setting Index Doccument", Toast.LENGTH_SHORT).show();
	 * Intent intent = new Intent(); Bundle bundle = new Bundle();
	 * CharSequence textmessageLinkWB; CharSequence textmessageDocName;
	 * textmessageLinkWB = messageLinkWB; textmessageDocName =
	 * messageDocName; bundle.putCharSequence("messageDocName",
	 * textmessageDocName); bundle.putCharSequence("messageLinkWB",
	 * textmessageLinkWB); intent.putExtras(bundle);
	 * intent.setClass(ViewImagesActivity.this, showFullImage.class);
	 * startActivity(intent); finish(); } } });
	 * 
	 * // setup on dismiss listener, set the icon back to normal
	 * mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener()
	 * {
	 * 
	 * @Override public void onDismiss() { //
	 * mMoreIv.setImageResource(R.drawable.ic_list_more);
	 * mQuickAction.dismiss(); } });
	 */

	/*
	 * btnCoverFlow.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) {
	 * mQuickAction.setAnimStyle(QuickAction.ANIM_AUTO);
	 * mQuickAction.show(v);
	 * 
	 * } });
	 * 
	 * btnBack.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { Intent intent = new Intent();
	 * Bundle bundle = new Bundle(); CharSequence textmessageLinkWB;
	 * textmessageLinkWB = messageLinkWB;
	 * bundle.putCharSequence("messageLinkWB", textmessageLinkWB);
	 * intent.putExtras(bundle); intent.setClass(ViewImagesActivity.this,
	 * ShowListProcessWithDocs.class); startActivity(intent); finish();
	 * 
	 * } });
	 */

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	GlobalVariable.config = getResources().getConfiguration();
	try {
	if (GlobalVariable.config.orientation == Configuration.ORIENTATION_PORTRAIT) {
	if (mZoomControl.getZoomState().getZoom() <= 2.8f) {
	resetZoomState();
	return;
	}
	} else if (GlobalVariable.config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	if (mZoomControl.getZoomState().getZoom() <= 2.6f) {
	mZoomControl.getZoomState().setPanX(0.5f);
	mZoomControl.getZoomState().setPanY(0.22f);
	mZoomControl.getZoomState().setZoom(2.6f);
	mZoomControl.getZoomState().notifyObservers();
	}
	}
	} catch (Exception e) {
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
	layoutZoomView.removeAllViewsInLayout();

	}

	@Override
	protected Long doInBackground(String... params) {

	GlobalVariable.mBitmap = kangoo;
	System.gc();
	return null;
	}

	@Override
	protected void onPostExecute(Long arg0) {
	setImageView();
	layoutZoomView.addView(mZoomView);
	mProgressBar.setVisibility(View.GONE);
	setCanvas = "1";
	}
	}

	public void goLeft() {
	/*setNullAsyncTask();
	count--;
	leftImage=topImage=0;
	if (count < 0) {
	count = 2;
	}
	isLeftorRight = false;
	mProgressBar.setVisibility(View.VISIBLE);
	layoutZoomView.startAnimation(animLeftToRight1);
	countDown = new MainCountDown(180, 180);
	countDown.start();
	setCanvas = "2";
	Toast.makeText(ViewImagesActivity.this,
	"Page " + String.valueOf(count + 1), Toast.LENGTH_SHORT).show();*/
	}

	public void goRight() {
	/*setNullAsyncTask();
	leftImage=topImage=0;
	count++;
	if (count == 3) {
	count = 0;
	}
	isLeftorRight = true;
	mProgressBar.setVisibility(View.VISIBLE);
	layoutZoomView.startAnimation(animRightToLeft1);
	countDown = new MainCountDown(180, 180);
	countDown.start();
	setCanvas = "2";
	Toast.makeText(ViewImagesActivity.this,
	"Page " + String.valueOf(count + 1), Toast.LENGTH_SHORT).show();*/
	}

	public void goTop() {
	/*setNullAsyncTask();
	leftImage=topImage=0;
	count++;
	if (count == 3) {
	count = 0;
	}
	isBottomToTop = true;
	mProgressBar.setVisibility(View.VISIBLE);
	layoutZoomView.startAnimation(animBottomToTop);
	countDown = new MainCountDown(180, 180);
	countDown.start();
	setCanvas = "2";
	Toast.makeText(ViewImagesActivity.this, "Backward Document",
	Toast.LENGTH_SHORT).show();*/
	}

	public void goBottom() {
	/*setNullAsyncTask();
	count++;
	leftImage=topImage=0;
	if (count == 3) {
	count = 0;
	}
	isBottomToTop = false;
	mProgressBar.setVisibility(View.VISIBLE);
	layoutZoomView.startAnimation(animTopToBottom);
	countDown = new MainCountDown(180, 180);
	countDown.start();
	setCanvas = "2";

	Toast.makeText(ViewImagesActivity.this, "Forward Document",
	Toast.LENGTH_SHORT).show();*/
	}

	public class MainCountDown extends CountDownTimer {

	public MainCountDown(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
	// GlobalVariable.clearBitmap();
	parserTask = new ParserTask();
	parserTask.execute();
	}

	@Override
	public void onTick(long millisUntilFinished) {
	}
	}

	private void setImageView() {
	mZoomControl = new ZoomControl();
	mZoomListener = new TouchZoomListener(mContext);
	mZoomView = new ImageZoomView(mContext, null);
	mZoomControl.setContext(mContext);
	mZoomListener.setZoomControl(mZoomControl);
	mZoomView.setZoomState(mZoomControl.getZoomState());
	mZoomView.setImage();
	mZoomView.setOnTouchListener(mZoomListener);
	mZoomControl.setAspectQuotient(mZoomView.getAspectQuotient());
	resetZoomState();
	}

	/*
	 * private void setValue(int count) { //
	 * txtTitleImage.setText(GlobalVariable.mAlbumsGrid.get(count).getTitle());
	 * int t = count + 1; // txtTitleImage.setText("Page_" + t); //
	 * txtNumberPage.setText(String.valueOf(count + 1) + strMaxSize); }
	 */

	private void setNullAsyncTask() {
	if (parserTask != null) {
	parserTask.cancel(true);
	parserTask = null;
	}
	}

	/*
	 * public void visibilityLayoutNav(boolean check) { if (check) {
	 * layoutNavigation.setVisibility(View.VISIBLE);
	 * layoutNavigation.startAnimation(animShowNavbar); } else {
	 * layoutNavigation.setVisibility(View.GONE); } }
	 */

	public void resetLayout() {
	layoutZoomView.removeAllViews();
	setImageView();
	layoutZoomView.addView(mZoomView);
	}

	@Override
	protected void onDestroy() {
	super.onDestroy();
	setNullAsyncTask();
	// GlobalVariable.clearBitmap();
	// GlobalVariable.byteBuffer = null;
	System.gc();
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
	 * inflater = getMenuInflater(); inflater.inflate(R.menu.menu_reset, menu);
	 * return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case R.id.menu_id_reset: { resetLayout(); break; } }
	 * return super.onOptionsItemSelected(item); }
	 */

	private void resetZoomState() {
	mZoomControl.getZoomState().setPanX(0.5f);
	if (GlobalVariable.config.orientation == Configuration.ORIENTATION_PORTRAIT) {
	mZoomControl.getZoomState().setPanY(0.5f);
	mZoomControl.getZoomState().setZoom(1f);
	}
	if (GlobalVariable.config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	mZoomControl.getZoomState().setPanY(0.22f);
	mZoomControl.getZoomState().setZoom(2.6f);
	}
	mZoomControl.getZoomState().notifyObservers();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (data != null) {
	setNullAsyncTask();
	mProgressBar.setVisibility(View.VISIBLE);
	count = Integer.valueOf(data.getStringExtra("COUNT"));
	}

	if (requestCode == CAMERA_REQUEST) {
	Bitmap photo = (Bitmap) data.getExtras().get("data");
	// imageView.setImageBitmap(photo);
	/*
	 * Toast.makeText(ViewImagesActivity.this, "Going To CAMERE",
	 * Toast.LENGTH_SHORT).show();
	 */
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

	public boolean onTouch(View v, MotionEvent event) {
	final int action = event.getAction();
	touchX = event.getX();
	touchY = event.getY();

	width2 = v.getWidth();
	height2 = v.getHeight();
	currentX = event.getX(0);
	currentY = event.getY(0);
	currentX1 = event.getX(1);
	currentY1 = event.getY(1);
	
	 Log.e("startx|eventX",start.x+"|"+event.getX());
	 
	if (mVelocityTracker == null) {
	mVelocityTracker = VelocityTracker.obtain();
	}
	mVelocityTracker.addMovement(event);
	switch (action) {
	case MotionEvent.ACTION_DOWN: {
	start.set(event.getX(), event.getY());
	spaceX = GlobalVariable.PANLEFT;
	spaceY = GlobalVariable.PANTOP;

	if (touchX > 5 && touchX < 20 && touchY > 2 && touchY < 40) {
	mQuickAction.dismiss();
	Intent intent = new Intent();
	Bundle bundle = new Bundle();
	CharSequence textMessage;
	CharSequence textDocName;
	textMessage = "" + messageLinkWB;
	textDocName = "" + messageDocName;
	bundle.putCharSequence("messageLinkWB", textMessage);
	bundle.putCharSequence("messageDocName", textDocName);
	intent.putExtras(bundle);
	intent.setClass(ViewImagesActivity.this, IndexDoc.class);
	startActivity(intent);
	finish();
	} else {
	/*
	 * if (setCanvas.equals("1")) { mQuickAction.show(v); } else {
	 * mQuickAction.dismiss(); }
	 */
	}

	// start.set(event.getX(), event.getY());
	// deltaY = GlobalVariable.PANTOP;

	// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
	mZoomControl.stopFling();
	mDownX = touchX;
	mDownY = touchY;
	mX = touchX;
	mY = touchY;

	// -------------------------------
	value = 1;
	mDownX = currentX;
	mDownY = currentY;
	if (isDoubleClick(mDownX, mDownY)) {

	// float newDist = spacing(event);
	setCanvas = "2";
	setZoom(0.2f);
	ScaleZoom = (float) (ScaleZoom * Math.pow(20, 0.2));
	if (ScaleZoom >= 30) {
		ScaleZoom = 1;
		leftImage=topImage=0;
	}
	top = kangoo.getHeight();
	left = kangoo.getWidth();

	// float scale = newDist / oldDist;

	mZoomControl.resetZoom();
	
	setCanvas = "1";
	value = 2;
	// isNavigation = false;
	// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
	break;
	} else {
	// zoomscale = 1;
	sX = mDownX;
	sY = mDownY;
	new CountDown(1000, 1000).start();
	}
	/*
	 * isNavigation = false;
	 * 
	 * if (isNavigation) { isNavigation = false; } else { isNavigation =
	 * true; }
	 */

	break;
	}
	case MotionEvent.ACTION_POINTER_DOWN:
	// oldDist = spacing(event);

	break;

	case MotionEvent.ACTION_POINTER_2_DOWN: {
	dxx = currentX - currentX1;
	dxy = currentY - currentY1;
	spaceTemp = (float) Math.sqrt(dxx * dxx + dxy * dxy);
	isZoomMutilPoint = true;
	break;
	}
	case MotionEvent.ACTION_POINTER_UP: {
		
	mZoomControl.stopFling();
	isZoomMutilPoint = false;
	break;
	}
	case MotionEvent.ACTION_POINTER_2_UP: {

	isZoomMutilPoint = false;
	break;
	}
	case MotionEvent.ACTION_MOVE: {
		 leftImage = leftImage + (event.getX() - start.x);
	      topImage = topImage + event.getY() - start.y;
	      Log.e("startx|eventX",start.x+"|"+event.getX());
	 	 Log.e("leftImage|topImage",leftImage+"|"+topImage);
	// top = Math.abs(positionY - prevY);
	// = Math.abs(positionX - prevX);
	/*
	 * Toast.makeText(ViewImagesActivity.this, "deltaY=" + top,
	 * Toast.LENGTH_SHORT).show();
	 */
	mQuickAction.dismiss();
	// setCanvas = "2";
	value = 2;
	/*
	 * isNavigation = false; ((ViewImagesActivity)
	 * mContext).visibilityLayoutNav(false);
	 */
	if (isZoomMutilPoint) {
	isMove = false;
	dxx = currentX1 - currentX;
	dxy = currentY1 - currentY;

	double space = Math.sqrt(dxx * dxx + dxy * dxy);
	if (space > spaceTemp) {
	setZoom(0.02f);
	}
	if (space < spaceTemp) {
	setZoom(-0.02f);
	}
	currentX = (int) ((currentX1 + currentX) / 2);
	currentY = (int) ((currentY1 + currentY) / 2);
	space = spaceTemp;
	} else {
	isMove = true;
	 
	dx = (touchX - mX) / v.getWidth();
	dy = (touchY - mY) / v.getHeight();
	mZoomControl.pan(-dx, -dy);
	

	}
	mX = touchX;
	mY = touchY;

	break;
	}
	case MotionEvent.ACTION_UP: {

		upX = (int) event.getX();
	    upY = (int) event.getY();
	    Log.e("UP","UP");
	// spaceX = spaceX + (upX - touchX);
	// spaceY = spaceY + (upY - touchY);
	// setCanvas = "1";
	if (isMove) {

	if (GlobalVariable.PANLEFT >= (deltaX + 100)) {
	mZoomControl.stopFling();
	((ViewImagesActivity) mContext).goLeft();
	break;
	}
	if (GlobalVariable.PANRIGHT <= (v.getWidth() - (deltaX + 100))) {
	mZoomControl.stopFling();
	((ViewImagesActivity) mContext).goRight();

	break;
	}

	if (GlobalVariable.PANTOP >= (deltaY + 50)) {
	mZoomControl.stopFling();
	((ViewImagesActivity) mContext).goTop();
	break;
	}
	if (GlobalVariable.PANBOTTOM <= (v.getHeight() - (deltaY +50))) {
	mZoomControl.stopFling();
	((ViewImagesActivity) mContext).goBottom();
	break;
	}

	}
	if (value == 1) {
	new Hidden(600, 600).start();
	}
	mVelocityTracker.computeCurrentVelocity(1000,
	mScaledMaximumFlingVelocity);
/*	mZoomControl.startFling(-mVelocityTracker.getXVelocity()
	/ v.getWidth(), -mVelocityTracker.getYVelocity()
	/ v.getHeight());*/
	break;
	}
	default: {
	mVelocityTracker.recycle();
	mVelocityTracker = null;
	break;
	}
	}

	return true;
	}

	/*
	 * public boolean onTouch(View v, MotionEvent event) { dumpEvent(event);
	 * switch (event.getAction() & MotionEvent.ACTION_MASK) { case
	 * MotionEvent.ACTION_DOWN: touchX = (int) event.getX(); touchY = (int)
	 * event.getY(); savedMatrix.set(matrix); start.set(event.getX(),
	 * event.getY()); mode = DRAG; break; case MotionEvent.ACTION_POINTER_DOWN:
	 * oldDist = spacing(event); if (oldDist > 10f) { savedMatrix.set(matrix);
	 * midPoint(mid, event); mode = ZOOM; setCanvas = "2"; } break; case
	 * MotionEvent.ACTION_UP: case MotionEvent.ACTION_POINTER_UP: mode = NONE;
	 * break; case MotionEvent.ACTION_MOVE: if (mode == DRAG) { setCanvas = "2";
	 * goRight(); setTitle("MOVE " + setCanvas); float newDist = spacing(event);
	 * scale = newDist / oldDist; if (scale == 1f) { if (event.getX() - start.x
	 * > 0) { setTitle("forward image"); } else { setTitle("backward image"); }
	 * } // ... matrix.set(savedMatrix); matrix.postTranslate(event.getX() -
	 * start.x, event.getY() - start.y);
	 * 
	 * } else if (mode == ZOOM) { float newDist = spacing(event); if (newDist >
	 * 10f) { matrix.set(savedMatrix); } } break; } return true; }
	 */

	private float spacing(MotionEvent event) {
	float x = event.getX(0) - event.getX(1);
	float y = event.getY(0) - event.getY(1);
	return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) {
	float x = event.getX(0) + event.getX(1);
	float y = event.getY(0) + event.getY(1);
	point.set(x / 2, y / 2);
	}

	public void loadCanvas() {

	layMain = (LinearLayout) this.findViewById(R.id.myScreen2);
	layMain.setOnTouchListener(this);
	layMain.addView(new Mens(this));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
	case KeyEvent.KEYCODE_DPAD_LEFT: {
	goLeft();
	break;
	}
	case KeyEvent.KEYCODE_DPAD_RIGHT: {
	goRight();
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
	intent.setClass(ViewImagesActivity.this,
	ShowListProcessWithDocs.class);
	startActivity(intent);
	finish();
	}

	}
	return false;
	}

	public static int getMinValue(float[] numbers) {
	float minValue = numbers[0];
	int min = 0;
	for (int i = 1; i < numbers.length; i++) {
	if (numbers[i] < minValue) {
	minValue = numbers[i];
	min = i;
	}
	}
	return min;
	}

	private void readRss_Segment(String Url) {
	SegmentX.clear();
	SegmentY.clear();
	SegmentHeight.clear();
	SegmentWidth.clear();
	try {
	// URL rssUrl = new URL(
	// "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=xml");
	// "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=protocol_buffer");

	URL rssUrl = new URL(Url);

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
	for (int i = 0; i < x; i++) {
	SegmentX.add((myRssFeed_Segment.getList().get(i).getX()));
	SegmentY.add((myRssFeed_Segment.getList().get(i).getY()));
	SegmentHeight.add((myRssFeed_Segment.getList().get(i).getH()));
	SegmentWidth.add((myRssFeed_Segment.getList().get(i).getW()));
	}

	}
	}

	public void initData() {

	}

	public void setZoom(float value) {
	mZoomControl.zoom((float) Math.pow(20, value), mDownX / width2, mDownY
	/ height2);
	}

	public class Hidden extends CountDownTimer {
	public Hidden(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
	if (value == 2) {
	return;
	}
	// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);

	// if (isNavigation) {
	// ((ViewImagesActivity) mContext).visibilityLayoutNav(true);
	// } else {
	// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
	// }

	}

	@Override
	public void onTick(long millisUntilFinished) {
	if (value == 2) {
	// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
	return;
	}
	}
	}

	private boolean isDoubleClick(float mX, float mY) {
	if ((mX >= sX - 10 && mX <= sX + 10)
	&& (mY >= sY - 10 && mY <= sY + 10)) {
	return true;
	}
	return false;
	}

	public class CountDown extends CountDownTimer {
	public CountDown(long millisInFuture, long countDownInterval) {
	super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
	sX = 10000000;
	sY = 10000000;
	}

	@Override
	public void onTick(long millisUntilFinished) {

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
	if (IndexSelected.equals("Amount :")) {
	NewValueEditText.setRawInputType(Configuration.KEYBOARD_12KEY);
	} else {

	}
	NewValueEditText.setText(IndexSelected);
	NewValueEditText.setOnClickListener(new View.OnClickListener() {
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
	+ ValueListIndex.get(0));
	ActionItem amountItem = new ActionItem(ID_AMOUNT, "Amount :"
	+ ValueListIndex.get(1));
	ActionItem currencyItem = new ActionItem(ID_CURRENCY, "Currency :"
	+ ValueListIndex.get(2));
	ActionItem typeItem = new ActionItem(ID_TYPE, "Type :"
	+ ValueListIndex.get(3));
	mQuickAction = new QuickAction(this);

	mQuickAction.addActionItem(creditorItem);
	mQuickAction.addActionItem(amountItem);
	mQuickAction.addActionItem(currencyItem);
	mQuickAction.addActionItem(typeItem);

	mQuickAction
	.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
	public void onItemClick(QuickAction quickAction, int pos,
	int actionId) {
	ActionItem actionItem = quickAction.getActionItem(pos);
	positionChange = pos;
	IndexSelected = ValueListIndex.get(pos);
	showAddDialog();
	Toast.makeText(ViewImagesActivity.this,
	actionItem.getTitle(), Toast.LENGTH_SHORT)
	.show();
	}
	});

	// setup on dismiss listener, set the icon back to normal
	mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {
	public void onDismiss() {
	// mMoreIv.setImageResource(R.drawable.ic_list_more);
	mQuickAction.dismiss();
	}
	});
	}

	public class Mens extends View {
	Button undo_Btn;

	public Mens(Context context) {
	super(context);
	mScaleDetector = new ScaleGestureDetector(context,
	new ScaleListener());
	setFocusable(true);
	undo_Btn = new Button(context);
	undo_Btn.measure(40, 40); // size of view
	int width = undo_Btn.getMeasuredWidth();
	int height = undo_Btn.getMeasuredHeight();
	int left = 170;
	int top = 200;
	undo_Btn.layout(left, top, left + width, top + height);
	undo_Btn.setBackgroundDrawable(getResources().getDrawable(
	R.xml.i_symbol));
	// undo_Btn.setBackgroundDrawable(@xml/thumbnail);
	undo_Btn.setVisibility(VISIBLE);
	// undo_Btn.setId(10);
	// undo_Btn.setPadding(50, 250, 0, 0);
	undo_Btn.setOnClickListener(new OnClickListener() {
	public void onClick(View v) {

	}
	});

	mContext = context;
	mLongPressTimeout = ViewConfiguration.getLongPressTimeout();
	mScaledMaximumFlingVelocity = ViewConfiguration.get(context)
	.getScaledMaximumFlingVelocity();
	mVibrator = (Vibrator) context.getSystemService("vibrator");

	}

	public class ScaleListener extends
	ScaleGestureDetector.SimpleOnScaleGestureListener {
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
	mScaleFactor *= detector.getScaleFactor();
	mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
	invalidate();
	return true;
	}
	}

	@Override
	protected void onDraw(Canvas canvas) {
	top = GlobalVariable.PANTOP;
	zoomscale = mZoomControl.getZoomState().getZoom();
	Paint paint = new Paint();
	paint.setColor(Color.RED);
	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeWidth(3);
	int x = myRssFeed_Segment.getList().size();
	float[] DeltaH = new float[x];
	Display display = getWindowManager().getDefaultDisplay();
	DeviceWidth = display.getWidth();
	DeviceHeight = display.getHeight();
	FirstImageWidth = 2115.0F;
	FirstImageHeight = 2921.0F;

	if (FirstImageWidth / DeviceWidth >= FirstImageHeight
	/ DeviceHeight) {
	ScaleFit = FirstImageWidth / DeviceWidth;
	FitImageHeight = FirstImageHeight * 1.0F / ScaleFit;
	deltaX = 0;
	deltaY = (DeviceHeight - FitImageHeight) / 2.0F;
	} else if (FirstImageWidth / DeviceWidth < FirstImageHeight
	/ DeviceHeight) {
	ScaleFit = FirstImageHeight / DeviceHeight;
	FitImageWidth = FirstImageWidth * 1.0F / ScaleFit;
	deltaX = (DeviceWidth - FitImageWidth) / 2.0F;
	deltaY = 0;
	}
	ImageY = touchY - deltaY;
	ImageX = touchX - deltaX;
	for (int i = 0; i < x; i++) {
	float a = Float.parseFloat(SegmentX.get(i));
	float b = Float.parseFloat(SegmentY.get(i));
	float c = Float.parseFloat(SegmentWidth.get(i));
	float d = Float.parseFloat(SegmentHeight.get(i));

	SegmentXLoop = (a / ScaleFit);
	SegmentYLoop = (b / ScaleFit);
	SegmentWLoop = (c / ScaleFit);
	SegmentHLoop = (d / ScaleFit);

	DeltaH[i] = (float) Math.sqrt((ImageX - SegmentXLoop)
	* (ImageX - SegmentXLoop) + (ImageY - SegmentYLoop)
	* (ImageY - SegmentYLoop));

	/*
	 * if (a <= ImageX && ImageX < a + c && b <= ImageY && ImageY <=
	 * b + d) { minDelta = i;
	 * 
	 * } else { minDelta = getMinValue(DeltaH); }
	 */
	}
	minDelta = getMinValue(DeltaH);

	float e = Float.parseFloat(SegmentX.get(minDelta));
	float f = Float.parseFloat(SegmentY.get(minDelta));
	float g = Float.parseFloat(SegmentWidth.get(minDelta));
	float h = Float.parseFloat(SegmentHeight.get(minDelta));
	if (setCanvas.equals("1")) {
	SegmentXDraw = (e / ScaleFit);
	SegmentYDraw = (f / ScaleFit);
	SegmentWDraw = (g / ScaleFit);
	SegmentHDraw = (h / ScaleFit);
	} else {
	SegmentXDraw = SegmentHDraw = SegmentWDraw = SegmentYDraw = 0;
	}

	canvas.drawRect(SegmentXDraw + deltaX, (SegmentYDraw + deltaY),
	(SegmentXDraw + deltaX + SegmentWDraw), (SegmentYDraw
	+ deltaY + SegmentHDraw), paint); 
	undo_Btn.draw(canvas);
	canvas.restore();
	invalidate();
	/*
	 * Toast.makeText(ViewImagesActivity.this, "top=" + top,
	 * Toast.LENGTH_SHORT).show();
	 */

	}
	}
	
	


}