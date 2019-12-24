package com.walker.lambda;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 分组
 * </p>
 *
 * @author mu qin
 * @date 2019/12/24
 */
public class GroupStreamDemo {

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
        private String grade;
        private double score;
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student("zhangsan", "1", 91d),
                new Student("lisi", "2", 89d), new Student("wangwu", "1", 50d),
                new Student("zhaoliu", "2", 78d), new Student("sunqi", "1", 59d));
        Map<String, List<Student>> groups = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        System.out.println(JSON.toJSONString(groups));
        Map<String, Long> gradeCountMap = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        System.out.println(JSON.toJSONString(gradeCountMap));
        Map<String, Long> wordCountMap = Stream.of("hello", "world", "abc", "hello")
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(JSON.toJSONString(wordCountMap));
        Map<String, Optional<Student>> topStudentMap = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.maxBy(Comparator.comparing(Student::getScore))));
        System.out.println(JSON.toJSONString(topStudentMap));
        Map<String, Student> topStudentMap2 = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getScore)), Optional::get)));
        System.out.println(JSON.toJSONString(topStudentMap2));
        // 分区
        Map<Boolean, List<Student>> bypass = students.stream().collect(Collectors.partitioningBy(t -> t.getScore() >= 60));
        System.out.println(bypass);
        Map<Boolean, Double> avgScoreMap = students.stream().collect(Collectors.partitioningBy(t -> t.getScore() >= 60, Collectors.averagingDouble(Student::getScore)));
        System.out.println(JSON.toJSONString(avgScoreMap));
        // 多级分组
        Map<String, Map<Boolean, List<Student>>> multiGroup = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.partitioningBy(t -> t.getScore() >= 60)));
        System.out.println(JSON.toJSONString(multiGroup));
    }
}
