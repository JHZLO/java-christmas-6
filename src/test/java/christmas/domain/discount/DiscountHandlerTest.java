package christmas.domain.discount;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.discount.strategies.Gift;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static christmas.domain.discount.constants.DiscountNumeric.DISCOUNT_MIN_PRICE;
import static christmas.domain.discount.constants.DiscountLabel.GIFT_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.SPECIAL_DISCOUNT;
import static christmas.domain.discount.constants.DiscountLabel.XMAS_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

class DiscountHandlerTest {

    private DiscountHandler discountHandler;
    private List<OrderProduct> validOrderProducts;
    private Gift gift;

    @BeforeEach
    void setUp() {
        validOrderProducts = List.of(
            new OrderProduct(Product.MUSHROOM_SOUP, 2),
            new OrderProduct(Product.BBQ_RIBS, 2),
            new OrderProduct(Product.CHOCOLATE_CAKE, 1)
        );
        gift = new Gift(true);
    }

    @Test
    @DisplayName("최소 금액을 충족하지 못하면 할인이 적용되지 않는다.")
    void noDiscountIfBelowMinPrice() {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(10)) {
            @Override
            public int calculatePreTotalPrice() {
                return DISCOUNT_MIN_PRICE - 1; // 최소 금액보다 1원 적음
            }
        };
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("createNoChristmas")
    @DisplayName("크리스마스 할인 기간 이후에는 크리스마스 디데이 할인이 적용되지 않는다.")
    void noChristmasDiscountAfter25(int day) {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(day));
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).doesNotContainKey(XMAS_DISCOUNT.toString());
    }

    @ParameterizedTest
    @MethodSource("createWeekend")
    @DisplayName("주말에는 주말 할인만 적용되고 평일 할인은 적용되지 않는다.")
    void weekendDiscountOnly(int day) {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(day));
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).containsKey("주말 할인").doesNotContainKey("평일 할인");
    }

    @ParameterizedTest
    @MethodSource("createWeekday")
    @DisplayName("평일에는 평일 할인만 적용되고 주말 할인은 적용되지 않는다.")
    void weekdayDiscountOnly(int day) {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(day));
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).containsKey("평일 할인").doesNotContainKey("주말 할인");
    }

    @ParameterizedTest
    @MethodSource("createSpecial")
    @DisplayName("특별 이벤트 날에는 특별 할인이 적용된다.")
    void specialDiscountApplied(int day) {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(day));
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).containsKey(SPECIAL_DISCOUNT.toString());
    }

    @Test
    @DisplayName("증정 이벤트 할인이 적용된다.")
    void applyGiftDiscount() {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(15));
        gift = new Gift(true); // 증정 이벤트 가능
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).containsKey(GIFT_DISCOUNT.toString());
    }

    @Test
    @DisplayName("증정 이벤트 할인이 적용되지 않는다.")
    void applyNotGiftDiscount() {
        // given
        Orders orders = new Orders(validOrderProducts, new VisitDateDto(15));
        gift = new Gift(false); // 증정 이벤트 비활성화
        discountHandler = new DiscountHandler(orders, gift);

        // when
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();

        // then
        assertThat(discounts).doesNotContainKey(GIFT_DISCOUNT.toString());
    }

    private static Stream<Arguments> createNoChristmas() {
        return Stream.of(
            Arguments.of(26),
            Arguments.of(27)
        );
    }

    private static Stream<Arguments> createWeekend() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(2),
            Arguments.of(8),
            Arguments.of(9),
            Arguments.of(15),
            Arguments.of(16)
        );
    }

    private static Stream<Arguments> createWeekday() {
        return Stream.of(
            Arguments.of(3),
            Arguments.of(4),
            Arguments.of(5),
            Arguments.of(6),
            Arguments.of(7),
            Arguments.of(18)
        );
    }

    private static Stream<Arguments> createSpecial() {
        return Stream.of(
            Arguments.of(3),
            Arguments.of(10),
            Arguments.of(24),
            Arguments.of(25)
        );
    }
}
