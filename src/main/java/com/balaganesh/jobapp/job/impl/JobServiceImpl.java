package com.balaganesh.jobapp.job.impl;

import com.balaganesh.jobapp.job.Job;
import com.balaganesh.jobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    private Long nextId = 1L;

    @Override
    public List<Job> getAllJobs() {
        return jobs;
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public void createJob(Job job) {

        job.setId(nextId++);

        jobs.add(job);
    }

    @Override
    public Job deleteJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                jobs.remove(job);
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        for (Job job1 : jobs) {
            if (job1.getId().equals(id)) {
                job1.setTitle(updatedJob.getTitle());
                job1.setDescription(updatedJob.getDescription());
                job1.setLocation(updatedJob.getLocation());
                job1.setMinSalary(updatedJob.getMinSalary());
                job1.setMaxSalary(updatedJob.getMaxSalary());
                return true;
            }
        }
        return false;
    }
}
