package com.example.kiosk.exceptions;

public class InvalidMenuSelectionException extends Exception {
    public InvalidMenuSelectionException(int rangeStart, int rangeEnd) {
        super("입력 가능한 숫자는 " + rangeStart + "~" + rangeEnd + "입니다.\n"
                + "메뉴를 다시 입력하세요.");
    }
}
