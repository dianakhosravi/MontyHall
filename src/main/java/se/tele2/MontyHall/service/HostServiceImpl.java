package se.tele2.MontyHall.service;

import se.tele2.MontyHall.common.Utility;
import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.List;

public class HostServiceImpl implements HostService {

    @Override
    public Box revealTheGoatBoxFromTheLeftBoxes(List<Box> boxes, Box playersFirstChoice)
            throws InvalidContentException {

        List<Box> twoLeftBoxes = Utility.exclude(boxes, playersFirstChoice);

        return twoLeftBoxes.stream()
                .filter(box -> !BoxContentType.CAR.equals(box.getBoxContentType()))
                .findAny()
                .orElseThrow(() -> new InvalidContentException("There is no box with GOAT!"));
    }

    @Override
    public Box suggestAnotherBox(List<Box> boxes, Box playersFirstChoice, Box revealedBox)
            throws InvalidContentException {

        return boxes.stream()
                .filter(box -> !box.equals(playersFirstChoice))
                .filter(box -> !box.equals(revealedBox))
                .findFirst()
                .orElseThrow(() -> new InvalidContentException("There is no box with GOAT!"));
    }


}