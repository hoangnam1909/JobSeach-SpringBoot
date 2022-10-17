package com.nhn.controllers.pub;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Comment;
import com.nhn.entity.User;
import com.nhn.mapper.CommentMapper;
import com.nhn.repository.UserRepository;
import com.nhn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/comment")
public class PublicCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/{company-user-id}")
    ResponseEntity<RespondObject> getByCompanyId(@PathVariable(name = "company-user-id") int companyUserId) {
        Optional<User> companyUser = userRepository.findById(companyUserId);
        if (companyUser.isPresent() && companyUser.get().getRole().equals(Constant.USER_ROLE.COMPANY)) {
            List<Comment> commentList = commentService.findAllIsAvailableByCompany(companyUser.get().getCompany());

            if (commentList.size() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No comments found", ""));
            else
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Comments found", commentMapper.toCommentShowResponseList(commentList)));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "company id does not exist", companyUserId));
        }
    }

}
