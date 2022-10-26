package com.nhn.mapper;

import com.nhn.common.Constant;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Candidate;
import com.nhn.entity.Company;
import com.nhn.entity.User;
import com.nhn.model.UserDTO;
import com.nhn.model.request.UserSignupRequest;
import com.nhn.model.request.admin_request.user.AdminAddUserRequest;
import com.nhn.model.request.admin_request.user.AdminUpdateUserRequest;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import com.nhn.model.response.CurrentUserResponse;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    @Autowired
    private UserRepository userRepository;

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

//    public User toEntityUpdate(User user, UpdateUserRequest request){
//
//        user.setAvatar(request.getAvatar());
//        user.setFullName(request.getFullName());
//        user.setEmail(request.getEmail());
//        user.setPhone(request.getPhone());
//        user.setDob(request.getDob());
//        user.setGender(request.isGender());
//        user.setAddress(request.getAddress());
//
//        return user;
//    }

//    public User toEntity(AdminAddUserRequest req) {
//        User user = new User();
//
//        user.setUsername(req.getUsername());
//        user.setAvatar(req.getAvatar());
//        user.setRole(req.getRole());
//        user.setActive(req.isActive());
//        user.setFullName(req.getFullName());
//        user.setEmail(req.getEmail());
//        user.setPhone(req.getPhone());
//        user.setDob(req.getDob());
//        user.setGender(req.isGender());
//        user.setAddress(req.getAddress());
//
//        if (req.getRole().equals(Constant.USER_ROLE.COMPANY)) {
//            Company company = new Company();
//            user.setCompany(companyRepository.save(company));
//        } else if (req.getRole().equals(Constant.USER_ROLE.CANDIDATE)) {
//            Candidate candidate = new Candidate();
//            user.setCandidate(candidateRepository.save(candidate));
//        }
//
//        return user;
//    }

    public User toEntity(AdminAddUserRequest request, String role) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setRole(role);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDob(request.getDob());
        user.setGender(request.isGender());
        user.setAddress(request.getAddress());

        if (role.equals(Constant.USER_ROLE.COMPANY)) {
            Company company = new Company();
            user.setCompany(companyRepository.save(company));
        } else if (role.equals(Constant.USER_ROLE.CANDIDATE)) {
            Candidate candidate = new Candidate();
            user.setCandidate(candidateRepository.save(candidate));
        }

        return user;
    }

//    public User toEntity(AdminUpdateUserRequest request, String role) {
//        User user = userRepository.findUserByIdAndRole(request.getId(), Constant.USER_ROLE.CANDIDATE);
//        if (user == null)
//            return null;
//
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setRole(Constant.USER_ROLE.CANDIDATE);
//        user.setActive(request.isActive());
//        user.setFullName(request.getFullName());
//        user.setEmail(request.getEmail());
//        user.setPhone(request.getPhone());
//        user.setDob(request.getDob());
//        user.setGender(request.isGender());
//        user.setAddress(request.getAddress());
//
//        return user;
//    }

    public User toEntity(UserSignupRequest req) {
        User user = new User();

        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());

        return user;
    }

    public User toEntity(User user, UpdateUserRequest request) {

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDob(request.getDob());

        System.err.println(Boolean.parseBoolean(request.getGender()));
        user.setGender(Boolean.parseBoolean(request.getGender()));
        user.setAddress(request.getAddress());

        return user;
    }

    public User toEntity(User user, AdminUpdateUserRequest request) {

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDob(request.getDob());

        user.setGender(request.isGender());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        user.setActive(request.isActive());

        return user;
    }

    public CurrentUserResponse toCurrentUserResponse(User user) {
        CurrentUserResponse response = new CurrentUserResponse();

        response.setUsername(user.getUsername());
        response.setAvatar(user.getAvatar());
        response.setRole(user.getRole());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setDob(user.getDob());
        response.setGender(user.isGender());
        response.setAddress(user.getAddress());
        response.setJoinedDate(user.getJoinedDate());

        if (user.getCandidate() != null)
            response.setCandidate(user.getCandidate());

        if (user.getCompany() != null)
            response.setCompany(user.getCompany());

        return response;
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

    public List<User> applyJobToUserList(List<ApplyJob> applyJobs) {
        List<User> userList = new ArrayList<>();

        applyJobs.forEach(applyJob -> {
            userList.add(applyJob.getCandidateUser());
        });

        return userList;
    }

}
