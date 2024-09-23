package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountLabel.GIFT_DISCOUNT;

import christmas.domain.Orders;
import christmas.domain.discount.Discount;
import christmas.domain.vo.Product;

public class Gift implements Discount {
    private static final String DEFAULT_GIFT_QUANTITY = "1개";
    private final boolean isGiftAvailable;

    public Gift(boolean isGiftAvailable) {
        this.isGiftAvailable = isGiftAvailable;
    }

    @Override
    public int applyDiscount(Orders orders) {
        if (isGiftAvailable) {
            return Product.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    @Override
    public String getDiscountName() {
        return GIFT_DISCOUNT.toString();
    }

    public String getGiftMessage() {
        if (isGiftAvailable) {
            return Product.CHAMPAGNE.getName() + " " + DEFAULT_GIFT_QUANTITY;
        }
        return Product.NONE.getName(); // "없음" 반환
    }
}
