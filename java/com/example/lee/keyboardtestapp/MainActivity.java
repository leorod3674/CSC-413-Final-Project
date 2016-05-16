package com.example.lee.keyboardtestapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends Activity {
    EditText editText;
    Button enterB, clearB;
    TextView myTV;
    String userText, replacedText;
    String[] splitter;
    private Map<String, Integer> mySymbolTable = new HashMap<>();
    private Vector<Shape> storeShapes = new Vector();
    RelativeLayout shapesView;
    Shape shape;
    int xPara, yPara, radPara, stylePara, rectLeft, rectTop,
            rectRight, rectBot, rectStyleNum;
    AST evalAST = new AST();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTV = (TextView) findViewById(R.id.textView);
        shapesView = (RelativeLayout) findViewById(R.id.myView);
        editText = (EditText) findViewById(R.id.editText);
        enterB = (Button) findViewById(R.id.enterB);
        clearB = (Button) findViewById(R.id.clearB);

        clearB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mySymbolTable.clear();
                shapesView.removeAllViewsInLayout();
                myTV.setText("");
            }
        });
        enterB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userInput();
                editText.setText("");
            }
        });
    }

    void adjustShapeAlpha() {
        Shape shapeInVec;
        float nuAlpha;
        for (int i = 0; i < storeShapes.size(); i++) {
            shapeInVec = storeShapes.get(i);
            nuAlpha = shapeInVec.getShapeAlpha();
            nuAlpha -= (0.1f);
            shapeInVec.setShapeAlpha(nuAlpha);
            if (nuAlpha <= 0.0f) {
                storeShapes.remove(shapeInVec);
                shapeInVec.removeShape();
            }
        }
    }

    void userInput() {
        userText = editText.getText().toString();
        myTV.append("\n");
        // Expressions: a = a + 1;
        if (userText.matches("\\s*[a-z]+\\s+(=)\\s+[(]*(\\(*\\s+|[0-9]+|[a-z]+|[-+/*]*|\\s+\\))+[)]*\\s*;")) {

            replacedText = userText.replaceAll("[ ]+", "").replaceAll("=", " ").replaceAll(";", "");
            splitter();
            if (!mySymbolTable.containsKey(splitter[0])) {
                myTV.setText("Variable isn't initialized.");
            } else {
                if (userText.contains("+") || userText.contains("-") || userText.contains("*") || userText.contains("/")) {
                    String varCheck, concatStr = "";
                    varCheck = userText.replaceAll("int ", "").replaceAll("=", " ").replaceAll(";", "").replaceAll("[ ]+", " ");
                    splitter = varCheck.split(" ");
                    for (int i = 1; i < splitter.length; i++) {
                        if (splitter[i].matches("[a-z]+")) {
                            if (mySymbolTable.containsKey(splitter[i])) {
                                int varValue = mySymbolTable.get(splitter[i]);
                                String valToStr = Integer.toString(varValue);
                                splitter[i] = valToStr;
                            }
                        }
                        concatStr += splitter[i];
                    }
                    Log.d(" concatStr ", ": " + concatStr);
                    if (!concatStr.equals(null)) {
                        splitter[1] = concatStr;
                        Log.d("splitter[1]", ": " + splitter[1]);
                    } else {
                        replacedText = userText.replaceAll("int ", "").replaceAll("[ ]+", "").replaceAll("=", " ").replaceAll(";", "");
                        splitter();
                    }
                    try {
                        myTV.setText("Evaluated result of: " + splitter[1] + " = ");
                        int result = evalAST.eval(splitter[1]);
                        myTV.setText(myTV.getText() +" "+ result);
                        splitter[1] = Integer.toString(result);
                        //Log.d("Contents of array", "splitter: " + splitter[0] + " " + splitter[1]);
                        //Log.d("Contents of array", "splitter: " + splitter[0] + " " + splitter[1]);
                        mySymbolTable.remove(splitter[0]);
                        mySymbolTable.put(splitter[0], result);
                    } catch (Exception E) {
                        myTV.setText("ERROR");
                    }
                } else {
                    myTV.setText(myTV.getText() + "\n" + userText + " " + "Valid statement");
                    if (mySymbolTable.containsKey(splitter[0])) {
                        symbolTable();
                    } else myTV.setText(userText + " " + "Variable hasn't been initialized.");

                    myTV.setText(myTV.getText() + "\n" + userText + " " + "Valid statement");
                    replacedText = userText.replaceAll("[ ]+", "").replaceAll("=", " ").replaceAll(";", "");
                    splitter();
                }
            }
        }   // Setting variables: int a = 0;
        else if (userText.matches("\\s*int\\s+[a-z]+\\s+(=)\\s+[(]*\\s*(\\(*\\s+|[0-9]+|[a-z]+|[-+/*]*|\\s+\\)*)+\\s*[)]*\\s*;")) {

            String varCheck, concatStr = "";
            if (userText.contains("+") || userText.contains("-") || userText.contains("*") || userText.contains("/")) {
                varCheck = userText.replaceAll("int ", "").replaceAll("=", " ").replaceAll(";", "").replaceAll("[ ]+", " ");
                splitter = varCheck.split(" ");
                for (int i = 1; i < splitter.length; i++) {
                    if (splitter[i].matches("[a-z]+")) {
                        if (mySymbolTable.containsKey(splitter[i])) {
                            int varValue = mySymbolTable.get(splitter[i]);
                            String valToStr = Integer.toString(varValue);
                            splitter[i] = valToStr;
                        }
                    }
                    concatStr += splitter[i];
                }
                Log.d(" concatStr ", ": " + concatStr);
                if (!concatStr.equals(null)) {
                    splitter[1] = concatStr;
                    Log.d("splitter[1]", ": " + splitter[1]);
                }
//                else {
//                    replacedText = userText.replaceAll("int ", "").replaceAll("[ ]+", "").replaceAll("=", " ").replaceAll(";", "");
//                    splitter();
//                }
                try {
                    int result = evalAST.eval(splitter[1]);
                    splitter[1] = Integer.toString(result);
//                    Log.d("Contents of array", "splitter: " + splitter[0] + " " + splitter[1]);
//                    Log.d("Contents of array", "splitter: " + splitter[0] + " " + splitter[1]);
                    symbolTable();
                } catch (Exception E) {
                    myTV.setText("ERROR IN HERE");
                }
            }
            else {
                myTV.setText(userText + " " + "Valid int statement");
                replacedText = userText.replaceAll("\\s*int\\s+", "").replaceAll("[ ]+", " ").replaceAll("\\s*=\\s+", " ").replaceAll(";", "");
                splitter();
                symbolTable();
            }
        }
        else if (userText.matches("\\s*circle(\\s*([0-9]+|[a-z]+)){3}\\s+[0-9]+\\s*;")) {

            myTV.setText(userText + " " + "Valid circle statement");
            replacedText = userText.replaceAll("\\s*circle\\s+", "").replaceAll(";", "");
            Log.d("Circle replacedText", ": " + replacedText);
            splitter();
            Log.d("Circle split Array", ": " + splitter[0] +" "+ splitter[1] +" "+ splitter[2] +" "+ splitter[3]);
            drawCircle();
        }
        else if (userText.matches("\\s*rect(\\s*([0-9]+|[a-z]+)){4}\\s+[0-9]+\\s*;")) {

            myTV.setText(userText + " " + "Valid rect statement");
            replacedText = userText.replaceAll("\\s*rect\\s+", "").replaceAll(";", "");
            splitter();
            drawRect();
        }
        else myTV.setText("Invalid statement");
    }

    void drawRect() {
        try {
            for (String varStr : splitter) {
                if (varStr.matches("[a-z|0-9]+")) {
                    if (!mySymbolTable.containsKey(splitter[0])) {
                        rectLeft = Integer.parseInt(splitter[0]);
                    } else if (mySymbolTable.containsKey(splitter[0])) {
                        rectLeft = mySymbolTable.get(splitter[0]);
                    }
                    if (!mySymbolTable.containsKey(splitter[1])) {
                        rectTop = Integer.parseInt(splitter[1]);
                    } else if (mySymbolTable.containsKey(splitter[1])) {
                        rectTop = mySymbolTable.get(splitter[1]);
                    }
                    if (!mySymbolTable.containsKey(splitter[2])) {
                        rectRight = Integer.parseInt(splitter[2]);
                    } else if (mySymbolTable.containsKey(splitter[2])) {
                        rectRight = mySymbolTable.get(splitter[2]);
                    }
                    if (!mySymbolTable.containsKey(splitter[3])) {
                        rectBot = Integer.parseInt(splitter[3]);
                    } else if (mySymbolTable.containsKey(splitter[3])) {
                        rectBot = mySymbolTable.get(splitter[3]);
                    }
                }
            }
        } catch (Exception E) {
            myTV.setText("A variable isn't initialized.");
            rectLeft = 0;
            rectTop = 0;
            rectRight = 0;
            rectBot = 0;
        }
        rectStyleNum = Integer.parseInt(splitter[4]);
        ShapeFactory factoryTest = AbstractShapeFactory.getShapeFactory(rectLeft, rectTop,
                rectRight, rectBot, rectStyleNum);
        Log.d("x,y,rad", "drawCircle: " + rectLeft + rectTop + rectRight + rectBot);
        adjustShapeAlpha();
        shape = factoryTest.getShape(getApplicationContext(), "rectangle");
        storeShapes.add(shape);
        shapesView.addView(shape);
    }

    void drawCircle() {
        try {
            for (String varStr : splitter) {
                if (varStr.matches("[a-z|0-9]+")) {
                    if (!mySymbolTable.containsKey(splitter[0])) {
                        xPara = Integer.parseInt(splitter[0]);
                    } else if (mySymbolTable.containsKey(splitter[0])) {
                        xPara = mySymbolTable.get(splitter[0]);
                    }
                    if (!mySymbolTable.containsKey(splitter[1])) {
                        yPara = Integer.parseInt(splitter[1]);
                    } else if (mySymbolTable.containsKey(splitter[1])) {
                        yPara = mySymbolTable.get(splitter[1]);
                    }
                    if (!mySymbolTable.containsKey(splitter[2])) {
                        radPara = Integer.parseInt(splitter[2]);
                    } else if (mySymbolTable.containsKey(splitter[2])) {
                        radPara = mySymbolTable.get(splitter[2]);
                    }
                }
            }
        } catch (Exception E) {
            myTV.setText("A variable isn't initialized.");
            xPara = 0;
            yPara = 0;
            radPara = 0;
        }
        stylePara = Integer.parseInt(splitter[3]);
        ShapeFactory factoryTest = AbstractShapeFactory.getShapeFactory(xPara, yPara, radPara, 0, stylePara);
        Log.d("x,y,rad", "drawCircle: " + xPara + yPara + radPara);
        adjustShapeAlpha();
        shape = factoryTest.getShape(getApplicationContext(), "circle");
        storeShapes.add(shape);
        shapesView.addView(shape);
    }

    void splitter() {
        splitter = replacedText.split("\\s+");
        Log.d("Array length", "splitter: " + splitter.length);
    }

    void symbolTable() {
        try {
            if ((mySymbolTable.containsKey(splitter[1]))) {
                int otherValue = mySymbolTable.get(splitter[1]);
                mySymbolTable.put(splitter[0], otherValue);
                myTV.setText("Value for " + splitter[0] + " is "
                        + mySymbolTable.get(splitter[0]));
            } else if (mySymbolTable.containsKey(splitter[0])) {
                myTV.setText("Variable '" + splitter[0] + "' already has value: "
                        + mySymbolTable.get(splitter[0]));
            }
            if (!mySymbolTable.containsKey(splitter[0])) {
                mySymbolTable.put(splitter[0], Integer.parseInt(splitter[1]));
                myTV.setText("Value for variable: " + splitter[0] + " is "
                        + mySymbolTable.get(splitter[0]));
            }
        } catch (Exception E) {
            myTV.setText("Error! You are trying to set a null variable to your current variable!");
        }
    }
}
