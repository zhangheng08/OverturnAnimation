package com.example.overturnanim;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private Trundler3d mTrundler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTrundler = (Trundler3d) findViewById(R.id.mTrundler);
    }
    
    @Override
	protected void onStart() {
		super.onStart();
		new Handler() {
			public void handleMessage(Message msg) {
				if(mTrundler != null) mTrundler.startAnim();
			}
		}.sendEmptyMessageDelayed(0, 2000);
	}



//	private void applyRotation(int position, float start, float end) {
//        // Find the center of the container
//        float centerY = 0;//mContainer.getHeight();
//        float centerX = mBottom.getWidth()/2;
//
//        // Create a new 3D rotation with the supplied parameter
//        // The animation listener is used to trigger the next animation
//        final Rotate3dAnimation rotation =
//                new Rotate3dAnimation(start, end, centerX, centerY, 0.0f, true);
//        rotation.setDuration(500);
//        rotation.setFillAfter(true);
//        rotation.setInterpolator(new LinearInterpolator());
//        rotation.setAnimationListener(new DisplayNextView(position));
//        mBottom.startAnimation(rotation);
//        mIsAnimFinished = false;
//    }
//    
//    private final class DisplayNextView implements Animation.AnimationListener {
//        private final int mPosition;
//
//        private DisplayNextView(int position) {
//            mPosition = position;
//        }
//
//        public void onAnimationStart(Animation animation) {
//        }
//
//        public void onAnimationEnd(Animation animation) {
//            if (!mIsAnimFinished) mBottom.post(new SwapViews(mPosition));
//        }
//
//        public void onAnimationRepeat(Animation animation) {
//        }
//    }
//
//    /**
//     * This class is responsible for swapping the views and start the second
//     * half of the animation.
//     */
//    private final class SwapViews implements Runnable {
//        private final int mPosition;
//
//        public SwapViews(int position) {
//            mPosition = position;
//        }
//
//        public void run() {
//        	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo2);
//            Bitmap convertImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()/2);
//            Matrix matrix = new Matrix();
//            matrix.setRotate(180.0f);
//        	convertImg = Bitmap.createBitmap(convertImg, 0, 0, convertImg.getWidth(), convertImg.getHeight(), matrix, false);
//            matrix.setScale(-1.0f, 1.0f);
//            convertImg = Bitmap.createBitmap(convertImg, 0, 0, convertImg.getWidth(), convertImg.getHeight(), matrix, false);
//            
//        	mBottom.setImageBitmap(convertImg);
//        	
//            float centerX = mBottom.getWidth()/2;
//            float centerY = 0;
//            Rotate3dAnimation rotation;
//			rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 0.0f, true);
//            rotation.setDuration(500);
//            rotation.setFillAfter(true);
//            rotation.setInterpolator(new DecelerateInterpolator());
//            mBottom.startAnimation(rotation);
//            mIsAnimFinished = true;
//        }
//    }
    
}//class
