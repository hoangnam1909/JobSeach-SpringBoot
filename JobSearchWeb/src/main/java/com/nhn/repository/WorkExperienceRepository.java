package com.nhn.repository;

import com.nhn.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {
}
