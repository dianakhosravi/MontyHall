package se.tele2.MontyHall.service;

import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.PrizeType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoxServiceImpl implements BoxService {
    @Override
    public List<Box> getReadyBoxes() {

        int boxNumberWithPrize = getNumberOfBoxWithPrize();
        List<Integer> collect = getNumberOfBoxesWithoutPrize(boxNumberWithPrize);

        Box boxWithPrize = createBox(boxNumberWithPrize, PrizeType.CAR);
        Box boxWithoutPrize1 = createBox(collect.get(0), PrizeType.GOAT);
        Box boxWithoutPrize2 = createBox(collect.get(1), PrizeType.GOAT);

        return List.of(boxWithPrize, boxWithoutPrize1, boxWithoutPrize2);
    }

    private List<Integer> getNumberOfBoxesWithoutPrize(int boxNumberWithPrize) {
        return Stream.of(1, 2, 3)
                .filter(b -> !(b.equals(boxNumberWithPrize)))
                .collect(Collectors.toList());
    }

    private int getNumberOfBoxWithPrize() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    private Box createBox(int numberOfBox, PrizeType prizeType) {
        return Box.builder()
                .boxNumber(numberOfBox)
                .prizeType(prizeType)
                .build();
    }
}
