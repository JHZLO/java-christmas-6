package christmas.service;

import static christmas.constants.Number.GIFT_THRESHOLD;

import christmas.domain.discount.strategies.Gift;
import christmas.domain.vo.BadgeType;
import christmas.dto.BenefitDto;
import christmas.dto.DiscountDto;
import christmas.dto.OrdersDto;

public class CalculateService {
    public BenefitDto calculateBenefits(OrdersDto ordersDto) { // 증정 품목 부여 여부 계산
        int preTotalPrice = calculatePreTotalPrice(ordersDto);
        boolean isGiftAvailable = preTotalPrice > GIFT_THRESHOLD;
        return new BenefitDto(preTotalPrice, new Gift(isGiftAvailable));
    }

    private int calculatePreTotalPrice(OrdersDto ordersDto) {
        return ordersDto.orders().calculatePreTotalPrice();
    }

    public BadgeType calculateBadgeType(DiscountDto discountDto){
        int totalDiscountPrice = discountDto.totalDiscountPrice();
        if (totalDiscountPrice >= BadgeType.SANTA_BADGE.getGoalPrice()) {
            return BadgeType.SANTA_BADGE;
        } else if (totalDiscountPrice >= BadgeType.TREE_BADGE.getGoalPrice()) {
            return BadgeType.TREE_BADGE;
        } else if (totalDiscountPrice >= BadgeType.STAR_BADGE.getGoalPrice()) {
            return BadgeType.STAR_BADGE;
        } else {
            return null;
        }
    }
}
