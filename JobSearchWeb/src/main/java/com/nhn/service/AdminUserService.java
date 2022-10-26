package com.nhn.service;

import com.nhn.entity.User;
import com.nhn.model.request.admin_request.user.AdminAddUserRequest;
import com.nhn.model.request.admin_request.user.AdminUpdateUserRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AdminUserService {

    User add(AdminAddUserRequest request);

    User updateProfileInfo(int userId, AdminUpdateUserRequest request);

    User updateUserAvatar(int userId, MultipartFile file);


}
