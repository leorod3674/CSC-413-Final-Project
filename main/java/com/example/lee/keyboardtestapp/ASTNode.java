package com.example.lee.keyboardtestapp;

public class ASTNode {

    public ASTNode leftT;
    public ASTNode rightT;
    public Object value;

        public ASTNode(Object val){
        leftT = rightT = null;
        value = val;
    }

}
