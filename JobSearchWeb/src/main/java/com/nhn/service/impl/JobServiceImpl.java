package com.nhn.service.impl;

import com.nhn.dto.request.JobRequest;
import com.nhn.dto.request.JobUpdateRequest;
import com.nhn.dto.request.RequirementRequest;
import com.nhn.mapper.JobMapper;
import com.nhn.model.Job;
import com.nhn.model.JobTag;
import com.nhn.model.Requirement;
import com.nhn.repository.JobRepository;
import com.nhn.repository.JobTagRepository;
import com.nhn.repository.RequirementRepository;
import com.nhn.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobTagRepository jobTagRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Override
    public Job insert(JobRequest request) {
        Job job = jobMapper.toEntity(request);
        Job jobSaved = jobRepository.save(job);
        jobRepository.flush();

        List<RequirementRequest> requirements = request.getRequirements();
        if (requirements.size() != 0) {
            for (RequirementRequest requirementRequest : requirements) {
                requirementRepository.save(new Requirement(requirementRequest.getContent(), jobSaved));
            }
        }

        jobRepository.flush();
        return jobSaved;
    }

    @Override
    public Job update(JobUpdateRequest request) {
        try {
            Job job = jobMapper.toEntity(request);

            for (int i = 0; i < request.getTagsId().size(); i++) {
                System.err.println(request.getTagsId().get(i));
                jobTagRepository.save(new JobTag(request.getId(), request.getTagsId().get(i)));
            }

            return jobRepository.save(job);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
