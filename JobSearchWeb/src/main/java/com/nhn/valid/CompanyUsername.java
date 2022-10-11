package com.nhn.valid;

import com.nhn.valid.validator.CompanyUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyUsernameValidator.class)
public @interface CompanyUsername {

    public String message() default "Could not find company user with username = '${validatedValue}'";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
