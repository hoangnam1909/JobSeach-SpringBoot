package com.nhn.valid;

import com.nhn.valid.validator.CandidateUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CandidateUsernameValidator.class)
public @interface CandidateUsername {

    public String message() default "Could not find candidate user with username = '${validatedValue}'";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
