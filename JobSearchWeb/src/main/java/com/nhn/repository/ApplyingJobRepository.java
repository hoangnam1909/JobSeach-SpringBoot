package com.nhn.repository;

import com.nhn.entity.ApplyingJob;
import com.nhn.entity.ApplyingJobId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyingJobRepository extends JpaRepository<ApplyingJob, ApplyingJobId> {

    List<ApplyingJob> findByApplyingJobIdCandidateUserId(int candidateUserId);

    List<ApplyingJob> findByApplyingJobIdJobId(int jobId);

}
