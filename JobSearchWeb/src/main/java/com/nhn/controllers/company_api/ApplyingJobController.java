package com.nhn.controllers.company_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.nhn.common.RespondObject;
import com.nhn.model.ApplyingJob;
import com.nhn.repository.ApplyingJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/applying-job")
public class ApplyingJobController {

    @Autowired
    private ApplyingJobRepository applyingJobRepository;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/candidate-user-id/{candidate-user-id}")
    ResponseEntity<RespondObject> getByCandidateUserId(@PathVariable(name = "candidate-user-id") String candidateUserId) {
        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdCandidateUserId(Integer.parseInt(candidateUserId));

        if (applyingJobs.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Candidate user id does not exist", candidateUserId));

        return ResponseEntity.status(HttpStatus.FOUND).body(
                new RespondObject("Found", String.format("Applying job of candidate with userId = %s found", candidateUserId), applyingJobs));
    }

//    @GetMapping("/job-id/{job-id}")
//    ResponseEntity<RespondObject> getByJobId(@PathVariable(name = "job-id") String jobId) {
//
//        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdJobId(Integer.parseInt(jobId));
//
//        if (applyingJobs.isEmpty())
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                    new RespondObject("Fail", "Job id does not exist", jobId));
//
//        return ResponseEntity.status(HttpStatus.FOUND).body(
//                new RespondObject("Found", String.format("Applying job of job with jobId = %s found", jobId), applyingJobs));
//    }

    @GetMapping("/job-id/{job-id}")
    ResponseEntity<RespondObject> getByJobId(@PathVariable(name = "job-id") String jobId) throws JsonProcessingException {

        List<ApplyingJob> applyingJobs = applyingJobRepository.findByApplyingJobIdJobId(Integer.parseInt(jobId));
//        ObjectMapper mapper = new ObjectMapper();

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("applyingJobFilter", SimpleBeanPropertyFilter.serializeAllExcept("job", "user"));

        String jsonString = mapper.writer(filters)
                .withDefaultPrettyPrinter()
                .writeValueAsString(applyingJobs);

        JsonNode actualObj = mapper.readTree(jsonString);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new RespondObject("Fail", "Job id does not exist", actualObj));
    }

}
