package com.nhn.validator;

import com.nhn.dto.request.UserSignUpRequest;
import com.nhn.entity.User;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SignupValidator implements Validator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            UserSignUpRequest userSignUpRequest = (UserSignUpRequest) target;
            User user = userRepository.findOneByUsernameEqualsIgnoreCase(userSignUpRequest.getUsername());

            if (user != null)
                errors.rejectValue
                        ("username",
                                "Username already exists",
                                "Please try again");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
