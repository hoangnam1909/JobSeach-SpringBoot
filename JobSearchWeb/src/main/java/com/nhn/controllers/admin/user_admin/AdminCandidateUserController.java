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
@RequestMapping(path = "/admin/api/candidate/user")
public class AdminCandidateUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<User> candidateUsers = userRepository.findUserByRole(Constant.USER_ROLE.CANDIDATE);
        return candidateUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Candidate found", candidateUsers))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No candidate found", ""));
    }

    @GetMapping("/{candidate-user-id}")
    ResponseEntity<RespondObject> getByUserId(@PathVariable(name = "candidate-user-id") int candidateUserId) {

        User candidateUser = userRepository.findUserByIdAndRole(candidateUserId, Constant.USER_ROLE.CANDIDATE);
        return candidateUser != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Candidate found", candidateUser))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No candidate found", ""));
    }

    @PostMapping(value = "/add", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> addCandidateUser(@RequestPart("user") @Valid AdminAddUserRequest request,
                                                   @RequestPart("file") MultipartFile file) {

        try {
            User user = userMapper.toEntity(request, Constant.USER_ROLE.CANDIDATE);
            userService.addOrUpdate(user, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate user successfully", user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    @PutMapping(value = "/update", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateCandidateUser(@RequestPart("user") @Valid AdminUpdateUserRequest request,
                                                      @RequestPart("file") MultipartFile file) {

        try {
            User user = userMapper.toEntity(request, Constant.USER_ROLE.CANDIDATE);
            userService.addOrUpdate(user, file);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Save candidate user successfully", user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    @PutMapping("/{candidate-user-id}")
    ResponseEntity<RespondObject> delete(@PathVariable(name = "candidate-user-id") int candidateUserId) {

        User candidateUser = userRepository.findUserByIdAndRole(candidateUserId, Constant.USER_ROLE.CANDIDATE);
        if (candidateUser == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Fail", "No candidate found", ""));

        candidateUser.setActive(false);
        userRepository.save(candidateUser);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Candidate user deleted", "deleted"));
    }

}
