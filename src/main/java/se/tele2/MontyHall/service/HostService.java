package se.tele2.MontyHall.service;

import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.Box;

import java.util.List;

public interface HostService {

    Box revealTheGoatBoxFromTheLeftBoxes(List<Box> boxes, Box playersFirstChoice) throws InvalidContentException;

    Box suggestAnotherBox(List<Box> boxes, Box playersFirstChoice, Box revealedBox) throws InvalidContentException;
}
