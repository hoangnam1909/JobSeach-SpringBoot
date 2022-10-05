package com.nhn.mapper;

import com.nhn.entity.ApplyingJob;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplyingJobMapper {

    public List<ApplyingJob> removeKey(List<ApplyingJob> applyingJobs){

        for (ApplyingJob applyingJob : applyingJobs){
            applyingJob.setJob(null);
        }

        return applyingJobs;
    }

}
