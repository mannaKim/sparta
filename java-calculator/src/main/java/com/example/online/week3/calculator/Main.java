package com.example.online.week3.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 숫자를 입력하세요: ");
        int num1 = sc.nextInt();
        System.out.print("두 번째 숫자를 입력하세요: ");
        int num2 = sc.nextInt();
        System.out.print("사칙연산 기호(+,-,*,/)를 입력하세요: ");
        String operator = sc.next();

        Calculator calculator = new Calculator(operator);
        System.out.println("결과 : " + calculator.calculate(num1, num2));
    }
}
