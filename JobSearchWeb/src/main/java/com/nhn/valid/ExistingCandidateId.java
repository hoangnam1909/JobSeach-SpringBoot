package com.nhn.valid;

import com.nhn.valid.validator.ExistingCandidateIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingCandidateIdValidator.class)
public @interface ExistingCandidateId {

    public String message() default "Could not find candidate with id = ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
