package com.example.calculator3;

import com.example.calculator3.ArithmeticCalculator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* Calculator 인스턴스 생성 */
        ArithmeticCalculator calculator = new ArithmeticCalculator();

        String answer = "";
        Scanner sc = new Scanner(System.in);

        // 반복문을 사용
        while (!answer.equals("exit")) {
            // 양의 정수(0 포함)를 입력 받기
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int num1 = sc.nextInt();
            System.out.print("두 번째 숫자를 입력하세요: ");
            int num2 = sc.nextInt();

            // 사칙연산 기호(➕,➖,✖️,➗) 입력 받기
            System.out.print("사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            if (operator == '+' || operator == '-' || operator == '*' || operator == '/'
                    || operator == '➕' || operator == '➖' || operator == '✖' || operator == '➗') {
                if ((operator == '/' || operator == '➗') && num2 == 0) {
                    System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                }
                else {
                    calculator.setResult(calculator.calculate(num1, num2, operator));
                    System.out.println("결과: " + calculator.getResult());
                    calculator.removeResult();
                }
            }
            else {
                System.out.println("사칙연산 기호가 적절하지 않습니다.");
            }

            // 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            answer = sc.next();
            System.out.println("-----------------------------------");
        }
    }
}
