package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.entity.Company;
import com.nhn.model.request.UpdateCompanyRequest;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.CompanyService;
import com.nhn.valid.CompanyUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping("{id}")
    ResponseEntity<RespondObject> get(@PathVariable(name = "id") int id) {

        try {
            Optional<Company> company = companyRepository.findById(id);

            return company.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save company successfully", value))).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Save company failed", "")));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Save company failed", ex));
        }
    }

    @PutMapping("/{username}")
    ResponseEntity<RespondObject> updateCompany(@PathVariable @Valid @CompanyUsername String username,
                                                @RequestBody UpdateCompanyRequest request) {

        try {
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
