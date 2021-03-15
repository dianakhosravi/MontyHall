package se.tele2.MontyHall.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChosenBox {
    private Box box;
    private Boolean acceptToChange;
    private Boolean success;
}
