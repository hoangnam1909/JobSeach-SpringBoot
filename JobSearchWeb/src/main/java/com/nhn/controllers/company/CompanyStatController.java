package com.nhn.controllers.company;

import com.nhn.Util.DateUtils;
import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/stat")
public class CompanyStatController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/job-applied/{job-id}")
    ResponseEntity<RespondObject> statJobApplied(@PathVariable(name = "job-id") int jobId) {
        try {
            Optional<Job> job = jobRepository.findById(jobId);
            if (job.isEmpty())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Job not found", ""));

            List<ApplyJob> applyJobs = applyJobRepository.findByJobAppliedOrderByCreatedDateDesc(job.get());
            List<Date> applyJobsDateList = new ArrayList<>();
            for (ApplyJob applyJob : applyJobs) {
                applyJobsDateList.add(applyJob.getCreatedDate());
            }

            Set<Date> dateList = DateUtils.distinctDate(applyJobsDateList);

            for (Date date : dateList) {
                System.err.println(applyJobs.stream()
                        .filter(applyJob -> DateUtils.removeTime(applyJob.getCreatedDate()).compareTo(date) == 0)
                        .count()
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", "msg", applyJobRepository.findAllDate()));
    }

}
