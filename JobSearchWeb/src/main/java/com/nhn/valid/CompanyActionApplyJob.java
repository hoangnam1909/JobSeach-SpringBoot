package com.nhn.valid;

import com.nhn.valid.validator.CompanyActionApplyJobValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyActionApplyJobValidator.class)
public @interface CompanyActionApplyJob {

    public String message() default "This job is not from this company ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
