package com.nhn.controllers.admin;

import com.nhn.common.RespondObject;
import com.nhn.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/admin/api/stats")
public class AdminStatsController {

    @Autowired
    private StatService statService;

    @GetMapping("/job-by-category-all-time")
    ResponseEntity<RespondObject> applyJobByCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statNumberOfJobWithJobCategoryAllTime()));
    }

}
