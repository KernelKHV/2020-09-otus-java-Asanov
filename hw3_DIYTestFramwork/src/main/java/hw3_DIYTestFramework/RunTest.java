package hw3_DIYTestFramework;

import hw3_DIYTestFramework.annotations.*;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunTest {
    private int testPassed;
    private int cntTest;
    private int testFail;

    private List<Method> beforeMethod;
    private List<Method> afterMethod;
    private Constructor constructor;

    public RunTest() {
    }

    private List<Method> getMethodAnnotation(Method[] methods, Class<? extends Annotation> annotations) {
        return Arrays.stream(methods)
                .filter(x -> x.isAnnotationPresent(annotations))
                .collect(Collectors.toList());
    }


    public void runTesting(String testClass) {
        try {
            Class<?> clazz = Class.forName(testClass);
            this.constructor = clazz.getDeclaredConstructor();
            Method[] methods = clazz.getDeclaredMethods();
            List<Method> methodTests = getMethodAnnotation(methods, Test.class);
            this.beforeMethod = getMethodAnnotation(methods, Before.class);
            this.afterMethod = getMethodAnnotation(methods, After.class);
            for (Method methodTest : methodTests) {
                this.cntTest++;
                invokeTest(methodTest);
            }
        } catch (ClassNotFoundException | NoSuchMethodException ex) {
            System.out.println("Тест не найден");
            this.testFail++;
        }
    }

    private void invokeTest(Method method) {
        try {
            Object constr = this.constructor.newInstance();
            for (Method befMethod : this.beforeMethod) {
                befMethod.invoke(constr);
            }
            method.invoke(constr);
            for (Method aftMethod : this.afterMethod) {
                aftMethod.invoke(constr);
            }
            this.testPassed++;
        } catch (Exception ex) {
            this.testFail++;
        }
    }

    public void printResult() {
        System.out.println("---Result---");
        System.out.println("Passed: " + this.testPassed);
        System.out.println("Failure: " + this.testFail);
        System.out.println("Count: " + this.cntTest);
        System.out.println("---The End---");
    }

}
