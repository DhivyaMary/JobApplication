package com.practice.jobApp.serviceImpl;


import com.practice.jobApp.entity.Job;
import com.practice.jobApp.repository.JobRepository;
import com.practice.jobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        Optional<Job> jobOptional = jobRepository.findById(job.getId());
        if (jobOptional != null) {
            Job jobObj = jobOptional.get();
            jobObj.setJobDescription(job.getJobDescription());
            jobObj.setMinSalary(job.getMinSalary());
            jobObj.setMaxSalary(job.getMaxSalary());
            jobObj.setExpInYears(job.getExpInYears());
            jobRepository.save(jobObj);
            return jobObj;
        } else
            return null;
    }

    @Override
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public Boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
