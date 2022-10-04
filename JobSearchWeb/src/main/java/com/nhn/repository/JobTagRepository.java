package com.nhn.repository;

import com.nhn.model.Job;
import com.nhn.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTagRepository extends JpaRepository<JobTag, Integer> {

    List<JobTag> findAllByJobId(int jobId);

}
