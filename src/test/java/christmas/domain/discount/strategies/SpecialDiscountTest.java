package christmas.domain.discount.strategies;

import static christmas.domain.discount.constants.DiscountNumeric.SPECIAL;
import static christmas.domain.discount.constants.DiscountNumeric.SPECIAL_DISCOUNT_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.SPECIAL_DISCOUNT;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SpecialDiscountTest {
    private SpecialDiscount specialDiscount;
    private List<OrderProduct> validOrderProducts;

    @BeforeEach
    void setUp() {
        specialDiscount = new SpecialDiscount();
        validOrderProducts = List.of(
            new OrderProduct(Product.MUSHROOM_SOUP, 2),
            new OrderProduct(Product.BBQ_RIBS, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSpecialDates")
    @DisplayName("특별 할인이 적용되는 날에 할인 금액이 올바르게 계산된다.")
    void applyDiscount_validDiscount(int specialDay) {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(specialDay);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = specialDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(SPECIAL_DISCOUNT_PRICE);
    }

    @Test
    @DisplayName("특별 할인이 적용되지 않는 날에는 할인 금액이 0이 반환된다.")
    void applyDiscount_noDiscount() {
        // given
        VisitDateDto visitDateDto = new VisitDateDto(5);
        Orders orders = new Orders(validOrderProducts, visitDateDto);

        // when
        int discount = specialDiscount.applyDiscount(orders);

        // then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("할인 이름이 올바르게 반환된다.")
    void getDiscountName() {
        // when
        String discountName = specialDiscount.getDiscountName();

        // then
        assertThat(discountName).isEqualTo(SPECIAL_DISCOUNT.toString());
    }

    static Stream<Integer> provideSpecialDates() {
        return SPECIAL.stream(); // SPECIAL 리스트에 포함된 날짜를 스트림으로 변환
    }
}
