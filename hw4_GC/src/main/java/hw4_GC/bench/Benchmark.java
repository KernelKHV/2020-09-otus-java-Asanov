package hw4_GC.bench;

import java.util.ArrayList;
import java.util.List;

public class Benchmark implements BenchmarkMBean {
    private volatile int size = 0;
    private final int loopCounter;
    private int elementCounterAdd;
    List<Long> array = new ArrayList<>();

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    public void run() throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            int local = size;
            for (int i = 0; i < local; i++) {
                array.add(idx * 2L);
                elementCounterAdd++;
            }
            array.subList(0, local / 2).clear();
            Thread.sleep(150);
        }
    }

    public int getSize() {
        return size;
    }

    public int getElementCounterAdd() {
        return elementCounterAdd;
    }

    public void setSize(int size) {
        System.out.println("new size:" + size);
        this.size = size;
    }
}
