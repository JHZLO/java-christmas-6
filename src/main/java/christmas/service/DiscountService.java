package christmas.service;

import christmas.domain.discount.DiscountHandler;
import christmas.dto.BenefitDto;
import christmas.dto.DiscountDto;
import christmas.dto.OrdersDto;
import java.util.Map;

public class DiscountService {
    public DiscountDto createDiscountDto(OrdersDto ordersDto, BenefitDto benefitDto) {
        DiscountHandler discountHandler = new DiscountHandler(ordersDto.orders(),
            benefitDto.gift());
        Map<String, Integer> discounts = discountHandler.getAvailableDiscounts();
        return new DiscountDto(discounts, sumAllPrice(discounts));
    }

    private Integer sumAllPrice(Map<String, Integer> discounts) {
        return discounts.values().stream().reduce(Integer::sum).orElse(0);
    }
}
