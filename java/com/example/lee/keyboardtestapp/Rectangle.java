package com.example.lee.keyboardtestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;


/**
 * Created by Lee on 5/11/2016.
 */
public class Rectangle extends Shape{

    public Rectangle(Context context,int rectLeft, int rectTop, int rectRight, int rectBot, int style){
        super(context, rectLeft, rectTop, rectRight, rectBot, style);
    }

    @Override
    String getShapeType() {
        return "rectangle";
    }

    public void onDraw(Canvas canvas){

        Rect aTestRectangle = new Rect(ex , why, radius, fourth);

        if(daStyle == 1){
            setFillColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
        }
        else if(daStyle == 2) {
            setFillColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
        }
        else if(daStyle == 3) {
            setFillColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
        }
        else if(daStyle == 4) {
            setFillColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
            setStrokeColor(daStyle);
            canvas.drawRect(aTestRectangle, stylePaint);
        }
    }
}
