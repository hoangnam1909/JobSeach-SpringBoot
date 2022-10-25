package com.nhn.controllers.authed;

import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.service.UserService;
import com.nhn.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(path = "/authed/api/user")
public class AuthedUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

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

}
