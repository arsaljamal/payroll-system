package com.arsal.payrollservice.repo;

import com.arsal.payrollservice.domain.JobGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobGroupReportRepository extends JpaRepository<JobGroup, Long> {

    JobGroup findByName(String name);

    Boolean existsByName(String name);

}
