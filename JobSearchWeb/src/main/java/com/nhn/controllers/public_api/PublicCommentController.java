package com.nhn.controllers.public_api;

import com.nhn.common.RespondObject;
import com.nhn.entity.Comment;
import com.nhn.entity.Company;
import com.nhn.mapper.CommentMapper;
import com.nhn.repository.CompanyRepository;
import com.nhn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/comment")
public class PublicCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("")
    ResponseEntity<RespondObject> getByCompanyId(@RequestParam(name = "companyId") @Valid Integer companyId) {
        Optional<Company> company = companyRepository.findById(companyId);

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

}
