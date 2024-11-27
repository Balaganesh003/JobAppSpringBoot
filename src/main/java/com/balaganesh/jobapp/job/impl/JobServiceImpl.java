package com.balaganesh.jobapp.job.impl;

import com.balaganesh.jobapp.job.Job;
import com.balaganesh.jobapp.job.JobRepository;
import com.balaganesh.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
         return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void createJob(Job job) {
        // Create a new Job object
        Job newJob = new Job();

        // Extract values from the input job or assign defaults if necessary
        newJob.setTitle(job.getTitle() != null ? job.getTitle() : "Default Job Title");
        newJob.setDescription(job.getDescription() != null ? job.getDescription() : "Default Job Description");
        newJob.setLocation(job.getLocation() != null ? job.getLocation() : "Default Location");
        newJob.setMinSalary(job.getMinSalary() != null ? job.getMinSalary() : "30000"); // Default minimum salary
        newJob.setMaxSalary(job.getMaxSalary() != null ? job.getMaxSalary() : "50000"); // Default maximum salary

        // Save the new Job object to the database
        jobRepository.save(newJob);
    }


    @Override
    public Job deleteJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job != null) {
            jobRepository.deleteById(id);
        }
        return job;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isEmpty()) {
            return false;
        }

        Job job = jobOptional.get();
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        jobRepository.save(job);
        return true;


    }
}
