package aexp.explist;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import oxseed.ocr.data.OCRDATA;
import oxseed.ocr.data.OCRDATA.Page.Segment;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.TestSegment.RSSFeed_Reader_Segment;
import com.test.androidtest.ActionItem;
import com.test.androidtest.QuickAction;

public class test2 extends Activity implements OnGestureListener,
		OnTouchListener {
	Boolean flag = true;
	private int NONE = 0;
	Path mPath;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	private GestureDetector gd;
	int mode = NONE;
	String TAG = "draw ";
	DrawableImageView d;
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix;
	PointF mid = new PointF();
	float scale = 1;
	float curX, curY;
	Bitmap kangoo;
	private ParserTask parserTask;
	private RSSFeed_Reader_Segment myRssFeed_Segment = null;
	private final List<String> SegmentX = new ArrayList();;
	private final List<String> SegmentY = new ArrayList();;
	private final List<String> SegmentWidth = new ArrayList();;
	private final List<String> SegmentHeight = new ArrayList();;

	private double ImageX, ImageY;
	private float deltaX, deltaY;
	private int DeviceWidth, DeviceHeight;
	private float FirstImageWidth, FirstImageHeight;
	private float FitImageWidth, FitImageHeight;
	private float SegmentXDraw, SegmentYDraw, SegmentHDraw, SegmentWDraw;
	private float ScaleWidth, ScaleHeight;
	private float ScaleFit;
	private float SegmentXLoop, SegmentYLoop, SegmentHLoop, SegmentWLoop;
	double SpaceX, SpaceY;
	int minDelta;
	float touchX = 0;
	float touchY = 0;
	int height;
	int count = 0;
	private ProgressBar mProgressBar;
	LinearLayout l;
	private MainCountDown countDown;
	private String messageLinkWB;
	private String messageDocName;
	List<Segment> listSegment;
	QuickAction mQuickAction;
	private static final int ID_CREDITOR = 1;
	private static final int ID_AMOUNT = 2;
	private static final int ID_CURRENCY = 3;
	private static final int ID_TYPE = 4;
	List<String> ValueListIndex = new ArrayList<String>();
	List<String> FirstListIndex = new ArrayList<String>();
	private int positionChange;
	private String IndexSelected;
	private EditText NewValueEditText;
	private String NewValue;

	
	  private final String[] LinkImage = {
	  "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600"
	  ,
	  "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor&image_output_format=png_gray&max_width=700&max_height=800"
	 ,
	  "https://ox6a.oxseed.net/services/ocr-archive?action=show_image&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&image_output_format=png_gray&max_width=500&max_height=600"
	  };
	 
/*	 * private final String[] LinkSegment = {
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=xml"
	 * ,
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&document_id=20100629154835621000826FFBB07EC5DD73C7BCCE07C4DF1C4A600000000b750c91781&mandant=condor"
	 * ,
	 * "https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=xml"
	 * };
	 */
/*
	private final String[] LinkImage = { "http://thcslienha.edu.vn/i1.png",
			"http://thcslienha.edu.vn/i2.png",
			"http://thcslienha.edu.vn/i1.png",
			"http://thcslienha.edu.vn/i1.png",
			"http://thcslienha.edu.vn/i2.png",
			"http://thcslienha.edu.vn/i1.png"};*/

	private final String[] LinkSegment = {
			"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=protocol_buffer",
			"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=protocol_buffer",
			"https://ox6a.oxseed.net/services/ocr-archive?action=show_segmentation&page_nr=0&document_id=20100629154625261000817AE59111D22744D07CC9EB55708AE4B0000000028a6ba6f1a&mandant=condor&response_format=protocol_buffer" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_image_activity);
		FirstListIndex.add("Coca Cola");
		FirstListIndex.add("100,00");
		FirstListIndex.add("Euro");
		FirstListIndex.add("bill");
		populateView();
		/*
		 * Bundle bundle = this.getIntent().getExtras(); CharSequence
		 * textMessage3 = bundle.getCharSequence("messageLinkWB"); messageLinkWB
		 * = "" + textMessage3; CharSequence textDocName =
		 * bundle.getCharSequence("messageDocName"); messageDocName = "" +
		 * textDocName;
		 */
		mProgressBar = (ProgressBar) findViewById(R.id.view_image_progress_bar);
		l = (LinearLayout) findViewById(R.id.myScreen2);

		Display display = getWindowManager().getDefaultDisplay();
		DeviceWidth = display.getWidth();
		DeviceHeight = display.getHeight();
		getDataProtobuf(LinkSegment[0]);

		// GlobalVariable.mBitmap = kangoo;
		// FirstImageWidth = 2115.0F;
		// FirstImageHeight = 2921.0F;

		if (FirstImageWidth / DeviceWidth >= FirstImageHeight / DeviceHeight) {
			ScaleFit = FirstImageWidth / DeviceWidth;
			FitImageHeight = FirstImageHeight * 1.0F / ScaleFit;
			FitImageWidth = DeviceWidth;
			deltaX = 0;
			deltaY = (DeviceHeight - FitImageHeight) / 2.0F;

		} else if (FirstImageWidth / DeviceWidth < FirstImageHeight
				/ DeviceHeight) {
			ScaleFit = FirstImageHeight / DeviceHeight;
			FitImageWidth = FirstImageWidth * 1.0F / ScaleFit;
			FitImageHeight = DeviceWidth;
			deltaX = (DeviceWidth - FitImageWidth) / 2.0F;
			deltaY = 0;
		}

		// getSegmentListFromServer();
		// readRss_Segment(LinkSegment[0]);
	//	setTitle("" + SegmentX.get(0) + "size=" + SegmentX.size());
		// kangoo = DecodeFile(LinkImage[0]);
		height = Math.round(DeviceWidth * (FirstImageHeight / FirstImageWidth));
		gd = new GestureDetector((OnGestureListener) this);

	}

	public void onclick(View view) {
		Log.e("height ", d.getMeasuredHeight() + "");
		Log.e("width ", d.getMeasuredWidth() + "");
		if (flag) {
			flag = false;

		} else {
			flag = true;
		}
	}

	public class DrawableImageView extends View {
		private Bitmap mBitmap;
		private Bitmap pic;
		private Canvas mCanvas;
		private final Paint mPaint;
		private int a = 255;
		private int r = 255;
		private int g = 255;
		private int b = 255;
		private float width = 20;
		float leftImage = 0;
		float topImage = 0;
		float test;
		float lastX;
		float lastY;

		PointF start = new PointF();

		float oldDist = 1f;
		Button undo_Btn;

		public DrawableImageView(Context c, Bitmap img) {
			super(c);
			undo_Btn = new Button(c);
			undo_Btn.measure(40, 40); // size of view
			int width = undo_Btn.getMeasuredWidth();
			int height = undo_Btn.getMeasuredHeight();
			int left = 320;
			int top = 480;
			undo_Btn.setPadding(320, 480, 0, 0);
			// undo_Btn.setLayoutParams(new LayoutParams(width,height));
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

			pic = img;
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
			mPaint.setARGB(a, r, g, b);

			Bitmap newBitmap = Bitmap.createBitmap(img.getWidth(),
					img.getHeight(), Bitmap.Config.RGB_565);
			Canvas newCanvas = new Canvas();
			newCanvas.setBitmap(newBitmap);
			if (img != null) {
				newCanvas.drawBitmap(img, 0, 0, null);
			}
			mBitmap = newBitmap;
			Log.e("Bitmap height", mBitmap.getHeight() + "");
			mCanvas = newCanvas;

			mCanvas.setBitmap(mBitmap);
			savedMatrix = new Matrix();

			matrix = new Matrix();
			savedMatrix.set(matrix);
			invalidate();
		}

		public DrawableImageView(Context c, Bitmap img, int alpha, int red,
				int green, int blue) {
			this(c, img);
			setColor(alpha, red, green, blue);
		}

		public DrawableImageView(Context c, Bitmap img, int alpha, int red,
				int green, int blue, float w) {
			this(c, img, alpha, red, green, blue);
			width = w;
		}

		public Bitmap getBitmap() {
			return mBitmap;
		}

		public void setWidth(float w) {
			width = w;
		}

		public void setColor(int alpha, int red, int green, int blue) {
			a = alpha;
			r = red;
			g = green;
			b = blue;
			mPaint.setARGB(a, r, g, b);
		}

		public void Undo() {
			mCanvas.drawBitmap(mBitmap, 0, 0, null);
			invalidate();
		}

		float scaleX;
		float scaleY;

		float mscale = 1;


		@Override
		protected void onLayout(boolean changed, int left, int top, int right,
				int bottom) {
			// super.onLayout(changed, left, top, right, bottom);
			Log.e("on layout changed called ", "called method ");

		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			Log.e("on size cahnged ", "called ");
			// scaleX = (float) w / mBitmap.getWidth();
			// scaleY = (float) h / mBitmap.getHeight();
			//
			scaleX = (float) oldw / w;
			scaleY = (float) oldh / h;

			Log.e("scale x | scale y", scaleX + " | " + scaleY);
			scale = scaleX > scaleY ? scaleY : scaleX;

			if (scale == 0)
				scale = 1;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			if (mBitmap != null) {
				matrix = new Matrix();
				Paint paint = new Paint();
				matrix.postTranslate(leftImage, topImage);
				matrix.postScale(scale, scale);
				canvas.drawColor(Color.BLACK);
				canvas.drawBitmap(mBitmap, matrix, paint);

				paint.setColor(Color.RED);
				paint.setStyle(Paint.Style.STROKE);
				paint.setStrokeWidth(3);

				int x = SegmentX.size();
				float[] DeltaH = new float[x];

				ImageY = (touchY - topImage * scale) / scale;
				ImageX = (touchX - leftImage * scale) / scale;
				for (int i = 0; i < x; i++) {
					float a = Float.parseFloat(SegmentX.get(i));
					float b = Float.parseFloat(SegmentY.get(i));
					float c = Float.parseFloat(SegmentWidth.get(i));
					float d = Float.parseFloat(SegmentHeight.get(i));
					// curX = (event.getX() / scale) - (left * scale);
					// curY = (event.getY() / scale) - (top * scale);

					SegmentXLoop = (float) ((a / ScaleFit));
					SegmentYLoop = (float) ((b / ScaleFit));
					SegmentWLoop = (float) ((c / ScaleFit));
					SegmentHLoop = (float) ((d / ScaleFit));
					if (SegmentXLoop <= ImageX
							&& ImageX <= SegmentXLoop + SegmentWLoop
							&& SegmentYLoop <= ImageY
							&& ImageY <= SegmentYLoop + SegmentHLoop) {
						SegmentXDraw = SegmentXLoop;
						SegmentYDraw = SegmentYLoop;
						SegmentWDraw = SegmentWLoop;
						SegmentHDraw = SegmentHLoop;
					} else {
						DeltaH[i] = (float) Math
								.sqrt((ImageX - (SegmentXLoop + SegmentWLoop / 2))
										* (ImageX - (SegmentXLoop + SegmentWLoop / 2))
										+ (ImageY - (SegmentYLoop + SegmentHLoop / 2))
										* (ImageY - (SegmentYLoop + SegmentHLoop / 2)));
					}
					minDelta = getMinValue(DeltaH);

					float e = (float) (Float.parseFloat(SegmentX.get(minDelta)));
					float f = (float) (Float.parseFloat(SegmentY.get(minDelta)));
					float g = (float) (Float.parseFloat(SegmentWidth
							.get(minDelta)));
					float h = (float) (Float.parseFloat(SegmentHeight
							.get(minDelta)));

					SegmentXDraw = scale * (e / ScaleFit);
					SegmentYDraw = scale * (f / ScaleFit);
					SegmentWDraw = scale * (g / ScaleFit);
					SegmentHDraw = scale * (h / ScaleFit);
				}
				canvas.drawRect(
						(float) (SegmentXDraw + leftImage * scale),
						(float) (SegmentYDraw + topImage * scale),
						(float) (SegmentXDraw + leftImage * scale + SegmentWDraw),
						(float) (SegmentYDraw + topImage * scale + SegmentHDraw),
						paint);
				undo_Btn.draw(canvas);
				canvas.restore();
				invalidate();
				// canvas.drawRect(curX,curX,curX+30,curY+30,paint);

			}
		}

		@Override
		public boolean onTouchEvent( MotionEvent event) {
			super.onTouchEvent(event);
			touchX = event.getX();
			touchY = event.getY();
			Log.e("left ", leftImage + "");
			Log.e("top ", topImage + "");
			Log.e("Scale", scale + "");
			Log.e("event.getX", event.getX() + "");
			Log.e("event.getY", event.getY() + "");
			Log.e("minDelta", minDelta + "");
			float umin, umax, vmin, vmax, xmin, xmax, ymin, ymax;
			xmin = 0;
			ymin = 0;
			xmax = this.getWidth();
			ymax = this.getHeight();
			umin = leftImage;
			vmin = topImage;
			umax = this.getWidth() - leftImage;
			vmax = this.getHeight() - topImage;
			float t = this.getWidth()/2;
			test=t/scale-xmax;
			curX = (event.getX() / scale) - (leftImage * scale);
			curY = (event.getY() / scale) - (topImage * scale);

			// if (flag) {

			Log.e("left | top", leftImage + " | " + topImage);
			Log.e("curX | curY", curX + " | " + curY);
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				savedMatrix.set(matrix);
				start.set(event.getX(), event.getY());
				Log.d(TAG, "mode=DRAG");
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
					intent.setClass(test2.this, IndexDoc.class);
					startActivity(intent);
					finish();
				} else {
					/*
					 * if (setCanvas.equals("1")) { mQuickAction.show(v); } else
					 * { mQuickAction.dismiss(); }
					 */
				}
				mQuickAction.show(d);
				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDist = spacing(event);
				Log.d(TAG, "oldDist=" + oldDist);
				if (oldDist > 10f) {
					savedMatrix.set(matrix);
					midPoint(mid, event);
					mode = ZOOM;
					Log.d(TAG, "mode=ZOOM");
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				Log.d(TAG, "mode=NONE");
				break;
			case MotionEvent.ACTION_MOVE:
				mQuickAction.dismiss();
				if (mode == DRAG) {
					matrix.set(savedMatrix);
					matrix.postTranslate(event.getX() - start.x, event.getY()
							- start.y);
					
					leftImage = leftImage + (event.getX() - start.x);
					topImage = topImage + event.getY() - start.y;

					if (leftImage * scale > this.getWidth() / 2) {
						goLeft();
						break;
					} else if (leftImage< test) {
						goRight();
						
						break;
					}

					if (topImage * scale > this.getHeight() / 2) {
						goBottom();
						break;
					} else if (topImage * scale < -(this.getHeight() / 2)) {
						goTop();
						break;
					}

					onLayout(true, (int) leftImage, (int) topImage,
							(int) (this.getWidth() - leftImage),
							(int) (this.getHeight() - topImage));
					Log.e("left top position ", leftImage + " |  " + topImage
							+ "");

				} else if (mode == ZOOM) {
					float newDist = spacing(event);
					Log.d(TAG, "newDist=" + newDist);
					if (newDist > 10f) {
						matrix.set(savedMatrix);
						scale = newDist / oldDist;
						matrix.postScale(scale, scale, mid.x, mid.y);
						
					}
				}
				break;
			}

			// matrixTurning();

			mCanvas.setMatrix(matrix);

			invalidate();
			// } else {

			// }
			return true;

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

/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT: {
			matrix.set(savedMatrix);
			matrix.postScale(scale, scale, mid.x, mid.y);
			if (scale == 1) {

			} else {
				scale = scale - 1;
			}
			break;
		}
		case KeyEvent.KEYCODE_DPAD_RIGHT: {
			matrix.set(savedMatrix);
			matrix.postScale(scale, scale, mid.x, mid.y);
			if (scale == 5) {

			} else {
				scale = scale + 1;
			}

			break;
		}

		}
		return false;
	}*/

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

	public void getDataProtobuf(String Url) {
		try {
			InputStream is = new URL(Url).openStream();
			OCRDATA.Page data = OCRDATA.Page.parseFrom(is);
			FirstImageWidth = data.getImageWidth();
			FirstImageHeight = data.getImageHeight();
			listSegment = data.getSegmentList();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SegmentX.clear();
		SegmentY.clear();
		SegmentHeight.clear();
		SegmentWidth.clear();
		try {
			for (Segment segmentLoop : listSegment) {
				SegmentX.add("" + segmentLoop.getX());
				SegmentY.add("" + segmentLoop.getY());
				SegmentWidth.add("" + segmentLoop.getW());
				SegmentHeight.add("" + segmentLoop.getH());
			}
		} catch (Exception e) {
			Log.e("DayTrader", "Exception getting ProtocolBuffer data", e);
		}

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

	/*
	 * // @Override public boolean onTouch(View arg0, MotionEvent arg1) { //
	 * TODO Auto-generated method stub return false; }
	 */

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
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
			// getSegmentListFromServer();
			// readRss_Segment(LinkSegment[count]);
			
		}

		@Override
		protected Long doInBackground(String... params) {
		

			return null;
		}

		@Override
		protected void onPostExecute(Long arg0) {
			kangoo = DecodeFile(LinkImage[count]);
			kangoo = Bitmap.createScaledBitmap(kangoo, DeviceWidth, height,
					true);
			d = new DrawableImageView(test2.this, kangoo);
			l.addView(d);
			mProgressBar.setVisibility(View.GONE);

		}
	}

	public void goLeft() {
		//leftImage=topImage=0;
		setNullAsyncTask();
		if (count == 0) {
			// count=0;
		} else {
			count = count - 1;
		}

		// kangoo = DecodeFile(LinkImage[count]);
		// kangoo = Bitmap.createScaledBitmap(kangoo, DeviceWidth, height,
		// true);
		mProgressBar.setVisibility(View.VISIBLE);
		countDown = new MainCountDown(180, 180);
		countDown.start();
		Toast.makeText(test2.this, "GO LEFT " + count, Toast.LENGTH_SHORT)
				.show();
	}

	public void goRight() {
		//leftImage=topImage=0;
		setNullAsyncTask();

		if (count == 5) {
			// count=2;
		} else {
			count = count + 1;
		}
		mProgressBar.setVisibility(View.VISIBLE);
		countDown = new MainCountDown(180, 180);
		countDown.start();
		Toast.makeText(test2.this, "GO RIGHT " + count, Toast.LENGTH_SHORT)
		.show();
	}

	public void goTop() {
		setNullAsyncTask();
		mProgressBar.setVisibility(View.VISIBLE);
		countDown = new MainCountDown(180, 180);
		countDown.start();
		Toast.makeText(test2.this, "BackWard Document", Toast.LENGTH_SHORT)
				.show();
	}

	public void goBottom() {
		setNullAsyncTask();
		mProgressBar.setVisibility(View.VISIBLE);
		countDown = new MainCountDown(180, 180);
		countDown.start();
		Toast.makeText(test2.this, "ForWard Document", Toast.LENGTH_SHORT)
				.show();
	}

	private void setNullAsyncTask() {
		if (parserTask != null) {
			parserTask.cancel(true);
			parserTask = null;
		}
	}

	public class MainCountDown extends CountDownTimer {

		public MainCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			l.removeAllViews();
			// GlobalVariable.clearBitmap();
			parserTask = new ParserTask();
			parserTask.execute();
		}

		@Override
		public void onTick(long millisUntilFinished) {

		}
	}

	public void populateView() {
		ValueListIndex.removeAll(ValueListIndex);
		for (int i = 0; i < FirstListIndex.size(); i++) {
			ValueListIndex.add(FirstListIndex.get(i));
		}
		mQuickAction = new QuickAction(this);
		for (int i = 0; i < ValueListIndex.size(); i++) {
			int t = i+1;
			ActionItem Item = new ActionItem(t,ValueListIndex.get(i)+": "
					+ ValueListIndex.get(i), getResources().getDrawable(
					R.drawable.creditor_icon));
			mQuickAction.addActionItem(Item);
		}
		
/*	ActionItem creditorItem = new ActionItem(ID_CREDITOR, "Creditor :"
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
*/
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					// @Override
					public void onItemClick(QuickAction quickAction, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);
						positionChange = pos;
						IndexSelected = ValueListIndex.get(pos);
						showAddDialog();
						Toast.makeText(test2.this, actionItem.getTitle(),
								Toast.LENGTH_SHORT).show();
					}
				});

		// setup on dismiss listener, set the icon back to normal
		mQuickAction.setOnDismissListener(new PopupWindow.OnDismissListener() {
			// @Override
			public void onDismiss() {
				// mMoreIv.setImageResource(R.drawable.ic_list_more);
				mQuickAction.dismiss();
			}
		});
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
			// @Override
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

				Toast.makeText(
						getBaseContext(),
						"You've changed succesfully to new value : " + NewValue,
						Toast.LENGTH_LONG).show();
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {
			// @Override
			public void onClick(View v) {
				loginDialog.dismiss();
			}
		});
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}


}