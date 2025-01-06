package com.example.calculator3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* ArithmeticCalculator 인스턴스 생성 */
        ArithmeticCalculator<Number> calculator = new ArithmeticCalculator<>();

        String answer = "";
        Scanner sc = new Scanner(System.in);

        // 사용자가 "exit"를 입력하기 전까지 반복
        while (!answer.equals("exit")) {

            // 연산할 숫자 2개 입력 받기
            // 입력 받은것이 숫자가 맞는지 확인
            String userInput;
            Number[] numbers = new Number[2];
            for (int i=0; i<2; i++) {
                System.out.print((i+1) + " 번째 숫자를 입력하세요: ");
                userInput = sc.next().trim();
                try {
                    numbers[i] = Double.parseDouble(userInput);
                } catch (NumberFormatException e) {
                    System.out.println(userInput + "은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
                    --i;
                    //continue;
                }
            }

            // 사칙연산 기호(➕,➖,✖️,➗) 입력 받기
            System.out.print("사칙연산 기호를 입력하세요: ");
            try {
                char operator = sc.next().trim().charAt(0);
                if (OperatorType.isValidOperator(operator)) {
                    if (OperatorType.isDivisionOperator(operator) && numbers[1].equals(0)) {
                        System.out.println("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
                    }
                    else {
                        calculator.setResult(calculator.calculate(numbers[0], numbers[1], operator));
                        System.out.println("결과: " + calculator.getResult());
                        calculator.removeResult();
                    }
                }
                else {
                    System.out.println("사칙연산 기호가 적절하지 않습니다.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            answer = sc.next();
            System.out.println("-----------------------------------");
        }
    }
}
