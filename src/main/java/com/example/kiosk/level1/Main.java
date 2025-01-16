package com.example.kiosk.level1;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 메뉴를 저장할 List
        List<List<String>> menuList = new ArrayList<>();
        menuList.add(List.of(new String[]{"ShackBurger", "W 6.9", "토마토, 양상추, 쉑소스가 토핑된 치즈버거"}));
        menuList.add(List.of(new String[]{"SmokeShack", "W 8.9", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"}));
        menuList.add(List.of(new String[]{"Cheeseburger", "W 6.9", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"}));
        menuList.add(List.of(new String[]{"Hamburger", "W 5.4", "비프패티를 기반으로 야채가 들어간 기본버거"}));

        while (true) {
            // 전체 메뉴 출력
            printMenuList(menuList);

            // Scanner를 사용하여 여러 햄버거 메뉴를 출력합니다.
            String selectedMenu = sc.next();
            try {
                // 반복문을 이용해서 특정 번호가 입력되면 프로그램을 종료합니다.
                if ("0".equals(selectedMenu)) break;

                // 입력받은 매뉴를 정수로 변환
                int menuNumber = Integer.parseInt(selectedMenu);
                
                // 메뉴 리스트에 있는 번호라면 해당 메뉴의 이름,가격,설명을 출력 
                if (menuNumber > 0 && menuNumber <= menuList.size()) {
                    printMenu(menuList.get(menuNumber - 1));
                } else { // 아니라면 IllegalArgumentException 예외 발생
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
    public static void printMenuList(List<List<String>> menuList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ SHAKESHACK MENU ]\n");
        for (int i = 0; i < menuList.size(); i++) {
            sb.append(i + 1).append(". ");
            sb.append(menuList.get(i).get(0)).append("\t | "); // 이름
            sb.append(menuList.get(i).get(1)).append("\t | "); // 가격
            sb.append(menuList.get(i).get(2)).append("\n"); // 설명
        }
        sb.append("0. 종료      | 종료");
        System.out.println(sb);
    }
    public static void printMenu(List<String> menu) {
        String menuString = "선택한 메뉴 : " + menu.get(0) + "\t | " + menu.get(1) + "\t | " + menu.get(2) + "\n";
        System.out.print(menuString);
    }
}
