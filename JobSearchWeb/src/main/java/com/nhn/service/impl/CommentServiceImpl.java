package com.nhn.service.impl;

import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import com.nhn.repository.CommentRepository;
import com.nhn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAllIsAvailableByCompany(Company company) {
        List<Comment> comments = commentRepository.findAllByCompany(company);

        comments.removeIf(comment -> (!comment.isAvailable()));

        return comments;
    }

    @Override
    public boolean delete(int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty())
            return false;
        else {
            Comment commentDeleting = comment.get();
            commentDeleting.setAvailable(false);
            commentRepository.save(commentDeleting);

            return true;
        }
    }

}
