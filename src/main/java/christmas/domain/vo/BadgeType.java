package christmas.domain.vo;

public enum BadgeType {
    STAR_BADGE("별", 5_000),
    TREE_BADGE("트리", 10_000),
    SANTA_BADGE("산타", 20_000);

    private final String badgeName;
    private final int goalPrice;

    BadgeType(String badgeName, int goalPrice) {
        this.badgeName = badgeName;
        this.goalPrice = goalPrice;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getGoalPrice() {
        return goalPrice;
    }
}
