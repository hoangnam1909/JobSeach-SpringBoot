package com.nhn.controllers.company;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Company;
import com.nhn.entity.User;
import com.nhn.mapper.CompanyMapper;
import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api")
public class CompanyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @PutMapping("/{username}")
    ResponseEntity<RespondObject> updateCompany(@PathVariable String username,
                                                @RequestBody UpdateCompanyRequest request) {

        try {
            if (!userRepository.existsByUsername(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Could not find user with username = " + username, ""));
            }

            Company company = companyService.update(username, request);

            return company != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Save company successfully", company))
                    :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Fail", "Save company failed", ""));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Save company failed", ex));
        }
    }

}
