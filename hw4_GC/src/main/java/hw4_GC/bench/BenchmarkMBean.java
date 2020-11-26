package hw4_GC.bench;

public interface BenchmarkMBean {
    void run() throws InterruptedException;

    int getSize();

    int getElementCounterAdd();

    void setSize(int size);
}
