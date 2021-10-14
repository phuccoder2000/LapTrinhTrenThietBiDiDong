package com.example.simpleexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SimpleExample extends View {
    public SimpleExample(final Context context){
        super(context);
    }
    public SimpleExample(Context context, AttributeSet attrs ){
        super(context,attrs);
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius = 100;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#CD5c5c"));
        canvas.drawCircle(x/2,y/2, radius,paint);
    }
}
