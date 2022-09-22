package com.nhn.mapper;

import com.nhn.dto.request.JobRequest;
import com.nhn.model.Job;
import com.nhn.repository.JobCategoryRepository;
import com.nhn.repository.JobTypeRepository;
import com.nhn.repository.PositionRepository;
import com.nhn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        job.setPosition(positionRepository.getById(req.getPositionId()));
        job.setJobCategory(jobCategoryRepository.getById(req.getJobCategoryId()));
        job.setJobType(jobTypeRepository.getById(req.getJobTypeId()));
        job.setUser(userRepository.findOneByUsernameEqualsIgnoreCase(req.getCompanyUsername()));
        job.setBeginningSalary(req.getBeginningSalary());
        job.setEndingSalary(req.getEndingSalary());

        return job;
    }

}
