package com.nhn.valid;

import com.nhn.valid.validator.CompanyJobValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyJobValidator.class)
public @interface CompanyJob {

    public String message() default "This job is not from this company ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
