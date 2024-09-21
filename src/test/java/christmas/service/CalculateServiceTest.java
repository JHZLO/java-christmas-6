package christmas.service;

import christmas.domain.Orders;
import christmas.domain.vo.BadgeType;
import christmas.dto.BenefitDto;
import christmas.dto.DiscountDto;
import christmas.dto.OrdersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CalculateServiceTest {
    private CalculateService calculateService;

    @BeforeEach
    void setUp() {
        calculateService = new CalculateService();
    }

    @Test
    @DisplayName("주문 금액이 120,000원을 넘을 경우 샴페인이 증정된다.")
    void returnsChampagneOverThreshold() {
        // given
        Orders orders = mock(Orders.class);
        when(orders.calculatePreTotalPrice()).thenReturn(130000);
        OrdersDto ordersDto = new OrdersDto(orders);

        // when
        BenefitDto benefitDto = calculateService.calculateBenefits(ordersDto);

        // then
        assertThat(benefitDto.getGiftMessage()).isEqualTo("샴페인 1개");
    }

    @Test
    @DisplayName("주문 금액이 120,000원 이하일 경우 증정 품목이 없다.")
    void returnsNullUnderThreshold() {
        // given
        Orders orders = mock(Orders.class);
        when(orders.calculatePreTotalPrice()).thenReturn(100000);
        OrdersDto ordersDto = new OrdersDto(orders);

        // when
        BenefitDto benefitDto = calculateService.calculateBenefits(ordersDto);

        // then
        assertThat(benefitDto.getGiftMessage()).isEqualTo("없음");
    }
    @Test
    @DisplayName("할인 금액이 5천원 이상, 1만원 미만인 경우 별 배지가 부여된다.")
    void returnsStarBadgeFor5000() {
        // given
        DiscountDto discountDto = new DiscountDto(null, 7000);
        // when
        BadgeType badgeType = calculateService.calculateBadgeType(discountDto);

        // then
        assertThat(badgeType).isEqualTo(BadgeType.STAR_BADGE);
    }
    @Test
    @DisplayName("할인 금액이 1만원 이상, 2만원 미만일 경우 트리 배지가 부여된다.")
    void returnsTreeBadgeFor10000() {
        // given
        DiscountDto discountDto = new DiscountDto(null, 15000);
        // when
        BadgeType badgeType = calculateService.calculateBadgeType(discountDto);

        // then
        assertThat(badgeType).isEqualTo(BadgeType.TREE_BADGE);
    }

    @Test
    @DisplayName("할인 금액이 2만원 이상일 경우 산타 배지가 부여된다.")
    void returnsSantaBadgeFor20000() {
        // given
        DiscountDto discountDto = new DiscountDto(null, 30000); // 총 할인 금액이 20000원
        // when
        BadgeType badgeType = calculateService.calculateBadgeType(discountDto);

        // then
        assertThat(badgeType).isEqualTo(BadgeType.SANTA_BADGE);
    }

    @Test
    @DisplayName("할인 금액이 5천원 미만일 경우 배지가 부여되지 않는다.")
    void returnsNoBadgeForBelow5000() {
        // given
        DiscountDto discountDto = new DiscountDto(null, 4000); // 총 할인 금액이 4000원
        // when
        BadgeType badgeType = calculateService.calculateBadgeType(discountDto);

        // then
        assertThat(badgeType).isNull();
    }
}
