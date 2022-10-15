package com.nhn.repository;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Integer> {

    List<ApplyJob> findByJobAppliedOrderByCreatedDateDesc(Job job);

    List<ApplyJob> findByJobApplied(Job job);

    ApplyJob findApplyJobByJobAppliedAndCandidateUser(Job job, User candidateUser);

    List<ApplyJob> findByCandidateUserOrderByCreatedDateDesc(User candidateUser);

    boolean existsApplyJobByJobAppliedAndCandidateUser(Job job, User candidateUser);

    @Query("SELECT aj.createdDate FROM ApplyJob aj")
    List<Object> findAllDate();

}
