package com.example.online.week4;

public class Main {
    public static void main(String[] args) {
        boolean calculateEnded = false;
        // 구현 2.

        Calculator calculator  = new Calculator(new AddOperation());
        //System.out.println(calculator.calculate(10, 20));
        calculator.setOperation(new MultiplyOperation());
        //System.out.println(calculator.calculate(10,20));
    }
}