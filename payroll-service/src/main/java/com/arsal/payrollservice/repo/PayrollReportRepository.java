package com.arsal.payrollservice.repo;

import com.arsal.payrollservice.domain.PayrollReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollReportRepository extends JpaRepository<PayrollReport, Long> {

    @Query(value = "SELECT p from PayrollReport p order by p.employeeId asc , p.startDate asc ")
    List<PayrollReport> findAllOrderedByEmployeeIdAndStartDate();

    Boolean existsByEmployeePayPeriodId(String employeePayPeriodId);

    PayrollReport findByEmployeePayPeriodId(String employeePayPeriodId);
}
