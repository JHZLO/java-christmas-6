package christmas.domain.discount;

import christmas.domain.Orders;

public interface Discount {
    int applyDiscount(Orders orders);
    String getDiscountName();
}
