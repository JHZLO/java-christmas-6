package christmas.domain;

import static christmas.constants.Number.COUNT_MAX;
import static christmas.constants.Number.COUNT_MIN;
import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;

import christmas.constants.exception.InputException;
import christmas.domain.vo.MenuType;
import christmas.domain.vo.Product;
import java.util.Objects;

public class OrderProduct {
    private final Product product;
    private final int count;

    public OrderProduct(Product product, int count) {
        validateProduct(product);
        validateCount(count);
        this.product = product;
        this.count = count;
    }

    public boolean isSameMenuType(MenuType targetMenuType) {
        return this.product.getMenuType() == targetMenuType;
    }

    public int calculatePrice() {
        return product.getPrice() * count;
    }

    private static void validateProduct(final Product product) { // 주문한 메뉴가 있는지
        if (isInProduct(product)) {
            throw new InputException(INVALID_ORDER);
        }
    }

    private static boolean isInProduct(final Product product) {
        return product == null;
    }

    private static void validateCount(final int count) {
        validateCountMinMax(count);
    }

    private static void validateCountMinMax(final int count) { // 최소, 최대 주문
        if (isValidCountMinMax(count)) {
            return;
        }
        throw new InputException(INVALID_ORDER);
    }

    private static boolean isValidCountMinMax(final int count) {
        return COUNT_MIN <= count && count <= COUNT_MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderProduct that = (OrderProduct) o;
        return product == that.product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public Product getProduct() {
        return product;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getCount() {
        return count;
    }
}
