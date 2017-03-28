package chapter05;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            Class cl = Class.forName(name);
            printModifiers(Modifier.toString(cl.getModifiers()));
            Class supercl = cl.getSuperclass();
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class) {
                System.out.print(" extends " + supercl.getName());
            }
            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethod(cl);
            System.out.println();
            printFields(cl);
            System.out.println("}");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.print("    ");
            printModifiers(Modifier.toString(constructor.getModifiers()));
            System.out.print(name + "(");

            Class[] paramTypes = constructor.getParameterTypes();
            printParameters(paramTypes);

            System.out.println(");");
        }
    }

    public static void printMethod(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Class returnType = method.getReturnType();
            String name = method.getName();

            System.out.print("    ");
            printModifiers(Modifier.toString(method.getModifiers()));
            System.out.print(returnType.getName() + " " + name + "(");

            Class[] paramTypes = method.getParameterTypes();
            printParameters(paramTypes);

            System.out.println(");");
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(field.getModifiers());
            printModifiers(modifiers);
            System.out.println(type.getName() + " " + name + ";");
        }
    }

    private static void printParameters(Class[] paramTypes) {
        for (int i = 0; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(paramType.getName());
        }
    }

    private static void printModifiers(String modifiers) {
        if (modifiers.length() > 0) {
            System.out.print(modifiers + " ");
        }
    }
}
