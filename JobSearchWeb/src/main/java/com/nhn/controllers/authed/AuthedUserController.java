package com.nhn.controllers.authed;

import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.UserService;
import com.nhn.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/authed/api/user")
public class AuthedUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping(value = "/update-user-info", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateProfile(@RequestPart("user") @Valid UpdateUserRequest request,
                                                @RequestPart("file") MultipartFile file) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);

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

    @PutMapping("/update-profile-info")
    @Transactional
    ResponseEntity<RespondObject> updateProfileInfo(@RequestBody @Valid UpdateUserRequest request) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);

            User user = userService.updateProfileInfo(username, request);

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

    @PutMapping(value = "/update-user-avatar", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @Transactional
    ResponseEntity<RespondObject> updateProfileAvatar(@RequestPart("file") MultipartFile file) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);

            User user = userService.updateAvatar(username, file);

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

    @PutMapping("/change-password")
    ResponseEntity<RespondObject> changePassword(@RequestBody Map<String, String> map) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);
            String currentPassword = map.get("currentPassword");
            String newPassword = map.get("newPassword");

            User user = userRepository.findUserByUsername(username);
            if (!passwordEncoder.matches(currentPassword, user.getPassword()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject(HttpStatus.BAD_REQUEST.name(), "Current password does not match", ""));

            if (currentPassword.equals(newPassword))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject(HttpStatus.BAD_REQUEST.name(), "New password must not match with current password", ""));

            if (userService.changePassword(user, currentPassword, newPassword))
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject(HttpStatus.OK.name(), "Change password successfully", true));
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject(HttpStatus.BAD_REQUEST.name(), "Change password failed", false));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject(HttpStatus.NOT_FOUND.name(), "Error", ex.getMessage()));
        }
    }

}
