package com.nhn.repository;

import com.nhn.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    void deleteAllByJobId(int jobId);

}
