package com.arsal.payrollservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payroll_report")
public class PayrollReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_id", nullable = false)
    private String employeeId;

    @Column(name="employee_pay_period_id", unique = true, nullable = false)
    private String employeePayPeriodId;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    public PayrollReport() {
    }

    public PayrollReport(String employeeId, String employeePayPeriodId, BigDecimal amountPaid, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.employeePayPeriodId = employeePayPeriodId;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeePayPeriodId() {
        return employeePayPeriodId;
    }

    public void setEmployeePayPeriodId(String employeePayPeriodId) {
        this.employeePayPeriodId = employeePayPeriodId;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
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
