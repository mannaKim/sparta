package com.example.kiosk.level7;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Burgers 메뉴
        List<MenuItem> burgersMenuItems = Arrays.asList(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                // Cheeseburger 메뉴는 키오스크 시작 전부터 품절되었다고 가정
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", true),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
        // Drinks 메뉴
        List<MenuItem> drinksMenuItems = Arrays.asList(
                new MenuItem("Shack-made Lemonade", 3.9, "매장에서 직접 만드는 상큼한 레몬에이드"),
                new MenuItem("Fifty/Fifty", 3.5, "레몬에이드와 아이스티의 만남"),
                new MenuItem("Fountain Soda", 2.7, "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프")
        );
        // Desserts 메뉴
        List<MenuItem> dessertsMenuItems = Arrays.asList(
                new MenuItem("Red Bean Shake", 6.5, "신선한 커스터드와 함께 우유와 레드빈이 블랜딩 된 시즈널 쉐이크"),
                new MenuItem("Shack Attack", 8.9, "초콜렛 처지 소스, 초콜렛 트리플 쿠키, Lumiere 초콜렛 청크와 스프링클이 들어간 진한 초콜렛 커스터드"),
                new MenuItem("Honey Butter Crunch", 8.9, "달콤한 허니버터소스와 슈가콘 쿠키가 함께하는 바닐라 커스터드")
        );

        Menu burgersMenu = new Menu("Burgers", burgersMenuItems);
        Menu drinksMenu = new Menu("Drinks", drinksMenuItems);
        Menu dessertsMenu = new Menu("Desserts", dessertsMenuItems);

        List<Menu> menuList = Arrays.asList(burgersMenu, drinksMenu, dessertsMenu);

        Kiosk kiosk = new Kiosk(menuList);
        kiosk.start();
    }
}
