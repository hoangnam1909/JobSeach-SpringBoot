package com.nhn.repository;

import com.nhn.model.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Integer> {
}
