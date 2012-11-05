package aexp.explist;
import com.hots.transition3d.Rotate3dAnimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{
	private LinearLayout layout;
	private ImageView imageview;
	private Context mContext;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mContext	= this;
        layout 		= (LinearLayout)findViewById(R.id.main_activity_layout);
        imageview 	= (ImageView)findViewById(R.id.main_activity_image);
        
        MainCountDown countdown = new MainCountDown(4000, 4000);
        countdown.start();
    }
    
    public class MainCountDown extends CountDownTimer
    {
		public MainCountDown(long millisInFuture, long countDownInterval)
		{
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onFinish()
		{
			applyRotation(0, 90);
		}
		@Override
		public void onTick(long millisUntilFinished) {}
    }
    
    private void applyRotation(float start, float end)
    {
        final float centerX = layout.getWidth() / 2.0f;
        final float centerY = layout.getHeight() / 2.0f;        
        final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(700);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView());
        layout.startAnimation(rotation);
    }

    private final class DisplayNextView implements Animation.AnimationListener
    {
        public void onAnimationStart(Animation animation) {}

        public void onAnimationEnd(Animation animation)
        {
        	layout.post(new SwapViews());
        }

        public void onAnimationRepeat(Animation animation) {}
    }

    private final class SwapViews implements Runnable
    {
        public void run()
        {
            final float centerX = layout.getWidth() / 2.0f;
            final float centerY = layout.getHeight() / 2.0f;
            Rotate3dAnimation rotation;            
            imageview.setVisibility(View.GONE);
            rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
            rotation.setDuration(700);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            layout.startAnimation(rotation);
            
            Intent intent = new Intent(mContext, ANDROID_RSS_READER.class);
            mContext.startActivity(intent);
            finish();
        }
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
    }
}