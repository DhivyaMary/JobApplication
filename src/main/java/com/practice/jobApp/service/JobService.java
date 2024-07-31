package com.practice.jobApp.service;

import com.practice.jobApp.entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface JobService {
    List<Job> getAllJobs();

    Job createJob(Job job);

    Job updateJob(Job job);

    Optional<Job> getJobById(Long id);

    Boolean deleteJob(Long id);
}
