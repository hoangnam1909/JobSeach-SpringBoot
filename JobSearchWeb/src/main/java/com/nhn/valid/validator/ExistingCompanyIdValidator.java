package com.nhn.valid.validator;

import com.nhn.repository.CompanyRepository;
import com.nhn.valid.ExistingCompanyId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingCompanyIdValidator implements ConstraintValidator<ExistingCompanyId, Integer> {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean isValid(Integer companyId, ConstraintValidatorContext constraintValidatorContext) {
        return companyRepository.existsById(companyId);
    }

}
