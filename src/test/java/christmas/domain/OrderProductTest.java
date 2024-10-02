package christmas.domain;

import christmas.domain.vo.Product;
import christmas.constants.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderProductTest {
    @Test
    @DisplayName("유효한 제품과 수량이 입력되면 예외가 발생하지 않는다.")
    void validProductAndCount() {
        // given
        Product validProduct = Product.MUSHROOM_SOUP;
        int validCount = 2;

        // when, then
        assertThatCode(() -> new OrderProduct(validProduct, validCount))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효하지 않은 제품이 입력되면 예외가 발생한다.")
    void validateInvalidProduct() {
        // given
        Product invalidProduct = null;

        // when, then
        assertThatThrownBy(() -> new OrderProduct(invalidProduct, 2))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("유효하지 않은 수량이 입력되면 예외가 발생한다.")
    void validateInvalidCount() {
        // given
        Product validProduct = Product.MUSHROOM_SOUP;
        int invalidCount = 100;

        // when, then
        assertThatThrownBy(() -> new OrderProduct(validProduct, invalidCount))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("최소 수량 미만의 수량이 입력되면 예외가 발생한다.")
    void validateBelowMinCount() {
        // given
        Product validProduct = Product.MUSHROOM_SOUP;
        int invalidCount = 0;

        // when, then
        assertThatThrownBy(() -> new OrderProduct(validProduct, invalidCount))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }
}
