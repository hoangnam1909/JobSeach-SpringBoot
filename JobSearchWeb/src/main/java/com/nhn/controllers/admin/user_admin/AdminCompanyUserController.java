package com.nhn.controllers.admin.user_admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.request.admin_request.candidate.AdminAddUserRequest;
import com.nhn.model.request.admin_request.candidate.AdminUpdateUserRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin/api/company/user")
public class AdminCompanyUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

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

    @GetMapping("/{company-user-id}")
    ResponseEntity<RespondObject> getById(@PathVariable(name = "company-user-id") int companyUserId) {

        User candidateUser = userRepository.findUserByIdAndRole(companyUserId, Constant.USER_ROLE.COMPANY);
        return candidateUser != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Company found", candidateUser)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No company found", "")
                );
    }

    @PostMapping(value = "/add", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> addCandidateUser(@RequestPart("user") @Valid AdminAddUserRequest request,
                                                   @RequestPart("file") MultipartFile file) {

        try {
            User user = userMapper.toEntity(request, Constant.USER_ROLE.COMPANY);
            userService.addOrUpdate(user, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save company user successfully", user)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

    @PutMapping(value = "/update", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateCompanyUser(@RequestPart("user") @Valid AdminUpdateUserRequest request,
                                                    @RequestPart("file") MultipartFile file) {

        try {
            User user = userMapper.toEntity(request, Constant.USER_ROLE.COMPANY);
            userService.addOrUpdate(user, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate user successfully", user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    @PutMapping("/activate/{company-user-id}")
    ResponseEntity<RespondObject> activate(@PathVariable(name = "company-user-id") int companyUserId) {

        User companyUser = userRepository.findUserByIdAndRole(companyUserId, Constant.USER_ROLE.COMPANY);
        if (companyUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Fail", "No company found", "")
            );

        companyUser.setActive(true);
        userRepository.save(companyUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Company user activated", "activated")
        );
    }

    @PutMapping("/deactivate/{company-user-id}")
    ResponseEntity<RespondObject> deActivate(@PathVariable(name = "company-user-id") int companyUserId) {

        User companyUser = userRepository.findUserByIdAndRole(companyUserId, Constant.USER_ROLE.COMPANY);
        if (companyUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Fail", "No company found", "")
            );

        companyUser.setActive(false);
        userRepository.save(companyUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Company user deactivated", "deactivated")
        );
    }

}
