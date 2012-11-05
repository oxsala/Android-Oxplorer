package aexp.explist;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.FloatMath;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hots.util.GlobalVariable;
import com.hots.zoom.ImageZoomView;
import com.hots.zoom.TouchZoomListener;
import com.hots.zoom.ZoomControl;
import com.test.androidtest.ActionItem;
import com.test.androidtest.QuickAction;

public class ViewImagesActivity extends Activity implements OnTouchListener {
	private Bundle extra;
	private TextView txtTitle;
	private TextView txtTitleImage;
	private TextView txtNumberPage;
	private TextView btnBack;
	private ImageButton btnLeft;
	private ImageButton btnRight;
	private ImageButton btnCoverFlow;
	private ProgressBar mProgressBar;
	private RelativeLayout layoutNavigation;
	private RelativeLayout layoutNavigationTop;
	private RelativeLayout layoutNavigationBottom;

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
	private Animation animShowNavbar;
	Bitmap kangoo;
	private String strMaxSize;
	private String filename;
	// String extStorageDirectory;
	private int count = 0;
	private boolean isLeftorRight = true;
	private String messageLinkWB;
	private String messageDocName;
	private final String[] LinkImage = {
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I1.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I3.png",
			"http://174.143.148.49/RssSample3.1/mandant/imgs/I2.png" };
	private static final int CAMERA_REQUEST = 1888;
	private static final int ID_SAVE = 1;
	private static final int ID_EMAIL = 2;
	private static final int ID_INDEX = 3;
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_image_activity);
		RelativeLayout RelativeMain = (RelativeLayout) this
				.findViewById(R.id.mydraw);
		RelativeMain.setOnTouchListener(this);
		RelativeMain.addView(new Mens(this));
		GlobalVariable.config = getResources().getConfiguration();
		mContext = this;
		layoutZoomView = (LinearLayout) findViewById(R.id.zoomview);
		layoutNavigation = (RelativeLayout) findViewById(R.id.navigation);
		layoutNavigationTop = (RelativeLayout) findViewById(R.id.navigation_top);
		layoutNavigationBottom = (RelativeLayout) findViewById(R.id.navigation_bottom);
		txtTitle = (TextView) findViewById(R.id.view_image_txt_title);
		txtTitleImage = (TextView) findViewById(R.id.view_image_txt_title_image);
		txtNumberPage = (TextView) findViewById(R.id.view_image_txt_number_page);
		btnBack = (TextView) findViewById(R.id.view_image_btn_back_top);
		btnLeft = (ImageButton) findViewById(R.id.view_image_btn_back);
		btnRight = (ImageButton) findViewById(R.id.view_image_btn_next);
		btnCoverFlow = (ImageButton) findViewById(R.id.view_image_btn_list);
		mProgressBar = (ProgressBar) findViewById(R.id.view_image_progress_bar);
		animRightToLeft = AnimationUtils.loadAnimation(mContext,
				R.anim.popup_right_to_left);
		animLeftToRight = AnimationUtils.loadAnimation(mContext,
				R.anim.popup_left_to_right);
		animRightToLeft1 = AnimationUtils.loadAnimation(mContext,
				R.anim.popup_right_to_left1);
		animLeftToRight1 = AnimationUtils.loadAnimation(mContext,
				R.anim.popup_left_to_right1);
		animShowNavbar = AnimationUtils.loadAnimation(mContext,
				R.anim.popup_show_navbar);
		Bundle bundle = this.getIntent().getExtras();
		CharSequence textMessage3 = bundle.getCharSequence("messageLinkWB");
		messageLinkWB = "" + textMessage3;
		CharSequence textDocName = bundle.getCharSequence("messageDocName");
		messageDocName = "" + textDocName;
		strMaxSize = "/3";
		count = 0;
		setValue(count);
		txtTitle.setText(messageDocName);
		((ViewImagesActivity) mContext).visibilityLayoutNav(false);
		btnLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goLeft();
			}
		});
		btnRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goRight();
			}
		});

		layoutNavigationTop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		layoutNavigationBottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});

		ActionItem addItem = new ActionItem(ID_SAVE, "Save File",
				getResources().getDrawable(R.drawable.save_file));
		final ActionItem acceptItem = new ActionItem(ID_EMAIL, "Send to email",
				getResources().getDrawable(R.drawable.send_email));
		ActionItem uploadItem = new ActionItem(ID_INDEX, "Setting Index",
				getResources().getDrawable(R.drawable.doc));
		final QuickAction mQuickAction = new QuickAction(this);

		mQuickAction.addActionItem(addItem);
		mQuickAction.addActionItem(acceptItem);
		mQuickAction.addActionItem(uploadItem);

		// setup the action item click listener
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction quickAction, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);

						if (actionId == ID_SAVE) { // Add item selected
							Toast.makeText(ViewImagesActivity.this,
									"Saving Image...", Toast.LENGTH_SHORT)
									.show();
							OutputStream outStream = null;
							String extStorageDirectory = Environment
									.getExternalStorageState().toString();
							kangoo = DecodeFile(LinkImage[count]);
							File file = new File(extStorageDirectory, "er.PNG");
							String filepath = Environment
									.getExternalStorageDirectory()
									.getAbsolutePath();
							String extraPath = "/Pic" + count + ".png";
							filepath += extraPath;
							FileOutputStream fos = null;
							try {
								int t = count + 1;
								fos = new FileOutputStream(filepath);
								kangoo.compress(Bitmap.CompressFormat.PNG, 75,
										fos);
								fos.flush();
								fos.close();
								Toast.makeText(
										ViewImagesActivity.this,
										"Saved Image Pic" + t
												+ ".png to SDCard",
										Toast.LENGTH_LONG).show();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(ViewImagesActivity.this,
										e.toString(), Toast.LENGTH_LONG).show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Toast.makeText(ViewImagesActivity.this,
										e.toString(), Toast.LENGTH_LONG).show();
							}

						} else if (actionId == ID_EMAIL) {
							acceptItem.setSelected(true);
							Toast.makeText(ViewImagesActivity.this,
									"Going To Email Box", Toast.LENGTH_SHORT)
									.show();
							Intent picMessageIntent = new Intent(
									android.content.Intent.ACTION_SEND);
							picMessageIntent.setType("image/png");
							File downloadedPic = new File(
									Environment
											.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
									"/Pic1.png");
							picMessageIntent.putExtra(Intent.EXTRA_STREAM, Uri
									.fromFile(downloadedPic));
							startActivity(picMessageIntent);
						} else if (actionId == ID_INDEX) {
							Toast.makeText(ViewImagesActivity.this,
									"Going To Setting Index Doccument",
									Toast.LENGTH_SHORT).show();
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							CharSequence textmessageLinkWB;
							CharSequence textmessageDocName;
							textmessageLinkWB = messageLinkWB;
							textmessageDocName = messageDocName;
							bundle.putCharSequence("messageDocName",
									textmessageDocName);
							bundle.putCharSequence("messageLinkWB",
									textmessageLinkWB);
							intent.putExtras(bundle);
							intent.setClass(ViewImagesActivity.this,
									showFullImage.class);
							startActivity(intent);
							finish();
						}
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

		btnCoverFlow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mQuickAction.setAnimStyle(QuickAction.ANIM_AUTO);
				mQuickAction.show(v);

			}
		});

		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		GlobalVariable.config = getResources().getConfiguration();
		try {
			if (GlobalVariable.config.orientation == Configuration.ORIENTATION_PORTRAIT) {
				// Màn hình d�?c
				if (mZoomControl.getZoomState().getZoom() <= 2.8f) {
					resetZoomState();
					return;
				}
			} else if (GlobalVariable.config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				// Màn hình ngang
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
		parserTask = new ParserTask(count);
		parserTask.execute();
	}

	private class ParserTask extends AsyncTask<String, Integer, Long> {
		private final int mCount;

		public ParserTask(int count) {
			mCount = count;
		}

		@Override
		protected void onPreExecute() {
			layoutZoomView.removeAllViewsInLayout();
		}

		@Override
		protected Long doInBackground(String... params) {
			// drawRectangle();
			Display display = getWindowManager().getDefaultDisplay();
			int dwidth = display.getWidth();
			int dheight = display.getHeight();
			kangoo = DecodeFile(LinkImage[count]);
			kangoo = Bitmap.createScaledBitmap(kangoo, dwidth, dheight, true);

			// GlobalVariable.clearBitmap();
			// filename = "images/" + GlobalVariable.ALBUMSID + "/largerimage/"
			// + GlobalVariable.mAlbumsGrid.get(mcount).getImage();
			/*
			 * filename = "images/asianhots/largerimage/asianhots01.jpg";
			 * GlobalVariable.byteBuffer = FileUtil.readFileFromAssets(mContext,
			 * filename);
			 */
			// GlobalVariable.mBitmap =
			// BitmapFactory.decodeByteArray(GlobalVariable.byteBuffer, 0,
			// GlobalVariable.byteBuffer.length);
			// GlobalVariable.mBitmap = kangoo;
			// GlobalVariable.byteBuffer = null;
			System.gc();
			return null;
		}

		@Override
		protected void onPostExecute(Long arg0) {
			setValue(count);
			setImageView();
			layoutZoomView.addView(mZoomView);
			mProgressBar.setVisibility(View.GONE);
			if (isLeftorRight) {
				layoutZoomView.startAnimation(animRightToLeft);
			} else {
				layoutZoomView.startAnimation(animLeftToRight);
			}
		}
	}

	public void goLeft() {
		setNullAsyncTask();
		count--;
		if (count < 0) {
			// count = GlobalVariable.mAlbumsGrid.size()-1;
			count = 2;
		}
		isLeftorRight = false;
		mProgressBar.setVisibility(View.VISIBLE);
		layoutZoomView.startAnimation(animLeftToRight1);
		countDown = new MainCountDown(180, 180);
		countDown.start();
	}

	public void goRight() {
		setNullAsyncTask();
		count++;
		// if(count == GlobalVariable.mAlbumsGrid.size())
		if (count == 3) {
			count = 0;
		}
		isLeftorRight = true;
		mProgressBar.setVisibility(View.VISIBLE);
		layoutZoomView.startAnimation(animRightToLeft1);
		countDown = new MainCountDown(180, 180);
		countDown.start();
	}

	public class MainCountDown extends CountDownTimer {

		public MainCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			// GlobalVariable.clearBitmap();
			parserTask = new ParserTask(count);
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

	private void setValue(int count) {
		// txtTitleImage.setText(GlobalVariable.mAlbumsGrid.get(count).getTitle());
		int t = count + 1;
		txtTitleImage.setText("Page_" + t);
		txtNumberPage.setText(String.valueOf(count + 1) + strMaxSize);
	}

	private void setNullAsyncTask() {
		if (parserTask != null) {
			parserTask.cancel(true);
			parserTask = null;
		}
	}

	public void visibilityLayoutNav(boolean check) {
		if (check) {
			layoutNavigation.setVisibility(View.VISIBLE);
			layoutNavigation.startAnimation(animShowNavbar);
		} else {
			layoutNavigation.setVisibility(View.GONE);
		}
	}

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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
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

	public class Mens extends View {
		Button undo_Btn;

		public Mens(Context context) {
			super(context);
			mScaleDetector = new ScaleGestureDetector(context,
					new ScaleListener());
			setFocusable(true);

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
			Paint paint = new Paint();
			canvas.drawColor(Color.BLACK);
			// canvas.drawBitmap(kangoo, 0, 0, paint);
			canvas.drawBitmap(kangoo, matrix, paint);
			paint.setColor(Color.RED);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(3);
			canvas.drawRect(touchX - 20, touchY - 20, 20 + touchX, touchY + 20,
					paint);
			canvas.restore();
			invalidate();
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

	public void drawRectangle() {
		RelativeLayout RelativeMain = (RelativeLayout) this
				.findViewById(R.id.mydraw);
		RelativeMain.setOnTouchListener(this);
		RelativeMain.addView(new Mens(this));
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		dumpEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			touchX = (int) event.getX();
			touchY = (int) event.getY();
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
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

				if (newDist > 10f) {
					matrix.set(savedMatrix);

				}
			}
			break;
		}

		// view.setImageMatrix(matrix);
		return true; // indicate event was handled

	}

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
}