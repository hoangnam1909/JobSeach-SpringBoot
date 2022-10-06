package com.nhn.valid;

import com.nhn.valid.validator.CompanyUserIdValidator;
import com.nhn.valid.validator.ValidJobIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyUserIdValidator.class)
public @interface CompanyUserId {

    public String message() default "Invalid company user id";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
