package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.entity.JobType;
import com.nhn.entity.Province;
import com.nhn.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/province")
public class ProvinceController {

    @Autowired
    private ProvinceRepository provinceRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<Province> provinces = provinceRepository.findAll();
        return provinces.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Provinces found", provinces)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "No provinces found", "")
                );
    }

}
