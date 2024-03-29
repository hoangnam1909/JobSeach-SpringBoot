package com.nhn.controllers.auth;

import com.nhn.common.RespondObject;
import com.nhn.entity.User;
import com.nhn.mapper.UserMapper;
import com.nhn.model.UserDTO;
import com.nhn.model.request.EmailDetails;
import com.nhn.model.request.LoginRequest;
import com.nhn.model.request.SignupRequest;
import com.nhn.model.response.CurrentUserResponse;
import com.nhn.repository.UserRepository;
import com.nhn.service.EmailService;
import com.nhn.service.UserService;
import com.nhn.service.impl.LoginService;
import com.nhn.service.impl.OTPService;
import com.nhn.util.EmailUtil;
import com.nhn.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private OTPService otpService;

    /*
        Chứng thực với access token
    */
    @PostMapping("/authenticated")
    ResponseEntity<RespondObject> generateToken(@RequestBody @Valid LoginRequest loginRequest) {

        if (userService.currentUser() == null) {
            Authentication authentication;

            User user;
            if (loginRequest.getUsername().contains("@"))
                user = userRepository.findUserByEmail(loginRequest.getUsername());
            else
                user = userRepository.findUserByUsername(loginRequest.getUsername());

            if (user == null)
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "Could not find user", null));

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "Wrong password ", null));

            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.err.println("logged in");
            } catch (Exception ex) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(loginService.login(loginRequest));
            }

            if (user.getCompany() != null && !user.isActive())
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "Company user deactivated", null));

            Map<String, String> accessTokenMap = new HashMap<>();
            accessTokenMap.put("username", user.getUsername());
            accessTokenMap.put("role", user.getRole());
            accessTokenMap.put("avatar", user.getAvatar());
            accessTokenMap.put("accessToken", jwtUtils.generateToken(user));
            accessTokenMap.put("refreshToken", user.getRefreshToken());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject(HttpStatus.OK.name(), "User logged in", accessTokenMap));
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new RespondObject(HttpStatus.CONFLICT.name(), "User logged in, please logout and try again", ""));
        }
    }

    @GetMapping("/generate-refresh-token")
    public ResponseEntity<RespondObject> generateRefreshToken() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RespondObject(HttpStatus.OK.name(), "Refresh token", UUID.randomUUID().toString()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RespondObject> refreshToken(@RequestBody Map<String, String> request) {

        String refreshToken = request.get("refreshToken");
        User user = userRepository.findUserByRefreshToken(refreshToken);
        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RespondObject("Not found", "No refresh token found", refreshToken));

        if (user.isActive()) {
            Map<String, String> accessTokenMap = new HashMap<>();
            accessTokenMap.put("username", user.getUsername());
            accessTokenMap.put("role", user.getRole());
            accessTokenMap.put("avatar", user.getAvatar());
            accessTokenMap.put("accessToken", jwtUtils.generateToken(user));

            String newRefreshToken = UUID.randomUUID().toString();
            user.setRefreshToken(newRefreshToken);
            userRepository.save(user);
            accessTokenMap.put("refreshToken", newRefreshToken);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Ok", "New access token", accessTokenMap));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new RespondObject("Failed", "Account disabled", null));
        }
    }

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
        Lấy thông tin của user đang đăng nhập
    */
    @GetMapping("/current-user")
    ResponseEntity<RespondObject> getCurrentUser() {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String username = jwtUtils.extractUsername(accessToken);
            CurrentUserResponse response = userMapper.toCurrentUserResponse(userRepository.findUserByUsername(username));

            if (response != null)
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "User logged in", response));
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "User not found", null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new RespondObject("Failed", "User not found", ""));
        }
    }

    /*
        Đăng ký
    */
    @PostMapping("/signup")
    ResponseEntity<RespondObject> signUp(@RequestBody @Valid SignupRequest request) {

        try {
            User userSaved = loginService.signUp(request);

            if (userSaved != null) {
                emailService.sendSimpleMail(EmailUtil.welcomeMailForm(userSaved));

                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Save user successfully", userSaved));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new RespondObject("Failed", "Save user failed", ""));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Failed", "Error", ex.getMessage()));
        }
    }

    /*
        Quên mật khẩu
     */
    @PostMapping("/forgot-password")
    ResponseEntity<RespondObject> forgotPassword(@RequestBody Map<String, String> map) {
        String email = map.get("email");
//        String otp = RandomStringUtils.randomNumeric(6);

        User user = userRepository.findUserByEmail(email);
        if (user == null)
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new RespondObject("Failed", "Could not find user with email = " + email, ""));

        int otp = otpService.generateOTP(email);
        EmailDetails emailDetails = EmailUtil.otpMailForm(user, String.valueOf(otp));
        emailService.sendSimpleMail(emailDetails);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RespondObject("Ok", "A verification code has been sent to email " + user.getEmail(), ""));
    }

    @PostMapping("/validate-otp")
    ResponseEntity<RespondObject> validateOtp(@RequestBody Map<String, String> map) {
        String email = map.get("email");
        int otp = Integer.parseInt(map.get("otp"));
//        String otp = RandomStringUtils.randomNumeric(6);

        //Validate the Otp
        if (otp >= 0) {

            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otp == serverOtp) {
                    otpService.clearOTP(email);

                    String token = UUID.randomUUID().toString();
                    if (userService.updateResetPassword(email, token)) {
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(new RespondObject(HttpStatus.OK.name(), "Reset password token", token));
                    } else {
                        return ResponseEntity
                                .status(HttpStatus.BAD_REQUEST)
                                .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "Update reset password failed", ""));
                    }
                } else {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "OTP FAILED", ""));
                }
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "OTP FAILED", ""));
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "OTP FAILED", ""));
        }
    }

    @PostMapping("/reset-password")
    ResponseEntity<RespondObject> resetPassword(@RequestParam(name = "token") String token,
                                                @RequestBody Map<String, String> map) {
        String newPassword = map.get("newPassword");
        if (userService.resetPassword(token, newPassword))
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject(HttpStatus.OK.name(), "Reset password successfully", ""));
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RespondObject(HttpStatus.BAD_REQUEST.name(), "Reset password failed", ""));
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
