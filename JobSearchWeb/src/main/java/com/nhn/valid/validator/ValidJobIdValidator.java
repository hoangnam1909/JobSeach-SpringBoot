package com.nhn.valid.validator;

import com.nhn.entity.Job;
import com.nhn.repository.JobRepository;
import com.nhn.valid.JobId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class ValidJobIdValidator implements ConstraintValidator<JobId, Integer> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Job> job = jobRepository.findById(value);
        return job.isPresent();
    }

}
