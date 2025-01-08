package com.example.calculator3;

import com.example.online.week4.BadInputException;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator <T extends Number> {
    // 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
    private final List<T> results = new ArrayList<>();

    // 연산 기호를 저장하는 필드
    private char operator;

    // 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    // 매개변수 타입은 제네릭, 반환은 항상 double
    public double calculate(T num1, T num2) {
        OperatorType operatorType = OperatorType.selectOperator(this.operator);
        return operatorType.operate(num1, num2);
    }

    /* Getter 메서드 구현 */
    public T getResults() {
        return this.results.get(results.size() - 1);  // 마지막 요소 리턴
    }
    public char getOperator() {
        return this.operator;
    }

    /* Setter 메서드 구현 */
    public void setResults(T answer) {
        this.results.add(answer);
    }
    public void setOperator(char operator) {
        this.operator = operator;
    }

    /* Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후
     App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정 */
    public void removeResult() {
        this.results.remove(0);
    }

    /* 저장된 연산 결과들 중 Scanner로 입력받은 값보다 큰 결과값 들을 출력
    * ArithmeticCalculator 클래스에 위 요구사항을 만족하는 조회 메서드를 구현합니다.
    * 단, 해당 메서드를 구현할 때 Lambda & Stream을 활용하여 구현합니다. */
   public void filterResultsGreaterThan(T[] inputs) {
       for (T input : inputs) {
           this.results.stream()
                   .filter(result -> Double.compare(result.doubleValue(), input.doubleValue()) > 0)
                   .forEach(f -> System.out.println("입력받은 값(" + input + ")보다 큰 결과값: " + f));
       }
   }
}

