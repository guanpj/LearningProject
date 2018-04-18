package com.longrise.jie.drawtest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by Jie on 2016-8-15.
 */
public class CanvasActivity extends Activity
{
    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        drawView = (DrawView) findViewById(R.id.drawView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        drawView.setBitmap(bitmap);

        Intent intent = getIntent();
        if(null != intent)
        {
            int drawMode = intent.getIntExtra("draw_mode", 0);
            if(drawMode != 0)
            {
                drawView.setDrawMode(drawMode);
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(null != drawView)
        {
            drawView.destroy();
            drawView = null;
        }
    }
}
