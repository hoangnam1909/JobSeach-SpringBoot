package com.nhn.repository;

import com.nhn.entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTypeRepository extends JpaRepository<JobType, Integer> {
}
