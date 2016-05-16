package com.example.lee.keyboardtestapp;

/**
 * Created by Lee on 5/15/2016.
 */
public class ASTNode {

    public ASTNode leftT;
    public ASTNode rightT;
    public Object value;

    public ASTNode(ASTNode lft, ASTNode rgt, Object val){
        leftT = lft;
        rightT = rgt;
        value = val;
    }

    public ASTNode(Object lft, Object rgt, Object val){
        leftT = new ASTNode(lft);
        rightT = new ASTNode(rgt);
        value = val;
    }

    public ASTNode(Object val){
        leftT = rightT = null;
        value = val;
    }

    public ASTNode(){
        leftT = rightT = null;
        value = null;
    }


}
