package com.example.calculator3;

public enum OperatorType {
    ADD('+', '➕') {
        @Override
        public <T extends Number> double operate(T num1, T num2) {
            return num1.doubleValue() + num2.doubleValue();
        }
    },
    SUBTRACT('-', '➖') {
        @Override
        public <T extends Number> double operate(T num1, T num2) {
            return num1.doubleValue() - num2.doubleValue();
        }
    },
    MULTIPLY('*', '✖') {
        @Override
        public <T extends Number> double operate(T num1, T num2) {
            return num1.doubleValue() * num2.doubleValue();
        }
    },
    DIVIDE('/', '➗') {
        @Override
        public <T extends Number> double operate(T num1, T num2) {
            return num1.doubleValue() / num2.doubleValue();
        }
    };

    // 필드
    private final char operationSymbol;
    private final char operationEmoji;

    // 생성자
    OperatorType(char symbol, char emoji) {
        this.operationSymbol = symbol;
        this.operationEmoji = emoji;
    }

    // 입력받은 사칙연산 기호가 올바른지 판별
    public static boolean isValidOperator(char input) {
        for (OperatorType op : OperatorType.values()) {
            if (op.operationSymbol == input || op.operationEmoji == input) {
                return true;
            }
        }
        return false;
    }

    // 나눗셈 연산이 유효한지 판별
    // 가능한 연산일 경우 : true / 불가능한 연산일 경우 : false
    public static boolean isValidDivision(char operator, Number number) {
        if (OperatorType.DIVIDE.operationSymbol == operator
                || OperatorType.DIVIDE.operationEmoji == operator) {
            return number.doubleValue() != 0;
        }
        return true;
    }

    // 매개변수는 제네릭 타입(Number 또는 그 하위 클래스들)으로 받고,
    // 반환 타입을 항상 double 타입으로 고정: 일반적인 산술 결과가 실수이기 때문에
    public abstract <T extends Number> double operate(T num1, T num2);
}
