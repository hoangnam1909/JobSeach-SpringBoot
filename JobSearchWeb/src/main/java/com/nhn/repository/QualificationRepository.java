package com.nhn.repository;

import com.nhn.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
}
