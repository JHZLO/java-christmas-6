package christmas.util.format;

import static christmas.constants.ViewMessage.NON_CONTENT;

import christmas.domain.vo.BadgeType;

public class BadgeFormatUtil {
    public static String formatBadgeType(BadgeType badgeType) {
        StringBuilder result = new StringBuilder();
        if (badgeType == null) {
            result.append(NON_CONTENT);
            return result.toString();
        }
        result.append(badgeType.getBadgeName());
        return result.toString();
    }
}
