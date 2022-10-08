package com.nhn.mapper;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.CandidateApplyJobRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplyJobMapper {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public ApplyJob toEntity(CandidateApplyJobRequest request){
        ApplyJob applyJob = new ApplyJob();

        Optional<Job> job = jobRepository.findById(request.getJobId());
        job.ifPresent(applyJob::setJobApp);

        Optional<User> user = userRepository.findById(request.getCandidateUserId());
        user.ifPresent(applyJob::setCandidateUser);

        return applyJob;
    }

}
