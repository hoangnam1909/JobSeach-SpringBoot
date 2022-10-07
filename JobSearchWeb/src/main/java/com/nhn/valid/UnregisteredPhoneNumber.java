package com.nhn.valid;

import com.nhn.valid.validator.UnregisteredPhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UnregisteredPhoneNumberValidator.class)
public @interface UnregisteredPhoneNumber {

    public String message() default "This phone number '${validatedValue}' is already in use";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
