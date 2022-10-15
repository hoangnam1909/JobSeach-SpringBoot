package com.nhn.mapper;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.response.CandidateApplyJobResponse;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ApplyJobMapper {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public ApplyJob toEntity(String candidateUsername, int jobId) {
        ApplyJob applyJob = new ApplyJob();

        Optional<Job> job = jobRepository.findById(jobId);
        User candidateUser = userRepository.findUserByUsername(candidateUsername);

        if (job.isPresent() && candidateUser != null) {
            job.ifPresent(applyJob::setJobApplied);
            applyJob.setCandidateUser(candidateUser);
        } else {
            return null;
        }

        return applyJob;
    }

    public CandidateApplyJobResponse toCandidateResponse(ApplyJob applyJob) {
        CandidateApplyJobResponse response = new CandidateApplyJobResponse();

        response.setId(applyJob.getId());
        response.setCreatedDate(applyJob.getCreatedDate());
        response.setStatus(applyJob.getStatus());
        response.setJobApplied(applyJob.getJobApplied());

        return response;
    }

    public List<CandidateApplyJobResponse> toResponseList(List<ApplyJob> applyJobList) {
        List<CandidateApplyJobResponse> result = new ArrayList<>();
        applyJobList.forEach(applyJob -> result.add(toCandidateResponse(applyJob)));

        return result;
    }

}
