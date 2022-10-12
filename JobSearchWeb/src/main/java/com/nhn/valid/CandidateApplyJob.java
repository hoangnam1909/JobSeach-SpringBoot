package com.nhn.valid;

import com.nhn.valid.validator.CandidateApplyJobValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CandidateApplyJobValidator.class)
public @interface CandidateApplyJob {

    public String message() default "";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
