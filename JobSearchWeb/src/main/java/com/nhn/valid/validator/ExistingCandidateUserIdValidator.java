package com.nhn.valid.validator;

import com.nhn.common.Constant;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.valid.ExistingCandidateUserId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ExistingCandidateUserIdValidator implements ConstraintValidator<ExistingCandidateUserId, Integer> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Integer candidateUserId, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> candidateUser = userRepository.findById(candidateUserId);
        return candidateUser.isPresent() && candidateUser.get().getRole().equals(Constant.USER_ROLE.CANDIDATE);
    }

}
