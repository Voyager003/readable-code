package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class StudyCafeSeatPassesTest {

    @Test
    @DisplayName("이용권을 요청 후 예상 이용권 반환 테스트")
    void CafeSeatPassesTest() {
        /*
         * given : 이용권 목록을 생성하고
         * when : 고정석 이용권을 요청하면
         * then : 고정석 이용권 반환을 기대
         */
        StudyCafeSeatPass seatPassHourly = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0);
        StudyCafeSeatPass seatPassWeekly = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);
        StudyCafeSeatPass seatPassFixed = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeSeatPasses seatPasses = StudyCafeSeatPasses.of(List.of(seatPassHourly, seatPassWeekly, seatPassFixed));

        List<StudyCafeSeatPass> result = seatPasses.findPassBy(StudyCafePassType.FIXED);

        assertThat(result.get(0)).isEqualTo(seatPassFixed);
    }
}
