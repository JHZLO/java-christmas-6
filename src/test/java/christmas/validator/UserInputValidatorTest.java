package christmas.validator;

import static christmas.constants.exception.error.ErrorMessage.INVALID_DATE;
import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.constants.exception.InputException;
import christmas.dto.VisitDateDto;
import christmas.util.parser.OrderParserUtil;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserInputValidatorTest {
    private VisitDateDto visitDateDto;

    @BeforeEach
    void setUp() {
        visitDateDto = new VisitDateDto(1);
    }

    @Test
    @DisplayName("날짜 입력 시에 정수가 입력되지 않으면 예외를 발생시킨다.")
    void validDateStringToInteger() {
        // given
        List<String> param = List.of("asd", "a23d", "", "3.2", "2,3-1");

        // when, then
        for (String input : param) {
            assertThatThrownBy(() -> UserInputValidator.validateStringToDate(input))
                .isInstanceOf(InputException.class)
                .hasMessageContaining(INVALID_DATE.getMessage());
        }

    }

    @Test
    @DisplayName("Orders 입력 시에 음식 개수 칸에 숫자가 입력되지 않으면 예외를 발생시킨다.")
    void validOrderCountIsNotNumber() {
        // given
        List<String> param = List.of("asd-", "", "asd-3.2", "asd-asd");

        // when, then
        for (String input : param) {
            assertThatThrownBy(() -> OrderParserUtil.parseOrder(input, visitDateDto))
                .isInstanceOf(InputException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
        }
    }

    @Test
    @DisplayName("Orders 입력이 비어 있으면 예외를 발생시킨다.")
    void validEmptyOrdersInput() {
        // given
        String input = "";

        // when, then
        assertThatThrownBy(() -> UserInputValidator.validateOrders(input))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_ORDER.getMessage());
    }

    @Test
    @DisplayName("Orders 입력 시에 hyphen이 하나가 아니라면 예외를 발생시킨다.")
    void validHyphenOrdersInput() {
        // given
        List<String> param = List.of("--", "a--b", "a-b--111", "abc123", "");

        // when, then
        for (String input : param) {
            assertThatThrownBy(() -> UserInputValidator.validateOrder(input))
                .isInstanceOf(InputException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
        }
    }

    @Test
    @DisplayName("Orders 입력 시에 hyphen을 기준으로 상품과 개수로 이루어지지 않은 형식이라면 예외를 발생시킨다.")
    void validOrdersInputFormat() {
        // given
        List<String> param = List.of("--", "a--b", "a-b--111", "abc123", "");

        // when, then
        for (String input : param) {
            assertThatThrownBy(() -> UserInputValidator.validateOrder(input))
                .isInstanceOf(InputException.class)
                .hasMessageContaining(INVALID_ORDER.getMessage());
        }
    }

    @Test
    @DisplayName("유효한 입력이 발생하면 예외가 발생하지 않는다.")
    void validNormalInputFormat() {
        // given
        String validInput = "양송이수프-1,초코케이크-3,제로콜라-3";

        // when, then
        assertThatCode(() -> OrderParserUtil.parseOrder(validInput, visitDateDto))
            .doesNotThrowAnyException();
    }
}
