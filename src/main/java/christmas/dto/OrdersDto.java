package christmas.dto;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import java.util.List;
import java.util.stream.Collectors;

public record OrdersDto(Orders orders) {
}
