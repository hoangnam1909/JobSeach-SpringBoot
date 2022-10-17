package com.nhn.repository;

import com.nhn.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer>,
        JpaSpecificationExecutor<Job> {

    Optional<Job> findByIdAndAvailableIsTrue(Integer jobId);

}
