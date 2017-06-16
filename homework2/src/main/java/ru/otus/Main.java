package ru.otus;

/**
 * Created by r on 6/16/2017.
 */
/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("new String() :" + measureMemory(()-> new String("")) + " bytes");//22
        System.out.println("new Object() :" + measureMemory(()-> new Object()) + " bytes");//19
        System.out.println("new Object[0] :" + measureMemory(()-> new Object[0]) + " bytes");//19  0
        System.out.println("new Object[1] :" + measureMemory(()-> new Object[1]) + " bytes");//27 +8
        System.out.println("new Object[2] :" + measureMemory(()-> new Object[2]) + " bytes");//27  0
        System.out.println("new Object[3] :" + measureMemory(()-> new Object[3]) + " bytes");//36 +8
        System.out.println("new Object[4] :" + measureMemory(()-> new Object[4]) + " bytes");//36  0
        System.out.println("new Object[5] :" + measureMemory(()-> new Object[5]) + " bytes");//43 +8
        System.out.println("new Object[6] :" + measureMemory(()-> new Object[6]) + " bytes");//43  0
        System.out.println("new MyClass() :" + measureMemory(()-> new MyClass()) + " bytes");//27
        System.out.println("new MyClass[1] :" + measureMemory(()-> new MyClass[1]) + " bytes");//27
        System.out.println("new MyClass[2] :" + measureMemory(()-> new MyClass[2]) + " bytes");//27
    }

    public static int measureMemory(Creatable object){
        int numberOfLoops = 5;
        int size = 6_000_000;
        int sleepTime = 500;
        int sum = 0;

        for (int j=0; j<numberOfLoops; j++){
            // System.out.println("Starting the loop " + (j+1) + " out of " + numberOfLoops);

            Runtime runtime = Runtime.getRuntime();
            System.gc();
            long memBefore = runtime.totalMemory() - runtime.freeMemory();
            Object[] objects = new Object[size];
            for(int i = 0; i<size; i++){
                objects[i] = object.createAnotherObject();

            }
            long memAfter = runtime.totalMemory() - runtime.freeMemory();
            sum += (int)((memAfter-memBefore)/size);
            System.gc();

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sum/numberOfLoops;
    }

    private static class MyClass {
        private int i = 0;
        private long l = 1;

    }
}
