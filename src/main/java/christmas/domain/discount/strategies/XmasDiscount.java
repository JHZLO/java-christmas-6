package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountNumeric.XMAS_DEFAULT_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountNumeric.XMAS_DISCOUNT_END_DAY;
import static christmas.domain.discount.constants.DiscountNumeric.XMAS_DISCOUNT_PER_DAY_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.XMAS_DISCOUNT;

import christmas.domain.Orders;
import christmas.domain.discount.Discount;

public class XmasDiscount implements Discount {
    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        return getDiscountPrice(orderDay);
    }

    @Override
    public String getDiscountName() {
        return XMAS_DISCOUNT.toString();
    }

    private int getDiscountPrice(int orderDay) {
        if (orderDay > XMAS_DISCOUNT_END_DAY) {
            return 0;
        }
        int extraDiscount = (orderDay - 1) * XMAS_DISCOUNT_PER_DAY_PRICE;
        return XMAS_DEFAULT_DISCOUNT_PRICE + extraDiscount;
    }

}
