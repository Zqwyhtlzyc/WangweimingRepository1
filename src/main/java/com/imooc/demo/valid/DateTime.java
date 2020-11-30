package com.imooc.demo.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTime {

    public static enum CheckType {datetime, date, time}

    CheckType type() default CheckType.datetime;

    String pattern() default "yyyy-MM-dd HH:mm:ss";

    String message() default "日期时间字符串格式无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
