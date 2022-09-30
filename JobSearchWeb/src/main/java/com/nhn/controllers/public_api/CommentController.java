package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.dto.request.CommentInsertRequest;
import com.nhn.mapper.CommentMapper;
import com.nhn.model.Comment;
import com.nhn.model.Company;
import com.nhn.repository.CommentRepository;
import com.nhn.repository.CompanyRepository;
import com.nhn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll(@RequestParam(name = "companyId") String companyId) {
        Optional<Company> company = companyRepository.findById(Integer.valueOf(companyId));

        if (company.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "company id does not exist", companyId));
        else {
            List<Comment> commentList = commentService.findAllIsAvailableByCompany(company.get());

            if (commentList.size() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Fail", "No comment found", ""));
            else
                return ResponseEntity.status(HttpStatus.FOUND).body(
                        new RespondObject("Found", "Comments found", commentMapper.toCommentShowResponseList(commentList)));
        }
    }

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

    @PutMapping("/delete/{id}")
    ResponseEntity<RespondObject> delete(@PathVariable(name = "id") String commentId) {
        Optional<Comment> comment = commentRepository.findById(Integer.valueOf(commentId));

        if (comment.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Id does not exist", commentId)
            );
        else {
            boolean deleteCheck = commentService.delete(Integer.parseInt(commentId));
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
