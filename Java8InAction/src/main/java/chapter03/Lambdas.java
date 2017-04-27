package chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Lambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello!");
        r.run();

        List<Apple> inventory = Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples = filter(inventory, apple -> "green".equals(apple.getColor()));
        System.out.println(greenApples);

        Comparator<Apple> c = (apple1, apple2) -> apple1.getWeight().compareTo(apple2.getWeight());

        inventory.sort(c);
        System.out.println(inventory);
    }


    public static List<Apple> filter(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple { color='" + color + "', weight='" + weight + "'}";
        }
    }

    interface ApplePredicate{
        boolean test(Apple apple);
    }
}
