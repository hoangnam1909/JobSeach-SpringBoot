package com.nhn.service;

import com.nhn.entity.User;
import com.nhn.model.request.authed_request.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {


//    UserDTO updateUser(String username, UpdateUserRequest request);

    User currentUser();

    User update(String username, UpdateUserRequest request, MultipartFile file);

    User updateProfileInfo(String username, UpdateUserRequest request);

    User updateAvatar(String username, MultipartFile file);

    boolean changePassword(User user, String currentPassword, String newPassword);

    boolean updateResetPassword(String email, String token);

    boolean resetPassword(String resetPasswordToken, String newPassword);

}
