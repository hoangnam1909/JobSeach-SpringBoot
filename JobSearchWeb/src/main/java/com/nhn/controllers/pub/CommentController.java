package com.nhn.controllers.pub;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.entity.Comment;
import com.nhn.entity.User;
import com.nhn.repository.CommentRepository;
import com.nhn.repository.UserRepository;
import com.nhn.valid.CompanyUserId;
import com.nhn.valid.CompanyUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/public/api/{company-user-id}/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    ResponseEntity<RespondObject> getAll(@PathVariable(name = "company-user-id") Integer companyUserId,
                                         @RequestParam(name = "page", defaultValue = "1") String page,
                                         @RequestParam(name = "size", required = false, defaultValue = "5") String size) throws Exception {

        Optional<User> companyUser = userRepository.findById(companyUserId);
        if (companyUser.isEmpty() || !companyUser.get().getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception("Could not find company user with user = 'Company username'");

        Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").descending());
        Page<Comment> comments = commentRepository.findAllByCompany(companyUser.get().getCompany(), paging);

        return comments.getTotalElements() != 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Found", "Comments found", comments))
                :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "Comments not found", null));
    }

}
