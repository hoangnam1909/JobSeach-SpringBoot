package com.nhn.valid.validator;

import com.nhn.repository.JobRepository;
import com.nhn.valid.ExistingJobId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingJobIdValidator implements ConstraintValidator<ExistingJobId, Integer> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(Integer jobId, ConstraintValidatorContext constraintValidatorContext) {
        return !jobRepository.existsById(jobId);
    }

}
