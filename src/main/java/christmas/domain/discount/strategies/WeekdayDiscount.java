package christmas.domain.discount.strategies;

import static christmas.constants.Number.EVENT_MONTH;
import static christmas.constants.Number.EVENT_YEAR;
import static christmas.domain.discount.constants.DiscountNumeric.WEEKDAY_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.WEEKDAY_DISCOUNT;

import christmas.domain.Orders;
import christmas.domain.discount.Discount;
import christmas.domain.vo.MenuType;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements Discount {
    @Override
    public int applyDiscount(Orders orders) {
        int orderDay = orders.getDate();
        if (isWeekend(orderDay)) {
            return 0;
        }
        return getDiscountPrice(orders);
    }

    @Override
    public String getDiscountName() {
        return WEEKDAY_DISCOUNT.toString();
    }

    public boolean isWeekend(int orderDay) {
        LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, orderDay);
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
    }

    private int getDiscountPrice(Orders orders) {
        int desertMenuCount = orders.findCountMenuType(MenuType.DESSERT);
        return desertMenuCount * WEEKDAY_DISCOUNT_PRICE;
    }
}
