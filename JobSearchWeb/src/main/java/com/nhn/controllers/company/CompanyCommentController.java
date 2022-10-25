package com.nhn.controllers.company;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Comment;
import com.nhn.entity.User;
import com.nhn.repository.CommentRepository;
import com.nhn.repository.UserRepository;
import com.nhn.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/comment")
public class CompanyCommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll(@RequestParam(name = "page", defaultValue = "1") String page,
                                         @RequestParam(name = "size", required = false, defaultValue = "5") String size) throws Exception {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);

        User companyUser = userRepository.findUserByUsername(companyUsername);
        if (companyUser == null || !companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception("Could not find company user with user = 'Company username'");

        Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").descending());
        Page<Comment> comments = commentRepository.findAllByCompany(companyUser.getCompany(), paging);

        return comments.getTotalElements() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Comments found", comments))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "Comments not found", null));
    }

}
