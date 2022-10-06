package com.nhn.valid;

import com.nhn.valid.validator.ValidJobIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidJobIdValidator.class)
public @interface JobId {

    public String message() default "Invalid job id";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
