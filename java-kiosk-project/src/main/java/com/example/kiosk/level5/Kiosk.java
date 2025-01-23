package com.example.kiosk.level5;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.List;
import java.util.Scanner;

/*
 * Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
 * 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
 */
public class Kiosk {
    private final List<Menu> menuList;

    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 카테고리 메뉴 출력
            Menu.printCategoryMenu(menuList);

            String selectedMenu = sc.next();
            try {
                if ("0".equals(selectedMenu)) break;

                int selectedMenuNumber = Integer.parseInt(selectedMenu);
                int categoryCount = menuList.size();
                if (selectedMenuNumber > 0 && selectedMenuNumber <= categoryCount) {
                    // 카테고리 메뉴 중 선택된 메뉴(menuItems) 출력
                    startViewSelectedCategoryMenu(selectedMenuNumber - 1);
                } else {
                    throw new InvalidMenuSelectionException(0, categoryCount);
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + selectedMenu + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
            } catch (InvalidMenuSelectionException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
            }
            System.out.println();
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private void startViewSelectedCategoryMenu(int index) {
        // 선택된 menuItems 필드 출력
        menuList.get(index).printMenuItems();

        Scanner sc = new Scanner(System.in);
        String selectedMenuItem = sc.next();
        try {
            if ("0".equals(selectedMenuItem)) return;
            int selectedMenuItemNumber = Integer.parseInt(selectedMenuItem);
            int menuItemsCount = menuList.get(index).getMenuItems().size();
            if (selectedMenuItemNumber > 0 && selectedMenuItemNumber <= menuItemsCount) {
                // menuItems 중 선택된 menuItem 출력
                menuList.get(index).printSelectedMenuItem(selectedMenuItemNumber - 1);
            } else {
                throw new InvalidMenuSelectionException(0, menuItemsCount);
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + selectedMenuItem + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
        } catch (InvalidMenuSelectionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }
}
