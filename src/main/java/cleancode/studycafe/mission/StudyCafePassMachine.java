package cleancode.studycafe.mission;

import cleancode.studycafe.mission.exception.AppException;
import cleancode.studycafe.mission.io.InputHandler;
import cleancode.studycafe.mission.io.OutputHandler;
import cleancode.studycafe.mission.io.StudyCafeFileHandler;
import cleancode.studycafe.mission.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler cafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            showMessage();
            StudyCafePass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> outputHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void showMessage() {
        outputHandler.showWelcomeMessage();
        outputHandler.showAnnouncement();
        outputHandler.askPassTypeSelection();
    }

    private StudyCafePass selectPass() {
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();
        List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);
        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        StudyCafePasses allPasses = cafeFileHandler.readStudyCafePasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }
        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);
        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();
            boolean isLockerSelected = outputHandler.askLockerPass(lockerPass);
            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(StudyCafePass pass) {
        StudyCafeLockerPasses allLockerPasses = cafeFileHandler.readLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }
}
