package com.nhn.repository;

import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJobRepository extends JpaRepository<ApplyJob, Integer> {

    List<ApplyJob> findAllByJobApp(Job job);

}
