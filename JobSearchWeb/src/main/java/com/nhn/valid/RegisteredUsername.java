package com.nhn.valid;

import com.nhn.valid.validator.RegisteredUsernameValidator;
import com.nhn.valid.validator.UnregisteredUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = RegisteredUsernameValidator.class)
public @interface RegisteredUsername {

    public String message() default "Could not find user with username = '${validatedValue}'";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
