package com.nhn.mapper;

import com.nhn.common.Constant;
import com.nhn.dto.UserDTO;
import com.nhn.dto.request.AdminUserInsertRequest;
import com.nhn.dto.request.UserSignUpRequest;
import com.nhn.dto.request.UserUpdateRequest;
import com.nhn.model.Candidate;
import com.nhn.model.Company;
import com.nhn.model.User;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    @Autowired
    private CompanyRepository companyRepository;

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
        user.setRole(req.getRole());
        user.setActive(req.isActive());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setDob(req.getDob());
        user.setGender(req.isGender());
        user.setAddress(req.getAddress());

        Optional<Company> company = companyRepository.findById(req.getCompanyId());
        if (company.isPresent())
            user.setCompany(company.get());

        Optional<Candidate> candidate = candidateRepository.findById(req.getCandidateId());
        if (candidate.isPresent())
            user.setCandidate(candidate.get());

        return user;
    }

    public User toEntity(AdminUserInsertRequest req) {
        User user = new User();

        user.setUsername(req.getUsername());
        user.setAvatar(req.getAvatar());
        user.setRole(req.getRole());
        user.setActive(req.isActive());
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setDob(req.getDob());
        user.setGender(req.isGender());
        user.setAddress(req.getAddress());

        if (req.getRole().equals(Constant.USER_ROLE.COMPANY)) {
            Company company = new Company();
            user.setCompany(companyRepository.save(company));
        } else if (req.getRole().equals(Constant.USER_ROLE.CANDIDATE)) {
            Candidate candidate = new Candidate();
            user.setCandidate(candidateRepository.save(candidate));
        }

        return user;
    }

    public User toEntity(UserSignUpRequest req) {
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
        user.setRole(req.getRole());

        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setRole(user.getRole());
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
