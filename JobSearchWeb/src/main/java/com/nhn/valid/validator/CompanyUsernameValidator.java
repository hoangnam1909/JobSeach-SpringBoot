package com.nhn.valid.validator;

import com.nhn.common.Constant;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyUsernameValidator implements ConstraintValidator<CompanyUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String companyUsername, ConstraintValidatorContext constraintValidatorContext) {

        try {
            User companyUser = userRepository.findUserByUsername(companyUsername);

            if (companyUser == null)
                return false;

            return companyUser.getRole().equals(Constant.USER_ROLE.COMPANY);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
