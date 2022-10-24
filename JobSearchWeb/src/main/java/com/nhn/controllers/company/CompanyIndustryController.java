package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.entity.Industry;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/industry")
public class CompanyIndustryController {

    @Autowired
    private IndustryRepository industryRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<Industry> industryList = industryRepository.findAll();

        if (industryList.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Processed", "No industries found", "No data"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Found industries", industryList));
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Industry> industryOptional = industryRepository.findById(Integer.parseInt(id));

        if (industryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Processed", "No industry found", "No data"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Found industry with id = " + id, industryOptional.get()));
        }
    }

}
