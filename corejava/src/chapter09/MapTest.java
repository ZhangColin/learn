package chapter09;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> staff = new HashMap<>();
        staff.put("144-25-5464", "Amy Lee");
        staff.put("567-24-2546", "Harry Hacker");
        staff.put("157-62-7935", "Gary Cooper");
        staff.put("456-62-5527", "Francesca Cruz");

        System.out.println(staff);

        staff.remove("567-24-2546");

        staff.put("456-62-5527", "Francesca Miller");

        System.out.println(staff.get("157-62-7935"));

        staff.forEach((k,v)-> System.out.println(String.format("key=%s, value=%s", k,v)));
    }
}
