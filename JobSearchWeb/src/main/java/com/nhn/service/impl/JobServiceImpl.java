package com.nhn.service.impl;

import com.nhn.entity.Job;
import com.nhn.entity.JobTag;
import com.nhn.entity.Requirement;
import com.nhn.mapper.JobMapper;
import com.nhn.model.request.CreateJobRequest;
import com.nhn.model.request.JobUpdateRequest;
import com.nhn.model.request.RequirementRequest;
import com.nhn.model.request.admin_request.job.AdminUpdateJobRequest;
import com.nhn.repository.JobRepository;
import com.nhn.repository.JobTagRepository;
import com.nhn.repository.RequirementRepository;
import com.nhn.repository.UserRepository;
import com.nhn.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobTagRepository jobTagRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Job update(AdminUpdateJobRequest request) {
        try {
            Optional<Job> jobOptional = jobRepository.findById(request.getId());

            if (jobOptional.isPresent()) {
                Job job = jobMapper.toEntity(request);

                jobTagRepository.deleteAll(jobTagRepository.findAllByJobId(request.getId()));
                for (Integer tagId : request.getTagsId())
                    jobTagRepository.save(new JobTag(request.getId(), tagId));

                requirementRepository.deleteAll(requirementRepository.findAllByJobId(request.getId()));
                for (RequirementRequest requirementRequest : request.getRequirements())
                    requirementRepository.save(new Requirement(requirementRequest.getContent(), job));

                jobRepository.save(job);
                jobRepository.flush();

                Optional<Job> jobUpdated = jobRepository.findById(request.getId());

                if (jobUpdated.isPresent()) {
                    jobRepository.flush();
                    Job jobResult = jobUpdated.get();
                    entityManager.refresh(jobResult);

                    return jobResult;
                } else {
                    return null;
                }
            }

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Job add(String companyUsername, CreateJobRequest request) {
        try {
            Job job = jobMapper.toEntity(request);
            job.setCompanyUser(userRepository.findUserByUsername(companyUsername));

            Job jobSaved = jobRepository.save(job);
            jobRepository.flush();

            for (Integer tagId : request.getTagsId())
                jobTagRepository.save(new JobTag(jobSaved.getId(), tagId));

            for (RequirementRequest requirementRequest : request.getRequirements())
                requirementRepository.save(new Requirement(requirementRequest.getContent(), job));

            jobRepository.flush();
            entityManager.refresh(jobSaved);
            return jobSaved;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Job update(JobUpdateRequest request) {
        try {
            Optional<Job> jobWithId = jobRepository.findById(request.getId());

            if (jobWithId.isPresent()) {
                Job job = jobMapper.toEntity(jobWithId.get(), request);

                jobTagRepository.deleteAll(jobTagRepository.findAllByJobId(request.getId()));
                for (Integer tagId : request.getTagsId())
                    jobTagRepository.save(new JobTag(request.getId(), tagId));

                requirementRepository.deleteAll(requirementRepository.findAllByJobId(request.getId()));
                for (RequirementRequest requirementRequest : request.getRequirements())
                    requirementRepository.save(new Requirement(requirementRequest.getContent(), job));

                jobRepository.save(job);
                jobRepository.flush();

                Optional<Job> jobUpdated = jobRepository.findById(request.getId());

                if (jobUpdated.isPresent()) {
                    jobRepository.flush();
                    Job jobResult = jobUpdated.get();
                    entityManager.refresh(jobResult);

                    return jobResult;
                } else {
                    return null;
                }
            }

            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(int jobId) {
        try {
            Optional<Job> job = jobRepository.findById(jobId);

            if (job.isPresent()) {
                Job jobDeleting = job.get();
                jobDeleting.setAvailable(false);

                jobRepository.save(jobDeleting);
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
