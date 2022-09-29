package com.nhn.mapper;

import com.nhn.dto.request.CommentInsertRequest;
import com.nhn.model.Candidate;
import com.nhn.model.Comment;
import com.nhn.model.Company;
import com.nhn.repository.CandidateRepository;
import com.nhn.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentMapper {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Comment toEntity(CommentInsertRequest request) {
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

}
