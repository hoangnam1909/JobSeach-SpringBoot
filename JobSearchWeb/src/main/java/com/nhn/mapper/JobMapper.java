package com.nhn.mapper;

import com.nhn.entity.*;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JobMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    public Job toEntity(CreateJobRequest req) {
        Job job = new Job();

        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setJobStartDate(req.getJobStartDate());

        job.setAddress(req.getAddress());

        Optional<Province> province = provinceRepository.findById(req.getProvinceId());
        province.ifPresent(job::setProvince);

        job.setNoOfVacancies(req.getNoOfVacancies());

        Optional<Position> position = positionRepository.findById(req.getPositionId());
        position.ifPresent(job::setPosition);

        Optional<JobCategory> jobCategory = jobCategoryRepository.findById(req.getJobCategoryId());
        jobCategory.ifPresent(job::setJobCategory);

        Optional<JobType> jobType = jobTypeRepository.findById(req.getJobTypeId());
        jobType.ifPresent(job::setJobType);

//        Optional<User> userCompany = Optional.ofNullable(userRepository.findOneByUsernameEqualsIgnoreCase(req.getCompanyUsername()));
//        userCompany.ifPresent(job::setCompanyUser);

        job.setSalary(req.getSalary());

        return job;
    }

    public Job toEntity(Job job, JobUpdateRequest request) {

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setJobStartDate(request.getJobStartDate());

        job.setAddress(request.getAddress());

        Optional<Province> province = provinceRepository.findById(request.getProvinceId());
        province.ifPresent(job::setProvince);

        job.setNoOfVacancies(request.getNoOfVacancies());
        job.setSalary(request.getSalary());

        if (job.getPosition().getId() != request.getPositionId()) {
            Optional<Position> position = positionRepository.findById(request.getPositionId());
            position.ifPresent(job::setPosition);
        }

        if (job.getJobCategory().getId() != request.getJobCategoryId()) {
            Optional<JobCategory> jobCategory = jobCategoryRepository.findById(request.getJobCategoryId());
            jobCategory.ifPresent(job::setJobCategory);
        }

        if (job.getJobType().getId() != request.getJobTypeId()) {
            Optional<JobType> jobType = jobTypeRepository.findById(request.getJobTypeId());
            jobType.ifPresent(job::setJobType);
        }

        return job;
    }

    public List<Job> applyJobToJobList(List<ApplyJob> applyJobs) {
        List<Job> jobList = new ArrayList<>();

        applyJobs.forEach(applyJob -> {
            jobList.add(applyJob.getJobApplied());
        });

        return jobList;
    }

}
