package com.example.calculaterserg.ui;

import android.content.Context;

import com.example.calculaterserg.R;
import com.example.calculaterserg.domain.Calculator;
import com.example.calculaterserg.domain.Operation;

public class CalculatorPresenter {

    private final ICalculatorView view;

    private final Context context;

    private final Calculator calculator;

    private Double leftArg = null;

    private Operation operation = null;

    private Double tmpValue = 0.0;

    private int power = 1;

    private boolean isEnteringRealPart = false;

    private boolean isDigitPressed = false;

    private boolean isEqualPressed = false;

    public CalculatorPresenter(Context context, ICalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
        this.context = context;
    }

    public void digitalKeyPressed(int digit) {

        if (isEqualPressed) {
            leftArg = null;
            isEqualPressed = false;
        }

        isDigitPressed = true;

        if (isEnteringRealPart) {
            tmpValue = tmpValue + digit / Math.pow(10, power);
            power++;
        } else {
            tmpValue = tmpValue * 10 + digit;
        }

        view.displayResult(context.getString(R.string.res, tmpValue));
    }

    public void operatorKeyPressed(Operation operation) {
        isEqualPressed = false;

        if (isDigitPressed) {
            if (leftArg == null) {
                leftArg = tmpValue;
            } else {
                leftArg = calculator.binaryOperation(leftArg, tmpValue, this.operation);

                view.displayResult(context.getString(R.string.res, leftArg));
            }
            this.operation = operation;
            tmpValue = 0.0;
            isEnteringRealPart = false;
            power = 1;
        } else {
            isDigitPressed = false;
            this.operation = operation;
        }
    }

    public void dotPressed() {
        isEnteringRealPart = true;
        isEqualPressed = false;
    }

    public void equalsPressed() {
        if (leftArg != null && this.operation != null && isDigitPressed) {
            leftArg = calculator.binaryOperation(leftArg, tmpValue, this.operation);

            view.displayResult(context.getString(R.string.res, leftArg));
            tmpValue = 0.0;
            isEnteringRealPart = false;
            power = 1;

            isDigitPressed = false;
            isEqualPressed = true;
        }
    }
}