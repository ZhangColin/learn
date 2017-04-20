package com.cartisan.java8.examples.chapter5;

import org.junit.Test;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StringCollectorTest {
    @Test
    public void testIdentityConstraint() {
        StringCollector collector = new StringCollector(", ", "<!--", "-->");
        Supplier<StringCombiner> supplier = collector.supplier();
        BiConsumer<StringCombiner, String> accumulator = collector.accumulator();
        BinaryOperator<StringCombiner> combiner = collector.combiner();
        Function<StringCombiner, String> finisher = collector.finisher();

        StringCombiner stringCombiner1 = supplier.get();
        accumulator.accept(stringCombiner1, "one");
        accumulator.accept(stringCombiner1, "two");
        String result1 = finisher.apply(stringCombiner1);

        StringCombiner stringCombiner2 = supplier.get();
        accumulator.accept(stringCombiner2, "one");
        accumulator.accept(stringCombiner2, "two");
        stringCombiner2 = combiner.apply(stringCombiner2, supplier.get());
        String result2 = finisher.apply(stringCombiner2);

        assertEquals(result1, result2);
    }

    @Test
    public void testAssociativityConstraint() {
        StringCollector collector = new StringCollector(", ", "<!--", "-->");
        Supplier<StringCombiner> supplier = collector.supplier();
        BiConsumer<StringCombiner, String> accumulator = collector.accumulator();
        BinaryOperator<StringCombiner> combiner = collector.combiner();
        Function<StringCombiner, String> finisher = collector.finisher();

        StringCombiner stringCombiner1 = supplier.get();
        accumulator.accept(stringCombiner1, "one");
        accumulator.accept(stringCombiner1, "two");
        String result1 = finisher.apply(stringCombiner1);

        StringCombiner stringCombiner2 = supplier.get();
        accumulator.accept(stringCombiner2, "one");
        StringCombiner stringCombiner3 = supplier.get();
        accumulator.accept(stringCombiner3, "two");
        String result2 = finisher.apply(combiner.apply(stringCombiner2, stringCombiner3));

        assertEquals(result1, result2);
    }

    @Test
    public void testCollectEmpty() {
        Stream<String> stream = Stream.of();
        String result = stream.collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!---->", result);
    }

    @Test
    public void testCollectSimple() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        String result = stream.collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!--one, two, three, four-->", result);
    }

    @Test
    public void testCollectPrallel() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        String result = stream.parallel().collect(new StringCollector(", ", "<!--", "-->"));
        assertEquals("<!--one, two, three, four-->", result);
    }

    @Test
    public void testCollectPrallelLargeDataSet() {
        List<String> data = IntStream.iterate(1, i->i+1)
                .mapToObj(Integer::toString)
                .limit(1000)
                .collect(Collectors.toList());

        String simpleresult = data.stream().collect(new StringCollector(", ", "<!--", "-->"));
        String parallelResult = data.parallelStream().collect(new StringCollector(", ", "<!--","-->"));
        assertEquals(simpleresult, parallelResult);
    }

}
