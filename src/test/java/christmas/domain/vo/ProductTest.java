package christmas.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    @DisplayName("유효한 이름으로 제품을 찾으면 해당 제품이 반환된다.")
    void returnsValidProduct() {
        // given
        String validProductName = "양송이수프";

        // when
        Product product = Product.findProductByName(validProductName);

        // then
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo(validProductName);
    }

    @Test
    @DisplayName("유효하지 않은 이름으로 제품을 찾으면 null이 반환된다.")
    void returnsNullForInvalidName() {
        // given
        String invalidProductName = "존재하지않는제품";

        // when
        Product product = Product.findProductByName(invalidProductName);

        // then
        assertThat(product).isNull();
    }

    @Test
    @DisplayName("유효한 제품 이름으로 메뉴 타입을 찾으면 해당 메뉴 타입이 반환된다.")
    void returnsValidProductToMenuType() {
        // given
        String validProductName = "양송이수프";

        // when
        MenuType menuType = Product.findMenuTypeByProductName(validProductName);

        // then
        assertThat(menuType).isEqualTo(MenuType.APPETIZER);
    }

    @Test
    @DisplayName("유효하지 않은 제품 이름으로 메뉴 타입을 찾으면 null이 반환된다.")
    void returnNullForInvalidName() {
        // given
        String invalidProductName = "존재하지않는제품";

        // when
        MenuType menuType = Product.findMenuTypeByProductName(invalidProductName);

        // then
        assertThat(menuType).isNull();
    }
}
