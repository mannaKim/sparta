package com.example.online.week4;

import java.util.regex.Pattern;

public class Parser {
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^[0-9]*$";

    private final Calculator calculator = new Calculator();

    public void parseFirstNum(String firstInput) throws BadInputException {
        // 구현 1.
        if (!Pattern.matches(NUMBER_REG, firstInput)) {
            throw new BadInputException("정수 타입을");
        }
        this.calculator.setFirstNumber(Integer.parseInt(firstInput));
    }

    public void parseSecondNum(String secondInput) throws BadInputException {
        // 구현 1.
        if (!Pattern.matches(NUMBER_REG, secondInput)) {
            throw new BadInputException("정수 타입을");
        }
        this.calculator.setSecondNumber(Integer.parseInt(secondInput));
    }

    public void parseOperator(String operationInput) throws BadInputException {
        // 구현 1.
        if (!Pattern.matches(OPERATION_REG, operationInput)) {
            throw new BadInputException("+,-,*,/ 중 원하는 연산자를");
        }

        switch (operationInput) {
            case "+" -> calculator.setOperation(new AddOperation());
            case "-" -> calculator.setOperation(new SubstractOperation());
            case "*" -> calculator.setOperation(new MultiplyOperation());
            case "/" -> calculator.setOperation(new DivideOperation());
        }
    }

    public double executeCalculator() {
        return calculator.calculate();
    }
}