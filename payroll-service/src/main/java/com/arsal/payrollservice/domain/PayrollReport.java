package com.arsal.payrollservice.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payroll_report")
public class PayrollReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="report_id", nullable = false)
    private String reportId;

    @Column(name="employee_id", nullable = false)
    private String employeeId;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    public PayrollReport() {
    }

    public PayrollReport(String reportId, String employeeId, Double amountPaid, Date startDate, Date endDate) {
        this.reportId = reportId;
        this.employeeId = employeeId;
        this.amountPaid = amountPaid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
