package com.nhn.controllers.public_api;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.dto.LoginRequest;
import com.nhn.dto.UserDTO;
import com.nhn.mapper.UserMapper;
import com.nhn.repository.UserRepository;
import com.nhn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/public")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/")
    public String welcome() {
        return "welcome huhu";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/authenticated")
    public ResponseEntity<RespondObject> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    loginService.login(loginRequest)
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Ok", "User logged in", jwtUtils.generateToken(loginRequest.getUsername()))
        );
    }

    @GetMapping("/current-user")
    public ResponseEntity<RespondObject> getCurrentUser(Principal principal) {
        String username = principal.getName();

        UserDTO userDTO = userMapper.toDTO(userRepository.findUserByUsername(username));

        System.err.println("username: " + userDTO.getUsername());
        System.err.println("fullName: " + userDTO.getFullName());
        System.err.println("role: " + userDTO.getRole());

        return userDTO.getUsername() != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "User logged in", userDTO)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Failed", "User not found", "")
                );
    }

    //    @PostMapping("/signup")
//    public ResponseEntity<RespondObject> signUp(@RequestBody UserSignUpRequestDTO userSignUpRequestDTO) {
//
//        RespondObject respondObject = loginService.signUp(userSignUpRequestDTO);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(respondObject);
//    }

    @PostMapping("/login")
    public ResponseEntity<RespondObject> login(@RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespondObject("Failed", "User login failed", "")
                    );
//            throw new Exception("Invalid credentials");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new RespondObject("Ok", "User logged in", "")
                );
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
