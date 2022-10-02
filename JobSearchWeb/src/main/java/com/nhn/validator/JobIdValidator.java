package com.nhn.validator;

import com.nhn.dto.request.IdRequest;
import com.nhn.model.Job;
import com.nhn.model.User;
import com.nhn.repository.JobRepository;
import com.nhn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class JobIdValidator implements Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            IdRequest idRequest = (IdRequest) target;
            Optional<Job> job = jobRepository.findById(idRequest.getId());
            User currentUser = userService.currentUser();

            if (currentUser == null) {
                errors.rejectValue("id", "has not logged in yet", "please login first");
                return;
            }

            if (job.isEmpty())
                errors.rejectValue("id", "job not found", "job not found");
            else {
                System.err.println("job company user id = " + job.get().getCompanyUser().getId());
                System.err.println("current user id = " + currentUser.getId());
                if (job.get().getCompanyUser().getId() != currentUser.getId())
                    errors.rejectValue("id", "do not have permission", "do not have permission");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
