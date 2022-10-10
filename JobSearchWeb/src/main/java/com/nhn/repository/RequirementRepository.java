package com.nhn.repository;

import com.nhn.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {

    void deleteAllByJobId(int jobId);

    List<Requirement> findAllByJobId(int jobId);

}
