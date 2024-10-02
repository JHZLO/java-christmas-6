package christmas.util.parser;

import christmas.domain.VisitDate;
import christmas.dto.VisitDateDto;
import christmas.validator.UserInputValidator;

public class DateParserUtil {
    public static VisitDateDto parseVisitDate(String input) {
        VisitDate visitDate = new VisitDate(stringToInteger(input));
        return new VisitDateDto(visitDate);
    }

    private static int stringToInteger(String input) { // 입력받은 String 문자열을 Integer 타입으로 변경
        UserInputValidator.validateStringToDate(input); // 정수로 들어온 값인지 검증
        return Integer.parseInt(input);
    }
}
