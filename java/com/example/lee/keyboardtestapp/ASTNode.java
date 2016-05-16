package com.example.lee.keyboardtestapp;

/**
 * Created by Lee on 5/15/2016.
 */
public class ASTNode {

    public ASTNode leftT;
    public ASTNode rightT;
    public Object value;

        public ASTNode(Object val){
        leftT = rightT = null;
        value = val;
    }

}
