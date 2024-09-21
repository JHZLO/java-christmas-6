package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountInfo.WEEKDAY_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountInfo.WEEKDAY_DISCOUNT_STRING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.discount.strategies.WeekdayDiscount;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekdayDiscountTest {
    private WeekdayDiscount weekdayDiscount;
    private List<OrderProduct> validOrderProducts;

    @BeforeEach
    void setUp() {
        weekdayDiscount = new WeekdayDiscount();
        validOrderProducts = List.of(
            new OrderProduct(Product.CHOCOLATE_CAKE, 2),    // 디저트 메뉴
            new OrderProduct(Product.RED_WINE, 1)
        );
    }

    @Test
    @DisplayName("평일에 디저트 메뉴가 주문되었을 때 할인 금액이 올바르게 계산된다.")
    void applyDiscount_validDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(4);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = weekdayDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(2 * WEEKDAY_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("주말에는 할인 금액이 0이 반환된다.")
    void applyDiscount_noDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(9);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = weekdayDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("할인 이름이 올바르게 반환된다.")
    void getDiscountName() {
        // when
        String discountName = weekdayDiscount.getDiscountName();

        // then
        assertThat(discountName).isEqualTo(WEEKDAY_DISCOUNT_STRING);
    }

    @Test
    @DisplayName("디저트 메뉴가 없을 때 할인 금액이 0이 반환된다.")
    void applyDiscount_noDessert() {
        // given
        List<OrderProduct> noDessertOrderProducts = List.of(
            new OrderProduct(Product.BBQ_RIBS, 1)   // 메인 메뉴만 주문
        );
        VisitDateDto visitDateDto = new VisitDateDto(4);
        Orders orders = new Orders(noDessertOrderProducts, visitDateDto);

        // when
        int discount = weekdayDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0); // 디저트 메뉴가 없으므로 할인 없음
    }
}
