package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountNumeric.SPECIAL;
import static christmas.domain.discount.constants.DiscountNumeric.SPECIAL_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.SPECIAL_DISCOUNT;

import christmas.domain.Orders;
import christmas.domain.discount.Discount;

public class SpecialDiscount implements Discount {
    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        if (isSpecial(orderDay)) {
            return getDiscountPrice();
        }
        return 0;
    }

    @Override
    public String getDiscountName() {
        return SPECIAL_DISCOUNT.toString();
    }

    private boolean isSpecial(int orderDay){
        return SPECIAL.contains(orderDay);
    }

    private int getDiscountPrice(){
        return SPECIAL_DISCOUNT_PRICE;
    }
}
