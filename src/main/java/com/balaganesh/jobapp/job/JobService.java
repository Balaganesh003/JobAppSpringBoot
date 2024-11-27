package com.balaganesh.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(Long id);
    void createJob(Job job);
    Job deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
}
