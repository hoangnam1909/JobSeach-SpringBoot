package com.nhn.controllers;

import com.nhn.Util.JwtUtils;
import com.nhn.common.RespondObject;
import com.nhn.dto.CusUserDetailsImpl;
import com.nhn.dto.LoginRequest;
import com.nhn.mapper.UserMapper;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import com.nhn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private AuthenticationManager authenticationManager;

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

//    @RequestMapping("/login-success")
//    public String loginSuccess() {
//        return "/login-success";
//    }

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
////            throw new Exception("Invalid username/password");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new RespondObject("Ok", "User logged in", jwtUtils.generateToken(loginRequest.getUsername()))
//        );
//    }

//    @RequestMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @RequestMapping("/login-success")
//    public String loggedIn() {
//        return "login-success";
//    }

    @GetMapping("/current-user")
    public ResponseEntity<RespondObject> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        System.err.println(username);

        return username != null ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "User logged in", username)
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
//
//    @PostMapping("/public/login")
//    public ResponseEntity<RespondObject> login(@RequestBody LoginRequest loginRequest,
//                                               HttpServletRequest request) {
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//
////        UsernamePasswordAuthenticationToken authReq
////                = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
////        Authentication auth = authenticationManager.authenticate(authReq);
////        SecurityContext sc = SecurityContextHolder.getContext();
////        sc.setAuthentication(auth);
//
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
//
//        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(authRequest);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        // Create a new session and add the security context.
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new RespondObject("Ok", "User logged in", "")
//                );
//    }

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
