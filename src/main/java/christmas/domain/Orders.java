package christmas.domain;

import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;

import christmas.constants.exception.InputException;
import christmas.domain.vo.MenuType;
import christmas.domain.vo.Product;
import christmas.dto.VisitDateDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {
    private final List<OrderProduct> orderProducts;
    private final VisitDateDto visitDateDto;

    public Orders(List<OrderProduct> orderProducts, VisitDateDto visitDateDto) {
        validateOrders(orderProducts);
        this.orderProducts = orderProducts;
        this.visitDateDto = visitDateDto;
    }

    public int findCountMenuType(MenuType targetMenuType) {
        return orderProducts.stream()
            .filter(orderProduct -> orderProduct.isSameMenuType(targetMenuType))
            .mapToInt(OrderProduct::getCount)
            .sum();
    }

    private void validateOrders(List<OrderProduct> orderProducts) {
        validateDuplicateProducts(orderProducts);
        validateNotOnlyDrinks(orderProducts);
    }

    private void validateDuplicateProducts(List<OrderProduct> orderProducts) { // 중복된 음식 검증
        Set<Product> productSet = new HashSet<>();
        for (OrderProduct orderProduct : orderProducts) {
            if (isDuplicateProduct(productSet, orderProduct)) {
                continue;
            }
            throw new InputException(INVALID_ORDER);
        }
    }

    private boolean isDuplicateProduct(Set<Product> productSet, OrderProduct orderProduct) {
        return productSet.add(orderProduct.getProduct());
    }

    private void validateNotOnlyDrinks(List<OrderProduct> orderProducts) { // 음료만 있는 경우 검증
        if (isOnlyDrinks(orderProducts)) {
            throw new InputException(INVALID_ORDER);
        }
    }

    public int calculatePreTotalPrice() {
        return orderProducts.stream()
            .mapToInt(OrderProduct::calculatePrice)
            .sum();
    }

    private boolean isOnlyDrinks(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
            .allMatch(orderProduct -> orderProduct.getProduct().getMenuType() == MenuType.DRINK);
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public int getDate(){
        return visitDateDto.date();
    }
}
