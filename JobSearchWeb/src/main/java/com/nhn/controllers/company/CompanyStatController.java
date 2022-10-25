package com.nhn.controllers.company;

import com.nhn.common.RespondObject;
import com.nhn.entity.ApplyJob;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.repository.ApplyJobRepository;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.util.DateUtils;
import com.nhn.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/stat")
public class CompanyStatController {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping("/job-applied/{job-id}")
    ResponseEntity<RespondObject> statJobApplied(@PathVariable(name = "job-id") int jobId) {
        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String companyUsername = jwtUtils.extractUsername(accessToken);
            User companyUser = userRepository.findUserByUsername(companyUsername);

            Optional<Job> job = jobRepository.findById(jobId);

            if (job.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Failed", "Job not found", ""));
            } else {
                if (job.get().getCompanyUser().getId() != companyUser.getId())
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                            new RespondObject("Forbidden", String.format("Job with id = %s is not yours", jobId), null));
            }

            List<ApplyJob> applyJobs = applyJobRepository.findByJobApplied(job.get());

            List<Date> applyJobsDateList = new ArrayList<>();
            for (ApplyJob applyJob : applyJobs) {
                applyJobsDateList.add(applyJob.getCreatedDate());
            }

            applyJobsDateList.sort(Comparator.reverseOrder());

            List<Date> dateList = DateUtils.listRemoveTime(applyJobsDateList);
            applyJobsDateList.sort(Comparator.reverseOrder());
            List<Date> sorted = dateList.stream()
                    .sorted(Comparator.comparingLong(Date::getTime)).toList();

            Set<Date> dateSet = new HashSet<>(sorted);

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
