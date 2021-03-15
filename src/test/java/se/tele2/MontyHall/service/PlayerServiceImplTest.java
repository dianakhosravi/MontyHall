package se.tele2.MontyHall.service;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.model.Box;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    PlayerServiceImpl sut;

    @RepeatedTest(10)
    void should_guess_a_box_from_the_list() {
        List<Box> boxes = Utility.getReadyBoxesRandomly();

        Box actual = sut.selectFromBoxes(boxes);

        assertThat(actual, is(in(boxes)));
    }

}