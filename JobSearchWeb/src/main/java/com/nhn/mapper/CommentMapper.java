package com.nhn.mapper;

import com.nhn.model.request.InsertCommentRequest;
import com.nhn.model.response.SimpleCommentResponse;
import com.nhn.entity.Candidate;
import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import com.nhn.entity.User;
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

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Comment toEntity(InsertCommentRequest request) {
        Comment comment = new Comment();

        Optional<Company> company = companyRepository.findById(request.getCompanyId());
        if (company.isPresent())
            comment.setCompany(company.get());
        else
            return null;

        Optional<Candidate> candidate = candidateRepository.findById(request.getCandidateId());
        if (candidate.isPresent())
            comment.setCandidate(candidate.get());
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
