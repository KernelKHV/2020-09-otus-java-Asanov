package hw5_AOP;

public class Hw5App {
    public static void main(String[] args) {

        System.out.println("С использованием логгирования");
        TestLog testLog =new TestLogV1();
        TestLog testLogProxy = Ioc.createProxyClass(testLog);
        testLogProxy.testLogFunc("Параметр1",2);
        testLogProxy.testLogFunc();
        testLogProxy.testLogFunc(1,"Параметр2");

        System.out.println("\nБез использования логгирования");
        TestLog testLog2 =new TestLogV2();
        TestLog testLogProxy2 = Ioc.createProxyClass(testLog2);
        testLogProxy2.testLogFunc("Параметр1",3);
        testLogProxy2.testLogFunc();
        testLogProxy2.testLogFunc(3,"Параметр2");
    }
}
