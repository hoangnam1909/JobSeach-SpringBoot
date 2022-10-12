package com.nhn.controllers.candidate;

import com.nhn.common.RespondObject;
import com.nhn.entity.Comment;
import com.nhn.mapper.CommentMapper;
import com.nhn.model.request.InsertCommentRequest;
import com.nhn.repository.CommentRepository;
import com.nhn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/candidate/api/comment")
public class CandidateCommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    /*
        Ứng viên thêm comment vào trang của nhà tuyển dụng
    */
    @PostMapping("/add")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid InsertCommentRequest request) {

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

    /*
        Ứng viên xoá comment
    */
    @DeleteMapping("/delete/{comment-id}")
    ResponseEntity<RespondObject> delete(@PathVariable(name = "comment-id") int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Id does not exist", commentId)
            );
        } else {
            boolean deleteCheck = commentService.delete(commentId);
            if (deleteCheck)
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Comment deleted", "")
                );
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Comment delete failed", "")
                );
        }
    }

}
