package chapter11.v1;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {
    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", ()->bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", ()->bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", ()->bestPriceFinder.findPricesFuture("myPhone27S"));
        execute("combined USD CompletableFuture v1", ()->bestPriceFinder.findPricesInUsD("myPhone27S"));
        execute("combined USD CompletableFuture v2", ()->bestPriceFinder.findPricesInUSD2("myPhone27S"));
        execute("combined USD CompletableFuture v3", ()->bestPriceFinder.findPricesInUSD3("myPhone27S"));
        execute("java7", ()->bestPriceFinder.findPricesInUSDJava7("myPhone27S"));
    }

    private static void execute(String msg, Supplier<List<String>> supplier) {
        long start = System.nanoTime();
        System.out.println(supplier.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
