package com.example.kiosk.level4;

import java.util.List;
import java.util.Scanner;

/*
 * Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
 * 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
 */
public class Kiosk {
    // Menu를 관리하는 리스트 필드
    public List<Menu> menuList;
    
    // 생성자를 통해 List<Menu> menuList 값 할당
    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    // main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리합니다.
    public void start() {
        // Scanner 선언
        Scanner sc = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력

            // 숫자 입력 받기

            // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
            // List<Menu>에 인덱스로 접근하면 Menu만 추출할 수 있겠죠?

            // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력

            // 숫자 입력 받기
            // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
            // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.
        }
    }
}