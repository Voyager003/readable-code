package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class StudyCafePassOrderTest {

    @Test
    @DisplayName("좌석 이용권 할인 및 총 금액 테스트")
    void CalculateDiscountPriceTest() {
        /*
         * given : 좌석(seat)과 락커(locker)가 주어지고 주문을 생성
         * when : 할인 금액과 총 금액을 계산
         * then : 할인 금액 105,000원과 총 금액 625,000원을 기대
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700_000, 0.15);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        int discountPrice = order.getDiscountPrice();
        int totalPrice = order.getTotalPrice();

        assertThat(discountPrice).isEqualTo(105_000);
        assertThat(totalPrice).isEqualTo(625_000);
    }

    @Test
    @DisplayName("사물함 이용권 없는 좌석 이용권의 총 금액 테스트")
    void CalculateTotalPriceWithoutLockerTest() {
        /*
         * given : 좌석(seat)이 주어지고 주문을 생성
         * when : 총 금액 계산
         * then : 총 금액 135,000원을 기대
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150_000, 0.1);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, null);

        int totalPrice = order.getTotalPrice();

        assertThat(totalPrice).isEqualTo(135_000);
    }

    @Test
    @DisplayName("락커 이용권 유무에 따른 반환 여부 테스트")
    void returnLockerPassWhenAvailableTest() {
        /*
         * given : 좌석(seat)과 락커(locker)가 주어지고 주문을 생성
         * when : 락커 이용권을 유무를 확인
         * then : 고정석이므로 락커 존재를 기대
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700_000, 0.15);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);
        StudyCafePassOrder order = StudyCafePassOrder.of(seatPass, lockerPass);

        Optional<StudyCafeLockerPass> result = order.getLockerPass();

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(lockerPass);
    }
}
