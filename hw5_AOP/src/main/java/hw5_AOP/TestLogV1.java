package hw5_AOP;

import hw5_AOP.Annotation.Log;

public class TestLogV1 implements TestLog  {

    @Log
    @Override
    public void testLogFunc(String param, int param2) {
        System.out.println(String.format("Лог метода testLogFunc, параметры: %s, %d" ,param,param2));
    }

    @Log
    @Override
    public void testLogFunc() {
        System.out.println("Лог метода testLogFunc вызов без параметров");
    }

    @Override
    public void testLogFunc(int param, String param2) {
        System.out.println(String.format("Вызов метода testLogFunc без логирования, параметры:%d, %s" ,param,param2));
    }
}
