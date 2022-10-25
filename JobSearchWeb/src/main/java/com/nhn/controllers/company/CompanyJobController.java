package com.nhn.controllers.company;

import com.nhn.util.JwtUtils;
import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.JobService;
import com.nhn.specifications.JobSpecification;
import com.nhn.specifications.SpecificationConverter;
import com.nhn.specifications.key.JobEnum;
import com.nhn.specifications.key.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/company/api/job")
public class CompanyJobController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobService jobService;

    @Autowired
    private SpecificationConverter specificationConverter;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest servletRequest;

    /*
        Nhà tuyển dụng lấy ra tin tuyển dụng theo id
    */
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Job> jobOptional = jobRepository.findByIdAndAvailableIsTrue(Integer.parseInt(id));

        if (jobOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("Processed", "No job found", "No data"));
        } else {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String companyUsername = jwtUtils.extractUsername(accessToken);
            User companyUser = userRepository.findUserByUsername(companyUsername);

            Job job = jobOptional.get();
            if (job.isAvailable()) {
                if (companyUser != null && companyUser.getId() == job.getCompanyUser().getId())
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Found job with id = " + id, job));
                else
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                            new RespondObject("Forbidden", String.format("Job with id = %s is not yours", id), null));
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad Request", String.format("Job with id = %s is unavailable", id), job));
        }
    }

    /*
        Nhà tuyển dụng lấy ra danh sách tất cả tin tuyển dụng
    */
    @GetMapping("/get-list")
    ResponseEntity<RespondObject> getList() throws Exception {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);

        User companyUser = userRepository.findUserByUsername(companyUsername);
        if (companyUser == null || !companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception(String.format("Could not find company user with user = '%s'", companyUsername));

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        List<Job> foundJobs = jobRepository.findAll(specification);

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("Ok", "Jobs found", foundJobs)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No jobs found", new ArrayList<>())
                );
    }

    /*
        Nhà tuyển dụng đếm số tin tuyển dụng hiện khả dụng
    */
    @GetMapping("/count")
    ResponseEntity<RespondObject> count() throws Exception {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);

        User companyUser = userRepository.findUserByUsername(companyUsername);
        if (companyUser == null || !companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception(String.format("Could not find company user with user = '%s'", companyUsername));

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Job found", "Jobs counted", jobRepository.count(specification)));
    }

    /*
        Nhà tuyển dụng lấy ra danh sách tất cả tin tuyển dụng có phân trang và các điều kiện kèm theo
    */
    @PostMapping("/get")
    ResponseEntity<RespondObject> getWithCondition(@RequestBody(required = false) Map<String, String> params,
                                                   @RequestParam(name = "page", defaultValue = "1") String page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "5") String size) throws Exception {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);

        User companyUser = userRepository.findUserByUsername(companyUsername);
        if (companyUser == null || !companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception(String.format("Could not find company user with user = '%s'", companyUsername));

        System.err.println("get jobs" + page);

        if (params != null) {
            if (Integer.parseInt(page) <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Fail", "Page number of out range", ""));
            }

            JobSpecification specification = specificationConverter.jobSpecification(params);
            specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            if (foundJobs.getTotalElements() == 0)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No job found", new ArrayList<>()));

            if (Integer.parseInt(page) > foundJobs.getTotalPages()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new RespondObject("Bad request", "Page number of out range", "Page number = " + page));
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Jobs found", foundJobs));
        } else {
            JobSpecification specification = new JobSpecification();
            specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            return foundJobs.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Not found", "No jobs found", new ArrayList<>())
                    );
        }
    }

    /*
        Nhà tuyển dụng thêm tin tuyển dụng
    */
    @PostMapping("/insert")
    ResponseEntity<RespondObject> insert(@RequestBody CreateJobRequest request) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String companyUsername = jwtUtils.extractUsername(accessToken);

            Job job = jobService.add(companyUsername, request);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job saved", job));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Save failed", ex.getMessage())
            );
        }
    }

    /*
        Nhà tuyển dụng cập nhật tin tuyển dụng
    */
    @PutMapping("/update")
    ResponseEntity<RespondObject> update(@RequestBody @Valid JobUpdateRequest request) {

        try {
            String accessToken = servletRequest.getHeader("authorization").substring(4);
            String companyUsername = jwtUtils.extractUsername(accessToken);
            User companyUser = userRepository.findUserByUsername(companyUsername);

            Optional<Job> job = jobRepository.findById(request.getId());

            if (job.get().getCompanyUser().getId() != companyUser.getId())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                        new RespondObject("Forbidden", String.format("Job with id = %s is not yours", request.getId()), null));

            Job jobUpdated = jobService.update(request);
            return jobUpdated != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Job updated", job.get())
                    ) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            new RespondObject("Failed", "Update job failed", null)
                    );
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RespondObject("Fail", "Job update failed", ex.getMessage())
            );
        }
    }

    /*
        Nhà tuyển dụng xoá tin tuyển dụng
    */
    @DeleteMapping("/delete/{job-id}")
    ResponseEntity<RespondObject> delete(@PathVariable(name = "job-id") int jobId) {

        String accessToken = servletRequest.getHeader("authorization").substring(4);
        String companyUsername = jwtUtils.extractUsername(accessToken);
        User companyUser = userRepository.findUserByUsername(companyUsername);

        Optional<Job> job = jobRepository.findById(jobId);

        if (job.get().getCompanyUser().getId() != companyUser.getId())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    new RespondObject("Forbidden", String.format("Job with id = %s is not yours", jobId), null));

        boolean deleteCheck = jobService.delete(jobId);
        if (deleteCheck)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job deleted", ""));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Job delete failed", ""));
    }

}
