package com.example.lee.keyboardtestapp;

import android.media.session.MediaSession;
import android.widget.TextView;

/**
 * Created by Lee on 5/15/2016.
 */
public class AST {

    StringBuffer input;
    int inputPos, temp;
//    ASTNode newRoot;
    enum TokenType {
        NUM, ADD, SUB, MUL, DIV, O_PAREN, C_PAREN, ENDT, NOTTOKEN;
    }

    int eval(String enter) {
        inputPos = 0;
        input = new StringBuffer(enter);
        ASTNode root = doAddSub();

        return evalAstTree(root);
    }

    TokenType nextTT() {
        if (inputPos == input.length()) {
            return TokenType.ENDT;
        } else if (input.charAt(inputPos) >= '0' && input.charAt(inputPos) <= '9') {
            return TokenType.NUM;
        } else if (input.charAt(inputPos) == '+') {
            inputPos++;
            return TokenType.ADD;
        } else if (input.charAt(inputPos) == '-') {
            inputPos++;
            return TokenType.SUB;
        } else if (input.charAt(inputPos) == '*') {
            inputPos++;
            return TokenType.MUL;
        } else if (input.charAt(inputPos) == '/') {
            inputPos++;
            return TokenType.DIV;
        } else if (input.charAt(inputPos) == '(') {
            inputPos++;
            return TokenType.O_PAREN;
        } else if(input.charAt(inputPos) == ')'){
            inputPos++;
            return TokenType.C_PAREN;
        }
        else return TokenType.NOTTOKEN;
    }

    void lastTT(){
        inputPos--;
    }

    ASTNode doMulDiv(){

        ASTNode root = inputTerm();
        TokenType nxtToken = nextTT();

        while(nxtToken == TokenType.MUL || nxtToken == TokenType.DIV){

            ASTNode newRoot = null;
            if(nxtToken == TokenType.MUL){
                newRoot = new ASTNode('*');
            }
            else if(nxtToken == TokenType.DIV){
                newRoot = new ASTNode('/');
            }

            newRoot.leftT = root;
            newRoot.rightT = inputTerm();
            root = newRoot;
            nxtToken = nextTT();
        }
        lastTT();
        return root;
    }

    ASTNode doAddSub(){
        ASTNode root = doMulDiv();
        TokenType nxtToken = nextTT();

        while(nxtToken == TokenType.ADD || nxtToken == TokenType.SUB){

            ASTNode newRoot = null;
            if(nxtToken == TokenType.ADD){
                newRoot = new ASTNode('+');
            }
            else if (nxtToken == TokenType.SUB){
                newRoot = new ASTNode('-');
            }

            newRoot.leftT = root;
            newRoot.rightT = doMulDiv();
            root = newRoot;
            nxtToken = nextTT();
        }
        lastTT();
        return root;
    }

    ASTNode inputTerm(){
        ASTNode root = null;
        TokenType nxtToken = nextTT();

        if(nxtToken == TokenType.NUM){
            int num = nextNum();
            root = new ASTNode(num);
        }
        else if(nxtToken == TokenType.O_PAREN){
            root = doAddSub();
            nxtToken = nextTT();
            if(nxtToken != TokenType.C_PAREN){

            }
        }
        return root;
    }

    int nextNum(){
        int i;
        for(i = inputPos; i < input.length(); i++){
            if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                break;
            }
        }

        String number = input.substring(inputPos, i);
        try{
            temp = Integer.parseInt(number);
        }catch(Exception E){

        }
        inputPos += (i - inputPos);
        return temp;
    }

    int evalAstTree(ASTNode myTree){

        if(myTree == null){
            return 0;
        }

        if(myTree.value.toString().equals("+")){
            return evalAstTree(myTree.leftT) + evalAstTree(myTree.rightT);
        }
        else if(myTree.value.toString().equals("-")){
            return evalAstTree(myTree.leftT) - evalAstTree(myTree.rightT);
        }
        else if(myTree.value.toString().equals("*")){
            return evalAstTree(myTree.leftT) * evalAstTree(myTree.rightT);
        }
        else if(myTree.value.toString().equals("/")){
            return evalAstTree(myTree.leftT) / evalAstTree(myTree.rightT);
        }
        else
            return Integer.valueOf(myTree.value.toString()).intValue();
    }

}
