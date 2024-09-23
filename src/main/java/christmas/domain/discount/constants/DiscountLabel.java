package christmas.domain.discount.constants;

public enum DiscountLabel {
    GIFT_DISCOUNT("증정 이벤트"),
    XMAS_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인");

    private final String description;

    DiscountLabel(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
