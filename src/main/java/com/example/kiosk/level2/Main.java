package com.example.kiosk.level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // List 선언 및 초기화
        List<MenuItem> menuList = new ArrayList<>();
        // add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        menuList.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuList.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // Scanner 선언
        Scanner sc = new Scanner(System.in);

        while (true) {
            MenuItem.printMenuList(menuList);

            // 숫자를 입력 받기
            String selectedMenu = sc.next();
            try {
                // 반복문을 이용해서 특정 번호가 입력되면 프로그램을 종료합니다.
                if ("0".equals(selectedMenu)) break;

                // 입력받은 매뉴를 정수로 변환
                int menuNumber = Integer.parseInt(selectedMenu);

                // 메뉴 리스트에 있는 번호라면 해당 메뉴의 이름,가격,설명을 출력
                if (menuNumber > 0 && menuNumber <= menuList.size()) {
                    menuList.get(menuNumber - 1).printMenu();
                } else { // 아니라면 IllegalArgumentException 예외 발생
                    throw new IllegalArgumentException("입력 가능한 숫자는 0~" + menuList.size() + "입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + selectedMenu + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
            }
            System.out.println("************************************************************************************");
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
