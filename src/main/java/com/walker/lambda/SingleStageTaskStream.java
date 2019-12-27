package com.walker.lambda;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * 构建依赖单一阶段的任务流
 * </p>
 *
 * @author mu qin
 * @date 2019/12/26
 */
public class SingleStageTaskStream {
    public static void main(String[] args) {
        // thenRun/thenRunAsync
        Runnable taskA = () -> System.out.println("TaskA");
        Runnable taskB = () -> System.out.println("TaskB");
        Runnable taskC = () -> System.out.println("TaskC");
        CompletableFuture.runAsync(taskA).thenRun(taskB).thenRun(taskC).join();
        // thenAccept/thenApply
        Supplier<String> taskD = () -> "hello";
        Function<String, String> taskE = String::toUpperCase;
        Consumer<String> taskF = t -> System.out.println("consume: " + t);
        CompletableFuture.supplyAsync(taskD).thenApply(taskE).thenAccept(taskF).join();
        // thenCompose
        Function<String, CompletableFuture<String>> taskH = t -> CompletableFuture.supplyAsync(t::toUpperCase);
        Consumer<String> taskJ = t -> System.out.println("consumer: " + t);
        CompletableFuture.supplyAsync(taskD).thenCompose(taskH).thenAccept(taskJ).join();
    }
}
