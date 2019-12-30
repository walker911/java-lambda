package com.walker.lambda.annotations;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/30
 */
public class SimpleFormatter {

    public static String format(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Label label = field.getAnnotation(Label.class);
            String name = label != null ? label.value() : field.getName();
            Object val = field.get(obj);
            if (val != null && field.getType() == LocalDate.class) {
                val = formatDate(field, val);
            }
            builder.append(name).append(":").append(val).append("\n");
        }
        return builder.toString();
    }

    private static Object formatDate(Field field, Object value) {
        Format format = field.getAnnotation(Format.class);
        if (format != null) {
            return DateTimeFormatter.ofPattern(format.pattern()).format((TemporalAccessor) value);
        }
        return value;
    }

}
