package com.nhn.valid;

import com.nhn.valid.validator.ExistingCompanyIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingCompanyIdValidator.class)
public @interface ExistingCompanyId {

    public String message() default "Could not find company with id = ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
