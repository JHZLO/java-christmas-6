package christmas.constants.exception;

import christmas.constants.exception.error.ErrorMessage;

public class InputException extends IllegalArgumentException {
    public InputException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
