package christmas.util.parser;

import static christmas.constants.Symbol.COMMA;
import static christmas.constants.Symbol.HYPHEN;

import christmas.domain.OrderProduct;
import christmas.domain.Orders;
import christmas.domain.vo.Product;
import christmas.dto.OrdersDto;
import christmas.dto.VisitDateDto;
import christmas.validator.UserInputValidator;
import java.util.ArrayList;
import java.util.List;

public class OrderParserUtil {
    private static final int STRING_INDEX = 0;
    private static final int INTEGER_INDEX = 1;

    public static OrdersDto parseOrder(String input, VisitDateDto visitDateDto) {
        List<OrderProduct> orderProducts = parseOrderProducts(input);
        Orders orders = new Orders(orderProducts, visitDateDto);
        return new OrdersDto(orders);
    }

    private static List<OrderProduct> parseOrderProducts(String input) {
        UserInputValidator.validateOrders(input); // 빈 입력인지 검증

        List<OrderProduct> orderProducts = new ArrayList<>();
        List<String> ordersInput = List.of(input.split(COMMA));

        for (String orderInput : ordersInput) {
            UserInputValidator.validateOrder(orderInput); // hyphen이 제대로 들어가 있는지 검증
            String[] parts = orderInput.split(HYPHEN);

            String productName = parts[STRING_INDEX];
            Product product = Product.findProductByName(productName);
            int count = UserInputValidator.validateInteger(parts[INTEGER_INDEX]); // 정수 검증

            orderProducts.add(new OrderProduct(product, count));
        }

        return orderProducts;
    }
}
