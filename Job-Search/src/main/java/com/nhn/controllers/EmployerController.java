package com.nhn.controllers;

import com.nhn.models.Employer;
import com.nhn.models.RespondObject;
import com.nhn.models.User;
import com.nhn.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/employer")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    List<Employer> getAll() {
        return employerRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> findById(@PathVariable int id) {
        Optional<Employer> foundEmployer = employerRepository.findById(id);

        return foundEmployer.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Employer found", foundEmployer)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "Employer not found", "")
                );
    }

}
