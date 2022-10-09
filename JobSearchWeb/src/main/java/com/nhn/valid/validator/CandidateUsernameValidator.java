package com.nhn.valid.validator;

import com.nhn.common.Constant;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CandidateUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CandidateUsernameValidator implements ConstraintValidator<CandidateUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String candidateUsername, ConstraintValidatorContext constraintValidatorContext) {
        User candidateUser = userRepository.findUserByUsername(candidateUsername);

        if (candidateUser == null)
            return false;

        return candidateUser.getRole().equals(Constant.USER_ROLE.CANDIDATE);
    }

}
