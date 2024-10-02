package christmas.domain;

import christmas.constants.exception.InputException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.constants.Number.DATE_MAX;
import static christmas.constants.Number.DATE_MIN;
import static christmas.constants.Number.EVENT_MONTH;
import static christmas.constants.Number.EVENT_YEAR;
import static christmas.constants.exception.error.ErrorMessage.INVALID_DATE;

public class VisitDate {
    private final int date;

    public VisitDate(final int date) {
        validateVisitDate(date);
        this.date = date;
    }

    private void validateVisitDate(final int date) {
        validateRange(date);
    }

    private void validateRange(final int date) {
        if (isInRange(date)) {
            return;
        }
        throw new InputException(INVALID_DATE);
    }

    private boolean isInRange(final int date) {
        return DATE_MIN <= date && date <= DATE_MAX;
    }

    public int getDate() {
        return date;
    }
}
