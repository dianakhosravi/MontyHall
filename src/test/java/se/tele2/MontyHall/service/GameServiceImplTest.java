package se.tele2.MontyHall.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.tele2.MontyHall.common.CommonConstant;
import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;
import se.tele2.MontyHall.model.ChosenBox;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class GameServiceImplTest {

    private GameService gameService;
    @Mock
    BoxServiceImpl boxService;
    @Mock
    HostServiceImpl hostService;
    @Mock
    PlayerServiceImpl playerService;

    @InjectMocks
    GameServiceImpl sut;

    @BeforeEach
    void setup(){
        gameService = new GameServiceImpl(
                new BoxServiceImpl(),
                new HostServiceImpl(),
                new PlayerServiceImpl()
        );
    }

    @Test
    void should_run_method_work() throws InvalidContentException {
        //Given
        List<Box> boxList = Utility.getReadyBoxesRandomly();
        Box playersFirstChoice = Box.builder().boxContentType(BoxContentType.CAR).build();
        Box revealedBox = Box.builder().boxContentType(BoxContentType.EMPTY).build();
        Box suggestedBox = Box.builder().boxContentType(BoxContentType.EMPTY).build();

        doReturn(boxList).when(boxService).prepareBoxes();
        doReturn(playersFirstChoice).when(playerService).selectFromBoxes(anyList());
        doReturn(true).when(playerService).accept();
        doReturn(revealedBox).when(hostService).revealTheEmptyBoxFromTheLeftBoxes(boxList, playersFirstChoice);
        doReturn(suggestedBox).when(hostService).suggestAnotherBox(boxList, playersFirstChoice, revealedBox);

        //When/Action
        ChosenBox actual = sut.run();
        ChosenBox expected = ChosenBox.builder()
                .box(suggestedBox)
                .acceptToChange(true)
                .success(false)
                .build();

        //Then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void simulate_the_game_with_100_times() throws InvalidContentException {
        Map<String, Double> actualResult;
        int success = 0;
        int fail = 0;

        for (int i = 0; i < 100; i++) {
            actualResult = gameService.simulate(100);
            if (actualResult.get(CommonConstant.CHANGE_CHOICE_SUCCESS) >
                    actualResult.get(CommonConstant.KEEP_CHOICE_SUCCESS)) {
                success += 1;
            } else {
                fail += 1;
            }
        }
        System.out.println("success " + success);
        System.out.println("fail " + fail);

        assertTrue(success > fail);
    }

}
