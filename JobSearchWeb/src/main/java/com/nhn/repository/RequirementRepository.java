package com.nhn.repository;

import com.nhn.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    List<Requirement> findAllByJobId(int jobId);

}
