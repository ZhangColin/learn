package chapter05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> tr2011 = transactions.stream().filter(transaction -> transaction.getYear()==2011)
                .sorted(comparing(Transaction::getValue)).collect(toList());
        System.out.println(tr2011);

        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList());
        System.out.println(cities);

        List<Trader> traders = transactions.stream().map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge")).distinct()
                .sorted(comparing(Trader::getName)).collect(toList());
        System.out.println(traders);

        String traderStr = transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted().reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        boolean milanBased = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        transactions.stream().map(Transaction::getTrader).filter(trader -> trader.getCity().equals("Milan"))
                .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);

        int highestValue = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        System.out.println(highestValue);
    }


}
