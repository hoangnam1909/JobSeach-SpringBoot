package com.nhn.models.mapper;

import com.nhn.models.Candidate;
import com.nhn.models.Employer;
import com.nhn.models.User;
import com.nhn.models.dto.UserDTO;
import com.nhn.models.request.UpdateUserReq;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class UserMapper {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }

        return INSTANCE;
    }

    public User toEntity(UpdateUserReq req) {
        User user = new User();

        user.setUsername(req.getUsername());
        user.setAvatar(req.getAvatar());
        user.setUserType(req.getUserType());
        user.setActive(req.isActive());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setDob(req.getDob());
        user.setGender(req.isGender());
        user.setAddress(req.getAddress());

        Optional<Employer> employer = employerRepository.findById(req.getEmployerId());
        if (employer.isPresent())
            user.setEmployer(employer.get());

        Optional<Candidate> candidate = candidateRepository.findById(req.getCandidateId());
        if (candidate.isPresent())
            user.setCandidate(candidate.get());

        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setUserType(user.getUserType());
        userDTO.setActive(user.isActive());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setDob(user.getDob());
        userDTO.setGender(user.isGender());
        userDTO.setAddress(user.getAddress());
        userDTO.setJoinedDate(user.getJoinedDate());

        return userDTO;
    }

}
