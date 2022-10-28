package com.nhn.repository;

import com.nhn.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer> {

    // bean convention getter declarations for fields to fetch
    interface JobCategoryWithoutJobs {
        Integer getId();
        String getName();
    }

    List<JobCategoryWithoutJobs> findAllBy();

}
