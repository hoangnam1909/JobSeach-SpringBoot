package com.nhn.valid.validator;

import com.nhn.repository.ApplyJobRepository;
import com.nhn.valid.ExistingApplyJobId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingApplyJobIdValidator implements ConstraintValidator<ExistingApplyJobId, Integer> {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Override
    public boolean isValid(Integer applyJobId, ConstraintValidatorContext constraintValidatorContext) {
        return applyJobRepository.existsById(applyJobId);
    }

}
