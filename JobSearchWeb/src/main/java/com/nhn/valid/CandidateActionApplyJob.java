package com.nhn.valid;

import com.nhn.valid.validator.CandidateActionApplyJobValidator;
import com.nhn.valid.validator.CompanyActionApplyJobValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CandidateActionApplyJobValidator.class)
public @interface CandidateActionApplyJob {

    public String message() default "This apply job is not from candidate ${validatedValue}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
