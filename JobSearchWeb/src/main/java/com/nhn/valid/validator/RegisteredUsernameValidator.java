package com.nhn.valid.validator;

import com.nhn.repository.UserRepository;
import com.nhn.valid.RegisteredUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisteredUsernameValidator implements ConstraintValidator<RegisteredUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsByUsername(username);
    }

}
