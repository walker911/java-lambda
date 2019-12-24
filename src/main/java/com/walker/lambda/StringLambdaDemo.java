package com.walker.lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/24
 */
public class StringLambdaDemo {
    public static void main(String[] args) {
        String result = Stream.of("abc", "老马", "hello").collect(Collectors.joining(",", "[", "]"));
        System.out.println(result);
    }
}
