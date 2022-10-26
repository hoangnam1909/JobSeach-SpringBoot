package com.nhn.controllers.admin.user_admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.request.admin_request.user.AdminAddUserRequest;
import com.nhn.model.request.admin_request.user.AdminUpdateUserRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.AdminUserService;
import com.nhn.service.EmailService;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/admin/api/user")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpecificationConverter specificationConverter;

    // ================ GET ================
    @GetMapping("/get-all")
    ResponseEntity<RespondObject> getAll() {

        List<User> users = userRepository.findAll();
        return users.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Users found", users))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "No users found", ""));
    }

    @GetMapping("/get-all-company")
    ResponseEntity<RespondObject> getAllCompany() {

        List<User> users = userRepository.findAllByRole(Constant.USER_ROLE.COMPANY);
        return users.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Company users found", users))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "No company users found", ""));
    }

    @GetMapping("/get-all-candidate")
    ResponseEntity<RespondObject> getAllCandidate() {

        List<User> users = userRepository.findAllByRole(Constant.USER_ROLE.CANDIDATE);
        return users.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Candidate users found", users))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "No candidate users found", ""));
    }

    @PostMapping("/get")
    ResponseEntity<RespondObject> getByCondition(@RequestBody(required = false) Map<String, String> params,
                                                 @RequestParam(name = "page", defaultValue = "1") String page,
                                                 @RequestParam(name = "size", defaultValue = "5") String size) {

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            UserSpecification specification = specificationConverter.userSpecification(params);
            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<User> foundUser = userRepository.findAll(specification, paging);

            System.err.println("getNumber = " + foundUser.getNumber());
            System.err.println("getNumberOfElements = " + foundUser.getNumberOfElements());
            System.err.println("getTotalElements = " + foundUser.getTotalElements());
            System.err.println("getTotalPages = " + foundUser.getTotalPages());

            if (Integer.parseInt(page) > foundUser.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Users found", foundUser));
        } else {
            List<User> foundUser = userRepository.findAll();
            return foundUser.size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("OK", "Users found", foundUser))
                    :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("FAIL", "No users", ""));
        }
    }

    @GetMapping("/{user-id}")
    ResponseEntity<RespondObject> getById(@PathVariable(name = "user-id") int userId) {

        Optional<User> candidateUser = userRepository.findById(userId);
        return candidateUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "User found", candidateUser.get()))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No user found", ""));
    }

    // ================ ADD ================
    @PostMapping("/add/user-info")
    @Transactional
    ResponseEntity<RespondObject> addUserInfo(@RequestBody @Valid AdminAddUserRequest request) {

        try {
            User user = adminUserService.add(request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save user successfully", user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    // ================ UPDATE ================
    @PutMapping("/update-user-info/{user-id}")
    @Transactional
    ResponseEntity<RespondObject> updateUserInfo(@PathVariable(name = "user-id") int userId,
                                                 @RequestBody @Valid AdminUpdateUserRequest request) {

        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "Could not find user with id = " + userId, null));

            User user = adminUserService.updateProfileInfo(userId, request);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject(HttpStatus.OK.name(), "Save user successfully", user)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Error", ex.getMessage())
            );
        }
    }

    @PutMapping(value = "/update-user-avatar/{user-id}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateUserAvatar(@PathVariable(name = "user-id") int userId,
                                                   @RequestPart("file") MultipartFile file) {

        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject(HttpStatus.NOT_FOUND.name(), "Could not find user with id = " + userId, null));

            User user = adminUserService.updateUserAvatar(userId, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject(HttpStatus.OK.name(), "Update user avatar successfully", user)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Error", ex.getMessage())
            );
        }
    }

    // ================ DELETE ================
    @PutMapping("/activate/{user-id}")
    ResponseEntity<RespondObject> activate(@PathVariable(name = "user-id") int userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject(HttpStatus.NOT_FOUND.name(), "No user found", "")
            );

        User user = userOptional.get();
        user.setActive(true);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "User activated", "activated")
        );
    }

    @PutMapping("/deactivate/{user-id}")
    ResponseEntity<RespondObject> deActivate(@PathVariable(name = "user-id") int userId) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject(HttpStatus.NOT_FOUND.name(), "No user found", "")
            );

        User user = userOptional.get();
        user.setActive(false);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "User deactivated", "deactivated")
        );
    }

    @DeleteMapping("/{user-id}")
    ResponseEntity<RespondObject> delete(@PathVariable(name = "user-id") int userId) {
        boolean exists = userRepository.existsById(userId);

        if (exists) {
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject(HttpStatus.OK.name(), "Delete User successfully", ""));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject(HttpStatus.NOT_FOUND.name(), "Can not find user with id = " + userId, ""));
    }

}
