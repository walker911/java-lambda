package com.walker.lambda;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/25
 */
public class FutureDemo {
    private static Random random = new Random();

    static int delayRandom(int min, int max) {
        int milli = max > min ? random.nextInt(max - min) : 0;
        try {
            Thread.sleep(milli + min);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return milli;
    }

    static Callable<Integer> externalTask = () -> {
        int time = delayRandom(20, 2000);
        return time;
    };

    static Supplier<Integer> externalTask2 = () -> {
        int time = delayRandom(20, 2000);
        return time;
    };

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static Future<Integer> callExternalService() {
        return executor.submit(externalTask);
    }

    public static Future<Integer> callExternalService2() {
        return CompletableFuture.supplyAsync(externalTask2, executor);
    }

    public static Future<Integer> callExternalService3() {
        FutureTask<Integer> future = new FutureTask<>(externalTask);
        new Thread(() -> future.run()).start();
        return future;
    }

    public static Future<Integer> callExternalService4() {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                future.complete(externalTask2.get());
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        return future;
    }

    public static void master() {
        // 执行异步任务
        Future<Integer> asyncRet = callExternalService();
        // 执行其它任务。。。
        // 获取异步任务的结果，处理可能的异常
        try {
            Integer ret = asyncRet.get();
            System.out.println(ret);
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        master();
    }
}
