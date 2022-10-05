package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.entity.Position;
import com.nhn.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/public/api/job-position")
public class JobPositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<Position> positions = positionRepository.findAll();
        return positions.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Job positions found", positions)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No job positions found", "")
                );
    }

}
