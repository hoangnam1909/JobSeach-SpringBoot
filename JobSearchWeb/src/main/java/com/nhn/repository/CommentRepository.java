package com.nhn.repository;

import com.nhn.model.Comment;
import com.nhn.model.Company;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByCompany(Company company);

}
