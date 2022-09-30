package com.nhn.controllers;

import com.nhn.common.RespondObject;
import com.nhn.model.Comment;
import com.nhn.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestAPI {

    @Autowired
    private CommentRepository commentRepository;

//    @GetMapping("")
//    ResponseEntity<RespondObject> getAll(@RequestParam(name = "companyId") String companyId) {
//        Comment comment = commentRepository.findOneByCompanyId();
//
//        if (commentList.size() == 0)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new RespondObject("Fail", "No comment found", ""));
//        else
//            return ResponseEntity.status(HttpStatus.FOUND).body(
//                    new RespondObject("Fail", "Comments found", toCommentShowResponseList(commentList)));
//    }

}
