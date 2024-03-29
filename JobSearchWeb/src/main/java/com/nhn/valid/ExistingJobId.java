package com.nhn.valid;

import com.nhn.valid.validator.ExistingJobIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingJobIdValidator.class)
public @interface ExistingJobId {

    public String message() default "Job id: ${validatedValue} is not present";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
