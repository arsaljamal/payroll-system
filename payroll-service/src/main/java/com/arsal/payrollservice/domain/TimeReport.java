package com.arsal.payrollservice.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "time_report")
public class TimeReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="report_id", nullable = false, unique = true)
    private String reportId;

    @Column(name="employee_id", nullable = false)
    private String employeeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "hours_worked", nullable = false)
    private Double hoursWorked;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="job_group_id", nullable=false)
    private JobGroup jobGroup;

    public TimeReport() {
    }

    public TimeReport(String reportId, String employeeId, Date date, Double hoursWorked, JobGroup jobGroup) {
        this.reportId = reportId;
        this.employeeId = employeeId;
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.jobGroup = jobGroup;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public JobGroup getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(JobGroup jobGroup) {
        this.jobGroup = jobGroup;
    }
}
