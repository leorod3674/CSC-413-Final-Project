package com.example.lee.keyboardtestapp;

import android.content.Context;

/**
 * Created by Lee on 5/11/2016.
 */
public class ShapeFactory {

    int styleNum, ex, why, ar, ze;

    public ShapeFactory(int x, int y, int r, int z, int style) {
        ex = x;     // ex = x is the first parameter
        why = y;    // why = y is the second parameter
        ar = r;     // ar = r is the third parameter
        ze = z;     // ze = z is the third parameter
        styleNum = style; // style number
    }

    public Shape getShape(Context context, String shape){
        if(shape == null) return null;

        if(shape.equals("circle")){
            return new Circle(context, ex, why, ar, ze, styleNum);
        }
        else if(shape.equals("rectangle")){
            return new Rectangle(context, ex, why, ar, ze, styleNum);
        }
        return null;
    }
}
