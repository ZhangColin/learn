package chapter01;

import annotaions.GuardedBy;
import annotaions.ThreadSafe;

@ThreadSafe
public class Sequence {
    @GuardedBy("this")
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
