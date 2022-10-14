package com.nhn.controllers.auth;

import com.nhn.Util.EmailUtil;
import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.UserDTO;
import com.nhn.model.request.EmailDetails;
import com.nhn.model.request.LoginRequest;
import com.nhn.model.request.UserSignupRequest;
import com.nhn.repository.UserRepository;
import com.nhn.service.EmailService;
import com.nhn.service.UserService;
import com.nhn.service.impl.LoginService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

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

    /*
        Chứng thực với access token
    */
    @PostMapping("/authenticated")
    ResponseEntity<RespondObject> generateToken(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    loginService.login(loginRequest)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "User logged in", jwtUtils.generateToken(loginRequest.getUsername()))
        );
    }

    /*
        Lấy ra thông tin của user đang đăng nhập
    */
    @GetMapping("/current-user")
    ResponseEntity<RespondObject> getCurrentUser() {
        try {
            UserDTO userDTO = userMapper.toDTO(userService.currentUser());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "User logged in", userDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new RespondObject("Failed", "User not found", ex));
        }
    }

    /*
        Đăng ký
    */
    @PostMapping("/signup")
    ResponseEntity<RespondObject> signUp(@RequestBody @Valid UserSignupRequest request) {

        try {
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

    /*
        Đăng nhập thường
     */
    @PostMapping("/login")
    ResponseEntity<RespondObject> login(@RequestBody @Valid LoginRequest loginRequest) {

        if (userService.currentUser() == null) {
            Authentication authentication;

            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.err.println("logged in");

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new RespondObject("Ok", "User logged in", authentication.getName()));
            } catch (Exception ex) {
                System.err.println("login failed");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new RespondObject("Login failed", "User login failed", ""));
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new RespondObject("Login failed", "User logged in, please logout and try again", ""));
        }
    }

    /*
        Quên mật khẩu
     */
    @PutMapping("/forgot-password/{username}")
    ResponseEntity<RespondObject> forgotPassword(@PathVariable(name = "username") String username) {

        User user = userRepository.findUserByUsername(username);
        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RespondObject("Failed", "Could not find user with username = " + username, ""));
        else {
            String code = RandomStringUtils.randomNumeric(6);
            System.err.println(code);

            EmailDetails emailDetails = EmailUtil.otpMailForm(user, code);
            emailService.sendSimpleMail(emailDetails);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Ok", "A verification code has been sent to email " + user.getEmail(), ""));
        }
    }

    /*
        Đăng xuất
     */
    @GetMapping("/logout")
    ResponseEntity<RespondObject> logout() {

        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            System.err.println("logged out");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Ok", "logged out", ""));
        } catch (Exception ex) {
            System.err.println("log out failed");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RespondObject("Failed", "User log out failed", ""));
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
