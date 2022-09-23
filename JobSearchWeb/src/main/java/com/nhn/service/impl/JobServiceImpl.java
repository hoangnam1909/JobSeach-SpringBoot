package com.nhn.service.impl;

import com.nhn.dto.request.JobUpdateRequest;
import com.nhn.mapper.JobMapper;
import com.nhn.model.Job;
import com.nhn.model.JobTag;
import com.nhn.repository.JobRepository;
import com.nhn.repository.JobTagRepository;
import com.nhn.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobTagRepository jobTagRepository;

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
