package com.nhn.controllers;

import com.nhn.models.RespondObject;
import com.nhn.models.User;
import com.nhn.models.dto.UserDTO;
import com.nhn.models.mapper.UserMapper;
import com.nhn.models.request.UpdateUserReq;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.nhn.specifications.UserSpecification.containsUsername;
import static org.springframework.data.jpa.domain.Specification.*;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")
    ResponseEntity<RespondObject> findByFullNameAndGender(@PathVariable("username") String username) {
//        Specification<User> specifications = Specification.where(UserSpecification.containsUsername(username));
        List<UserDTO> userDTOS = UserMapper.toDTOList(userRepository.findAll(where(containsUsername(username))));

        return !userDTOS.isEmpty() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "Users found", userDTOS)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "Users not found", "")
                );
    }

    @GetMapping("")
    List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> findById(@PathVariable int id) {
        Optional<User> foundUser = userRepository.findById(id);

        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("OK", "User found", foundUser)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("FAIL", "User not found", "")
                );
    }

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody User user) {
        if (user.getUsername() == null ||
                user.getPassword() == null ||
                user.getUserType() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("FAIL", "Not null columns is null", "")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK", "Save User successfully", userRepository.save(user))
        );
    }

//    @PutMapping("/{id}")
//    ResponseEntity<RespondObject> upsert(@PathVariable int id,
//                                         @RequestBody User newUser) {
//
//        User updatedUser = userRepository.findById(id)
//                // Update user
//                .map(user -> {
//                    user.setUsername(newUser.getUsername());
//                    user.setPassword(newUser.getPassword());
//                    user.setAvatar(newUser.getAvatar());
//                    user.setUserType(newUser.getUserType());
//                    user.setActive(newUser.getActive());
//                    user.setFullName(newUser.getFullName());
//                    user.setEmail(newUser.getEmail());
//                    user.setPhone(newUser.getPhone());
//                    user.setDob(newUser.getDob());
//                    user.setGender(newUser.getGender());
//                    user.setAddress(newUser.getAddress());
//                    user.setEmployer(newUser.getEmployer());
//                    user.setCandidate(newUser.getCandidate());
//                    return userRepository.save(user);
//                }).orElseGet(() -> {
//                    newUser.setId(id);
//                    return userRepository.save(newUser);
//                });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new RespondObject("OK", "Update User successfully", updatedUser)
//        );
//    }

    @PutMapping("/{id}")
    ResponseEntity<RespondObject> upsert(@PathVariable int id,
                                         @RequestBody UpdateUserReq req) {
        UserDTO userDTO = userService.updateUser(id, req);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("OK", "Update User successfully", userDTO)
        );
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
