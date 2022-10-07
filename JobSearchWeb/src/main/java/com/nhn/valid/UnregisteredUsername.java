package com.nhn.valid;

import com.nhn.valid.validator.UnregisteredUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UnregisteredUsernameValidator.class)
public @interface UnregisteredUsername {

    public String message() default "This username '${validatedValue}' is already in use";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
