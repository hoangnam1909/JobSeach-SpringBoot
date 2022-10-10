package com.nhn.repository;

import com.nhn.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer>,
        JpaSpecificationExecutor<Job> {

    Optional<Job> findByIdAndAvailableIsTrue(Integer jobId);

}
