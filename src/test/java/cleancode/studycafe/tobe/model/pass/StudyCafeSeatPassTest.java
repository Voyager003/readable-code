package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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



}
