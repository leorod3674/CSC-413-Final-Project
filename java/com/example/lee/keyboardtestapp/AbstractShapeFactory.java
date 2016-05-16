package com.example.lee.keyboardtestapp;

/**
 * Created by Lee on 5/11/2016.
 */
public abstract class AbstractShapeFactory {

    public static ShapeFactory getShapeFactory(int x, int y, int r, int z, int style){

        return new ShapeFactory(x, y, r, z, style);
    }
}
