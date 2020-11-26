package hw4_GC;

import com.sun.management.GarbageCollectionNotificationInfo;
import hw4_GC.bench.Benchmark;
import hw4_GC.bench.BenchmarkMBean;

import javax.management.MBeanServer;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Параметры первого запуска:
-Xms1024m
-Xmx1024m
-Xlog:gc=debug:file=D:\java\otus\homework\hw4_GC\logs\G1GC\gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=D:\java\otus\homework\hw4_GC\logs\G1GC\dump
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100

Параметры второго запуска:
-Xms1024m
-Xmx1024m
-Xlog:gc=debug:file=D:\java\otus\homework\hw4_GC\logs\SerialGC\gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=D:\java\otus\homework\hw4_GC\logs\SerialGC\dump
-XX:+UseSerialGC
-XX:MaxGCPauseMillis=100
 */


public class Hw4App {
    private static long workTime;
    private static List<GarbageCollectionNotificationInfo> gcInfo = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("==== Begin HW4 ====");
        switchOnMonitoring();
        long beginTime = System.currentTimeMillis();

        int elementCounter = 5 * 1000 * 1000;
        int loopCounter = 100000;
        //run(elementCounter,loopCounter);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("hw4_GC:type=Benchmark");

        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(elementCounter);

        try {
            mbean.run();
        } catch (OutOfMemoryError e) {
            System.out.println(e.getMessage());
        }

        workTime = (System.currentTimeMillis() - beginTime) / 1000;
        System.out.println("time it Works: " + workTime + " sec");
        Result(mbean);
        System.out.println("==== THE END HW4 ====");
    }

    private static void Result(BenchmarkMBean mBean) {
        System.out.println("Result:");

        Map<Long, Double> gcTime = gcInfo.stream().collect(Collectors.groupingBy(
                gcNotif -> gcNotif.getGcInfo().getStartTime() / 60_000,
                Collectors.summingDouble(gcDur -> gcDur.getGcInfo().getDuration())
        ));

        Map<String, Long> gcCnt = gcInfo.stream().collect(Collectors.groupingBy(
                GarbageCollectionNotificationInfo::getGcName,
                Collectors.counting()
        ));

        Map<String, Double> gcDuration = gcInfo.stream().collect(Collectors.groupingBy(
                GarbageCollectionNotificationInfo::getGcName,
                Collectors.summingDouble(gcDur -> gcDur.getGcInfo().getDuration())
        ));

        Long SumDur = gcInfo.stream().mapToLong(
                gcTimeDur -> gcTimeDur.getGcInfo().getDuration()
        ).sum();

        System.out.println("Total time work: " + SumDur + "ms");
        System.out.println("Count elements add to list: " + mBean.getElementCounterAdd());
        gcCnt.forEach((name, cnt) -> System.out.println(
                "Name GC: " + name + " count starts: " + cnt + " time works " + gcDuration.get(name)
        ));
        gcTime.forEach((min, ms) -> System.out.println(
                min + " min GC work: " + ms + "ms"
        ));
    }

    /*private static void run(int elementCounter, int loopCounter) throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            List<Object> array = new ArrayList<>();
            for (int i = 0; i < elementCounter; i++) {
                array.add(new String(new char[0]));
            }
            Thread.sleep(10);
        }
    }*/

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    gcInfo.add(info);
                    String gcName = info.getGcName();
                    String gcAction = info.getGcAction();
                    String gcCause = info.getGcCause();

                    long startTime = info.getGcInfo().getStartTime();
                    long duration = info.getGcInfo().getDuration();

                    System.out.println("start:" + startTime + " Name:" + gcName + ", action:" + gcAction + ", gcCause:" + gcCause + "(" + duration + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
