package com.nhn.service;

import com.nhn.model.Comment;
import com.nhn.model.Company;

import java.util.List;

public interface CommentService {

    List<Comment> findAllIsAvailableByCompany(Company company);

    boolean delete(int commentId);

}
