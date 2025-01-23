package com.example.kiosk.level6;

import java.util.Objects;

/*
 * MenuItem: 세부 메뉴 속성 가지는 클래스
 * 햄버거의 이름, 가격설명
 * 예시: ShackBurger, 6.9, 토마토, 양상추, 쉑소스가 토핑된 치즈버거
 * new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거")
 */
public class MenuItem {
    private final String menuName;
    private final double menuPrice;
    private final String menuDescription;

    public MenuItem (String menuName, double menuPrice, String menuDescription) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDescription = menuDescription;
    }

    public String getMenuName() {
        return menuName;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    // menuItem의 String format을 반환하는 static 함수
    public static String formatMenuItem(MenuItem item) {
        return String.format(
                "%-20s\t | W %.1f\t | %s",
                item.getMenuName(),
                item.getMenuPrice(),
                item.getMenuDescription()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Double.compare(menuPrice, menuItem.menuPrice) == 0 && Objects.equals(menuName, menuItem.menuName) && Objects.equals(menuDescription, menuItem.menuDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuName, menuPrice, menuDescription);
    }
}
