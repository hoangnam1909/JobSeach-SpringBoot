package com.nhn.valid.validator;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.model.request.company.CompanyActionApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyActionApplyJob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CompanyActionApplyJobValidator implements ConstraintValidator<CompanyActionApplyJob, CompanyActionApplyJobRequest> {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(CompanyActionApplyJobRequest request, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(request.getApplyJobId());
        User companyUser = userRepository.findUserByUsername(request.getCompanyUsername());

        if (applyJob.isEmpty() || companyUser == null)
            return false;

        return applyJob.filter(job -> job.getJobApplied().getCompanyUser().getId() == companyUser.getId()).isPresent();
    }

}
