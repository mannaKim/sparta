package com.example.kiosk.level6;

import java.util.HashMap;

/*
 * Cart: CartItem 클래스를 관리하는 클래스
*/
public class Cart {
    private final HashMap<MenuItem, Integer> cartItemMap;

    public Cart() {
        cartItemMap = new HashMap<>();
    }

    public HashMap<MenuItem, Integer> getCartItemMap() {
        return cartItemMap;
    }

    public void addCartItem(MenuItem item) {
        int itemQuantity = cartItemMap.getOrDefault(item, 0) + 1;
        cartItemMap.put(item, itemQuantity);

        /*System.out.println("[ 현재 장바구니 ]");
        for( MenuItem cartItem : cartItemMap.keySet() ){
            String stringMenu = Menu.formatMenuItem(cartItem)
                    + "\t | 수량: " + cartItemMap.get(cartItem);
            System.out.println(stringMenu);
        }*/
    }

    // Order Menu를 출력하는 함수
    public void printOrderMenu(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[ ORDER MENU ]\n");
        sb.append(index + 1).append(". Orders\t| 장바구니를 확인 후 주문합니다.\n");
        sb.append(index + 2).append(". Cancel\t| 진행중인 주문을 취소합니다.");
        System.out.println(sb);
    }

    // 주문하기: 장바구니에 담긴 상품 출력, 토탈 금액 출력

    // 취소하기: 장바구니 리셋
}
