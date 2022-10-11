package com.nhn.controllers.admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import com.nhn.valid.ExistingCandidateUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/admin/api/candidate")
public class AdminCandidateController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll() {

        List<User> candidateUsers = userRepository.findUserByRole(Constant.USER_ROLE.CANDIDATE);
        return candidateUsers.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Candidate found", candidateUsers)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No candidate found", "")
                );
    }

    @GetMapping("/{candidate-user-id}")
    ResponseEntity<RespondObject> getAll(@PathVariable(name = "candidate-user-id") @Valid @ExistingCandidateUserId int candidateUserId) {

        Optional<User> candidateUsers = userRepository.findById(candidateUserId);
        return candidateUsers.map(user -> ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "Candidate found", user)
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Fail", "No candidate found", "")
        ));
    }

}
