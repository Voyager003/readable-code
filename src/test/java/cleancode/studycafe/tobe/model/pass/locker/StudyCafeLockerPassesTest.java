package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudyCafeLockerPassesTest {

    @Test
    @DisplayName("동일 기간과 유형의 동일한 종류의 이용권이 있는 경우 테스트")
    void lockerPassesTest() {
        /*
         * given : 같은 이용 기간을 가진 고정석(FIXED) 이용권과 락커 패스 생성
         * when : 같은 이용권 유형과 같은 기간의 락커 이용권 요청하면
         * then : 락커 이용권이 존재
         */
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700_000, 0.15);
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of(List.of(lockerPass));

        Optional<StudyCafeLockerPass> result = lockerPasses.findLockerPassBy(seatPass);

        assertThat(result).isPresent();
    }
}
