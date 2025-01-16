package com.example.kiosk.level6;

import java.util.List;
import java.util.Scanner;

/*
 * Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
 * 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
 */
public class Kiosk {
    // Menu를 관리하는 리스트 필드
    private final List<Menu> menuList;
    private Cart cart;

    // 생성자를 통해 List<Menu> menuList 필드 값 할당
    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        cart = new Cart();

        while (true) {
            // 카테고리 메뉴 출력
            Menu.printCategoryMenu(menuList);

            // Order 메뉴 출력
            int categoryCount = menuList.size();
            int additionalOrderMenuCount = 2;
            int totalMenuCount = categoryCount + additionalOrderMenuCount;
            boolean canOrder = false;
            if (!cart.getCartItemMap().isEmpty()) {
                cart.printOrderMenu(categoryCount);
                canOrder = true;
            }

            String selectedMenu = sc.next();
            try {
                if ("0".equals(selectedMenu)) break;

                int selectedMenuNumber = Integer.parseInt(selectedMenu);
                if (selectedMenuNumber > 0 ) {
                    if (selectedMenuNumber <= categoryCount) {
                        // 카테고리 메뉴 중 선택된 메뉴(menuItems) 출력
                        startViewSelectedCategoryMenu(selectedMenuNumber - 1);
                    }
                    else if (canOrder && selectedMenuNumber <= totalMenuCount) {
                        // 주문하기 Or 취소하기
                        if (selectedMenuNumber == totalMenuCount) {
                            cart.cancelOrder();
                            System.out.println("\n전체 주문이 취소되어 처음으로 돌아갑니다.");
                            System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
                        }
                        else {
                            startPlaceOrder();
                        }
                    }
                }
                else {
                    throw new IllegalArgumentException("입력 가능한 숫자는 0~" + categoryCount + "입니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + selectedMenu + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
            } catch (IllegalArgumentException e) {
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
                this.menuList.get(index).printSelectedMenuItem(selectedMenuItemNumber - 1);
                // 해당 item 장바구니에 담기
                startAddToCart(menuList.get(index).getMenuItem(selectedMenuItemNumber - 1));
            } else {
                throw new IllegalArgumentException("입력 가능한 숫자는 0~" + menuItemsCount + "입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + selectedMenuItem + "\"은 숫자가 아닙니다. 숫자를 다시 입력하세요.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }

    private void startAddToCart(MenuItem item) {
        final int ADD_TO_CART_OPTION = 1; // 장바구니에 추가
        final int CANCEL_OPTION = 2;      // 취소

        System.out.println("\n\"" + Menu.formatMenuItem(item) + "\"");
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t2. 취소");

        Scanner sc = new Scanner(System.in);
        String addToCartChoice = sc.next();

        try {
            int addToCartChoiceNumber = Integer.parseInt(addToCartChoice);

            if (addToCartChoiceNumber == ADD_TO_CART_OPTION) {
                cart.addCartItem(item);
                System.out.println("\n"+ item.getMenuName() + " 이 장바구니에 추가되었습니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else if (addToCartChoiceNumber == CANCEL_OPTION) {
                System.out.println("\n취소되었습니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else {
                throw new IllegalArgumentException("입력 가능한 숫자는 " + ADD_TO_CART_OPTION + "~" + CANCEL_OPTION + "입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + addToCartChoice + "\"은/는 숫자가 아닙니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }

    private void startPlaceOrder() {
        final int PLACE_ORDER_OPTION = 1;       // 주문하기
        final int RETURN_TO_MENU_OPTION = 2;    // 메뉴판으로 돌아가기

        System.out.println("\n아래와 같이 주문하시겠습니까?");
        cart.printCartItemsWithTotal();
        System.out.println("1. 주문\t2. 메뉴판");

        Scanner sc = new Scanner(System.in);
        String placeOrderChoice = sc.next();

        try {
            int placeOrderChoiceNumber = Integer.parseInt(placeOrderChoice);

            if (placeOrderChoiceNumber == PLACE_ORDER_OPTION) {
                cart.placeOrder();
            }
            else if (placeOrderChoiceNumber == RETURN_TO_MENU_OPTION) {
                System.out.println("\n메뉴판으로 돌아갑니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else {
                throw new IllegalArgumentException("입력 가능한 숫자는 " + PLACE_ORDER_OPTION + "~" + RETURN_TO_MENU_OPTION + "입니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + placeOrderChoice + "\"은/는 숫자가 아닙니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }
}