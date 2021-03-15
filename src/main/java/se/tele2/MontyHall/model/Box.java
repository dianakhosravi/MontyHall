package se.tele2.MontyHall.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Box {

    private int boxNumber;
    private BoxContentType boxContentType;
}
