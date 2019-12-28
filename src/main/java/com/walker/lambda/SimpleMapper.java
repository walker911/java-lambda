package com.walker.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/27
 */
public class SimpleMapper {

    @Data
    @AllArgsConstructor
    static class Student {
        private String name;
        private int age;
    }

    public static String toString(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append(clazz.getCanonicalName()).append("\n");
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            builder.append(field.getName()).append(":").append(field.get(obj)).append("\n");
        }
        return builder.toString();
    }

    public static Object fromString(String str) throws Exception {
        String[] lines = str.split("\n");
        if (lines.length < 1) {
            throw new IllegalArgumentException();
        }
        Class<?> clazz = Class.forName(lines[0]);
        Object obj = clazz.newInstance();
        if (lines.length > 1) {
            for (int i = 1; i < lines.length; i++) {
                String[] fv = lines[i].split(":");
                if (fv.length != 2) {
                    throw new IllegalArgumentException();
                }
                Field field = clazz.getDeclaredField(fv[0]);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                setFieldValue(field, obj, fv[1]);
            }
        }
        return obj;
    }

    private static void setFieldValue(Field field, Object obj, String value) throws Exception {
        Class<?> type = field.getType();
        if (type == int.class) {
            field.setInt(obj, Integer.parseInt(value));
        } else if (type == byte.class) {
            field.setByte(obj, Byte.parseByte(value));
        } else if (type == short.class){
            field.setShort(obj, Short.parseShort(value));
        } else if (type == long.class) {
            field.setLong(obj, Long.parseLong(value));
        } else if (type == float.class) {
            field.setFloat(obj, Float.parseFloat(value));
        } else if (type == double.class) {
            field.setDouble(obj, Double.parseDouble(value));
        } else if (type == char.class) {
            field.setChar(obj, value.charAt(0));
        } else if (type == boolean.class) {
            field.setBoolean(obj, Boolean.parseBoolean(value));
        } else if (type == String.class) {
            field.set(obj, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(obj, constructor.newInstance(value));
        }
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student("zhangsan", 19);
        String result = toString(student);
        System.out.println(result);
        Student s = (Student) fromString(result);
        System.out.println(s.getAge());
    }
}
