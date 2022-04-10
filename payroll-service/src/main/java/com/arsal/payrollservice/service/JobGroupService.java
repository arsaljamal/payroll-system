package com.arsal.payrollservice.service;

import com.arsal.payrollservice.domain.JobGroup;
import com.arsal.payrollservice.repo.JobGroupReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

@Service
public class JobGroupService {
    private Logger logger = Logger.getLogger(String.valueOf(JobGroupService.class));

    private final JobGroupReportRepository jobGroupReportRepository;

    @Autowired
    public JobGroupService(JobGroupReportRepository jobGroupReportRepository) {
        this.jobGroupReportRepository = jobGroupReportRepository;
    }

    @PostConstruct
    public void prePopulateJobGroups() {
        create("A", 20.0);
        create("B", 30.0);
    }

    public Boolean create(String name, Double rate) {
        logger.info("Creating JobGroup " + name);
        JobGroup jobGroup = new JobGroup(name, rate);
        if (!jobGroupReportRepository.existsByName(name)) {
            jobGroupReportRepository.save(jobGroup);
        }
        return jobGroupReportRepository.existsByName(name);
    }

    public JobGroup find(String name) {
        return jobGroupReportRepository.findByName(name);
    }
}
