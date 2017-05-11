package chapter01;

import annotaions.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNexe() {
        return value++;
    }
}
