package hw5_AOP;

public class TestLogV2 implements TestLog {
    @Override
    public void testLogFunc(String param, int param2) {
        System.out.println(String.format("Вызов метода testLogFunc без логирования, параметры: %s, %d" ,param,param2));
    }

    @Override
    public void testLogFunc() {
        System.out.println("Вызов метода testLogFunc без логирования, без параметров");
    }

    @Override
    public void testLogFunc(int param, String param2) {
        System.out.println(String.format("Вызов метода testLogFunc без логирования, параметры:%d, %s" ,param,param2));
    }
}
