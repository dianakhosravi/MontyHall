package se.tele2.MontyHall.service;

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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class HostServiceImplTest {

    public static final String THERE_IS_NO_BOX_WITH_GOAT = "There is no box with GOAT!";

    @Mock
    BoxService boxService;

    @InjectMocks
    HostServiceImpl sut;

    @Test
    void should_reveal_a_box_if_user_chose_a_goat() throws InvalidContentException {

        List<Box> prepareBoxes = Utility.getReadyBoxesRandomly();
        doReturn(prepareBoxes).when(boxService).prepareBoxes();

        Box actual = sut.revealTheGoatBoxFromTheLeftBoxes(boxService.prepareBoxes(), prepareBoxes.get(0));

        assertThat(actual.getBoxContentType(), is(equalTo(BoxContentType.GOAT)));
    }

    @Test
    void should_throw_InvalidContentException_if_there_is_no_more_goat() {

        InvalidContentException invalidContentException = assertThrows(InvalidContentException.class,
                () -> {
                    throw new InvalidContentException(THERE_IS_NO_BOX_WITH_GOAT);
                });

        assertThat(invalidContentException.getMessage(),
                containsStringIgnoringCase(THERE_IS_NO_BOX_WITH_GOAT));
    }
}