package com.example.kiosk.level3;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.List;
import java.util.Scanner;

/*
* Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
* 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
*/
public class Kiosk {
    public List<MenuItem> menuItems;

    public Kiosk (List<MenuItem> items) {
        this.menuItems = items;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            MenuItem.printMenuList(menuItems);

            String selectedMenu = sc.next();
            try {
                if ("0".equals(selectedMenu)) break;

                int menuNumber = Integer.parseInt(selectedMenu);
                if (menuNumber > 0 && menuNumber <= menuItems.size()) {
                    menuItems.get(menuNumber - 1).printMenu();
                } else {
                    throw new InvalidMenuSelectionException(0, menuItems.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + selectedMenu + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
            } catch (InvalidMenuSelectionException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
            }
            System.out.println("************************************************************************************");
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
