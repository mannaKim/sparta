package com.example.kiosk.level6;

import java.util.HashMap;

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

    public double getTotalPrice() {
        double totalPrice = 0;
        for( MenuItem cartItem : cartItemMap.keySet() ){
            totalPrice += cartItem.getMenuPrice();
        }
        return totalPrice;
    }

    public void addCartItem(MenuItem item) {
        int itemQuantity = cartItemMap.getOrDefault(item, 0) + 1;
        cartItemMap.put(item, itemQuantity);
    }

    public void cancelOrder() {
        cartItemMap.clear();
    }

    public void placeOrder() {
        System.out.println("\n주문이 완료되었습니다. 금액은 W " + getTotalPrice() + " 입니다.");
        System.out.println("**************************************************************");
        cartItemMap.clear();
    }

    public void printCartItemsWithTotal() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ Orders ]\n");
        for( MenuItem cartItem : cartItemMap.keySet() ){
            sb.append("수량: ").append(cartItemMap.get(cartItem)).append("\t | ");
            sb.append(MenuItem.formatMenuItem(cartItem));
            sb.append("\n");
        }

        sb.append("\n[ Total ]\n");
        sb.append("W ").append(getTotalPrice()).append("\n");

        System.out.println(sb);
    }

    public void printOrderMenu(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ ORDER MENU ]\n");
        sb.append(index + 1).append(". Orders\t| 장바구니를 확인 후 주문합니다.\n");
        sb.append(index + 2).append(". Cancel\t| 진행중인 주문을 취소합니다.");
        System.out.println(sb);
    }
}
