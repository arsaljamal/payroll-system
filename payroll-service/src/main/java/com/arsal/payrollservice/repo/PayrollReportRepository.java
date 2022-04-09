package com.arsal.payrollservice.repo;

import com.arsal.payrollservice.domain.PayrollReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollReportRepository extends JpaRepository<PayrollReport, Long> {
    List<PayrollReport> findAllByReportId(String reportId);
}
