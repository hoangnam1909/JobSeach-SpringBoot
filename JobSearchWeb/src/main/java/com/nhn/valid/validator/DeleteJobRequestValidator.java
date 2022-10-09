package com.nhn.valid.validator;

import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.ApplyingJobGetRequest;
import com.nhn.model.request.company.DeleteJobRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.DeleteJobRequestValid;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DeleteJobRequestValidator implements ConstraintValidator<DeleteJobRequestValid, DeleteJobRequest> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean isValid(DeleteJobRequest request, ConstraintValidatorContext constraintValidatorContext) {

        Optional<User> user = userRepository.findById(request.getCompanyUserId());
        Optional<Job> job = jobRepository.findById(request.getJobId());

        if (user.isPresent() && job.isPresent()) {
            return user.get().getId() == job.get().getCompanyUser().getId();
        }

        return false;
    }

}
