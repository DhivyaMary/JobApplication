package com.practice.jobApp.controller;

import com.practice.jobApp.entity.Job;
import com.practice.jobApp.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        if (!jobs.isEmpty())
            return new ResponseEntity(jobs, HttpStatus.OK);
        else
            return new ResponseEntity("No Jobs Found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/id")
    public ResponseEntity getJobById(@RequestParam Long id) {
        Optional<Job> job = jobService.getJobById(id);
        if (job.isPresent())
            return new ResponseEntity(job.get(), HttpStatus.OK);
        else
            return new ResponseEntity("No Job Found", HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity createJob(Job job) {
        Job newJob = jobService.createJob(job);
        return new ResponseEntity(newJob, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        Job updatedJob = jobService.updateJob(job);
        if (updatedJob != null)
            return new ResponseEntity(updatedJob, HttpStatus.OK);
        else
            return new ResponseEntity("No Job to Update", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        Boolean isDeleted = jobService.deleteJob(id);
        if (isDeleted)
            return new ResponseEntity("Job Deleted Success", HttpStatus.OK);
        else
            return new ResponseEntity("No Job Found to Delete", HttpStatus.NOT_FOUND);
    }


}
