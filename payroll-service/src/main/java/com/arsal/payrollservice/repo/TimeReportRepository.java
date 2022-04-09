package com.arsal.payrollservice.repo;

import com.arsal.payrollservice.domain.TimeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {

    Boolean existsByReportId(String reportId);

    List<TimeReport> findByEmployeeId(String employeeId);

    List<TimeReport> findByReportId(String reportId);
}
