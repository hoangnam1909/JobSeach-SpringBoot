package com.nhn.repository;

import com.nhn.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, Integer>,
        JpaSpecificationExecutor<Job> {
}