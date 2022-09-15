package com.nhn.service;

import com.nhn.dto.CusUserDetailsImpl;
import com.nhn.model.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userRepository.findUserByUsername(username);

            if (user == null)
                throw new UsernameNotFoundException("User does not exist!!!");
            else {
                return new CusUserDetailsImpl(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


//        Set<GrantedAuthority> auth = new HashSet<>();
//        auth.add(new SimpleGrantedAuthority(user.getUserType()));
//
//        return new org.springframework.security.core
//                .userdetails.User(user.getUsername(), user.getPassword(), auth);
        return null;
    }
}
