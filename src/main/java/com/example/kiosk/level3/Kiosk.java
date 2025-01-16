package com.example.kiosk.level3;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.List;
import java.util.Scanner;

/*
* Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
* 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
*/
public class Kiosk {
    // MenuItem을 관리하는 리스트가 필드로 존재합니다.
    public List<MenuItem> menuItems;

    // List<MenuItem> menuItems 는 Kiosk 클래스 생성자를 통해 값을 할당합니다.
    public Kiosk (List<MenuItem> items) {
        this.menuItems = items;
    }

    // main 함수에서 관리하던 입력과 반복문 로직은 이제 start 함수를 만들어 관리합니다.
    public void start() {
        // Scanner 선언
        Scanner sc = new Scanner(System.in);

        while (true) {
            MenuItem.printMenuList(this.menuItems);

            // 숫자를 입력 받기
            String selectedMenu = sc.next();
            try {
                // 반복문을 이용해서 특정 번호가 입력되면 프로그램을 종료합니다.
                if ("0".equals(selectedMenu)) break;

                // 입력받은 매뉴를 정수로 변환
                int menuNumber = Integer.parseInt(selectedMenu);

                // 메뉴 리스트에 있는 번호라면 해당 메뉴의 이름,가격,설명을 출력
                if (menuNumber > 0 && menuNumber <= this.menuItems.size()) {
                    this.menuItems.get(menuNumber - 1).printMenu();
                } else { // 아니라면 IllegalArgumentException 예외 발생
                    throw new InvalidMenuSelectionException(0, this.menuItems.size());
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
