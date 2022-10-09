package com.nhn.controllers.pub;

import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.UserDTO;
import com.nhn.model.request.AdminUserInsertRequest;
import com.nhn.model.request.EmailDetails;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.EmailService;
import com.nhn.service.UserService;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.UserSpecification;
import com.nhn.valid.RegisteredUsername;
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

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpecificationConverter specificationConverter;

//    @GetMapping("/username/{username}")
//    ResponseEntity<RespondObject> findByFullNameAndGender(@PathVariable("username") String username) {
////        Specification<User> specifications = Specification.where(UserSpecification.containsUsername(username));
//        List<UserDTO> userDTOS = userMapper.toDTOList(userRepository.findAll(where(containsUsername(username))));
//
//        return !userDTOS.isEmpty() ?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new RespondObject("OK", "Users found", userDTOS)
//                ) :
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new RespondObject("FAIL", "Users not found", "")
//                );
//    }

    @GetMapping("")
    ResponseEntity<RespondObject> getAll(@RequestBody(required = false) Map<String, String> params,
                                         @RequestParam(name = "page", defaultValue = "1") String page) {

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            UserSpecification specification = specificationConverter.userSpecification(params);
            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, 5, Sort.by("id").ascending());

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
                            new RespondObject("OK", "Users found", foundUser)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("FAIL", "No users", "")
                    );
        }
    }

    @GetMapping("/{username}")
    ResponseEntity<RespondObject> findByUsername(@PathVariable String username) {
        User foundUser = userRepository.findUserByUsername(username);

        return foundUser != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "User found", foundUser)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "User not found", "")
                );
    }

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid AdminUserInsertRequest req) {
        try {
            UserDTO userSaved = userService.add(req);

            if (userSaved != null) {
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient(userSaved.getEmail());
                emailDetails.setSubject("Chào mừng bạn đến với website tìm kiếm việc làm");
                emailDetails.setMsgBody("Bạn vừa đăng ký thành công tài khoản");

                emailService.sendSimpleMail(emailDetails);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Save user successfully", userSaved)
                );
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new RespondObject("Failed", "Save user failed", "")
                );
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

//    @PutMapping("/{username}")
//    ResponseEntity<RespondObject> upsert(@PathVariable String username,
//                                         @RequestBody UpdateUserRequest request) {
//        try {
//            UserDTO userDTO = userService.updateUser(username, request);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new RespondObject("OK", "Update User successfully", userDTO)
//            );
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new RespondObject("Error", "Bad request", ex.getMessage())
//            );
//        }
//    }

    @PutMapping(value = "/{username}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> testImage(@PathVariable(name = "username") @Valid @RegisteredUsername String username,
                                            @RequestPart("user") UpdateUserRequest request,
                                            @RequestPart("file") MultipartFile file) {

        try {
            User user = userService.update(username, request, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save user successfully", user)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<RespondObject> delete(@PathVariable int id) {
        boolean exists = userRepository.existsById(id);

        if (exists) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("OK", "Delete User successfully", "")
            );
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("FAIL", "Can not find user with id = " + id, "")
        );
    }

}
