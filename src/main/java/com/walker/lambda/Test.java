package com.walker.lambda;

import java.util.stream.Stream;

/**
 * @author walker
 * @date 2019/5/15
 */
public class Test {
    public static void main(String[] args) {
        Stream.iterate(1, t -> t + 2).limit(100).forEach(System.out::println);
    }
}
