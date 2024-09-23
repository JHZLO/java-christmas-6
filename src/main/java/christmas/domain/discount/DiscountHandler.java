package christmas.domain.discount;

import static christmas.domain.discount.constants.DiscountNumeric.DISCOUNT_MIN_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.GIFT_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.SPECIAL_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.WEEKDAY_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.WEEKEND_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.XMAS_DISCOUNT;

import christmas.domain.Orders;

import christmas.domain.discount.strategies.Gift;
import christmas.domain.discount.strategies.SpecialDiscount;
import christmas.domain.discount.strategies.WeekdayDiscount;
import christmas.domain.discount.strategies.WeekendDiscount;
import christmas.domain.discount.strategies.XmasDiscount;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DiscountHandler {
    private final Orders orders;
    private final Map<String, Discount> discounts;
    private final Gift gift;

    public DiscountHandler(Orders orders, Gift gift) {
        this.orders = orders;
        this.gift = gift;
        this.discounts = initializeDiscountStrategies();
    }

    public Map<String, Integer> getAvailableDiscounts() { // 할인이 적용되는 최소 금액인지 확인
        if (orders.calculatePreTotalPrice() < DISCOUNT_MIN_PRICE) {
            return Collections.emptyMap();
        }
        return getDiscountResults();
    }

    private Map<String, Discount> initializeDiscountStrategies() {
        return Map.ofEntries(
            Map.entry(XMAS_DISCOUNT.toString(), new XmasDiscount()),
            Map.entry(SPECIAL_DISCOUNT.toString(), new SpecialDiscount()),
            Map.entry(WEEKDAY_DISCOUNT.toString(), new WeekdayDiscount()),
            Map.entry(WEEKEND_DISCOUNT.toString(), new WeekendDiscount()),
            Map.entry(GIFT_DISCOUNT.toString(), gift)
        );
    }

    private Map<String, Integer> getDiscountResults() { // 할인 되는 전략 추가
        Map<String, Integer> result = new HashMap<>();
        discounts.forEach((key, discount) -> addDiscountIfApplicable(orders, result, key,
            discount)); // 할인이 적용되는지 확인
        return result;
    }

    private void addDiscountIfApplicable(Orders orders, Map<String, Integer> result, String key,
        Discount discount) {
        int discountAmount = discount.applyDiscount(orders);
        if (discountAmount > 0) {
            result.put(key, discountAmount);
        }
    }
}
