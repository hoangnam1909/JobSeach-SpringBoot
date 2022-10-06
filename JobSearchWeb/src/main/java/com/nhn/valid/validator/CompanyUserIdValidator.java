package com.nhn.valid.validator;

import com.nhn.common.Constant;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyUserId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CompanyUserIdValidator implements ConstraintValidator<CompanyUserId, Integer> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> companyUser = userRepository.findById(value);
        return companyUser.map(user -> user.getRole().equals(Constant.USER_ROLE.COMPANY)).orElse(false);
    }

}
