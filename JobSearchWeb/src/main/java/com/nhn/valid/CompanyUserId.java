package com.nhn.valid;

import com.nhn.valid.validator.CompanyUserIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyUserIdValidator.class)
public @interface CompanyUserId {

    public String message() default "Company user id: {validatedValue} is not present";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
