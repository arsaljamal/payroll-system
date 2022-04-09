package com.arsal.payrollservice.service;

import com.arsal.payrollservice.domain.JobGroup;
import com.arsal.payrollservice.repo.JobGroupReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobGroupService {

    private final JobGroupReportRepository jobGroupReportRepository;

    @Autowired
    public JobGroupService(JobGroupReportRepository jobGroupReportRepository) {
        this.jobGroupReportRepository = jobGroupReportRepository;
    }

    public void create(String name, Double rate) {
        JobGroup jobGroup = new JobGroup(name, rate);
        if (!jobGroupReportRepository.existsByName(name)) {
            jobGroupReportRepository.save(jobGroup);
        }
    }

    public JobGroup find(String name) {
        JobGroup jobGroup = jobGroupReportRepository.findByName(name);
        return jobGroup;
    }
}
