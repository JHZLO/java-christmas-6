package christmas.domain.discount.constants;

import java.util.List;

public class DiscountNumeric {
    public static final List<Integer> SPECIAL = List.of(3, 10, 17, 24, 25, 31);
    public static final int SPECIAL_DISCOUNT_PRICE = 1000;
    public static final int WEEKDAY_DISCOUNT_PRICE = 2023;
    public static final int WEEKEND_DISCOUNT_PRICE = 2023;
    public static final int XMAS_DISCOUNT_END_DAY = 25;
    public static final int XMAS_DEFAULT_DISCOUNT_PRICE = 1000;
    public static final int XMAS_DISCOUNT_PER_DAY_PRICE = 100;
    public static final int DISCOUNT_MIN_PRICE = 10000;
}
