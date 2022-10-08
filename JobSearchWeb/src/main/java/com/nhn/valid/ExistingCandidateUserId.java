package com.nhn.valid;

import com.nhn.valid.validator.ExistingCandidateUserIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE_USE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ExistingCandidateUserIdValidator.class)
public @interface ExistingCandidateUserId {

    public String message() default "Could not find user has CANDIDATE role with user id = ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
