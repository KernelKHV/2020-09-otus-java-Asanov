package hw3_DIYTestFramework;

public class Hw3App {
    public static void main(String[] args) {
        System.out.println("Begin test");
        RunTest run = new RunTest();
        run.runTesting("hw3_DIYTestFramework.TestingClass");
        run.printResult();
    }
}
