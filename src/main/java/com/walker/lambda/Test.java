package com.walker.lambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author walker
 * @date 2019/5/15
 */
public class Test {
    public static void main(String[] args) {
        String msg = "hello";
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(() -> System.out.println(msg));
    }
}
