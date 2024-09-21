package christmas.util.format;

import static christmas.constants.Symbol.COLON;
import static christmas.constants.Symbol.FORMAT_NUMBER;
import static christmas.constants.Symbol.MINUS;
import static christmas.constants.Symbol.NEW_LINE;
import static christmas.constants.Symbol.WHITE_SPACE;
import static christmas.constants.ViewMessage.NON_BENEFIT;

import java.util.Map;

public class DiscountFormatUtil {
    public static String formatDiscountResults(Map<String, Integer> discountResults) {
        StringBuilder result = new StringBuilder();
        if (isDiscountNonBenefit(discountResults)) {
            return NON_BENEFIT.toString(); // "없음" 출력
        }
        discountResults.forEach((discountName, amount) ->
            result.append(discountName)
                .append(COLON)
                .append(WHITE_SPACE)
                .append(MINUS)
                .append(String.format(FORMAT_NUMBER, amount))
                .append(NEW_LINE)
        );
        return result.toString();
    }

    private static boolean isDiscountNonBenefit(Map<String, Integer> discountResults) {
        return discountResults.isEmpty();
    }
}
