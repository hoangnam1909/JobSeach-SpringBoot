package com.nhn.service.impl;

import com.nhn.entity.JobCategory;
import com.nhn.repository.JobCategoryRepository;
import com.nhn.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Override
    public List<Map<String, String>> statNumberOfJobWithJobCategoryAllTime() {
        List<Map<String, String>> result = new ArrayList<>();
        List<JobCategory> jobCategoryList = jobCategoryRepository.findAll();

        jobCategoryList.forEach(jobCategory -> {
            int counter = jobCategory.getJobs().size();
            if (counter != 0) {
                Map<String, String> map = new HashMap<>();
                map.put("jobCategoryName", jobCategory.getName());
                map.put("counter", String.valueOf(counter));

                result.add(map);
            }
        });

        return result;
    }

}
