package com.longrise.jie.drawtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view)
    {
        int drawMode = 0;
        switch (view.getId())
        {
            case R.id.btnDrawAxis:
                drawMode = DrawView.DRAW_MODE_AXIS;
                break;
            case R.id.btnDrawARGB:
                drawMode = DrawView.DRAW_MODE_ARGB;
                break;
            case R.id.btnDrawText:
                drawMode = DrawView.DRAW_MODE_TEXT;
                break;
            case R.id.btnDrawPoint:
                drawMode = DrawView.DRAW_MODE_POINT;
                break;
            case R.id.btnDrawLine:
                drawMode = DrawView.DRAW_MODE_LINE;
                break;
            case R.id.btnDrawRect:
                drawMode = DrawView.DRAW_MODE_RECT;
                break;
            case R.id.btnDrawCircle:
                drawMode = DrawView.DRAW_MODE_CIRCLE;
                break;
            case R.id.btnDrawOval:
                drawMode = DrawView.DRAW_MODE_OVAL;
                break;
            case R.id.btnDrawArc:
                drawMode = DrawView.DRAW_MODE_ARC;
                break;
            case R.id.btnDrawPath:
                drawMode = DrawView.DRAW_MODE_PATH;
                break;
            case R.id.btnDrawBitmap:
                drawMode = DrawView.DRAW_MODE_BITMAP;
                break;
        }
        if(drawMode != 0)
        {
            Intent intent = new Intent(this, CanvasActivity.class);
            intent.putExtra("draw_mode", drawMode);
            startActivity(intent);
        }
    }
}
