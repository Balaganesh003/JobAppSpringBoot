package com.balaganesh.jobapp.job;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;



import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>>  getJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
         jobService.createJob(job);
      return new ResponseEntity<>( "Job Created SuccessFully" ,HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        Job job = jobService.getJobById(Long.parseLong(id));
        if (job == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Job job = jobService.deleteJobById(id);
        if (job == null) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( "Job Deleted SuccessFully With Id " + job.getId() ,HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob) {
       boolean isUpdated = jobService.updateJobById(id, updatedJob);
        if (!isUpdated) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>( "Job Updated SuccessFully"  ,HttpStatus.OK);

    }



}
