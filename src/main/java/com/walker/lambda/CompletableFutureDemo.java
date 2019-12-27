package com.walker.lambda;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/26
 */
public class CompletableFutureDemo {

    private static Random random = new Random();
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    static int delayRandom(int min, int max) {
        int milli = max > min ? random.nextInt(max - min) : 0;
        try {
            Thread.sleep(milli + min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return milli;
    }

    static Supplier<Integer> externalTask = () -> delayRandom(20, 2000);

    public static void main(String[] args) {
        // whenComplete
        CompletableFuture.supplyAsync(externalTask).whenComplete((result, ex) -> {
            if (result != null) {
                System.out.println(result);
            }
            if (ex != null) {
                ex.printStackTrace();
            }
        }).join();
        // handle
        String ret = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("test");
        }).handle((result, ex) -> "hello").join();
        System.out.println(ret);
    }

}
