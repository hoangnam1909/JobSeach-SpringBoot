package com.nhn.controllers.pub;

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
@RequestMapping(path = "/public/api/user")
public class PublicUserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<User> userOptional = userRepository.findById(Integer.parseInt(id));

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Processed", "No user found", "No data"));
        } else {
            User user = userOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Found user with id = " + id, user));
        }
    }

}
