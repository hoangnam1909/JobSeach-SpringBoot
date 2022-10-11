package com.nhn.valid.validator;

import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.company.CompanyJobRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyJob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CompanyJobValidator implements ConstraintValidator<CompanyJob, CompanyJobRequest> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(CompanyJobRequest request, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findUserByUsername(request.getCompanyUsername());
        Optional<Job> job = jobRepository.findById(request.getJobId());

        if (user != null && job.isPresent()) {
            return user.getId() == job.get().getCompanyUser().getId();
        }

        return false;
    }

}
