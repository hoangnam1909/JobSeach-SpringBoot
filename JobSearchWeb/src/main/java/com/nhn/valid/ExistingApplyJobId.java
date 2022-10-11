package com.nhn.valid;

import com.nhn.valid.validator.ExistingApplyJobIdValidator;
import com.nhn.valid.validator.ExistingJobIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingApplyJobIdValidator.class)
public @interface ExistingApplyJobId {

    public String message() default "Apply job id: ${validatedValue} is not present";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
