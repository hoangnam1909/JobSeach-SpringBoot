package com.nhn.mapper;

import com.nhn.dto.UserDTO;
import com.nhn.dto.UserSignUpRequestDTO;
import com.nhn.dto.UserUpdateRequest;
import com.nhn.model.Candidate;
import com.nhn.model.Employer;
import com.nhn.model.User;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public User toEntity(UserUpdateRequest req) {
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

    public User toEntity(UserSignUpRequestDTO req) {
        User user = new User();

        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setAvatar(req.getAvatar());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setDob(req.getDob());
        user.setGender(req.isGender());
        user.setAddress(req.getAddress());
        user.setUserType(req.getUserType());

        Optional<Employer> employer = employerRepository.findById(req.getEmployerId());
        employer.ifPresent(user::setEmployer);

        Optional<Candidate> candidate = candidateRepository.findById(req.getCandidateId());
        candidate.ifPresent(user::setCandidate);

        return user;
    }

    public UserDTO toDTO(User user) {
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

    public List<UserDTO> toDTOList(List<User> users) {
        List<UserDTO> userList = new ArrayList<>();

        users.forEach(user -> {
            userList.add(toDTO(user));
        });

        return userList;
    }

}
