package hw4_GC;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

/*
Параметры первого запуска:
-Xms512m
-Xmx512m
-Xlog:gc=debug:file=D:\java\otus\homework\hw4_GC\logs\1\gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=D:\java\otus\homework\hw4_GC\logs\1\dump
-XX:+UseG1GC
-XX:MaxGCPauseMillis=100

Параметры второго запуска:
-Xms512m
-Xmx512m
-Xlog:gc=debug:file=D:\java\otus\homework\hw4_GC\logs\2\gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=D:\java\otus\homework\hw4_GC\logs\2\dump
-XX:+UseSerialGC
-XX:MaxGCPauseMillis=100
 */


public class Hw4App {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==== Begin HW4 ====");
        switchOnMonitoring();
        int elementCounter = 5 * 1000 * 1000;
        int loopCounter = 1000;
        run(elementCounter,loopCounter);
        System.out.println("==== THE END HW4 ====");
    }

    private static void run(int elementCounter, int loopCounter) throws InterruptedException {
        for (int idx = 0; idx < loopCounter; idx++) {
            List<Object> array = new ArrayList<>();
            for (int i = 0; i < elementCounter; i++) {
                array.add(new String(new char[0]));
            }
            Thread.sleep(10);
        }
    }

    private static void switchOnMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
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
