package christmas.controller;

import christmas.constants.exception.InputException;
import christmas.domain.vo.BadgeType;
import christmas.dto.BenefitDto;
import christmas.dto.DiscountDto;
import christmas.dto.OrdersDto;
import christmas.dto.VisitDateDto;
import christmas.service.CalculateService;
import christmas.service.DiscountService;
import christmas.util.format.BadgeFormatUtil;
import christmas.util.format.OrdersFormatUtil;
import christmas.util.parser.DateParserUtil;
import christmas.util.format.DiscountFormatUtil;
import christmas.util.parser.OrderParserUtil;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CalculateService calculateService;
    private final DiscountService discountService;

    private VisitDateDto visitDateDto;
    private BenefitDto benefitDto;
    private OrdersDto ordersDto;
    private DiscountDto discountDto;

    public EventPlannerController(InputView inputView, OutputView outputView,
        CalculateService calculateService, DiscountService discountService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculateService = calculateService;
        this.discountService = discountService;
    }

    public void run() {
        outputEventPlannerGuide();
        runUntilNoException(inputVisitDate());
        runUntilNoException(inputOrder());
        outputEventBenefits();
        outputPreTotalPrice();
        outputGiftAvailable();
        outputDiscountResult();
        outputTotalDiscoutPrice();
        outputTotalPriceAfterDiscount();
        outputEventBadgeType();
    }

    private void runUntilNoException(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (InputException e) {
                outputView.printResult(e.getMessage());  // 예외 메시지 출력
            }
        }
    }

    private void outputEventPlannerGuide() { // 안내 문구 출력
        outputView.printHeaderNotice();
    }

    private Runnable inputVisitDate() { // 방문 날짜를 입력 받는다.
        return () -> {
            String date = inputView.inputVisitDate();
            visitDateDto = DateParserUtil.parseVisitDate(date);
        };
    }

    private Runnable inputOrder() { // 주문 메뉴와 개수를 입력 받는다.
        return () -> {
            String inputOrder = inputView.inputOrder();
            ordersDto = OrderParserUtil.parseOrder(inputOrder, visitDateDto);
        };
    }

    private void outputEventBenefits() { // 혜택 출력
        outputView.printHeaderEventBenefits(); // 안내 문구 출력
        String ordersList = OrdersFormatUtil.formatOrdersList(ordersDto);
        outputView.printOrderDetails(ordersList); // 주문 메뉴 출력
    }

    private void outputPreTotalPrice() { // 할인 전 총주문 금액 출력
        benefitDto = calculateService.calculateBenefits(ordersDto);
        outputView.printPreTotalPrice(benefitDto.preTotalPrice());
    }

    private void outputGiftAvailable() { // 증정 품복 부여 여부 출력
        String giftMessage = benefitDto.getGiftMessage();
        outputView.printIsGiftAvailable(giftMessage);
    }

    private void outputDiscountResult() { // 할인 혜택 내역 결과 출력
        discountDto = discountService.createDiscountDto(ordersDto, benefitDto);
        String discountMessage = DiscountFormatUtil.formatDiscountResults(discountDto.discounts());
        outputView.printDiscoutResults(discountMessage);
    }

    private void outputTotalDiscoutPrice() { // 총 혜택 금액 출력
        String totalDiscountMessage = DiscountFormatUtil.formatTotalDiscount(discountDto);
        outputView.printTotalDiscountPrice(totalDiscountMessage);
    }

    private void outputTotalPriceAfterDiscount() { // 할인 후 예상 금액 출력
        String totalPriceMessage = DiscountFormatUtil.formatTotalPriceAfterDiscount(ordersDto,
            discountDto);
        outputView.printTotalPriceAfterDiscount(totalPriceMessage);
    }

    private void outputEventBadgeType() { // 12월 이벤트 배지 출력
        BadgeType badgeType = calculateService.calculateBadgeType(discountDto);
        String badgeTypeMessage = BadgeFormatUtil.formatBadgeType(badgeType);
        outputView.printBadgeType(badgeTypeMessage);
    }
}
