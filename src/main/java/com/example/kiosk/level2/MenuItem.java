package com.example.kiosk.level2;

import java.util.List;

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

    MenuItem (String menuName, double menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    public static void printMenuList(List<MenuItem> menuList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ SHAKESHACK MENU ]\n");
        for (int i = 0; i < menuList.size(); i++) {
            sb.append(i + 1).append(". ");
            sb.append(menuList.get(i).menuName).append("\t | ");
            sb.append(menuList.get(i).menuPrice).append("\t | ");
            sb.append(menuList.get(i).menuDescription).append("\n");
        }
        sb.append("0. 종료      | 종료");
        System.out.println(sb);
    }

    public void printMenu() {
        String menuString = "선택한 메뉴 : " + menuName + "\t | " + menuPrice + "\t | " + menuDescription + "\n";
        System.out.print(menuString);
    }
}
