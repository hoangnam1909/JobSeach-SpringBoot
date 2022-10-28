package com.nhn.repository;

import com.nhn.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Integer> {

    interface PositionWithoutJobs {
        Integer getId();
        String getName();
        String getDescription();
    }

    List<PositionWithoutJobs> findAllBy();

}
