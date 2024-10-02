package christmas.domain;

import christmas.domain.VisitDate;
import christmas.constants.exception.InputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constants.exception.error.ErrorMessage.INVALID_DATE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VisitDateTest {
    @Test
    @DisplayName("유효한 날짜가 입력되면 예외가 발생하지 않는다.")
    void validDateDoesNotThrowException() {
        // given
        int validDate = 15;

        // when, then
        assertThatCode(() -> new VisitDate(validDate))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효하지 않은 날짜가 입력되면 예외가 발생한다.")
    void invalidDateThrowsException() {
        // given
        int invalidDate = 50;

        // when, then
        assertThatThrownBy(() -> new VisitDate(invalidDate))
            .isInstanceOf(InputException.class)
            .hasMessageContaining(INVALID_DATE.getMessage());
    }
}
