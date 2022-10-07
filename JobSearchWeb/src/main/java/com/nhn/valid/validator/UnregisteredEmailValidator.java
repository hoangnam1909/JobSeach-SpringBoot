package com.nhn.valid.validator;

import com.nhn.repository.UserRepository;
import com.nhn.valid.UnregisteredEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnregisteredEmailValidator implements ConstraintValidator<UnregisteredEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(email);
    }

}
