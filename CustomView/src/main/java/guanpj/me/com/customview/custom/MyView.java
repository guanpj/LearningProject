package guanpj.me.com.customview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import guanpj.me.com.customview.R;

/**
 * Created by Jie on 2017/8/13.
 */

public class MyView extends View {

    private int backColor;
    private int textColor;
    private int textSize;

    private Bitmap mImageBitmap;
    private Long mText;
    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private Rect mRect;
    private Rect mTextRect;
    private Rect mImageRect;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        backColor = ta.getColor(R.styleable.MyView_back_color, Color.BLUE);
        textColor = ta.getColor(R.styleable.MyView_text_color, Color.GRAY);
        textSize = ta.getDimensionPixelSize(R.styleable.MyView_text_size, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                15, getResources().getDisplayMetrics()));
        ta.recycle();

        mImageBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);

        mPaint = new Paint();
        mRect = new Rect();
        mTextRect = new Rect();
        mText = 1l;
        mPaint.getTextBounds(mText.toString(), 0, mText.toString().length(), mTextRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            int widthWithImage = getPaddingLeft() + getPaddingRight() + mImageBitmap.getWidth();
            int widthWithText = getPaddingLeft() + getPaddingRight() + mTextRect.width();

            if(widthMode == MeasureSpec.AT_MOST) {
                int desireWidth = Math.max(widthWithImage, widthWithText);
                mWidth = Math.min(desireWidth, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            int desireHeight = getPaddingBottom() + getPaddingTop() + mImageBitmap.getHeight() + mTextRect.height();
            mHeight = Math.min(desireHeight, heightSize);
        }

        mWidth = Math.min(mWidth, mHeight);
        mHeight = Math.min(mWidth, mHeight);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        mPaint.setAntiAlias(true);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        int r = getMeasuredWidth() / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        //圆心的横坐标为当前的View的左边起始位置+半径
        int centerX = getMeasuredWidth() / 2;
        //圆心的纵坐标为当前的View的顶部起始位置+半径
        int centerY = getMeasuredHeight() / 2;

        mPaint.reset();
        mPaint.setColor(backColor);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(centerX, centerY, r, mPaint);

        /*mRect.left = (int) (r - Math.sqrt(2) * 1.0f / 2 * r);
        mRect.top = (int) (r - Math.sqrt(2) * 1.0f / 2 * r);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * r);
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * r);*/

        mRect.left = getPaddingLeft();
        mRect.right = mWidth - getPaddingRight();
        mRect.top = getPaddingTop();
        mRect.bottom = mHeight - getPaddingBottom();

        mImageRect = new Rect(mRect.left + mRect.width()/4, mRect.top + mRect.height()/2,
                mRect.right - mRect.width()/4, mRect.bottom);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        canvas.drawBitmap(mImageBitmap,null, mImageRect, mPaint);

        mPaint.reset();
        mPaint.setColor(textColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        if(mTextRect.width() > mImageRect.width()) {
            TextPaint paint = new TextPaint(mPaint);
            String textStr = TextUtils.ellipsize(mText.toString(), paint, mImageRect.width(),
                    TextUtils.TruncateAt.MIDDLE).toString();
            canvas.drawText(textStr, mImageRect.left, mImageRect.top - mImageRect.height() / 2f, mPaint);
        } else {
            canvas.drawText(mText.toString(), mImageRect.left + mImageRect.width() / 2f - mTextRect.width() / 2f, mImageRect.top - mImageRect.height()/2f, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(x > mImageRect.left && x < mImageRect.right && y > mImageRect.top && y < mImageRect.bottom) {
                    mText++;
                    mPaint.getTextBounds(mText.toString(), 0, mText.toString().length(), mTextRect);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
