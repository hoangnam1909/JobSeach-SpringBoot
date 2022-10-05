package com.nhn.service;

import com.nhn.entity.Comment;
import com.nhn.entity.Company;

import java.util.List;

public interface CommentService {

    List<Comment> findAllIsAvailableByCompany(Company company);

    boolean delete(int commentId);

}
