package com.nhn.valid.validator;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.User;
import com.nhn.model.request.candidate.CandidateActionApplyJobRequest;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CandidateActionApplyJob;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CandidateActionApplyJobValidator implements ConstraintValidator<CandidateActionApplyJob, CandidateActionApplyJobRequest> {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(CandidateActionApplyJobRequest request, ConstraintValidatorContext constraintValidatorContext) {
        Optional<ApplyJob> applyJob = applyJobRepository.findById(request.getApplyJobId());
        User candidateUser = userRepository.findUserByUsername(request.getCandidateUsername());

        if (applyJob.isEmpty() || candidateUser == null)
            return false;

        return applyJob.get().getCandidateUser().getId() == candidateUser.getId();
    }

}
