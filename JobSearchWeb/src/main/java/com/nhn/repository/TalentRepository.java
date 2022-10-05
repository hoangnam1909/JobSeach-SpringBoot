package com.nhn.repository;

import com.nhn.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentRepository extends JpaRepository<Talent, Integer> {
}
