package se.tele2.MontyHall.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    PlayerServiceImpl sut;

    @RepeatedTest(100)
    void should_guess_a_number_from_the_list() {
        //Given
        List<Integer> numbers = List.of(4, 3, 0, 5, 6, 100, 50);
        //When
        int actual = sut.selectFromNumbers(numbers);
        System.out.println(actual);
        //Then
        assertThat(actual, is(in(numbers)));
    }

}