package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountInfo.WEEKEND_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountInfo.WEEKEND_DISCOUNT_STRING;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.discount.strategies.WeekendDiscount;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {
    private WeekendDiscount weekendDiscount;
    private List<OrderProduct> validOrderProducts;

    @BeforeEach
    void setUp() {
        weekendDiscount = new WeekendDiscount();
        validOrderProducts = List.of(
            new OrderProduct(Product.BBQ_RIBS, 2),  // 메인 메뉴
            new OrderProduct(Product.RED_WINE, 1)
        );
    }

    @Test
    @DisplayName("주말에 메인 메뉴가 주문되었을 때 할인 금액이 올바르게 계산된다.")
    void applyDiscount_validDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(9);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = weekendDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(2 * WEEKEND_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("주말이 아닌 날에는 할인 금액이 0이 반환된다.")
    void applyDiscount_noDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(6);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = weekendDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("할인 이름이 올바르게 반환된다.")
    void getDiscountName() {
        // when
        String discountName = weekendDiscount.getDiscountName();

        // then
        assertThat(discountName).isEqualTo(WEEKEND_DISCOUNT_STRING);
    }

    @Test
    @DisplayName("메인 메뉴가 없을 때 할인 금액이 0이 반환된다.")
    void applyDiscount_noMainMenu() {
        // given -> 메인 메뉴 없음
        List<OrderProduct> noMainMenuOrderProducts = List.of(
            new OrderProduct(Product.RED_WINE, 1),
            new OrderProduct(Product.CHOCOLATE_CAKE, 1)
        );
        VisitDateDto visitDateDto = new VisitDateDto(8);
        Orders orders = new Orders(noMainMenuOrderProducts, visitDateDto);

        // when
        int discount = weekendDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0);
    }
}
