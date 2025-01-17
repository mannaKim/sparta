package com.example.kiosk.level7;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

import java.util.List;
import java.util.Scanner;

/*
 * Kiosk: 프로그램 순서 및 흐름 제어를 담당하는 클래스
 * 설명: 키오스크 프로그램의 메뉴를 관리하고 사용자 입력을 처리하는 클래스입니다.
 */
public class Kiosk {
    private final List<Menu> menuList;
    private Cart cart;

    public Kiosk(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        cart = new Cart();

        while (true) {
            // 카테고리 출력
            Menu.printCategoryMenu(menuList);
            int categoryCount = menuList.size();

            // Order 메뉴(주문하기/취소하기) 출력
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
                if (!canOrder) {
                    if (selectedMenuNumber > 0 && selectedMenuNumber <= categoryCount) {
                        startViewSelectedCategoryMenu(selectedMenuNumber - 1);
                    }
                    else {
                        throw new InvalidMenuSelectionException(0, categoryCount);
                    }
                }
                else {
                    if (selectedMenuNumber > 0 && selectedMenuNumber <= categoryCount) {
                        startViewSelectedCategoryMenu(selectedMenuNumber - 1);
                    }
                    else if (selectedMenuNumber > 0 && selectedMenuNumber <= totalMenuCount) {
                        if (selectedMenuNumber == totalMenuCount) {
                            startCancelOrder();
                        }
                        else {
                            startPlaceOrder();
                        }
                    }
                    else {
                        throw new InvalidMenuSelectionException(0, totalMenuCount);
                    }
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
        // 메뉴 출력
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
                // 해당 item 장바구니에 담기
                startAddToCart(menuList.get(index).getMenuItem(selectedMenuItemNumber - 1));
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

    private void startAddToCart(MenuItem item) {
        final int ADD_TO_CART_OPTION = 1; // 장바구니에 추가
        final int CANCEL_OPTION = 2;      // 취소

        System.out.println("\n\"" + MenuItem.formatMenuItem(item) + "\"");
        if (item.isSoldOut()) {
            System.out.println("위 메뉴는 품절되어 장바구니에 추가하실 수 없습니다.");
            return;
        }
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t2. 취소");

        Scanner sc = new Scanner(System.in);
        String addToCartChoice = sc.next();

        try {
            int addToCartChoiceNumber = Integer.parseInt(addToCartChoice);

            if (addToCartChoiceNumber == ADD_TO_CART_OPTION) {
                cart.addCartItem(item);
                System.out.println("\n\""+ item.getMenuName() + "\"이 장바구니에 추가되었습니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else if (addToCartChoiceNumber == CANCEL_OPTION) {
                System.out.println("\n취소되었습니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else {
                throw new InvalidMenuSelectionException(ADD_TO_CART_OPTION, CANCEL_OPTION);
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + addToCartChoice + "\"은/는 숫자가 아닙니다.");
        } catch (InvalidMenuSelectionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }

    private void startPlaceOrder() {
        final int PLACE_ORDER_OPTION = 1;       // 주문하기
        final int RETURN_TO_MENU_OPTION = 2;    // 메뉴판으로 돌아가기

        // "기존 장바구니에서 특정 메뉴 빼기 기능을 통한 스트림 활용"을 위해
        // 장바구니에 담아둔 상품(SmokeShack)이 품절된 경우 가정
        menuList.get(0).getMenuItem(1).setSoldOut(true);
        cart.removeSoldOutItems();
        if (cart.getCartItemMap().isEmpty()) {
            System.out.println("\n장바구니가 비었습니다.");
            System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            return;
        }

        System.out.println("\n아래와 같이 주문하시겠습니까?");
        cart.printCartItemsWithTotal();
        System.out.println("1. 주문\t2. 메뉴판");

        Scanner sc = new Scanner(System.in);
        String placeOrderChoice = sc.next();

        try {
            int placeOrderChoiceNumber = Integer.parseInt(placeOrderChoice);

            if (placeOrderChoiceNumber == PLACE_ORDER_OPTION) {
                startApplyDiscount();
            }
            else if (placeOrderChoiceNumber == RETURN_TO_MENU_OPTION) {
                System.out.println("\n메뉴판으로 돌아갑니다.");
                System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
            }
            else {
                throw new InvalidMenuSelectionException(PLACE_ORDER_OPTION, RETURN_TO_MENU_OPTION);
            }
        } catch (NumberFormatException e) {
            System.out.println("\"" + placeOrderChoice + "\"은/는 숫자가 아닙니다.");
        } catch (InvalidMenuSelectionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }

    private void startCancelOrder() {
        cart.cancelOrder();
        System.out.println("\n전체 주문이 취소되어 처음으로 돌아갑니다.");
        System.out.println("\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
    }

    private void startApplyDiscount() {
        System.out.println("\n할인 정보를 입력해주세요.");
        UserType.printDiscountInformation();

        Scanner sc = new Scanner(System.in);
        String userTypeChoice = sc.next();

        try {
            int userTypeChoiceNumber = Integer.parseInt(userTypeChoice);
            UserType userType = UserType.selectedUserType(userTypeChoiceNumber);

            cart.placeOrder(userType);

        } catch (NumberFormatException e) {
            System.out.println("\"" + userTypeChoice + "\"은/는 숫자가 아닙니다.");
        } catch (InvalidMenuSelectionException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다. : " + e.getMessage());
        }
    }
}
