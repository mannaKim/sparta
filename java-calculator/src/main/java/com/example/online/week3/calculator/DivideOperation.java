package com.example.online.week3.calculator;

public class DivideOperation extends AbstractOperation {
    @Override
    public double operate(int firstNumber, int secondNumber) {
        return (double) (firstNumber / secondNumber);
    }
}
