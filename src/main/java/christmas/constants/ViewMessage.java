package christmas.constants;

public enum ViewMessage {
    EVENT_PLANNER_NOTICE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ANNOUNCE_EVENT_BENEFITS("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    NOTICE_MENU("<주문 메뉴>"),
    NOTICE_PRE_TOTAL_PRICE("<할인 전 총주문 금액>"),
    NOTICE_GIFT("<증정 메뉴>"),
    NOTICE_DISCOUNT("<혜택 내역>"),
    NON_CONTENT("없음"),
    NOTICE_TOTAL_DISCOUNT("<총혜택 금액>"),
    NOTICE_PRICE_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    NOTICE_BADGE_TYPE("<12월 이벤트 배지>");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
