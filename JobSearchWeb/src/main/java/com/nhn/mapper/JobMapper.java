package com.nhn.mapper;

import com.nhn.dto.request.JobRequest;
import com.nhn.model.*;
import com.nhn.repository.JobCategoryRepository;
import com.nhn.repository.JobTypeRepository;
import com.nhn.repository.PositionRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Job toEntity(JobRequest req) {
        Job job = new Job();

        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setJobStartDate(req.getJobStartDate());
        job.setLocation(req.getLocation());
        job.setNoOfVacancies(req.getNoOfVacancies());

        Optional<Position> position = positionRepository.findById(req.getPositionId());
        position.ifPresent(job::setPosition);

        Optional<JobCategory> jobCategory = jobCategoryRepository.findById(req.getJobCategoryId());
        jobCategory.ifPresent(job::setJobCategory);

        Optional<JobType> jobType = jobTypeRepository.findById(req.getJobTypeId());
        jobType.ifPresent(job::setJobType);

        Optional<User> userCompany = Optional.ofNullable(userRepository.findOneByUsernameEqualsIgnoreCase(req.getCompanyUsername()));
        userCompany.ifPresent(job::setUser);

        job.setBeginningSalary(req.getBeginningSalary());
        job.setEndingSalary(req.getEndingSalary());

        return job;
    }

}
