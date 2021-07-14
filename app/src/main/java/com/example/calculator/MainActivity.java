package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            display.setShowSoftInputOnFocus(false);
        }
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void btnZero(View view) {
        updateText("0");
    }

    public void btnOne(View view) {
        updateText("1");
    }

    public void btnTwo(View view) {
        updateText("2");
    }

    public void btnThree(View view) {
        updateText("3");
    }

    public void btnFour(View view) {
        updateText("4");
    }

    public void btnFive(View view) {
        updateText("5");
    }

    public void btnSix(View view) {
        updateText("6");
    }

    public void btnSeven(View view) {
        updateText("7");
    }

    public void btnEight(View view) {
        updateText("8");
    }

    public void btnNine(View view) {
        updateText("9");
    }

    public void btnPlusMinus(View view) {
        updateText("-");
    }

    public void btnAdd(View view) {
        updateText("+");
    }

    public void btnSubtract(View view) {
        updateText("-");
    }

    public void btnMultiply(View view) {
        updateText("×");
    }

    public void btnEquals(View view) {
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");
        Expression expression=new Expression(userExp);

        String result=String.valueOf(expression.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void btnExponent(View view) {
        updateText("^");
    }

    public void btnBackspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void btnDevide(View view) {
        updateText("÷");
    }

    public void btnPoint(View view) {
        updateText(".");
    }

    public void btnParentheses(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }
        if (openPar==closePar|| display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos+1);
        }
        else if (closePar<openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
            display.setSelection(cursorPos+1);
        }

    }
    public void btnClear(View view){
        updateText("");
    }
}