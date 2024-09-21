package christmas.domain.discount.constants;

import java.util.List;

public class DiscountInfo { // TODO: String, Integer 클래스 분리
    public static final List<Integer> SPECIAL = List.of(3, 10, 17, 24, 25, 31);
    public static final String DEFAULT_GIFT_QUANTITY = "1개";
    public static final String GIFT_DISCOUNT_STRING = "증정 이벤트";
    public static final String XMAS_DISCOUNT_STRING = "크리스마스 디데이 할인";
    public static final String WEEKDAY_DISCOUNT_STRING = "평일 할인";
    public static final String WEEKEND_DISCOUNT_STRING = "주말 할인";
    public static final String SPECIAL_DISCOUNT_STRING = "특별 할인";
    public static final int SPECIAL_DISCOUNT_PRICE = 1000;
    public static final int WEEKDAY_DISCOUNT_PRICE = 2023;
    public static final int WEEKEND_DISCOUNT_PRICE = 2023;
    public static final int XMAS_DISCOUNT_END_DAY = 25;
    public static final int XMAS_DEFAULT_DISCOUNT_PRICE = 1000;
    public static final int XMAS_DISCOUNT_PER_DAY_PRICE = 100;
    public static final int DISCOUNT_MIN_PRICE = 10000;

}
