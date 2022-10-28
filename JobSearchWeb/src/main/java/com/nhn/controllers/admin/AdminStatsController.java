package com.nhn.controllers.admin;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.service.StatService;
import com.nhn.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin/api/stats")
public class AdminStatsController {

    @Autowired
    private StatService statService;

    @GetMapping("/jobs-by-published-date")
    ResponseEntity<RespondObject> jobByPublishedDate(@RequestBody Map<String, String> map) throws ParseException {
        Date fromDate = DateUtils.strToDate(map.get("fromDate"), Constant.DATE_FORMAT.FORMAT1);
        Date toDate = DateUtils.strToDate(map.get("toDate"), Constant.DATE_FORMAT.FORMAT1);

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statJobPublishedDate(fromDate, toDate)));
    }

    @GetMapping("/jobs-by-category")
    ResponseEntity<RespondObject> jobByCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statNumberOfJobWithJobCategory()));
    }

    @GetMapping("/jobs-by-position")
    ResponseEntity<RespondObject> jobByPosition() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statNumberOfJobWithPosition()));
    }

    @GetMapping("/jobs-by-job-type")
    ResponseEntity<RespondObject> jobByJobType() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statNumberOfJobWithJobType()));
    }

    @GetMapping("/apply-jobs")
    ResponseEntity<RespondObject> applyJob(@RequestBody Map<String, String> map) throws ParseException {
        Date fromDate = DateUtils.strToDate(map.get("fromDate"), Constant.DATE_FORMAT.FORMAT1);
        Date toDate = DateUtils.strToDate(map.get("toDate"), Constant.DATE_FORMAT.FORMAT1);

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject(HttpStatus.OK.name(), "Stat result", statService.statApplyJob(fromDate, toDate)));
    }

}
