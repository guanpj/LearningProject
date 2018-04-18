package com.longrise.jie.drawtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jie on 2016-8-15.
 */
public class DrawView extends View
{
    public static final int DRAW_MODE_AXIS = 1;
    public static final int DRAW_MODE_ARGB = 2;
    public static final int DRAW_MODE_TEXT = 3;
    public static final int DRAW_MODE_POINT = 4;
    public static final int DRAW_MODE_LINE = 5;
    public static final int DRAW_MODE_RECT = 6;
    public static final int DRAW_MODE_CIRCLE = 7;
    public static final int DRAW_MODE_OVAL = 8;
    public static final int DRAW_MODE_ARC = 9;
    public static final int DRAW_MODE_PATH = 10;
    public static final int DRAW_MODE_BITMAP = 11;

    private Bitmap bitmap;
    private int drawMode;
    private Paint paint;
    private float textHeight;
    private float fontSize;
    private float density;

    public DrawView(Context context)
    {
        super(context);
        init(null, 0);
    }

    public DrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setDrawMode(int mode)
    {
        this.drawMode = mode;
    }

    private void init(AttributeSet attrs, int defStyle)
    {
        fontSize = getResources().getDimensionPixelSize(R.dimen.default_font_size);
        density = getResources().getDisplayMetrics().density;
        textHeight  = fontSize;
        paint = new TextPaint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(fontSize);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        switch (drawMode)
        {
            case DRAW_MODE_AXIS:
                drawAxis(canvas);
                break;
            case DRAW_MODE_ARGB:
                drawARGB(canvas);
                break;
            case DRAW_MODE_TEXT:
                drawText(canvas);
                break;
            case DRAW_MODE_POINT:
                drawPoint(canvas);
                break;
            case DRAW_MODE_LINE:
                drawLine(canvas);
                break;
            case DRAW_MODE_RECT:
                drawRECT(canvas);
                break;
            case DRAW_MODE_CIRCLE:
                drawCircle(canvas);
                break;
            case DRAW_MODE_OVAL:
                drawOval(canvas);
                break;
            case DRAW_MODE_ARC:
                drawArc(canvas);
                break;
        }
    }

    private void drawArc(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int count = 5;
        float ovalHeight = canvasHeight / (count + 1);
        float left = 10 * density;
        float top = 0;
        float right = canvasWidth - left;
        float bottom= ovalHeight;
        RectF rectF = new RectF(left, top, right, bottom);

        paint.setStrokeWidth(2 * density);//设置线宽
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式

        //绘制用drawArc绘制完整的椭圆
        canvas.translate(0, ovalHeight / count);
        canvas.drawArc(rectF, 0, 360, true, paint);

        //绘制椭圆的四分之一,起点是钟表的3点位置，从3点绘制到6点的位置
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        //绘制椭圆的四分之一,将useCenter设置为false
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, false, paint);

        //绘制椭圆的四分之一，只绘制轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);

        //绘制带有轮廓线的椭圆的四分之一
        //1. 先绘制椭圆的填充部分
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, paint);
        //2. 再绘制椭圆的轮廓线部分
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        paint.setColor(0xff0000ff);//设置轮廓线条为蓝色
        canvas.drawArc(rectF, 0, 90, true, paint);
    }

    private void drawOval(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        float quarter = canvasHeight / 4;
        float left = 10 * density;
        float top = 0;
        float right = canvasWidth - left;
        float bottom= quarter;
        RectF rectF = new RectF(left, top, right, bottom);

        //绘制椭圆形轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为画线条模式
        paint.setStrokeWidth(2 * density);//设置线宽
        paint.setColor(0xff8bc5ba);//设置线条颜色
        canvas.translate(0, quarter / 4);
        canvas.drawOval(rectF, paint);

        //绘制椭圆形填充面
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.translate(0, (quarter + quarter / 4));
        canvas.drawOval(rectF, paint);

        //画两个椭圆，形成轮廓线和填充色不同的效果
        canvas.translate(0, (quarter + quarter / 4));
        //1. 首先绘制填充色
        paint.setStyle(Paint.Style.FILL);//设置画笔为填充模式
        canvas.drawOval(rectF, paint);//绘制椭圆形的填充效果
        //2. 将线条颜色设置为蓝色，绘制轮廓线
        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
        paint.setColor(0xff0000ff);//设置填充色为蓝色
        canvas.drawOval(rectF, paint);//设置椭圆的轮廓线
    }

    private void drawCircle(Canvas canvas)
    {
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.FILL);//默认绘图为填充模式
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int halfCanvasWidth = canvasWidth / 2;
        int count = 3;
        int D = canvasHeight / (count + 1);
        int R = D / 2;

        //绘制圆
        canvas.translate(0, D / (count + 1));
        canvas.drawCircle(halfCanvasWidth, R, R, paint);

        //通过绘制两个圆形成圆环
        //1. 首先绘制大圆
        canvas.translate(0, D + D / (count + 1));
        canvas.drawCircle(halfCanvasWidth, R, R, paint);
        //2. 然后绘制小圆，让小圆覆盖大圆，形成圆环效果
        int r = (int)(R * 0.75);
        paint.setColor(0xffffffff);//将画笔设置为白色，画小圆
        canvas.drawCircle(halfCanvasWidth, R, r, paint);

        //通过线条绘图模式绘制圆环
        canvas.translate(0, D + D / (count + 1));
        paint.setColor(0xff8bc5ba);//设置颜色
        paint.setStyle(Paint.Style.STROKE);//绘图为线条模式
        float strokeWidth = (float)(R * 0.25);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(halfCanvasWidth, R, R, paint);
    }

    private void drawRECT(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int left1 = 10;
        int top1 = 10;
        paint.setColor(0xff000000);
        int right1 = canvasWidth / 3;
        int bottom1 = canvasHeight /3;
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left1, top1, right1, bottom1, paint);

        paint.setColor(0xff8bc5ba);
        paint.setStyle(Paint.Style.STROKE);
        int left2 = canvasWidth / 3 * 2;
        int top2 = 10;
        int right2 = canvasWidth - 10;
        int bottom2 = canvasHeight / 3;
        canvas.drawRect(left2, top2, right2, bottom2, paint);
    }

    private void drawLine(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int deltaY = canvasHeight/6;

        paint.setStyle(Paint.Style.FILL);

        canvas.drawLine(5, 0, canvasWidth - 50, deltaY /2, paint);

        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.BUTT);
        float[] pts = {0, deltaY, 150, 150, 150, 150, canvasWidth, deltaY};
        canvas.drawLines(pts, paint);

        paint.setStrokeWidth(5*density);
    }

    private void drawPoint(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int x = canvasWidth/2;
        int y = canvasHeight/3;
        int deltaY = y/2;

        paint.setColor(Color.RED);
        paint.setStrokeWidth(20*density);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(x, y, paint);

        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(x, y, paint);

        canvas.translate(0, deltaY);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, paint);
    }

    private void drawText(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        float translateY = textHeight;

        //绘制正常文本
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("正常绘制文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //绘制绿色文本
        paint.setColor(0xff00ff00);//设置字体为绿色
        canvas.save();
        canvas.translate(0, translateY);//将画笔向下移动
        canvas.drawText("绘制绿色文本", 0, 0, paint);
        canvas.restore();
        paint.setColor(0xff000000);//重新设置为黑色
        translateY += textHeight * 2;

        //设置左对齐
        paint.setTextAlign(Paint.Align.LEFT);//设置左对齐
        canvas.save();
        canvas.translate(canvasWidth/2, translateY);
        canvas.drawText("左对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //设置居中对齐
        paint.setTextAlign(Paint.Align.CENTER);//设置居中对齐
        canvas.save();
        canvas.translate(canvasWidth/2, translateY);
        canvas.drawText("居中对齐文本", 0, 0, paint);
        canvas.restore();
        translateY += textHeight * 2;

        //设置右对齐
        paint.setTextAlign(Paint.Align.RIGHT);//设置右对齐
        canvas.save();
        canvas.translate(canvasWidth/2, translateY);
        canvas.drawText("右对齐文本", 0, 0, paint);
        canvas.restore();
        paint.setTextAlign(Paint.Align.LEFT);//重新设置为左对齐
        translateY += textHeight * 2;

        //设置下划线
        paint.setUnderlineText(true);//设置具有下划线
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("下划线文本", 0, 0, paint);
        canvas.restore();
        paint.setUnderlineText(false);//重新设置为没有下划线
        translateY += textHeight * 2;

        //绘制加粗文字
        paint.setFakeBoldText(true);//将画笔设置为粗体
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("粗体文本", 0, 0, paint);
        canvas.restore();
        paint.setFakeBoldText(false);//重新将画笔设置为非粗体状态
        translateY += textHeight * 2;

        //文本绕绘制起点顺时针旋转
        canvas.save();
        canvas.translate(0, translateY);
        canvas.rotate(20);
        canvas.drawText("文本绕绘制起点旋转20度", 0, 0, paint);
        canvas.restore();
    }

    private void drawARGB(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        int cx = canvasWidth/2;
        int cy = cx;
        int radius = canvasWidth/2;

        canvas.drawARGB(255, 170, 147, 67);

        paint.setColor(0xffffffff);
        canvas.drawRect(0, 0, canvasWidth, canvasWidth, paint);

        paint.setColor(0xff00ff00);
        canvas.drawCircle(canvasWidth -10, canvasWidth -10, 10, paint);
        canvas.save();
        paint.setColor(Color.RED);
        canvas.drawCircle(10, canvasWidth -10, 10, paint);
        canvas.rotate(90, cx, cy);

        /*canvas.drawCircle(cx, cy, radius, paint);
        //canvas.save();  //保存现在图层，新建图层

        canvas.rotate(45, cx, cy);  //旋转45度
        paint.setColor(0xff000000);
        canvas.drawLine(cx, cy - radius/2, cx, cy + radius/2, paint);
        canvas.drawLine(cx - radius/2, cy, cx + radius/2, cy, paint);*/
        //canvas.restore();
    }

    private void drawAxis(Canvas canvas)
    {
        int canvasWidth = canvas.getWidth();
        int canvasHeight =canvas.getHeight();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(6*density);

        paint.setColor(0xff00ff00);
        canvas.drawLine(0, 0, canvasWidth, 0, paint);
        paint.setColor(0xff0000ff);
        canvas.drawLine(0, 0, 0, canvasHeight, paint);

        canvas.translate(canvasWidth/4, canvasHeight/4);
        paint.setColor(0xffffff00);
        canvas.drawLine(0, 0, canvasWidth, 0, paint);
        paint.setColor(0xff00ffff);
        canvas.drawLine(0, 0, 0, canvasHeight, paint);

        canvas.translate(canvasWidth*3/4, canvasHeight*3/4);
        canvas.rotate(180);
        paint.setColor(0xffff0000);
        canvas.drawLine(0, 0, canvasWidth*3/4, 0, paint);
        paint.setColor(0xffff00ff);
        canvas.drawLine(0, 0, 0, canvasHeight*3/4, paint);
    }

    public void setBitmap(Bitmap bm)
    {
        releaseBitmap();
        bitmap = bm;
    }

    private void releaseBitmap()
    {
        if(null != bitmap && !bitmap.isRecycled())
        {
            bitmap.recycle();
        }
        bitmap = null;
    }

    public void destroy()
    {
        releaseBitmap();
    }
}
