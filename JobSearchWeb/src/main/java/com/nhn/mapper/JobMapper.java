package com.nhn.mapper;

import com.nhn.dto.request.JobRequest;
import com.nhn.dto.request.JobUpdateRequest;
import com.nhn.model.*;
import com.nhn.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JobMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

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
        userCompany.ifPresent(job::setCompanyUser);

        job.setSalary(req.getSalary());

        return job;
    }

    public Job toEntity(JobUpdateRequest req) {
        Optional<Job> jobById = jobRepository.findById(req.getId());

        if (jobById.isPresent()) {
            Job job = jobById.get();

//            job.setId(req.getId());
            job.setTitle(req.getTitle());
            job.setDescription(req.getDescription());
            job.setJobStartDate(req.getJobStartDate());
            job.setLocation(req.getLocation());
            job.setNoOfVacancies(req.getNoOfVacancies());
            job.setSalary(req.getSalary());

            if (job.getPosition().getId() != req.getPositionId()) {
                Optional<Position> position = positionRepository.findById(req.getPositionId());
                position.ifPresent(job::setPosition);
            }

            if (job.getJobCategory().getId() != req.getJobCategoryId()) {
                Optional<JobCategory> jobCategory = jobCategoryRepository.findById(req.getJobCategoryId());
                jobCategory.ifPresent(job::setJobCategory);
            }

            if (job.getJobType().getId() != req.getJobTypeId()) {
                Optional<JobType> jobType = jobTypeRepository.findById(req.getJobTypeId());
                jobType.ifPresent(job::setJobType);
            }

            return job;
        }

        return null;
    }

}
