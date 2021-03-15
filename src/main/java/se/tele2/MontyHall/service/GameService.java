package se.tele2.MontyHall.service;

import se.tele2.MontyHall.exception.InvalidContentException;
import se.tele2.MontyHall.model.ChosenBox;

import java.util.Map;

public interface GameService {

    ChosenBox run() throws InvalidContentException;

    Map<String, Double> simulate(Integer times) throws InvalidContentException;

    String getResultMessageFor(int times) throws InvalidContentException;
}
