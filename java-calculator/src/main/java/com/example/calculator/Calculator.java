package com.example.calculator;

import java.util.Scanner;

/* Lv 1. 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기 */
/*
 * 양의 정수(0 포함)를 입력 받기
 * 사칙연산 기호(➕,➖,✖️,➗) 입력 받기
 * 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기
 * 반복문을 사용하되, 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정하기
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = "";

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

            int result; // 결과를 저장할 변수 (정수형)

            //위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기
            switch (operator) {
                case '➕':
                case '+':
                    result = num1 + num2;
                    System.out.println("결과: " + result);
                    break;
                case '➖':
                case '-':
                    result = num1 - num2;
                    System.out.println("결과: " + result);
                    break;
                //case '✖️': // 해당 이모지는 1bit가 넘어서 사용 불가..!
                case '✖':
                case '*':
                    result = num1 * num2;
                    System.out.println("결과: " + result);
                    break;
                case '➗':
                case '/':
                    if (num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                        break;
                    }
                    result = num1 / num2;
                    System.out.println("결과: " + result);
                    break;
                default:
                    System.out.println("사칙연산 기호가 적절하지 않습니다.");
                    break;
            }

            // 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            answer = sc.next();
            System.out.println("-----------------------------------");
        }
    }
}
