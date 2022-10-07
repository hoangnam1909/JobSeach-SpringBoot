package com.nhn.valid;

import com.nhn.valid.validator.ExistingJobIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingJobIdValidator.class)
public @interface ExistingJobId {

    public String message() default "Could not find job with id = ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
