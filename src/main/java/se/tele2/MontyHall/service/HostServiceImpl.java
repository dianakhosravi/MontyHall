package se.tele2.MontyHall.service;

import org.springframework.stereotype.Service;
import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    @Override
    public Box revealTheEmptyBoxFromTheLeftBoxes(List<Box> boxes, Box playersFirstChoice)
            throws InvalidContentException {

        List<Box> twoLeftBoxes = Utility.exclude(boxes, playersFirstChoice);

        return twoLeftBoxes.stream()
                .filter(box -> !BoxContentType.MONEY.equals(box.getBoxContentType()))
                .findAny()
                .orElseThrow(() -> new InvalidContentException("There is no empty box!"));
    }

    @Override
    public Box suggestAnotherBox(List<Box> boxes, Box playersFirstChoice, Box revealedBox)
            throws InvalidContentException {

        return boxes.stream()
                .filter(box -> !box.equals(playersFirstChoice))
                .filter(box -> !box.equals(revealedBox))
                .findFirst()
                .orElseThrow(() -> new InvalidContentException("There is no empty box!"));
    }

}