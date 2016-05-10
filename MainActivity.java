package com.example.leo.myapplication4;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button enterB;
    TextView myTV;
    String temp;
    String x;
    String [] tokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();

        myTV = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        enterB = (Button) findViewById(R.id.enterB);

        enterB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                userInput();
            }
        });
    }

    void userInput(){
        temp = editText.getText().toString();
        myTV.append("\n");

        if(temp.matches("\\s*int\\s+[a-z]+\\s+=\\s+[0-9]+\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ "valid int statement");
            x = temp.replaceAll("int ", "").replaceAll(" =", "").replaceAll(";", "");
            splitter();
            //myTV.setText(myTV.getText() +"\n"+ x);

        }
        else if(temp.matches("\\s*circle(\\s+([0-9]+|[a-z]+)){3}\\s+[0-9]\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ "valid circle statement");
            x = temp.replaceAll("circle ", "").replaceAll(";", " ");
            splitter();
            //myTV.setText(myTV.getText() +"\n"+ x);

        }
        else if(temp.matches("\\s*rect(\\s+([0-9]+|[a-z]+)){4}\\s+[0-9]\\s*;")){
            myTV.setText(myTV.getText() +"\n"+ "valid rect statement");
            x = temp.replaceAll("rect ", "").replaceAll(";", " ");
            splitter();
            //myTV.setText(myTV.getText() +"\n"+ x);
        }
        else myTV.setText(myTV.getText() +"\n"+ "invalid statement");


//        for(int i = 0; i < temp.length(); i++){
//            char chr = temp.charAt(i);
//            myTV.setText(myTV.getText() +""+ chr);
//            // myTV.setText(myTV.getText() +""+ charArr[i]);
//        }
    }

        void splitter(){
            tokens = x.split(" ");
            myTV.setText(myTV.getText() +"\n"+ x + tokens.length);

        }

        void tokenHashMap(){

            
        }
}
