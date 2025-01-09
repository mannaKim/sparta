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
            for (int i = 0; i < 2; i++) {
                System.out.print((i + 1) + " 번째 숫자를 입력하세요: ");
                userInput = sc.next().trim();
                try {
                    numbers[i] = Double.parseDouble(userInput);

                } catch (NumberFormatException e) {
                    System.out.println(userInput + "은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
                    System.out.println("--------------------------------------------------");
                    --i;
                }
            }

            // 사칙연산 기호(➕,➖,✖️,➗) 입력 받기
            while (true) {
                System.out.print("사칙연산 기호를 입력하세요: ");
                try {
                    char operator = sc.next().trim().charAt(0);

                    // 입력받은 사칙연산 기호가 올바른지 판별
                    if (!OperatorType.isValidOperator(operator)) {
                        throw new IllegalArgumentException("사칙연산 기호가 적절하지 않습니다.");
                    }

                    // 나눗셈 연산이 유효한지 판별
                    if (!OperatorType.isValidDivision(operator, numbers[1])) {
                        throw new IllegalArgumentException("나눗셈의 분모(두 번째 숫자)가 0일 수 없습니다.");
                    }

                    calculator.setOperator(operator);
                    break;  // 유효한 연산자 입력 시 반복 종료

                } catch (Exception e) {
                    System.out.println(e.getMessage() + " 사칙연산 기호를 다시 입력하세요.");
                    System.out.println("사용 가능한 사칙연산 기호: +, -, *, /, ➕, ➖, ✖, ➗");
                    System.out.println("--------------------------------------------------");
                }
            }

            calculator.setResult(calculator.calculate(numbers[0], numbers[1]));
            System.out.println("결과: " + calculator.getResult());

            // results 리스트에 요소가 5개 이상이 되면, removeResult 메서드 실행하여 마지막 요소 삭제
            if (calculator.getResultsSize() > 5) calculator.removeResult();
            
            // results 리스트에 저장된 결과 중 현재 연산에서 입력받은 값보다 큰 결과값 출력
            calculator.filterResultsGreaterThan(numbers);

            // 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            answer = sc.next();
            System.out.println("**************************************************");
        }
    }
}
