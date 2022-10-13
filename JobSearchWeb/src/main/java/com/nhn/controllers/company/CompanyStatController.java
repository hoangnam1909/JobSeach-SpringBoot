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
import java.util.stream.Collectors;

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

            List<ApplyJob> applyJobs = applyJobRepository.findByJobApplied(job.get());
//            System.err.println("applyJobs repo size = " + applyJobs.size());

            List<Date> applyJobsDateList = new ArrayList<>();
            for (ApplyJob applyJob : applyJobs) {
                applyJobsDateList.add(applyJob.getCreatedDate());
            }

//            System.err.println("before sort + " + applyJobsDateList.size());
//            for (Date date : applyJobsDateList) {
//                System.err.println(date);
//            }

            applyJobsDateList.sort(Comparator.reverseOrder());
//            System.err.println("sorted + " + applyJobsDateList.size());
//            for (Date date : applyJobsDateList) {
//                System.err.println(date);
//            }

            List<Date> dateList = DateUtils.listRemoveTime(applyJobsDateList);
            applyJobsDateList.sort(Comparator.reverseOrder());
            List<Date> sorted = dateList.stream()
                    .sorted(Comparator.comparingLong(Date::getTime)).toList();
//            System.err.println("sorted sof");
//            for (Date date : sorted) {
//                System.err.println(date);
//            }

            Set<Date> dateSet = new HashSet<>(sorted);
//            System.err.println("\n**distinct sorted size = " + dateSet.size());
//            for (Date date : dateSet) {
//                System.err.println(date);
//            }
//            System.err.println("\n** end distinct sorted");

            List<Map<String, Object>> statResult = new ArrayList<>();
            for (Date date : dateSet) {
                Map<String, Object> map = new HashMap<>();
                map.put("date", date);

                long counter = applyJobs.stream()
                        .filter(applyJob -> DateUtils.removeTime(applyJob.getCreatedDate()).compareTo(date) == 0)
                        .count();
                map.put("counter", counter);

                statResult.add(map);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "stat number of candidate apply for job by date", statResult));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Error", ex.getMessage(), ex.toString()));
        }

    }

}
