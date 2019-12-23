package com.walker.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author walker
 * @date 2019/5/18
 */
public class StreamDemo {

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
        private double score;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "def", "hello", "Abc");
        List<String> retList = list.stream().filter(s -> s.length() <= 3).map(String::toLowerCase)
                .distinct().collect(Collectors.toList());
        System.out.println(retList);
        List<Student> students = Arrays.asList(new Student("zhangsan", 98d),
                new Student("lisi", 89d), new Student("wangwu", 98d));
        List<String> above90Names = students.stream().filter(student -> student.getScore() > 90)
                .peek(System.out::println)
                .map(Student::getName).collect(Collectors.toList());
        double sum = students.stream().mapToDouble(Student::getScore).sum();
        System.out.println(sum);
        List<String> lines = Arrays.asList("hello abc", "老马 编程");
        List<String> words = lines.stream().flatMap(line -> Arrays.stream(line.split("\\s+")))
                .collect(Collectors.toList());
        System.out.println(words);
        Student student = students.stream().max(Comparator.comparing(Student::getScore)).get();
        System.out.println(student);
        boolean allPass = students.stream().allMatch(t -> t.getScore() >= 60);
    }
}
