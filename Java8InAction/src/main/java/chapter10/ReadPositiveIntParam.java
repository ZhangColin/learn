package chapter10;

import java.util.Optional;
import java.util.Properties;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

public class ReadPositiveIntParam {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        System.out.println(readDurationImperative(properties, "a"));
        System.out.println(readDurationImperative(properties, "b"));
        System.out.println(readDurationImperative(properties, "c"));
        System.out.println(readDurationImperative(properties, "d"));

        System.out.println(readDurationWithOptional(properties, "a"));
        System.out.println(readDurationWithOptional(properties, "b"));
        System.out.println(readDurationWithOptional(properties, "c"));
        System.out.println(readDurationWithOptional(properties, "d"));
    }
    public static int readDurationImperative(Properties properties, String name) {
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {

            }
        }

        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::s2i)
                .filter(i -> i > 0).orElse(0);
    }

    private static Optional<Integer> s2i(String s) {
        try {
            return of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return empty();
        }
    }
}
