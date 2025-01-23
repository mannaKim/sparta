package com.example.kiosk.level2;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner sc = new Scanner(System.in);

        while (true) {
            MenuItem.printMenuList(menuList);

            String selectedMenu = sc.next();
            try {
                if ("0".equals(selectedMenu)) break;

                int menuNumber = Integer.parseInt(selectedMenu);
                if (menuNumber > 0 && menuNumber <= menuList.size()) {
                    menuList.get(menuNumber - 1).printMenu();
                } else {
                    throw new InvalidMenuSelectionException(0, menuList.size());
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
