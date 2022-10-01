package com.nhn.controllers.admin_api;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.dto.request.CandidateRequest;
import com.nhn.dto.request.CompanyRequest;
import com.nhn.mapper.CompanyMapper;
import com.nhn.model.Candidate;
import com.nhn.model.Company;
import com.nhn.model.User;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/api/v1/company")
public class CompanyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<User> candidateUsers = userRepository.findUserByRole(Constant.USER_ROLE.COMPANY);
        return candidateUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Companies found", candidateUsers)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No companies found", "")
                );
    }

    @PutMapping("")
    ResponseEntity<RespondObject> updateCandidate(@RequestBody CompanyRequest request) {

        try {
            Company company = companyRepository.save(companyMapper.toEntity(request));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Save company successfully", company));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Save company failed", ex));
        }
    }

}
