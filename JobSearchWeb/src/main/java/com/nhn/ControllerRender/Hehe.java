package com.nhn.ControllerRender;

import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class Hehe {

    @Autowired
    private UserRepository userRepository;

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

        User user = userRepository.findUserByUsername(username);

        System.err.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPassword());
        System.err.println("role: " + user.getUserType());

        return "/login-success";
    }

}
