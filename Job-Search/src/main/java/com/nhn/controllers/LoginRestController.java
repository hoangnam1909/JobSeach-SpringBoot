package com.nhn.controllers;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.dto.CustomUserDetail;
import com.nhn.dto.UserLoginRequestDTO;
import com.nhn.dto.UserSignUpRequestDTO;
import com.nhn.mapper.UserMapper;
import com.nhn.repository.UserRepository;
import com.nhn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/current-user")
    public ResponseEntity<RespondObject> getCurrentUser(@AuthenticationPrincipal CustomUserDetail userDetail){
        try {
            String username = userDetail.getUsername();

            return username != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "User logged in", username)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Failed", "User not found", "")
                    );
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Error", "Error", ex));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<RespondObject> signUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {

        RespondObject respondObject = loginService.signUp(userSignUpRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respondObject);
    }

    @PostMapping("/login")
    public ResponseEntity<RespondObject> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {

        RespondObject respondObject = loginService.login(userLoginRequestDTO);

        // return
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(respondObject);
    }

    @GetMapping("/privateApi")
    public ResponseEntity<RespondObject> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
        RespondObject respondObject = new RespondObject();

        jwtUtils.verify(auth);

        respondObject.setData("this is private api");
        return ResponseEntity.status(HttpStatus.OK).body(respondObject);
    }

}
