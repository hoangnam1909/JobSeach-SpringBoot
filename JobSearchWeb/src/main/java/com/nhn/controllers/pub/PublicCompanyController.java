package com.nhn.controllers.pub;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/company")
public class PublicCompanyController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{company-user-id}")
    ResponseEntity<RespondObject> get(@PathVariable(name = "company-user-id") int companyUserId) {

        try {
            Optional<User> companyUser = userRepository.findById(companyUserId);
            if (companyUser.isEmpty() || !companyUser.get().getRole().equals(Constant.USER_ROLE.COMPANY))
                throw new Exception(String.format("Could not find company user with user id = '%d'", companyUserId));

            return companyUser.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Company user found", value)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Fail", "Company user not found", "")));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Failed", "Fail to get company user", ex.getMessage()));
        }
    }

}
