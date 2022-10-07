package com.nhn.valid.validator;

import com.nhn.repository.CandidateRepository;
import com.nhn.valid.ExistingCandidateId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingCandidateIdValidator implements ConstraintValidator<ExistingCandidateId, Integer> {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public boolean isValid(Integer candidateId, ConstraintValidatorContext constraintValidatorContext) {
        return !candidateRepository.existsById(candidateId);
    }

}
