package christmas.dto;

import christmas.domain.VisitDate;

public record VisitDateDto(int date) {
    public VisitDateDto(VisitDate visitDate) {
        this(visitDate.getDate());
    }
}
