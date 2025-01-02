package com.example.online.calculator;

public class Calculator {
    /*AddOperation addOperation = new AddOperation();
    SubstractOperation substractOperation = new SubstractOperation();
    MultiplyOperation multiplyOperation = new MultiplyOperation();
    DivideOperation divideOperation = new DivideOperation();
    */

    // Step 1 : 더하기, 빼기, 나누기, 곱하기 연산을 수행할 수 있는 Calculator 클래스를 만듭니다.
    public double calculate(String operator, int firstNumber, int secondNumber) {
        return switch (operator) {
            case "+" -> (double) (firstNumber + secondNumber);
            case "-" -> (double) (firstNumber - secondNumber);
            case "*" -> (double) (firstNumber * secondNumber);
            case "/" -> (double) (firstNumber / secondNumber);
            case "%" -> (double) (firstNumber % secondNumber);
            default -> 0.0;
        };
    }
}