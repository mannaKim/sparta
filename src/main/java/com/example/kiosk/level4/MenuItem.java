package com.example.kiosk.level4;

/*
 * MenuItem: 세부 메뉴 속성 가지는 클래스
 * 햄버거의 이름, 가격설명
 * 예시: ShackBurger, 6.9, 토마토, 양상추, 쉑소스가 토핑된 치즈버거
 * new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
 */
public class MenuItem {
    public String menuName;
    public double menuPrice;
    public String menuDescription;

    public MenuItem (String menuName, double menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }
}