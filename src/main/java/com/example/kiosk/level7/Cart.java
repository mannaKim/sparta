package com.example.kiosk.level7;

import java.util.HashMap;
import java.util.List;

/*
 * Cart: 장바구니 클래스
 */
public class Cart {
    private final HashMap<MenuItem, Integer> cartItemMap;

    public Cart() {
        cartItemMap = new HashMap<>();
    }

    public HashMap<MenuItem, Integer> getCartItemMap() {
        return cartItemMap;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for( MenuItem cartItem : cartItemMap.keySet() ){
            totalPrice += (cartItem.getMenuPrice() * cartItemMap.get(cartItem));
        }
        return totalPrice;
    }

    public double calculateTotalPrice(double discountRate) {
        double originalTotalPrice = calculateTotalPrice();
        return originalTotalPrice * (1 - discountRate);
    }

    public void addCartItem(MenuItem item) {
        int itemQuantity = cartItemMap.getOrDefault(item, 0) + 1;
        cartItemMap.put(item, itemQuantity);
    }

    public void cancelOrder() {
        cartItemMap.clear();
    }

    public void removeSoldOutItems() {
        List<MenuItem> soldOutItems = cartItemMap.keySet().stream()
                .filter(MenuItem::isSoldOut)
                .toList();

        System.out.println();
        soldOutItems.forEach(item -> System.out.println("\"" + item.getMenuName() + "\"은 품절되어 장바구니에서 삭제합니다."));

        soldOutItems.forEach(cartItemMap::remove);
    }

    public void placeOrder(UserType userType) {
        StringBuilder sb = new StringBuilder("\n주문이 완료되었습니다. ");
        if (userType.getDiscountRate() > 0) {
            String orderString = String.format(
                    "\"%s\" 할인이 적용되어, 금액은 W %.2f 입니다.",
                    userType.getDiscountMenuName(),
                    calculateTotalPrice(userType.getDiscountRate())
            );
            sb.append(orderString);
        }
        else {
            String orderString = String.format(
                    "금액은 W %.2f 입니다.",
                    calculateTotalPrice()
            );
            sb.append(orderString);
        }
        System.out.println(sb);
        System.out.println("**************************************************************");
        cartItemMap.clear();
    }

    public void printCartItemsWithTotal() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ Orders ]\n");
        cartItemMap.keySet().stream()
                .map(item -> String.format("수량: %d\t | %s\n",
                        cartItemMap.get(item),
                        MenuItem.formatMenuItem(item)))
                .forEach(sb::append);

        sb.append("\n[ Total ]\n");
        sb.append("W ").append(String.format("%.2f", calculateTotalPrice())).append("\n");

        System.out.println(sb);
    }

    public void printOrderMenu(int index) {
        final int ORDER_OPTION = 1;     // 주문하기
        final int CANCEL_OPTION = 2;    // 취소하기
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ ORDER MENU ]\n");
        sb.append(index + ORDER_OPTION).append(". Orders\t| 장바구니를 확인 후 주문합니다.\n");
        sb.append(index + CANCEL_OPTION).append(". Cancel\t| 진행중인 주문을 취소합니다.");
        System.out.println(sb);
    }
}
