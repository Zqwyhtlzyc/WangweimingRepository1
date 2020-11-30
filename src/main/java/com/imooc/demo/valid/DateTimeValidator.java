package com.imooc.demo.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator implements ConstraintValidator<DateTime, String> {
    // 校验类型，枚举值datetime/date/time
    private DateTime.CheckType type;
    // 日期时间校验格式
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public void initialize(DateTime constraintAnnotation) {
        type = constraintAnnotation.type();
        dateTimeFormatter = DateTimeFormatter.ofPattern(constraintAnnotation.pattern());
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
     // 如果 value 为空或空串则不进行格式验证，为空验证可以使用
        // @NotBlank @NotNull @NotEmpty
        // 等注解来进行控制，职责分离
        if (value == null || value.trim().equals("")) {
            return true;
        }
        // 格式解析正常则为校验通过，异常则不通过
        try {
            switch (type) {
                case datetime:
                    LocalDateTime.parse(value, dateTimeFormatter);
                    return true;
                case date:
                    LocalDate.parse(value, dateTimeFormatter);
                    return true;
                case time:
                    LocalTime.parse(value, dateTimeFormatter);
                    return true;
                    default:
            return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

