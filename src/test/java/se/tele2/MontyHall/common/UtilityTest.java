package se.tele2.MontyHall.common;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static se.tele2.MontyHall.common.Utility.*;

class UtilityTest {

    public static final String GOAT = "GOAT";
    public static final String CAR = "CAR";

    @RepeatedTest(10)
    void should_extract_randomly_a_number_from_a_list() {

        Integer[] numbers = {1, 4, 5, 100, 40};

        int actual = getARandomNumberFrom(numbers);

        assertThat(actual, is(oneOf(numbers)));
    }

    @Test
    void should_return_a_list_without_the_excluded_number() {

        Integer[] numbers = {1, 4, 5, 100, 40};

        List<Integer> actual = exclude(5, numbers);

        assertThat(actual, is(containsInAnyOrder(1, 4, 100, 40)));
    }

    @Test
    void should_return_an_empty_list_if_list_includes_only_the_excluded_number() {

        Integer[] numbers = {40};

        List<Integer> actual = exclude(40, numbers);

        assertThat(actual, is(empty()));
    }

    @Test
    void should_return_the_same_list_if_the_excluded_number_does_not_exist() {

        Integer[] numbers = {1, 4, 5, 100, 40};

        List<Integer> actual = exclude(14, numbers);

        assertThat(actual, is(containsInAnyOrder(1, 4, 5, 100, 40)));
    }

    @Test
    void should_return_the_list_of_boxes() {
        //given
        Box box1 = Box.builder().boxNumber(1).boxContentType(BoxContentType.GOAT).build();
        Box box2 = Box.builder().boxNumber(2).boxContentType(BoxContentType.GOAT).build();
        Box box3 = Box.builder().boxNumber(3).boxContentType(BoxContentType.CAR).build();

        List<Box> actual = prepareBoxes(GOAT, GOAT, CAR);

        assertThat(actual, is(containsInAnyOrder(box1, box2, box3)));
    }

    @Test
    void should_return_the_list_of_boxes_with_null() {
        Box box1 = Box.builder().boxNumber(1).boxContentType(BoxContentType.GOAT).build();
        Box box2 = Box.builder().boxNumber(2).boxContentType(BoxContentType.CAR).build();

        List<Box> actual = prepareBoxes(GOAT, null, CAR);
        List<Box> actual_with_null = prepareBoxes(null, null, null);

        assertThat(actual, is(containsInAnyOrder(box1, box2)));
        assertThat(actual_with_null, is(empty()));
    }
}