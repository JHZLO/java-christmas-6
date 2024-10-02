package christmas.dto;

import java.util.Map;

public record DiscountDto(Map<String, Integer> discounts, Integer totalDiscountPrice) {
}
