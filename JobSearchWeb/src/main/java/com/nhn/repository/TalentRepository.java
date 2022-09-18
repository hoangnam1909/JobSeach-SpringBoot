package com.nhn.repository;

import com.nhn.model.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Integer> {
}
