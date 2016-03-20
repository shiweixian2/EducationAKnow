package com.bestcode95.educationaknow.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import com.bestcode95.educationaknow.R;


/**
 * Created by shiweixian on 2015/8/2.
 */
public class BottomTab extends View {

    private static final String INSTANCE_STATUS = "instance_status";
    private static final String STATUS_ALPHA = "status_alphs";
    private Bitmap mIconBitmap;
    private Context mContext;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private float mAlpha = 0.0f;
    private Rect mIconRect;
    private int mIconWidth;
    private int mIconHeight;
    private int top;

    public BottomTab(Context context) {
        this(context, null, 0);
        mContext = context;
    }

    public BottomTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    /**
     * 获取自定义属性的值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChangeIconColor);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) a.getDrawable(attr);
            try {
                mIconBitmap = bitmapDrawable.getBitmap();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mIconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        mIconHeight = Math.min(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
                getMeasuredHeight() - getPaddingLeft() - getPaddingRight());
        int left = getMeasuredWidth() / 2 - mIconWidth / 2;
        top = getMeasuredHeight() / 2 - mIconHeight / 2;
        mIconRect = new Rect(left, top, left + mIconWidth, top + mIconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);

        //  setupTargetBitmap(alpha);
//        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void setTargetBitmap(int resId) {
        Drawable drawable = mContext.getResources().getDrawable(resId);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        mIconBitmap = bitmapDrawable.getBitmap();
        invalidateView();
    }

    /**
     * 重绘
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    /**
     * 防止后台程序被停止恢复后界面出现问题
     *
     * @return
     */
    @Override
    protected Parcelable onSaveInstanceState() {

        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putFloat(STATUS_ALPHA, mAlpha);
        return bundle;
    }

    /**
     * 防止后台程序被停止恢复后界面出现问题
     *
     * @return
     */
    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mAlpha = bundle.getFloat(STATUS_ALPHA);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
