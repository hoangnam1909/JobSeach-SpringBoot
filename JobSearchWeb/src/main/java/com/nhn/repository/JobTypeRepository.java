package com.nhn.repository;

import com.nhn.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {

    interface JobTypeWithoutJobs {
        Integer getId();
        String getName();
        String getDescription();
    }

    List<JobTypeWithoutJobs> findAllBy();

}
