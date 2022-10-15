package com.nhn.mapper;

import com.nhn.common.Constant;
import com.nhn.entity.Candidate;
import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import com.nhn.entity.User;
import com.nhn.model.request.InsertCommentRequest;
import com.nhn.model.response.SimpleCommentResponse;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.CompanyRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CommentMapper {

    @Autowired
    private UserRepository userRepository;

    public Comment toEntity(String candidateUsername, InsertCommentRequest request) {
        Comment comment = new Comment();

        Optional<User> companyUser = userRepository.findById(request.getCompanyUserId());
        if (companyUser.isPresent())
            comment.setCompany(companyUser.get().getCompany());
        else
            return null;

        User candidateUser = userRepository.findUserByUsername(candidateUsername);
        if (candidateUser != null && candidateUser.getRole().equals(Constant.USER_ROLE.CANDIDATE))
            comment.setCandidate(candidateUser.getCandidate());
        else
            return null;

        comment.setContent(request.getContent());

        return comment;
    }

    public SimpleCommentResponse toResponse(Comment comment) {
        SimpleCommentResponse response = new SimpleCommentResponse();

        User candidateUser = userRepository.findOneByCandidate(comment.getCandidate());

        response.setFullName(candidateUser.getFullName());
        response.setAvatar(candidateUser.getAvatar());
        response.setContent(comment.getContent());
        response.setCreatedDate(comment.getCreatedDate());

        return response;
    }

    public List<SimpleCommentResponse> toCommentShowResponseList(List<Comment> comments) {
        List<SimpleCommentResponse> responseList = new ArrayList<>();

        comments.forEach(comment -> responseList.add(toResponse(comment)));

        return responseList;
    }

}
