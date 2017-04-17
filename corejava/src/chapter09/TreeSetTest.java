package chapter09;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Comparator.comparing;

public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 4562));
        parts.add(new Item("Widget", 1234));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        NavigableSet<Item> sortByDescription = new TreeSet<>(comparing(Item::getDescription));

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}
