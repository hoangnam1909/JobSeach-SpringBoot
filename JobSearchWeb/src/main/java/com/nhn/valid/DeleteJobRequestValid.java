package com.nhn.valid;

import com.nhn.valid.validator.DeleteJobRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DeleteJobRequestValidator.class)
public @interface DeleteJobRequestValid {

    public String message() default "This job is not from this company ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
