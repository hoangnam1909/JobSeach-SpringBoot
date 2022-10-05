package com.nhn.repository;

import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByCompany(Company company);

}
