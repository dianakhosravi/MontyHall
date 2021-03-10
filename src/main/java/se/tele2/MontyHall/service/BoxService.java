package se.tele2.MontyHall.service;

import se.tele2.MontyHall.model.Box;

import java.util.List;

public interface BoxService {

    List<Box> getReadyBoxes();
}
