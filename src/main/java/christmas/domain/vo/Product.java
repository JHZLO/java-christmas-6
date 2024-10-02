package christmas.domain.vo;

import java.util.Arrays;

public enum Product {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuType.APPETIZER),
    TAPAS("타파스", 5_500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, MenuType.MAIN),
    BBQ_RIBS("바비큐립", 54_000, MenuType.MAIN),
    SEA_FOOD_PASTA("해산물파스타", 35_000, MenuType.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),
    ZERO_COKE("제로콜라", 3_000, MenuType.DRINK),
    RED_WINE("레드와인", 60_000, MenuType.DRINK),
    CHAMPAGNE("샴페인", 25_000, MenuType.DRINK),
    NONE("없음", 0, MenuType.NONE);;

    private final String name;
    private final int price;
    private final MenuType menuType;

    Product(String name, int price, MenuType menuType) {
        this.name = name;
        this.price = price;
        this.menuType = menuType;
    }

    public static Product findProductByName(String name) {
        return Arrays.stream(values())
            .filter(e -> e.name.equals(name))
            .findAny()
            .orElse(null);
    }

    public static MenuType findMenuTypeByProductName(String name) {
        return Arrays.stream(values())
            .filter(e -> e.name.equals(name))
            .map(Product::getMenuType)
            .findAny()
            .orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuType getMenuType() {
        return menuType;
    }
}
