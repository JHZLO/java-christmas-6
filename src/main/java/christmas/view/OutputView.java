package christmas.view;

import static christmas.constants.Symbol.COUNT_STRING;
import static christmas.constants.Symbol.FORMAT_NUMBER;
import static christmas.constants.ViewMessage.ANNOUNCE_EVENT_BENEFITS;
import static christmas.constants.ViewMessage.EVENT_PLANNER_NOTICE;
import static christmas.constants.ViewMessage.NOTICE_BADGE_TYPE;
import static christmas.constants.ViewMessage.NOTICE_DISCOUNT;
import static christmas.constants.ViewMessage.NOTICE_GIFT;
import static christmas.constants.ViewMessage.NOTICE_MENU;
import static christmas.constants.ViewMessage.NOTICE_PRE_TOTAL_PRICE;
import static christmas.constants.ViewMessage.NOTICE_PRICE_AFTER_DISCOUNT;
import static christmas.constants.ViewMessage.NOTICE_TOTAL_DISCOUNT;


public class OutputView {
    public void printHeaderNotice() {
        System.out.println(EVENT_PLANNER_NOTICE);
    }

    public void printHeaderEventBenefits() {
        System.out.println(ANNOUNCE_EVENT_BENEFITS);
        printWhiteSpace();
    }

    public void printResult(String result) {
        System.out.println(result);
    }

    public void printOrderDetails(String ordersList) {
        System.out.println(NOTICE_MENU);
        System.out.println(ordersList);
    }

    public void printPreTotalPrice(final int preTotalPrice) {
        System.out.println(NOTICE_PRE_TOTAL_PRICE);
        System.out.printf(FORMAT_NUMBER, preTotalPrice);
        printWhiteSpace();
    }

    public void printIsGiftAvailable(final String giftMessage) {
        printWhiteSpace();
        System.out.println(NOTICE_GIFT);
        System.out.println(giftMessage);
    }

    public void printDiscoutResults(final String discountMessage) {
        printWhiteSpace();
        System.out.println(NOTICE_DISCOUNT);
        System.out.println(discountMessage);
    }

    public void printTotalDiscountPrice(final String totalDiscountMessage) {
        System.out.println(NOTICE_TOTAL_DISCOUNT);
        System.out.println(totalDiscountMessage);
    }

    public void printTotalPriceAfterDiscount(final String totalPriceMessage) {
        printWhiteSpace();
        System.out.println(NOTICE_PRICE_AFTER_DISCOUNT);
        System.out.println(totalPriceMessage);
    }

    public void printBadgeType(final String badgeTypeMessage){
        printWhiteSpace();
        System.out.println(NOTICE_BADGE_TYPE);
        System.out.println(badgeTypeMessage);
    }

    private static void printWhiteSpace() {
        System.out.println();
    }
}
