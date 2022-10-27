package com.nhn.controllers.company;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.entity.Job_;
import com.nhn.entity.User;
import com.nhn.entity.User_;
import com.nhn.repository.UserRepository;
import com.nhn.specifications.JobSpecification;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.UserSpecification;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import com.nhn.specifications.key.UserEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/candidate")
public class CompanyCandidateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecificationConverter specificationConverter;

    @PostMapping("/search")
    ResponseEntity<RespondObject> getAll(@RequestBody(required = false) Map<String, String> params,
                                         @RequestParam(name = "page", defaultValue = "1") String page,
                                         @RequestParam(name = "size", required = false, defaultValue = "5") String size) {

        System.err.println("get jobs" + page);

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            UserSpecification specification = specificationConverter.userSpecification(params);
            specification.add(new SearchCriteria(User_.ACTIVE, true, SearchOperation.EQUAL));
            specification.add(new SearchCriteria(User_.ROLE, Constant.USER_ROLE.CANDIDATE, SearchOperation.EQUAL));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by(User_.FULL_NAME).descending());

            Page<User> foundUsers = userRepository.findAll(specification, paging);

            if (foundUsers.getTotalElements() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No users found", new ArrayList<>()));

            if (Integer.parseInt(page) > foundUsers.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad request", "Page number of out range", "Page number = " + page));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Users found", foundUsers));
        } else {
            UserSpecification specification = new UserSpecification();
            specification.add(new SearchCriteria(User_.ACTIVE, true, SearchOperation.EQUAL));
            specification.add(new SearchCriteria(User_.ROLE, Constant.USER_ROLE.CANDIDATE, SearchOperation.EQUAL));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by(User_.FULL_NAME).descending());

            Page<User> foundUsers = userRepository.findAll(specification, paging);

            return foundUsers.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Users found", foundUsers))
                    :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Not found", "No users found", new ArrayList<>()));
        }
    }

}
