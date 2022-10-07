package com.nhn.valid.validator;

import com.nhn.repository.UserRepository;
import com.nhn.valid.UnregisteredUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnregisteredUsernameValidator implements ConstraintValidator<UnregisteredUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByUsername(username);
    }
}
