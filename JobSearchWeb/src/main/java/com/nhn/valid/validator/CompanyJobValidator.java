package com.nhn.valid.validator;

import com.nhn.dto.request.ApplyingJobGetRequest;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyJob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CompanyJobValidator implements ConstraintValidator<CompanyJob, ApplyingJobGetRequest> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(ApplyingJobGetRequest request, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findById(request.getCompanyUserId());
        Optional<Job> job = jobRepository.findById(request.getJobId());

        if (user.isPresent() && job.isPresent()) {
            return user.get().getId() == job.get().getCompanyUser().getId();
        }

        return false;
    }

}
