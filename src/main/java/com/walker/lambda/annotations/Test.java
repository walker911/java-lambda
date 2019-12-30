package com.walker.lambda.annotations;

import com.walker.lambda.annotations.Test.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/30
 */
public class Test {

    @Data
    @AllArgsConstructor
    static class Student {
        @Label("姓名")
        private String name;
        @Label("出生日期")
        @Format(pattern = "yyyy/MM/dd")
        private LocalDate born;
        @Label("分数")
        private double score;
    }

    public static void main(String[] args) throws Exception {
        Student zhangsan = new Student("zhangsan", LocalDate.of(1990, 12, 12), 80.9d);
        System.out.println(SimpleFormatter.format(zhangsan));
    }
}
