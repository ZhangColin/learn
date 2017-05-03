package chapter11;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {
    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone275"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone275"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone275"));
        bestPriceFinder.printPricesStream("myPhone275");
    }

    private static void execute(String msg, Supplier<List<String>> supplier) {
        long start = System.nanoTime();
        System.out.println(supplier.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
