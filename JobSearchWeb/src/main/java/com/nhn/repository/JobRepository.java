package com.nhn.repository;

import com.nhn.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer>,
        JpaSpecificationExecutor<Job> {

    interface JobDateInfo {
        Integer getId();
        Date getPublishedDate();
        Date getModifiedDate();
    }

    List<JobDateInfo> findAllBy();

    Optional<Job> findByIdAndAvailableIsTrue(Integer jobId);

}
