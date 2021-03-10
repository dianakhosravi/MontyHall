package se.tele2.MontyHall.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.PrizeType;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//@ExtendWith(MockitoExtension.class)
class BoxServiceImplTest {

//    @Mock
//    private BoxService sut;

    private final BoxService boxService = new BoxServiceImpl();

    /*@Test
    void should_work() {
        doReturn(List.of(Box.builder()
                .boxNumber(1)
                .prizeType(PrizeType.CAR)
                .build())
        ).when(sut).getReadyBoxes();

        List<Box> boxes = sut.getReadyBoxes();
        assertThat("checkIfTheMethodWorks", 1, is(boxes.size()));
    }
*/
    @Test
    void should_return_three_boxes() {
        List<Box> boxes = boxService.getReadyBoxes();
        assertThat(boxes.size(), is(equalTo(3)));
    }

    @RepeatedTest(10)
    void should_boxes_contain_only_one_prize() {
        List<Box> readyBoxes = boxService.getReadyBoxes();
        int expectedNumberOfPrizes = (int) readyBoxes.stream()
                .filter(item -> item.getPrizeType().equals(PrizeType.CAR))
                .count();
        assertThat(expectedNumberOfPrizes, is(equalTo(1)));
    }

    @Test
    void should_not_return_boxes_with_repeated_numbers() {
        List<Box> readyBoxes = boxService.getReadyBoxes();
        List<Integer> actualNumbers = readyBoxes.stream()
                .map(Box::getBoxNumber)
                .collect(Collectors.toList());

        assertThat(actualNumbers, hasItems(3, 1, 2));
    }

}