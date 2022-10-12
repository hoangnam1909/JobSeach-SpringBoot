package com.nhn.valid.validator;

import com.nhn.common.Constant;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.CandidateApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CandidateApplyJob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CandidateApplyJobValidator implements ConstraintValidator<CandidateApplyJob, CandidateApplyJobRequest> {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Override
    public boolean isValid(CandidateApplyJobRequest request, ConstraintValidatorContext context) {
        Optional<Job> job = jobRepository.findById(request.getJobId());
        if (job.isEmpty())
            return false;

        User candidateUser = userRepository.findUserByUsername(request.getCandidateUsername());

        if (applyJobRepository.existsApplyJobByJobAppliedAndCandidateUser(job.get(), candidateUser)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("You have applied for this job")
                    .addConstraintViolation();

            ApplyJob applyJobCheckExisted = applyJobRepository.findApplyJobByJobAppliedAndCandidateUser(job.get(), candidateUser);
            if (applyJobCheckExisted.getStatus().equals(Constant.APPLYING_STATUS.BLOCKED)) {
                System.err.println("begin job block check");
                context.buildConstraintViolationWithTemplate("You are banned on this job")
                        .addConstraintViolation();
                System.err.println("end job block check");
            }

            return false;
        }

        return true;
    }

}
