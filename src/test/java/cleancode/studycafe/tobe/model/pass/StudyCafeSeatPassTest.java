package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudyCafeSeatPassTest {

    @Test
    @DisplayName("시간 단위 이용권 테스트")
    void hourlyPassTest() {
        /*
         * given : 시간(HOURLY) 단위 이용권을 생성
         * when : 사물함 이용 가능 여부를 확인
         * then : 시간 단위 이용권 사용자는 사물함을 사용할 수 없으므로 true
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0);

        boolean cannotUseLocker = seatPass.cannotUseLocker();

        assertThat(cannotUseLocker).isTrue();
    }

    @Test
    @DisplayName("고정석 단위 이용권 테스트")
    void fixedPassTest() {
        /*
         * given : 고정(FIXED) 이용권을 생성
         * when : 사물함 이용 가능 여부를 확인
         * then : 고정석 이용권 용자는 사물함을 사용할 수 있으므로 false
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);

        boolean cannotUseLocker = seatPass.cannotUseLocker();

        assertThat(cannotUseLocker).isFalse();
    }

    @Test
    @DisplayName("이용권을 생성 후, 이용권 종합 정보 테스트")
    void passTotalTest() {
        /*
         * given : 주간(WEEKLY) 이용권을 생성
         * when : 이용권 타입, 대여 기간, 가격, 할인 가격이 주어졌을 때
         * then : 주어진 조건에 맞는 객체가 생성되었는지 검증
         */
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);

        StudyCafePassType passType = seatPass.getPassType();
        int duration = seatPass.getDuration();
        int price = seatPass.getPrice();
        int discountPrice = seatPass.getDiscountPrice();

        assertThat(passType).isEqualTo(StudyCafePassType.WEEKLY);
        assertThat(duration).isEqualTo(12);
        assertThat(price).isEqualTo(400_000);
        assertThat(discountPrice).isEqualTo(60_000);
    }
}
