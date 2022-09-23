package com.nhn.repository;

import com.nhn.model.JobTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTagRepository extends JpaRepository<JobTag, Integer> {
}
