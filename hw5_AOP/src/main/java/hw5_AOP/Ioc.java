package hw5_AOP;

import hw5_AOP.Annotation.Log;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Ioc {
    private Ioc() {
    }

    public static TestLog createProxyClass(TestLog testLog) {
        InvocationHandler handler = new DemoInvocationHandler(testLog);
        return (TestLog) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLog.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLog testLog;
        private final Set<Method> testMethods;

        DemoInvocationHandler(TestLog testLog) {
            this.testLog = testLog;
            testMethods = Arrays.stream(testLog.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(Log.class))
                    .collect(Collectors.toSet());
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (testMethods.stream()
                    .anyMatch( testingM->
                            testingM.getName().equals(method.getName())
                                    &&  Arrays.equals(testingM.getParameterTypes(), method.getParameterTypes())
                    )
            )
                System.out.println("executed method: " + method.getName() + ", param: " + Arrays.toString(args).replaceAll("[\\[\\]]", ""));
            return method.invoke(testLog, args);
        }

    }
}
