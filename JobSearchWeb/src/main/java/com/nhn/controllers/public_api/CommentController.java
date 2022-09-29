package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.CommentInsertRequest;
import com.nhn.mapper.CommentMapper;
import com.nhn.model.Comment;
import com.nhn.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("")
    ResponseEntity<RespondObject> insert(@RequestBody CommentInsertRequest request) {

        try {
            Comment comment = commentMapper.toEntity(request);
            Comment commentChecking;

            if (comment != null)
                commentChecking = commentRepository.save(comment);
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Request is not valid", request)
                );

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Comment saved", commentChecking));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Save comment failed", ex.getMessage())
            );
        }
    }

}
