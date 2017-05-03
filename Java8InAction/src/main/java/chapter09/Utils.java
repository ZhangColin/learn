package chapter09;

import java.util.List;

public class Utils {
    public static void paint(List<Resizable> list) {
        list.forEach(r->r.setAbsoluteSize(42,42));
    }
}
