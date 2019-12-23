package com.walker.lambda;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author walker
 * @date 2019/5/17
 */
public class LambdaDemo {
    static class Student {
        private String name;
        private double score;

        public Student() {
        }

        public Student(String name, double score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student("zhangsan", 98d),
                new Student("lisi", 89d), new Student("wangwu", 98d));
        List<Student> result = filter(students, student -> student.getScore() > 90);
        System.out.println(JSON.toJSONString(result));
        List<String> names = map(students, Student::getName);
        System.out.println(JSON.toJSONString(names));
        List<Student> upperName = map(students, student -> new Student(student.getName().toUpperCase(), student.getScore()));
        System.out.println(JSON.toJSONString(upperName));
        foreach(students, student -> student.setName(student.getName().toUpperCase()));
        System.out.println(JSON.toJSONString(students));
        students.sort(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getName));
        System.out.println(JSON.toJSONString(students));
    }

    public static <E> List<E> filter(List<E> list, Predicate<E> predicate) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (predicate.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> retList = new ArrayList<>(list.size());
        for (T t : list) {
            retList.add(mapper.apply(t));
        }
        return retList;
    }

    public static <E> void foreach(List<E> list, Consumer<E> consumer) {
        for (E e : list) {
            consumer.accept(e);
        }
    }
}
