package christmas.domain.discount.strategies;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.discount.constants.DiscountInfo.XMAS_DISCOUNT_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class XmasDiscountTest {
    private XmasDiscount xmasDiscount;
    private List<OrderProduct> validOrderProducts;

    @BeforeEach
    void setUp() {
        xmasDiscount = new XmasDiscount();
        validOrderProducts = List.of(
            new OrderProduct(Product.MUSHROOM_SOUP, 2),
            new OrderProduct(Product.RED_WINE, 1)
        );
    }

    @Test
    @DisplayName("크리스마스 디데이 할인 금액이 올바르게 계산된다.")
    void applyDiscount_validDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(10);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = xmasDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(1000 + (10 - 1) * 100);
    }

    @Test
    @DisplayName("할인 적용이 안 되는 날짜에서는 0이 반환된다.")
    void applyDiscount_noDiscount() {
        // given
        List<OrderProduct> orderProducts = List.of();
        VisitDateDto visitDateDto = new VisitDateDto(26);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = xmasDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0);  // 할인 기간 이후이므로 0원 할인
    }

    @Test
    @DisplayName("할인 이름이 올바르게 반환된다.")
    void getDiscountName() {
        // when
        String discountName = xmasDiscount.getDiscountName();

        // then
        assertThat(discountName).isEqualTo(XMAS_DISCOUNT_STRING);
    }
}
