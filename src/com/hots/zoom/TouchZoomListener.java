package com.hots.zoom;

import aexp.explist.ViewImagesActivity;
import android.content.Context;
import android.graphics.Matrix;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import com.hots.util.GlobalVariable;

/**
 * Listener for controlling zoom state through touch events
 */
public class TouchZoomListener implements View.OnTouchListener {
	/**
	 * Enum defining listener modes. Before the view is touched the listener is
	 * in the UNDEFINED mode. Once touch starts it can enter either one of the
	 * other two modes: If the user scrolls over the view the listener will
	 * enter PAN mode, if the user lets his finger rest and makes a longpress
	 * the listener will enter ZOOM mode.
	 */

	/** Zoom control to manipulate */
	private ZoomControl mZoomControl;
	private ScaleGestureDetector mScaleDetector;
	private final float mScaleFactor = 1.f;
	/** X-coordinate of previously handled touch event */
	private float mX;
	private float TouchX;
	private float TouchY;
	/** Y-coordinate of previously handled touch event */
	private float mY;

	/** X-coordinate of latest down event */
	private float mDownX;

	/** Y-coordinate of latest down event */
	private float mDownY;

	/** Velocity tracker for touch events */
	private VelocityTracker mVelocityTracker;

	/** Duration in ms before a press turns into a long press */
	private final int mLongPressTimeout;

	/** Vibrator for tactile feedback */
	private final Vibrator mVibrator;

	/** Maximum velocity for fling */
	private final int mScaledMaximumFlingVelocity;

	private int width, height, value;
	// private boolean isNavigation = true;
	private float currentX, currentX1;
	private float currentY, currentY1;
	private float spaceTemp = 0;
	private boolean isZoomMutilPoint = false;
	private float sX, sY;
	private boolean isMove = true;
	private final Context mContext;
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	/**
	 * Creates a new instance
	 * 
	 * @param context
	 *            Application context
	 */
	public TouchZoomListener(Context context) {

		mContext = context;
		mLongPressTimeout = ViewConfiguration.getLongPressTimeout();
		mScaledMaximumFlingVelocity = ViewConfiguration.get(context)
				.getScaledMaximumFlingVelocity();
		mVibrator = (Vibrator) context.getSystemService("vibrator");
	}

	/**
	 * Sets the zoom control to manipulate
	 * 
	 * @param control
	 *            Zoom control
	 */
	public void setZoomControl(ZoomControl control) {
		mZoomControl = control;
	}

	// implements View.OnTouchListener
	public boolean onTouch(View v, MotionEvent event) {
		final int action = event.getAction();
		TouchX = event.getX();
		TouchY = event.getY();
		float dxx, dxy;

		width = v.getWidth();
		height = v.getHeight();

		currentX = event.getX(0);
		currentY = event.getY(0);
		currentX1 = event.getX(1);
		currentY1 = event.getY(1);

		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);

		switch (action) {
		case MotionEvent.ACTION_DOWN: {
			mZoomControl.stopFling();
			mDownX = TouchX;
			mDownY = TouchY;
			mX = TouchX;
			mY = TouchY;
			// -------------------------------
			value = 1;
			mDownX = currentX;
			mDownY = currentY;
			if (isDoubleClick(mDownX, mDownY)) {
				setZoom(0.35f);
				mZoomControl.resetZoom();
				value = 2;
				// isNavigation = false;
				// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
				break;
			} else {
				sX = mDownX;
				sY = mDownY;
				new CountDown(1000, 1000).start();
			}
			// isNavigation = false;

			/*
			 * if (isNavigation) { isNavigation = false; } else { isNavigation =
			 * true; }
			 */

			break;
		}
		case MotionEvent.ACTION_POINTER_2_DOWN: {
			dxx = currentX - currentX1;
			dxy = currentY - currentY1;
			spaceTemp = (float) Math.sqrt(dxx * dxx + dxy * dxy);
			isZoomMutilPoint = true;
			break;
		}
		case MotionEvent.ACTION_POINTER_UP: {
			isZoomMutilPoint = false;
			break;
		}
		case MotionEvent.ACTION_POINTER_2_UP: {
			isZoomMutilPoint = false;
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			value = 2;
			// isNavigation = false;
			// ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
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
				final float dx = (TouchX - mX) / v.getWidth();
				final float dy = (TouchY - mY) / v.getHeight();
				mZoomControl.pan(-dx, -dy);
			}
			mX = TouchX;
			mY = TouchY;
			break;
		}
		case MotionEvent.ACTION_UP: {
			if (isMove) {
				if (GlobalVariable.PANLEFT >= 5) {
					mZoomControl.stopFling();
					((ViewImagesActivity) mContext).goLeft();
					break;
				}
				if (GlobalVariable.PANRIGHT <= (v.getWidth() - 5)) {
					mZoomControl.stopFling();
					((ViewImagesActivity) mContext).goRight();
					break;
				}
				/*
				 * if (GlobalVariable.PANTOP >= 5) { mZoomControl.stopFling();
				 * ((ViewImagesActivity) mContext).goTop(); break; } if
				 * (GlobalVariable.PANBOTTOM <= (v.getHeight() - 5)) {
				 * mZoomControl.stopFling(); ((ViewImagesActivity)
				 * mContext).goBottom(); break; }
				 */
			}
			if (value == 1) {
				new Hidden(600, 600).start();
			}
			mVelocityTracker.computeCurrentVelocity(1000,
					mScaledMaximumFlingVelocity);
			mZoomControl.startFling(-mVelocityTracker.getXVelocity()
					/ v.getWidth(), -mVelocityTracker.getYVelocity()
					/ v.getHeight());
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

	public void setZoom(float value) {
		mZoomControl.zoom((float) Math.pow(20, value), mDownX / width, mDownY
				/ height);
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
			/*
			 * ((ViewImagesActivity) mContext).visibilityLayoutNav(false);
			 * 
			 * if (isNavigation) { ((ViewImagesActivity)
			 * mContext).visibilityLayoutNav(true); } else {
			 * ((ViewImagesActivity) mContext).visibilityLayoutNav(false); }
			 */

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

}