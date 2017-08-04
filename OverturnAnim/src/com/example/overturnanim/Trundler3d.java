package com.example.overturnanim;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author zhangheng
 * @since 2010/11/1
 */
public class Trundler3d extends RelativeLayout {

	private Context mContext;
	private ImageView mImgBed;
	private ImageView mImgTop;
	private ImageView mImgBottom;
	private ViewGroup mImgContainer;
	private boolean mIsAnimFinished;
	
	public Trundler3d(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		layoutInit();
	}
	
	private void layoutInit() {
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		mImgBed = (ImageView)inflater.inflate(R.layout.trundler_inner1, null);
		mImgBed.setLayoutParams(new FrameLayout.LayoutParams(160, 215));
		RelativeLayout.LayoutParams imgbedLayout = new RelativeLayout.LayoutParams(160, 215);
		imgbedLayout.addRule(CENTER_IN_PARENT);
		mImgBed.setLayoutParams(imgbedLayout);
		addView(mImgBed);
		
		mImgContainer = (ViewGroup)inflater.inflate(R.layout.trundler_inner2, null);
		RelativeLayout.LayoutParams imgContainerLayout = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 215);
		imgContainerLayout.addRule(CENTER_IN_PARENT);
		mImgContainer.setLayoutParams(imgContainerLayout);
		mImgTop = (ImageView)mImgContainer.findViewById(R.id.top);
		LinearLayout.LayoutParams imgTopLayout = new LinearLayout.LayoutParams(160, 0);
		imgTopLayout.weight = 1;
		mImgTop.setLayoutParams(imgTopLayout);
		mImgBottom = (ImageView)mImgContainer.findViewById(R.id.bottom);
		LinearLayout.LayoutParams imgBottomLayout = new LinearLayout.LayoutParams(160, 0);
		imgBottomLayout.weight = 1;
		mImgBottom.setLayoutParams(imgBottomLayout);
		addView(mImgContainer);
		
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo1);
        Bitmap convertImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()/2);
		mImgTop.setImageBitmap(convertImg);
		convertImg = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight()/2, bitmap.getWidth(), bitmap.getHeight()/2);
		mImgBottom.setImageBitmap(convertImg);
	}
	
	private void applyRotation(float start, float end) {
        float centerY = 0;
        float centerX = mImgBottom.getWidth()/2;
        Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, 0.0f, false);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setAnimationListener(new DisplayNextView());
        mImgBottom.startAnimation(rotation);
        mIsAnimFinished = false;
    }
    
    private final class DisplayNextView implements Animation.AnimationListener {

        private DisplayNextView() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        	if (!mIsAnimFinished) mImgBottom.post(new Montage());
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    private final class Montage implements Runnable {

        public void run() {
        	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.photo2);
            Bitmap convertImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight()/2);
            Matrix matrix = new Matrix();
            matrix.setRotate(180.0f);
        	convertImg = Bitmap.createBitmap(convertImg, 0, 0, convertImg.getWidth(), convertImg.getHeight(), matrix, false);
            matrix.setScale(-1.0f, 1.0f);
            convertImg = Bitmap.createBitmap(convertImg, 0, 0, convertImg.getWidth(), convertImg.getHeight(), matrix, false);
            mImgBottom.setImageBitmap(convertImg);
        	
            float centerX = mImgBottom.getWidth()/2;
            float centerY = 0;
            Rotate3dAnimation rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 0.0f, false);
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            mImgBottom.startAnimation(rotation);
            mIsAnimFinished = true;
        }
    }
    
    public void startAnim() {
    	applyRotation(0, 90);
    }
	
}//class
