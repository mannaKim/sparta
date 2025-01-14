package com.example.kiosk.level4;

import java.util.List;

/*
* MenuItem 클래스를 관리하는 클래스
* 예시 : 버거 메뉴, 음료 메뉴 등 각 카테고리 내에 여러 MenuItem을 포함합니다.
*/
public class Menu {
    public String menuCategory;
    public List<MenuItem> menuItems;    // MenuItem 클래스를 List로 관리

    public Menu(String menuCategory, List<MenuItem> menuItems) {
        this.menuCategory = menuCategory;
        this.menuItems = menuItems;
    }

    // List에 들어있는 MenuItem을 순차적으로 보여주는 함수
    // List를 리턴하는 함수

    // 구조에 맞게 함수를 선언해놓고 가져다 사용하세요.
}
