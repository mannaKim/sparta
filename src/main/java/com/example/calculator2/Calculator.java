package com.example.calculator2;

/*
* 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
* 1) 양의 정수 2개(0 포함)와 연산 기호를 매개변수로 받아 사칙연산(➕,➖,✖️,➗) 기능을 수행한 후
  2) 결과 값을 반환하는 메서드와 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성합니다.
*/
public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
    int result;

    // 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    public int calculate(int num1, int num2, char op) {
        return switch (op) {
            case '➕', '+' -> num1 + num2;
            case '➖', '-' -> num1 - num2;
            case '✖', '*' -> num1 * num2;
            case '➗', '/' -> num1 / num2;
            default -> 0;
        };
    }
}
