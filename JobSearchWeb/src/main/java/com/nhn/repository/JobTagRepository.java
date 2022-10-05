package com.nhn.repository;

import com.nhn.entity.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTagRepository extends JpaRepository<JobTag, Integer> {

    List<JobTag> findAllByJobId(int jobId);

}
