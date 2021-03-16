package se.tele2.MontyHall.common;

import se.tele2.MontyHall.model.Box;
import se.tele2.MontyHall.model.BoxContentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Utility {

    public static int getARandomNumberFrom(Integer... numbers) {
        Random random = new Random();
        int randomIndex = random.nextInt(numbers.length);
        return numbers[randomIndex];
    }

    public static Box getARandomBoxFrom(List<Box> boxes) {
        Random random = new Random();
        int randomIndex = random.nextInt(boxes.size());
        return boxes.get(randomIndex);
    }

    public static Boolean getARandomAnswer() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static List<Integer> exclude(int excludedNumber, Integer... numbers) {
        return Arrays.stream(numbers)
                .filter(b -> !(b.equals(excludedNumber)))
                .collect(Collectors.toList());
    }

    public static List<Box> exclude(List<Box> boxes, Box excludedBox) {
        return boxes.stream()
                .filter(b -> !b.equals(excludedBox))
                .collect(Collectors.toList());
    }

    public static List<Box> prepareBoxes(String... types) {
        List<Box> boxes = new ArrayList<>();
        AtomicInteger index = new AtomicInteger(1);

        Arrays.stream(types)
                .filter(Objects::nonNull)
                .forEach(type -> boxes.add(
                        Box.builder()
                                .boxNumber(index.getAndIncrement())
                                .boxContentType(BoxContentType.valueOf(type))
                                .build())
                );
        return boxes;
    }

    public static List<Box> getReadyBoxesRandomly() {
        List<String> contentTypes = List.of(CommonConstant.EMPTY, CommonConstant.EMPTY, CommonConstant.CAR);
        List<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        Collections.shuffle(integers);

        return prepareBoxes(
                contentTypes.get(integers.get(0)),
                contentTypes.get(integers.get(1)),
                contentTypes.get(integers.get(2))
        );
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
