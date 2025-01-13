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
        // 반복문을 활용해 List 안에 있는 MenuItem을 하나씩 출력
        StringBuilder sb = new StringBuilder();
        sb.append("[ SHAKESHACK MENU ]\n");
        for (int i = 0; i < menuList.size(); i++) {
            sb.append(i + 1).append(". ");
            sb.append(menuList.get(i).menuName).append("\t | "); // 이름
            sb.append(menuList.get(i).menuPrice).append("\t | "); // 가격
            sb.append(menuList.get(i).menuDescription).append("\n"); // 설명
        }
        sb.append("0. 종료      | 종료");
        System.out.println(sb);
    }

    public void printMenu() {
        String menuString = "선택한 메뉴 : " + this.menuName + "\t | " + this.menuPrice + "\t | " + this.menuDescription + "\n";
        System.out.print(menuString);
    }
}
