package christmas.domain.discount.strategies;

import static christmas.constants.Number.EVENT_MONTH;
import static christmas.constants.Number.EVENT_YEAR;
import static christmas.domain.discount.constants.DiscountNumeric.WEEKEND_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.WEEKEND_DISCOUNT;

import christmas.domain.Orders;
import christmas.domain.discount.Discount;
import christmas.domain.vo.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements Discount {
    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        if (isWeekend(orderDay)) {
            return getDiscountPrice(orders);
        }
        return 0;
    }

    @Override
    public String getDiscountName() {
        return WEEKEND_DISCOUNT.toString();
    }

    public boolean isWeekend(int orderDay) {
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, orderDay);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
    }

    private int getDiscountPrice(Orders orders) {
        int mainMenuCount = orders.findCountMenuType(MenuType.MAIN);
        return mainMenuCount * WEEKEND_DISCOUNT_PRICE;
    }
}
