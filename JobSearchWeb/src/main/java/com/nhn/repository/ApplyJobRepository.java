package com.nhn.repository;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.CreatedDateI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Integer> {

    List<ApplyJob> findByJobAppliedOrderByCreatedDateDesc(Job job);

    ApplyJob findApplyJobByJobAppliedAndCandidateUser(Job job, User candidateUser);

    List<ApplyJob> findByCandidateUserOrderByCreatedDateDesc(User candidateUser);

    boolean existsApplyJobByJobAppliedAndCandidateUser(Job job, User candidateUser);

    @Query("SELECT aj.createdDate FROM ApplyJob aj")
    List<Object> findAllDate();

}
