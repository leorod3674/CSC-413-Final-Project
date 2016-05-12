package com.example.lee.keyboardtestapp;

import android.content.Context;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Lee on 5/11/2016.
 */
public class Circle extends Shape{

    int xx, yy, rr;
    //Random randy = new Random();

    public Circle(Context context, int x, int y, int r, int z, int style) {
        super(context, x, y, r, z, style);
    }

    @Override
    String getShapeType() {
        return "circle";
    }

    @Override
    public void onDraw(Canvas canvas){

        xx = ex;
        yy = why;
        rr = radius;

        if(daStyle == 1){
            setFillColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
        }
        else if(daStyle == 2) {
            setFillColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
        }
        else if(daStyle == 3) {
            setFillColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
        }
        else if(daStyle == 4) {
            setFillColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawCircle(xx, yy, rr, stylePaint);
        }

    }
}
