package se.tele2.MontyHall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tele2.MontyHall.common.CommonConstant;
import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;
import se.tele2.MontyHall.model.ChosenBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {
    private final BoxService boxService;
    private final HostServiceImpl hostService;
    private final PlayerServiceImpl playerService;

    @Autowired
    public GameServiceImpl(BoxService boxService,
                           HostServiceImpl hostServiceImpl,
                           PlayerServiceImpl playerService) {
        this.boxService = boxService;
        this.hostService = hostServiceImpl;
        this.playerService = playerService;
    }

    @Override
    public ChosenBox run() throws InvalidContentException {
        List<Box> prepareBoxes =
                boxService.prepareBoxes();
        Box playersFirstChoice =
                playerService.selectFromBoxes(prepareBoxes);
        Box revealedBox =
                hostService.revealTheGoatBoxFromTheLeftBoxes(prepareBoxes, playersFirstChoice);
        Box suggestedBox =
                hostService.suggestAnotherBox(prepareBoxes, playersFirstChoice, revealedBox);

        if (playerService.accept()) {
            return ChosenBox.builder()
                    .box(suggestedBox)
                    .acceptToChange(true)
                    .success((suggestedBox.getBoxContentType().equals(BoxContentType.CAR)))
                    .build();
        } else {
            return ChosenBox.builder()
                    .box(playersFirstChoice)
                    .acceptToChange(false)
                    .success((playersFirstChoice.getBoxContentType().equals(BoxContentType.CAR)))
                    .build();
        }
    }

    @Override
    public Map<String, Double> simulate(Integer times) throws InvalidContentException {
        Map<String, Double> result = new HashMap<>();
        ChosenBox chosenBox;

        int changeChoiceSuccess = 0;
        int keepChoiceSuccess = 0;
        int changeChoiceFail = 0;
        int keepChoiceFail = 0;

        for (int i = 0; i < times; i++) {
            chosenBox = run();
            if (chosenBox.getSuccess()) {
                if (chosenBox.getAcceptToChange()) {
                    changeChoiceSuccess++;
                } else {
                    keepChoiceSuccess++;
                }
            } else {
                if (chosenBox.getAcceptToChange()) {
                    changeChoiceFail++;
                } else {
                    keepChoiceFail++;
                }
            }
        }
        result.put(CommonConstant.CHANGE_CHOICE_SUCCESS, changeChoiceSuccess * 100.0 / times);
        result.put(CommonConstant.KEEP_CHOICE_SUCCESS, keepChoiceSuccess * 100.0 / times);
        result.put(CommonConstant.CHANGE_CHOICE_FAIL, changeChoiceFail * 100.0 / times);
        result.put(CommonConstant.KEEP_CHOICE_FAIL, keepChoiceFail * 100.0 / times);

        return result;
    }

}