package com.example.lee.keyboardtestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.xml.xpath.XPathExpression;

public class MainActivity extends Activity{

    EditText editText;
    Button enterB;
    TextView myTV;
    String userText, replacedText,temp;
    String[] splitter;
    private Map<String, Integer> mySymbolTable = new HashMap<>();

    RelativeLayout shapesView;
    Shape shape;
    int xPara, yPara, radPara, stylePara, rectLeft, rectTop, rectRight, rectBot, rectStyleNum;
    private Vector<Shape> storeShapes = new Vector();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTV = (TextView) findViewById(R.id.textView);
        shapesView = (RelativeLayout) findViewById(R.id.myView);
        editText = (EditText) findViewById(R.id.editText);
        enterB = (Button) findViewById(R.id.enterB);

        enterB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                userInput();
            }
        });
    }

    void adjustShapeAlpha(){
        Shape shapeInVec;
        float nuAlpha;

        for(int i = 0; i < storeShapes.size(); i++){
            shapeInVec = storeShapes.get(i);
            nuAlpha = shapeInVec.getShapeAlpha();
            nuAlpha -= (0.1f);
            shapeInVec.setShapeAlpha(nuAlpha);

            if(nuAlpha <= 0.0f){
                storeShapes.remove(shapeInVec);
                shapeInVec.removeShape();
            }
        }
    }

    void userInput(){
        userText = editText.getText().toString();
        myTV.append("\n");

        if(userText.matches("\\s*int\\s+[a-z]+\\s+(=)\\s+[(]*([0-9]+|[a-z]+)+[)]*\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ userText +" "+ "Valid int statement");
            replacedText = userText.replaceAll("int ","").replaceAll("[ ]+","").replaceAll("="," ").replaceAll(";","");
            splitter();
            symbolTable();
        }
        else if(userText.matches("\\s*circle(\\s+([0-9]+|[a-z]+)){3}\\s+[0-9]+\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ userText +" "+ "Valid circle statement");
            replacedText = userText.replaceAll("circle ", "").replaceAll("[ ]+"," ").replaceAll(";","");
            splitter();
            drawCircle();
        }
        else if(userText.matches("\\s*rect(\\s+([0-9]+|[a-z]+)){4}\\s+[0-9]+\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ userText +" "+ "Valid rect statement");
            replacedText = userText.replaceAll("rect ", "").replaceAll("[ ]+"," ").replaceAll(";","");
            splitter();
            drawRect();
        }
        else myTV.setText(myTV.getText() +"\n"+ "Invalid statement");
    }

    void drawRect(){
        rectLeft = Integer.parseInt(splitter[0]);
        rectTop = Integer.parseInt(splitter[1]);
        rectRight = Integer.parseInt(splitter[2]);
        rectBot = Integer.parseInt(splitter[3]);
        rectStyleNum = Integer.parseInt(splitter[4]);

        rectStyleNum = Integer.parseInt(splitter[4]);
        ShapeFactory factoryTest = AbstractShapeFactory.getShapeFactory(rectLeft, rectTop, rectRight, rectBot, rectStyleNum);
        adjustShapeAlpha();
        shape = factoryTest.getShape(getApplicationContext(), "rectangle");
        storeShapes.add(shape);
        shapesView.addView(shape);
    }

    void drawCircle(){
        xPara = Integer.parseInt(splitter[0]);
        yPara = Integer.parseInt(splitter[1]);
        radPara = Integer.parseInt(splitter[2]);
        stylePara = Integer.parseInt(splitter[3]);

        ShapeFactory factoryTest = AbstractShapeFactory.getShapeFactory(xPara, yPara, radPara, 0, stylePara);
        adjustShapeAlpha();
        shape = factoryTest.getShape(getApplicationContext(), "circle");
        storeShapes.add(shape);
        shapesView.addView(shape);
    }

    void splitter(){
        String temper = "";
        if(replacedText.contains("(")){
            String[] parenSplitter = replacedText.split("\\),\\(|\\)|\\(");

            for(int i = 0; i < parenSplitter.length; i++){
                temp = (parenSplitter[0] + parenSplitter[1]);
                Log.d("Temp", "splitter: " + temp);
            }
            splitter = temp.split(" ");
        }
        else splitter = replacedText.split(" ");

        for(String spl : splitter){
            temper += spl;

            Log.d("Contents of array", "splitter: " + spl);
        }
        myTV.setText(temper);
        Log.d("Array length", "splitter: " + splitter.length);
    }

    void symbolTable(){

        try{
            if((mySymbolTable.containsKey(splitter[1]))){
                int otherValue = mySymbolTable.get(splitter[1]);
                mySymbolTable.put(splitter[0], otherValue);
                myTV.setText(myTV.getText() +"\n"+ "Value for this variable is : "
                        + mySymbolTable.get(splitter[0]));
            }else if(mySymbolTable.containsKey(splitter[0])){
                myTV.setText(myTV.getText() +"\n"+ "Value already has value : "
                        + mySymbolTable.get(splitter[0]));
            }
            if(!mySymbolTable.containsKey(splitter[0])){
                mySymbolTable.put(splitter[0], Integer.parseInt(splitter[1]));
                myTV.setText(myTV.getText() +"\n"+ "Value for this variable is : "
                        + mySymbolTable.get(splitter[0]));
            }
        }catch(Exception E){
            myTV.setText(myTV.getText() +"\n"
                        + "Error! You are trying to set a null variable to your current variable!");
        }
    }
}
