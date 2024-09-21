package christmas.validator;

import static christmas.constants.Number.ORDER_VALID_LENGTH;
import static christmas.constants.Symbol.HYPHEN;
import static christmas.constants.exception.error.ErrorMessage.INVALID_DATE;
import static christmas.constants.exception.error.ErrorMessage.INVALID_ORDER;

import christmas.constants.exception.InputException;

public class UserInputValidator {
    public static void validateStringToDate(String input) { // 문자열-> 숫자 검증 과정
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException(INVALID_DATE);
        }
    }

    public static void validateOrders(String orders) { // orders가 비어있는지 확인
        if (isEmptyOrders(orders)) {
            throw new InputException(INVALID_ORDER);
        }
    }

    private static boolean isEmptyOrders(String orders) {
        return orders == null || orders.trim().isEmpty();
    }

    public static void validateOrder(String order) { // hyphen이 정상적으로 들어가 있는지 확인
        String[] parts = order.split(HYPHEN); // parts[0] : product , parts[1] : count
        validateInputOrderFormat(parts);
    }

    private static void validateInputOrderFormat(String[] parts) { // 상품과 개수로 이루어져 있는지 확인
        if (isValidLength(parts)) {
            return;
        }
        throw new InputException(INVALID_ORDER);
    }

    private static boolean isValidLength(String[] parts) {
        return parts.length == ORDER_VALID_LENGTH;
    }

    public static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException(INVALID_ORDER);
        }
    }

}
