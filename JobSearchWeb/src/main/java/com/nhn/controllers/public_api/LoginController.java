package com.nhn.controllers.public_api;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.dto.request.EmailDetails;
import com.nhn.dto.request.LoginRequest;
import com.nhn.dto.UserDTO;
import com.nhn.dto.request.UserSignupRequest;
import com.nhn.mapper.UserMapper;
import com.nhn.repository.UserRepository;
import com.nhn.service.EmailService;
import com.nhn.service.UserService;
import com.nhn.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtils jwtUtils;

//    @Autowired
//    private UserSignupRequestValidator userSignupRequestValidator;

//    @PostMapping("/authenticated")
//    public ResponseEntity<RespondObject> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//            );
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    loginService.login(loginRequest)
//            );
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new RespondObject("Ok", "User logged in", jwtUtils.generateToken(loginRequest.getUsername()))
//        );
//    }

    @GetMapping("/current-user")
    public ResponseEntity<RespondObject> getCurrentUser() {
        try {
            UserDTO userDTO = userMapper.toDTO(userService.currentUser());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "User logged in", userDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new RespondObject("Failed", "User not found", ex));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<RespondObject> signUp(@Valid @RequestBody UserSignupRequest request,
                                                BindingResult result) {

        try {
//            userSignupRequestValidator.validate(request, result);
            if (result.hasErrors()) {
                System.err.println(result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new RespondObject("Fail", "Invalid request", result.getAllErrors()));
            }

            UserDTO userSaved = loginService.signUp(request);

            if (userSaved != null) {
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setRecipient(userSaved.getEmail());
                emailDetails.setSubject("Chào mừng bạn đến với website tìm kiếm việc làm");
                emailDetails.setMsgBody("Bạn vừa đăng ký thành công tài khoản");

                emailService.sendSimpleMail(emailDetails);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Save user successfully", userSaved)
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

    @PostMapping("/login")
    public ResponseEntity<RespondObject> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.err.println("logged in");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Ok", "User logged in", authentication.getName())
                    );
        } catch (Exception ex) {
            System.err.println("login failed");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RespondObject("Failed", "User login failed", "")
                    );
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<RespondObject> logout() {

        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            System.err.println("logged out");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Ok", "logged out", "")
                    );
        } catch (Exception ex) {
            System.err.println("log out failed");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RespondObject("Failed", "User log out failed", "")
                    );
        }
    }

//    @GetMapping("/privateApi")
//    public ResponseEntity<RespondObject> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
//        RespondObject respondObject = new RespondObject();
//
//        jwtUtils.verify(auth);
//
//        respondObject.setData("this is private api");
//        return ResponseEntity.status(HttpStatus.OK).body(respondObject);
//    }

}
