package chapter05;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (visited.contains(object)) {
            return "...";
        }
        visited.add(object);
        Class clazz = object.getClass();
        if (clazz == String.class) {
            return (String) object;
        }
        if (clazz.isArray()) {
            String r = clazz.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(object); i++) {
                if (i > 0) {
                    r += ",";
                }
                Object value = Array.get(object, i);
                if (clazz.getComponentType().isPrimitive()) {
                    r += value;
                } else {
                    r += toString(value);
                }
            }
            return r + "}";
        }

        String r = clazz.getName();
        do {
            r+="[";
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.endsWith("[")) {
                        r += ",";
                    }
                    r += field.getName() + "=";
                    try {
                        Class t = field.getType();
                        Object value = field.get(object);
                        if (t.isPrimitive()) {
                            r += value;
                        } else {
                            r += toString(value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            clazz = clazz.getSuperclass();
        } while (clazz!=null);

        return r;
    }
}
