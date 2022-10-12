package com.nhn.controllers.company;

import com.nhn.common.Constant;
import com.nhn.common.RespondObject;
import com.nhn.common.SearchCriteria;
import com.nhn.entity.Job;
import com.nhn.entity.User;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.model.request.company.CompanyJobRequest;
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

    /*
        Nhà tuyển dụng lấy ra danh sách tất cả tin tuyển dụng
    */
    @GetMapping("/get-list/{company-username}")
    ResponseEntity<RespondObject> getAllList(@PathVariable(name = "company-username") String companyUsername) throws Exception {

        User companyUser = userRepository.findUserByUsername(companyUsername);
        if (companyUser == null || !companyUser.getRole().equals(Constant.USER_ROLE.COMPANY))
            throw new Exception(String.format("Could not find company user with user = '%s'", companyUsername));

        JobSpecification specification = new JobSpecification();
        specification.add(new SearchCriteria(JobEnum.COMPANY_USERNAME, companyUsername, SearchOperation.COMPANY_USERNAME));
        specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

        List<Job> foundJobs = jobRepository.findAll(specification);

        return foundJobs.size() > 0 ?
                ResponseEntity.status(HttpStatus.FOUND).body(
                        new RespondObject("Found", "Jobs found", foundJobs)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("Not found", "No jobs found", new ArrayList<>())
                );
    }

    /*
        Nhà tuyển dụng lấy ra danh sách tất cả tin tuyển dụng có phân trang và các điều kiện kèm theo
    */
    @PostMapping("/get/{company-username}")
    ResponseEntity<RespondObject> getAll(@RequestBody(required = false) Map<String, String> params,
                                         @PathVariable(name = "company-username") String companyUsername,
                                         @RequestParam(name = "page", defaultValue = "1") String page,
                                         @RequestParam(name = "size", required = false, defaultValue = "5") String size) throws Exception {

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

            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new RespondObject("Found", "Jobs found", foundJobs));
        } else {
            JobSpecification specification = new JobSpecification();
            specification.add(new SearchCriteria(JobEnum.AVAILABLE, true, SearchOperation.AVAILABLE));

            Pageable paging = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size), Sort.by("id").ascending());

            Page<Job> foundJobs = jobRepository.findAll(specification, paging);

            return foundJobs.getContent().size() > 0 ?
                    ResponseEntity.status(HttpStatus.FOUND).body(
                            new RespondObject("Found", "Jobs found", foundJobs)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new RespondObject("Not found", "No jobs found", new ArrayList<>())
                    );
        }
    }

    /*
        Nhà tuyển dụng đếm số tin tuyển dụng hiện khả dụng
    */
    @GetMapping("/count/{company-username}")
    ResponseEntity<RespondObject> count(@PathVariable(name = "company-username") String companyUsername) throws Exception {

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
        Nhà tuyển dụng lấy ra tin tuyển dụng
    */
    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getById(@PathVariable String id) {

        Optional<Job> job = jobRepository.findByIdAndAvailableIsTrue(Integer.parseInt(id));
        return job.map(value -> ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("Job found", "Job a found with id = " + id, value)
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new RespondObject("Processed", "No jobs found", "No data")
        ));
    }

    /*
        Nhà tuyển dụng thêm tin tuyển dụng
    */
    @PostMapping("/insert")
    ResponseEntity<RespondObject> insert(@RequestBody @Valid CreateJobRequest request) {

        try {
            Job job = jobService.add(request);
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
            Job job = jobService.update(request);
            return job != null ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new RespondObject("Ok", "Job updated", job)
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
    @DeleteMapping("/delete")
    ResponseEntity<RespondObject> delete(@RequestBody @Valid CompanyJobRequest request) {
        boolean deleteCheck = jobService.delete(request.getJobId());

        if (deleteCheck)
            return ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("Ok", "Job deleted", ""));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RespondObject("Fail", "Job delete failed", ""));
    }

}
