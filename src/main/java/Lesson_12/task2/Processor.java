package Lesson_12.task2;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Processor {
    public void processInts(
            Collection<Integer> ints,
            Predicate<Integer> filter,
            Function<Integer, Integer> mapper,
            Consumer<Integer> consumer
    ) {
        for (Integer i : ints) {
            if (filter.test(i)) {
                Integer mapped = mapper.apply(i);
                consumer.accept(mapped);
            }
        }
    }
}