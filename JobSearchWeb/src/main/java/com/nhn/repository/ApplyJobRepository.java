package com.nhn.repository;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Integer> {

    List<ApplyJob> findByJobApp(Job job);

    List<ApplyJob> findByCandidateUserOrderByCreatedDateDesc(User candidateUser);

}
