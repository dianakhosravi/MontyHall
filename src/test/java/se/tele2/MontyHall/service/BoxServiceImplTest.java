package se.tele2.MontyHall.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import se.tele2.MontyHall.common.CommonConstant;
import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class BoxServiceImplTest {
    private List<Box> readyBoxes;

    @InjectMocks
    private BoxServiceImpl sut;

    @BeforeEach
    void setup() {
        readyBoxes = sut.prepareBoxes();
    }

    @RepeatedTest(10)
    void should_boxes_contain_only_one_car() {
        int actualSize = (int) readyBoxes.stream()
                .filter(item -> BoxContentType.CAR.equals(item.getBoxContentType()))
                .count();
        assertThat(actualSize, is(equalTo(1)));
    }

    @Test
    void should_not_return_boxes_with_repeated_numbers() {
        //Given

        //When
        List<Integer> actualNumbers = readyBoxes.stream()
                .map(Box::getBoxNumber)
                .collect(Collectors.toList());

        //Then
        assertThat(actualNumbers, is(containsInAnyOrder(3, 1, 2)));
    }

    @Test
    void should_return_left_boxes_when_player_select_one() {
        //Given
        List<Box> boxes = sut.prepareBoxes();
        Box selectedBox = boxes.get(1);

        //When
        List<Box> actual = sut.LeftBoxes(boxes, selectedBox);

        //Then
        assertThat(actual, not(hasItem(selectedBox)));
    }

    @Test
    void selectedBox_should_not_exist_in_leftBoxes() {
        List<Box> prepareBoxes = Utility.prepareBoxes(
                CommonConstant.EMPTY,
                CommonConstant.EMPTY,
                CommonConstant.CAR
        );

        List<Box> actual = sut.LeftBoxes(prepareBoxes, prepareBoxes.get(2));

        assertThat(actual.size(), is(equalTo(2)));
        assertFalse(actual.contains(prepareBoxes.get(2)));

    }
}