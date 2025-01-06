package com.example.calculator3;

import java.util.ArrayList;
import java.util.List;

public class ArithmeticCalculator /* Hint */ {
    // 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
    private List<Integer> result = new ArrayList<>();

    // 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
    public int calculate(int num1, int num2, char op) {
        return switch (op) {
            case '➕', '+' -> OperatorType.ADD.calculate(num1, num2);
            case '➖', '-' -> OperatorType.SUBTRACT.calculate(num1, num2);
            case '✖', '*' -> OperatorType.MULTIPLY.calculate(num1, num2);
            case '➗', '/' -> OperatorType.DIVIDE.calculate(num1, num2);
            default -> 0;
        };
    }

    /* Getter 메서드 구현 */
    public int getResult() {
        return this.result.get(result.size() - 1);  // 마지막 요소 리턴
    }

    /* Setter 메서드 구현 */
    public void setResult(int r) {
        this.result.add(r);
    }

    /* Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후
     App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정 */
    public void removeResult() {
        this.result.remove(0);
    }
}

