package com.caij.progressview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Caij on 2016/10/20.
 */

public class ProgressView extends View {

    public static final float DEFAULT_START_ANGLE = 270F;

    private Paint mCirclePaint;
    private Paint mFanPaint;
    private RectF mFanRectF = new RectF();

    private int mMaxProgress;
    private int mProgress;
    private int mFanPadding;
    private int mCircleLineWidth;

    private float mStartAngle;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ProgressView, defStyleAttr, defStyleRes);
        int circleColor = a.getColor(R.styleable.ProgressView_circle_color, getResources().getColor(R.color.colorPrimary));
        int fanColor = a.getColor(R.styleable.ProgressView_fan_progress_color, getResources().getColor(R.color.colorPrimary));
        mCircleLineWidth = a.getDimensionPixelOffset(R.styleable.ProgressView_circle_line_width, getResources().getDimensionPixelOffset(R.dimen.circle_line_width));
        mFanPadding = a.getDimensionPixelOffset(R.styleable.ProgressView_fan_padding, getResources().getDimensionPixelOffset(R.dimen.fan_padding));
        mStartAngle = a.getFloat(R.styleable.ProgressView_fan_start_angle, DEFAULT_START_ANGLE);
        a.recycle();

        mCirclePaint = new Paint();
        mCirclePaint.setColor(circleColor);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleLineWidth);

        mFanPaint = new Paint();
        mFanPaint.setColor(fanColor);
        mFanPaint.setStyle(Paint.Style.FILL);
        mFanPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        int length = Math.min(width, height);

        canvas.drawCircle(width / 2f, height / 2f, length / 2f - mCircleLineWidth, mCirclePaint);

        int fanWidth = length - mCircleLineWidth * 2 - mFanPadding * 2;
        int left = width - length + mCircleLineWidth + mFanPadding;
        int top = height - length + mCircleLineWidth + mFanPadding;
        mFanRectF.set(left, top, left + fanWidth, top + fanWidth);
        float sweepAngle = mProgress * 1f / mMaxProgress * 360;
        canvas.drawArc(mFanRectF, mStartAngle, sweepAngle, true, mFanPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public void setMax(int max) {
        mMaxProgress = max;
        invalidate();
    }
}
