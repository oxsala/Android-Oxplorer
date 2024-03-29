package com.hots.zoom;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hots.util.GlobalVariable;

/**
 * View capable of drawing an image at different zoom state levels
 */
public class ImageZoomView extends View implements Observer {

	float touchX = 0;
	float touchY = 0;
	/** Paint object used when drawing bitmap. */
	private final Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

	/** Rectangle used (and re-used) for cropping source image. */
	private final Rect mRectSrc = new Rect();

	/** Rectangle used (and re-used) for specifying drawing area on canvas. */
	private final Rect mRectDst = new Rect();

	/** Object holding aspect quotient */
	private final AspectQuotient mAspectQuotient = new AspectQuotient();

	private int width, height;

	/** State of the zoom. */
	private ZoomState mState;

	/**
	 * Constructor
	 */
	public ImageZoomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Set image bitmap
	 * 
	 * @param bitmap
	 *            The bitmap to view and zoom into
	 */
	public void setImage() {
		if (GlobalVariable.mBitmap != null) {
			width = GlobalVariable.mBitmap.getWidth();
			height = GlobalVariable.mBitmap.getHeight();
			mAspectQuotient.updateAspectQuotient(getWidth(), getHeight(),
					width, height);
			mAspectQuotient.notifyObservers();
			invalidate();
		}
	}

	/**
	 * Set object holding the zoom state that should be used
	 * 
	 * @param state
	 *            The zoom state
	 */
	public void setZoomState(ZoomState state) {
		if (mState != null) {
			mState.deleteObserver(this);
		}
		mState = state;
		mState.addObserver(this);
		invalidate();
	}

	/**
	 * Gets reference to object holding aspect quotient
	 * 
	 * @return Object holding aspect quotient
	 */
	public AspectQuotient getAspectQuotient() {
		return mAspectQuotient;
	}

	// Superclass overrides

	@Override
	protected void onDraw(Canvas canvas) {
		try {
			if (GlobalVariable.mBitmap != null) {
				final float aspectQuotient = mAspectQuotient.get();

				final int viewWidth = getWidth();
				final int viewHeight = getHeight();
				final int bitmapWidth = width;
				final int bitmapHeight = height;

				final float panX = mState.getPanX();
				final float panY = mState.getPanY();
				final float zoomX = mState.getZoomX(aspectQuotient) * viewWidth
						/ bitmapWidth;
				final float zoomY = mState.getZoomY(aspectQuotient)
						* viewHeight / bitmapHeight;

				// Setup source and destination rectangles
				mRectSrc.left = (int) (panX * bitmapWidth - viewWidth
						/ (zoomX * 2));
				mRectSrc.top = (int) (panY * bitmapHeight - viewHeight
						/ (zoomY * 2));
				mRectSrc.right = (int) (mRectSrc.left + viewWidth / zoomX);
				mRectSrc.bottom = (int) (mRectSrc.top + viewHeight / zoomY);
				mRectDst.left = getLeft();
				mRectDst.top = getTop();
				mRectDst.right = getRight();
				mRectDst.bottom = getBottom();

				// Adjust source rectangle so that it fits within the source
				// image.
				if (mRectSrc.left < 0) {
					mRectDst.left += -mRectSrc.left * zoomX;
					mRectSrc.left = 0;
				}
				if (mRectSrc.right > bitmapWidth) {
					mRectDst.right -= (mRectSrc.right - bitmapWidth) * zoomX;
					mRectSrc.right = bitmapWidth;
				}
				if (mRectSrc.top < 0) {
					mRectDst.top += -mRectSrc.top * zoomY;
					mRectSrc.top = 0;
				}
				if (mRectSrc.bottom > bitmapHeight) {
					mRectDst.bottom -= (mRectSrc.bottom - bitmapHeight) * zoomY;
					mRectSrc.bottom = bitmapHeight;
				}

				GlobalVariable.PANLEFT = mRectDst.left;
				GlobalVariable.PANRIGHT = mRectDst.right;
				GlobalVariable.PANTOP = mRectDst.top;
				GlobalVariable.PANBOTTOM = mRectDst.bottom;

				canvas.drawBitmap(GlobalVariable.mBitmap, mRectSrc, mRectDst,
						mPaint);
				/*
				 * Paint paint = new Paint(); paint.isFilterBitmap();
				 * paint.setColor(Color.RED);
				 * paint.setStyle(Paint.Style.STROKE); paint.setStrokeWidth(3);
				 * //canvas.drawRect(50,50,50,50, paint);
				 * canvas.drawRect(touchX-80,touchY-60,40+touchX,touchY-20,
				 * paint);
				 */
			}
		} catch (Exception e) {
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		mAspectQuotient.updateAspectQuotient(right - left, bottom - top, width,
				height);
		mAspectQuotient.notifyObservers();
	}

	// implements Observer
	public void update(Observable observable, Object data) {
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			touchX = (int) event.getX();
			touchY = (int) event.getY();
		}

		return true; // indicate event was handled
	}

}