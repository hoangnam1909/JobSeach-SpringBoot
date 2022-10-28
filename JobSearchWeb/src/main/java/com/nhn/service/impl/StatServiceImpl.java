package com.nhn.service.impl;

import com.nhn.common.Constant;
import com.nhn.entity.JobCategory;
import com.nhn.entity.JobType;
import com.nhn.entity.Position;
import com.nhn.repository.*;
import com.nhn.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Override
    public List<Map<String, String>> statNumberOfJobWithJobCategory() {
        List<Map<String, String>> result = new ArrayList<>();
        List<JobCategory> jobCategoryList = jobCategoryRepository.findAll();

        jobCategoryList.forEach(jobCategory -> {
            int counter = jobCategory.getJobs().size();
            if (counter != 0) {
                Map<String, String> map = new HashMap<>();
                map.put("jobCategoryName", jobCategory.getName());
                map.put("numberOfJobs", String.valueOf(counter));

                result.add(map);
            }
        });

        return result;
    }

    @Override
    public List<Map<String, String>> statNumberOfJobWithPosition() {
        List<Map<String, String>> result = new ArrayList<>();
        List<Position> positionList = positionRepository.findAll();

        positionList.forEach(position -> {
            int counter = position.getJobs().size();
            if (counter != 0) {
                Map<String, String> map = new HashMap<>();
                map.put("positionName", position.getName());
                map.put("numberOfJobs", String.valueOf(counter));

                result.add(map);
            }
        });

        return result;
    }

    @Override
    public List<Map<String, String>> statNumberOfJobWithJobType() {
        List<Map<String, String>> result = new ArrayList<>();
        List<JobType> jobTypeList = jobTypeRepository.findAll();

        jobTypeList.forEach(jobType -> {
            int counter = jobType.getJobs().size();
            if (counter != 0) {
                Map<String, String> map = new HashMap<>();
                map.put("jobTypeName", jobType.getName());
                map.put("numberOfJobs", String.valueOf(counter));

                result.add(map);
            }
        });

        return result;
    }

    @Override
    public Map<String, String> statApplyJob(Date fromDate, Date toDate) {

        List<ApplyJobRepository.ApplyJobInfoOnly> applyJobInfoOnlyList = applyJobRepository.findAllBy();

        AtomicInteger pendingCounter = new AtomicInteger();
        AtomicInteger approveCounter = new AtomicInteger();

        applyJobInfoOnlyList.forEach(applyJob -> {
            if (applyJob.getCreatedDate().after(fromDate) && applyJob.getCreatedDate().before(toDate)) {
                pendingCounter.getAndIncrement();
            }

            if (applyJob.getModifiedDate() != null) {
                if (applyJob.getModifiedDate().after(fromDate)
                        && applyJob.getModifiedDate().before(toDate)
                        && applyJob.getStatus().equals(Constant.APPLYING_STATUS.APPROVED)) {
                    approveCounter.getAndIncrement();
                }
            }
        });

        Map<String, String> result = new HashMap<>();
        result.put("numberOfPending", String.valueOf(pendingCounter));
        result.put("numberOfApproved", String.valueOf(approveCounter));

        return result;
    }

    @Override
    public Map<String, String> statJobCreatedDate(Date fromDate, Date toDate) {

        List<JobRepository.JobDateInfo> jobDateInfos = jobRepository.findAllBy();

        AtomicInteger counter = new AtomicInteger();

        jobDateInfos.forEach(jobDateInfo -> {
            if (jobDateInfo.getPublishedDate().after(fromDate) && jobDateInfo.getPublishedDate().before(toDate)) {
                counter.getAndIncrement();
            }
        });

        Map<String, String> result = new HashMap<>();
        result.put("counter", String.valueOf(counter));

        return result;
    }

}
