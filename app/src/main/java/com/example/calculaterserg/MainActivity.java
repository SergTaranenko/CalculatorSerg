package com.example.calculaterserg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button_9;
    private Button button_8;
    private Button button_7;
    private Button button_6;
    private Button button_5;
    private Button button_4;
    private Button button_3;
    private Button button_2;
    private Button button_1;
    private Button button_0;
    private Button button_equals;
    private Button button_plus;
    private Button button_minus;
    private Button button_multi;
    private Button button_share;
    private Button button_erase;
    private TextView mNumberTextView;
    private static String stringNum = "0";
    private static int strToInt;
    private static int firstNum;
    private static String lastOperPressed = "None";

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, strToInt); }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_cons);
        if (savedInstanceState != null) {
            strToInt = savedInstanceState.getInt(KEY_INDEX);
            stringNum = Integer.toString(strToInt);
        }
        mNumberTextView = (TextView) findViewById(R.id.memory_screen);
        mNumberTextView.setText(stringNum);
        button_9 = (Button) findViewById(R.id.button_9);
        numUpdate(button_9, "9");
        button_8 = (Button) findViewById(R.id.button_8);
        numUpdate(button_8, "8");
        button_7 = (Button) findViewById(R.id.button_7);
        numUpdate(button_7, "7");
        button_6 = (Button) findViewById(R.id.button_6);
        numUpdate(button_6, "6");
        button_5 = (Button) findViewById(R.id.button_5);
        numUpdate(button_5, "5");
        button_4 = (Button) findViewById(R.id.button_4);
        numUpdate(button_4, "4");
        button_3 = (Button) findViewById(R.id.button_3);
        numUpdate(button_3, "3");
        button_2 = (Button) findViewById(R.id.button_2);
        numUpdate(button_2, "2");
        button_1 = (Button) findViewById(R.id.button_1);
        numUpdate(button_1, "1");
        button_0 = (Button) findViewById(R.id.button_0);
        numUpdate(button_0, "0");
        button_plus = (Button) findViewById(R.id.button_plus);
        operUpdate(button_plus, "addition");
        button_minus = (Button) findViewById(R.id.button_minus);
        operUpdate(button_minus, "subtraction");
        button_multi = (Button) findViewById(R.id.button_multi);
        operUpdate(button_multi, "multiplication");
        button_share = (Button) findViewById(R.id.button_share);
        operUpdate(button_share, "division");
        button_equals = (Button) findViewById(R.id.button_equals);
        equalsUpdate(button_equals);
    }
    public void numUpdate(final Button buttonName, final String num){
        buttonName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lastOperPressed == "add" || lastOperPressed == "subtract" || lastOperPressed == "multiply" || lastOperPressed == "divide"){
                    if(firstNum == Integer.parseInt(mNumberTextView.getText().toString())){
                        mNumberTextView.setText("");
                    }
                }
                if(lastOperPressed == "Equals"){
                    mNumberTextView.setText("");
                    firstNum = 0;
                    lastOperPressed = "None";
                }
                if(mNumberTextView.getText().toString() == "Can't Divide by 0!"){
                    mNumberTextView.setText("0");
                }
                stringNum = mNumberTextView.getText().toString();
                stringNum = stringNum + num;
                strToInt = Integer.parseInt(stringNum);
                stringNum = Integer.toString(strToInt);
                mNumberTextView.setText(stringNum);
                if(lastOperPressed == "divide" & strToInt == 0){
                    mNumberTextView.setText("Can't Divide by 0!");
                    firstNum = 0;
                    lastOperPressed = "None";
                }
                Log.d(TAG, Integer.toString(firstNum));
            }
        });
    }
    public void operUpdate(final Button buttonName, final String opName){
        buttonName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lastOperPressed == "add"){
                    firstNum = firstNum + strToInt;
                    mNumberTextView.setText(Integer.toString(firstNum));
                }
                else if(lastOperPressed == "subtract"){
                    firstNum = firstNum - strToInt;
                    mNumberTextView.setText(Integer.toString(firstNum));
                }
                else if(lastOperPressed == "multiply"){
                    firstNum = firstNum * strToInt;
                    mNumberTextView.setText(Integer.toString(firstNum));
                }
                else if(lastOperPressed == "divide"){
                    firstNum = firstNum / strToInt;
                    mNumberTextView.setText(Integer.toString(firstNum));
                }
                else if(lastOperPressed == "None" || lastOperPressed == "Equals"){
                    firstNum = Integer.parseInt(mNumberTextView.getText().toString());
                }
                if(opName == "addition"){
                    lastOperPressed = "add";
                }
                else if(opName == "subtraction"){
                    lastOperPressed = "subtract";
                }
                else if(opName == "multiplication"){
                    lastOperPressed = "multiply";
                }
                else if(opName == "division"){
                    lastOperPressed = "divide";
                }
                    else if(opName == "erase"){
                        lastOperPressed = "erase";
                }
            }
        });
    }
    public void equalsUpdate(final Button buttonName){
        buttonName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(lastOperPressed == "add"){
                    mNumberTextView.setText(Integer.toString(firstNum + strToInt));
                }
                else if(lastOperPressed == "subtract"){
                    mNumberTextView.setText(Integer.toString(firstNum - strToInt));
                }
                else if(lastOperPressed == "multiply"){
                    mNumberTextView.setText(Integer.toString(firstNum * strToInt));
                }
                else if(lastOperPressed == "divide"){
                    if(strToInt != 0) {
                        mNumberTextView.setText(Integer.toString(firstNum / strToInt));
                    }
                    else {
                        mNumberTextView.setText("Can't Divide by 0!");
                    }
                }
                lastOperPressed = "Equals";
            }
        });
    }

}
