package com.nhn.valid;

import com.nhn.valid.validator.UnregisteredEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UnregisteredEmailValidator.class)
public @interface UnregisteredEmail {

    public String message() default "This email '${validatedValue}' is already in use";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
