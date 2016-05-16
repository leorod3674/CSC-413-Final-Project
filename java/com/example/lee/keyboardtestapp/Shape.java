package com.example.lee.keyboardtestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Lee on 5/11/2016.
 */
public abstract class Shape extends View{

    int daStyle, ex, why, radius, fourth;
    Paint stylePaint;

    public Shape(Context context, int x, int y, int r, int z, int style) {
        super(context);
        setShapeAlpha(1.0f);
        stylePaint = new Paint();
        daStyle = style;
        ex = x;
        why = y;
        radius = r;
        fourth = z;
    }

    void setFillColor(int fill){

        stylePaint.setStyle(Paint.Style.FILL);

        if(fill == 1){
            stylePaint.setColor(Color.BLUE);
        }
        else if(fill == 2){
            stylePaint.setColor(Color.RED);
        }
        else if(fill == 3){
            stylePaint.setColor(Color.BLACK);
        }
        else if(fill == 4){
            stylePaint.setColor(Color.MAGENTA);
        }
    }

    void setStrokeColor(int stroke){

        stylePaint.setStyle(Paint.Style.STROKE);
        stylePaint.setStrokeWidth(20.0f);

        if(stroke == 1){
            stylePaint.setColor(Color.RED);
        }
        else if(stroke == 2){
            stylePaint.setColor(Color.GREEN);
        }
        else if(stroke == 3){
            stylePaint.setColor(Color.YELLOW);
        }
        else if(stroke == 4){
            stylePaint.setColor(Color.GRAY);
        }
    }

    void setShapeAlpha(float alpha){
        setAlpha(alpha);
    }

    float getShapeAlpha(){
        return getAlpha();
    }

    void removeShape(){
        setVisibility(View.GONE);
    }

    abstract String getShapeType();

    public abstract void onDraw(Canvas canvas);


}
