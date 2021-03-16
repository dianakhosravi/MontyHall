package se.tele2.MontyHall.service;

import org.springframework.stereotype.Service;
import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.List;
import java.util.stream.Collectors;

import static se.tele2.MontyHall.common.Utility.exclude;
import static se.tele2.MontyHall.common.Utility.getARandomNumberFrom;

@Service
public class BoxServiceImpl implements BoxService {

    @Override
    public List<Box> prepareBoxes() {

        Integer[] numbers = {1, 2, 3};
        int numberForCar = getARandomNumberFrom(numbers);
        List<Integer> numbersForEmpty = exclude(numberForCar, numbers);

        Box boxWithCar = createBox(numberForCar, BoxContentType.CAR);
        Box emptyBox1 = createBox(numbersForEmpty.get(0), BoxContentType.EMPTY);
        Box emptyBox2 = createBox(numbersForEmpty.get(1), BoxContentType.EMPTY);

        return List.of(boxWithCar, emptyBox1, emptyBox2);
    }

    @Override
    public List<Box> LeftBoxes(List<Box> boxes, Box selectedBox) {
        return boxes.stream()
                .filter(box -> !box.equals(selectedBox))
                .collect(Collectors.toList());
    }

    private Box createBox(int numberOfBox, BoxContentType boxContentType) {
        return Box.builder()
                .boxNumber(numberOfBox)
                .boxContentType(boxContentType)
                .build();
    }
}
