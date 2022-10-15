package com.nhn.service;

import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import com.nhn.model.request.InsertCommentRequest;
import com.nhn.model.response.SimpleCommentResponse;

import java.util.List;

public interface CommentService {

    List<Comment> findAllIsAvailableByCompany(Company company);

    SimpleCommentResponse add(String candidateUsername, InsertCommentRequest request);

    boolean delete(int commentId);

}
