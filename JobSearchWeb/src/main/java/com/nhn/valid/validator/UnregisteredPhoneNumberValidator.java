package com.nhn.valid.validator;

import com.nhn.repository.UserRepository;
import com.nhn.valid.UnregisteredPhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnregisteredPhoneNumberValidator implements ConstraintValidator<UnregisteredPhoneNumber, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByPhone(phone);
    }

}
