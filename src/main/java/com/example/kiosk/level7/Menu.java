package com.example.kiosk.level7;

import java.util.List;

/*
 * Menu: MenuItem 클래스를 관리하는 클래스
 * 예시 : 버거 메뉴, 음료 메뉴 등 각 카테고리 내에 여러 MenuItem을 포함합니다.
 */
public class Menu {
    private final String menuCategory;
    private final List<MenuItem> menuItems;

    public Menu(String menuCategory, List<MenuItem> menuItems) {
        this.menuCategory = menuCategory;
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem(int index) {
        return menuItems.get(index);
    }

    // 카테고리 메뉴를 출력하는 static 함수 (메인 메뉴)
    public static void printCategoryMenu(List<Menu> menuList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ MAIN MENU ]\n");
        menuList.stream()
                .map(menu -> (menuList.indexOf(menu) + 1) + ". " + menu.menuCategory + "\n")
                .forEach(sb::append);
        sb.append("0. 종료      | 종료");
        System.out.println(sb);
    }

    // Menu 객체의 menuItems 필드를 출력하는 함수
    public void printMenuItems() {
        String menuHeader = String.format("\n[ %s MENU ]\n", menuCategory.toUpperCase());
        StringBuilder sb = new StringBuilder(menuHeader);
        menuItems.stream()
                .map(item -> String.format("%d. %-20s\t | W %.1f\t | %s\n",
                        menuItems.indexOf(item) + 1,
                        item.getMenuName(),
                        item.getMenuPrice(),
                        item.getMenuDescription()))
                .forEach(sb::append);
        sb.append("0. 뒤로가기");
        System.out.println(sb);
    }

    // Menu 객체의 menuItems 중 선택된 menuItem을 출력하는 함수
    public void printSelectedMenuItem(int index) {
        String menuString = String.format(
                "선택한 메뉴 : %s\t | W %.1f\t | %s\n",
                menuItems.get(index).getMenuName(),
                menuItems.get(index).getMenuPrice(),
                menuItems.get(index).getMenuDescription()
        );
        System.out.print(menuString);
    }
}
