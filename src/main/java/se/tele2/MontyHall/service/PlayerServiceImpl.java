package se.tele2.MontyHall.service;

import org.springframework.stereotype.Service;
import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.model.Box;

import java.util.List;

@Service
public class PlayerServiceImpl {

    public Box selectFromBoxes(List<Box> boxes) {
        return Utility.getARandomBoxFrom(boxes);
    }

    public int selectFromNumbers(List<Integer> numbers) {
        return Utility.getARandomNumberFrom(numbers.toArray(Integer[]::new));
    }

    public boolean accept() {
        return Utility.getARandomAnswer();
    }
}
