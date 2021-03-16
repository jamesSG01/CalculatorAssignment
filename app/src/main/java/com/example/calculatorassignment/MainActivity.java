package com.example.calculatorassignment;

import androidx.appcompat.app.AppCompatActivity;
import  org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.result_display);
        //clear text when click on display.
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(text.getText().toString())){
                    text.setText("");
                }
            }
        });

    }
    public void updateTxt(String toAddStr) {
        String oldStr = text.getText().toString();
        int cursorPos = text.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(text.getText().toString()) ) {
            text.setText(toAddStr);
            text.setSelection(cursorPos + 1);
        } else {
            text.setText(String.format("%s%s%s", leftStr, toAddStr, rightStr));
            text.setSelection(cursorPos + 1);

        }//
    }
    // to check & set ( )
    public void parBTN(View view){
        int cursorPos = text.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = text.getText().length();

        for (int i = 0; i < cursorPos; i++){
            if (text.getText().toString().substring(i,i+1).equals("(")){
                openPar += 1;
            }
            if (text.getText().toString().substring(i,i+1).equals(")")){
                closedPar += 1;
            }
        }
        if (openPar == closedPar || text.getText().toString().substring(textLen-1,textLen).equals("(")) {
            updateTxt("(");
            text.setSelection(cursorPos+1);
        } else if (closedPar < openPar && !text.getText().toString().substring(textLen-1,textLen).equals(")")) {
            updateTxt(")");
        }
        text.setSelection(cursorPos+1);
    }
    public void backspaceButton(View view) {
        int cursorPos = text.getSelectionStart();
        int textLen = text.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) text.getText();
            selection.replace(cursorPos -1, cursorPos, "");
            text.setText(selection);
            text.setSelection(cursorPos -1 );
        }
    }
    public void clearText(View view) {
        text.setText("");
    }
    public void equalButton(View view) {
        String userExp = text.getText().toString();
        userExp = userExp.replaceAll("÷ ","/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());
        text.setText(result);
        text.setSelection(result.length());
    }
    public void percentButton(View view) {
        updateTxt("%");
    }
    public void dotButton(View view) {
        updateTxt(".");
    }
    public void zeroButton(View view) {
        updateTxt("0");

    }
    public void oneButton(View view) {
        updateTxt("1");
    }
    public void twoButton(View view) {
        updateTxt("2");
    }
    public void threeButton(View view) {
        updateTxt("3");
    }
    public void fourButton(View view) {
        updateTxt("4");
    }
    public void fiveButton(View view) {
        updateTxt("5");
    }
    public void sixButton(View view) {
        updateTxt("6");
    }
    public void sevenButton(View view) {
        updateTxt("7");
    }
    public void eightButton(View view) {
        updateTxt("8");
    }
    public void nineButton(View view) {
        updateTxt("9");
    }
    public void multiplyButton(View view) {
        updateTxt("×");
    }
    public void divideButton(View view) {
        updateTxt("÷ ");
    }
    public void expButton(View view) {
        updateTxt("^");
    }
    public void substractButton(View view) {
        updateTxt("-");
    }
    public void addButton(View view) {
        updateTxt("+");
    }
}