package com.nhn.validator;

import com.nhn.common.Constant;
import com.nhn.dto.request.ApplyingJobGetRequest;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class JobIdValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            ApplyingJobGetRequest applyingJobGetRequest = (ApplyingJobGetRequest) target;

            Optional<Job> jobOpt = jobRepository.findById(applyingJobGetRequest.getJobId());
            Optional<User> companyUserOpt = userRepository.findById(applyingJobGetRequest.getCompanyUserId());

            if (companyUserOpt.isPresent()) {
                User companyUser = companyUserOpt.get();

                if (!companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
                    errors.rejectValue
                            ("companyUserId",
                                    "Company does not have permission",
                                    "Company does not have permission");

                if (jobOpt.isPresent()) {
                    Job job = jobOpt.get();

                    if (job.getCompanyUser().getId() != companyUser.getId())
                        errors.rejectValue
                                ("jobId",
                                        "Company does not have permission",
                                        "Company does not have permission to job with id = " + job.getId());
                } else {
                    errors.rejectValue("jobId", "Job is not exist", "Retry with other job id");
                }

            } else {
                errors.rejectValue("companyUserId", "Company is not exist", "please login first");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
