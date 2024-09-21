package christmas.view;

import static christmas.constants.Symbol.COUNT_STRING;
import static christmas.constants.Symbol.FORMAT_NUMBER;
import static christmas.constants.ViewMessage.ANNOUNCE_EVENT_BENEFITS;
import static christmas.constants.ViewMessage.EVENT_PLANNER_NOTICE;
import static christmas.constants.ViewMessage.NOTICE_DISCOUNT;
import static christmas.constants.ViewMessage.NOTICE_GIFT;
import static christmas.constants.ViewMessage.NOTICE_MENU;
import static christmas.constants.ViewMessage.NOTICE_PRE_TOTAL_PRICE;


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

    public void printDiscoutResults(final String discountMessage){
        printWhiteSpace();
        System.out.println(NOTICE_DISCOUNT);
        System.out.println(discountMessage);
    }

    private static void printWhiteSpace() {
        System.out.println();
    }
}
