package com.example.calculaterserg.domain;

public class CalculatorImpl implements Calculator {

    @Override
    public double binaryOperation(double argumentOne, double argumentTwo, Operation operation) {
        switch (operation) {
            case ADD:
                return argumentOne + argumentTwo;
            case MULT:
                return argumentOne * argumentTwo;
            case DIV:
                return argumentOne / argumentTwo;
            case SUB:
                return argumentOne - argumentTwo;
        }
        return 0;
    }
}
