package com.nhn.ControllerRender;

import com.nhn.model.UserDTO;
import com.nhn.mapper.UserMapper;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class Hehe {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/public/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @RequestMapping("/success")
    public String loginSuccess(Principal principal) {
        String username = principal.getName();

        UserDTO user = userMapper.toDTO(userRepository.findUserByUsername(username));

        System.err.println("username: " + user.getUsername());
        System.err.println("fullName: " + user.getFullName());
        System.err.println("role: " + user.getRole());

        return "/login-success";
    }

}
