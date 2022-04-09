package com.arsal.payrollservice.dto;

public class EmployeeReportDto {

    public String employeeId;

    public PayPeriodDto payPeriodDto;

    public String amountPaid;

    public EmployeeReportDto(String employeeId, PayPeriodDto payPeriodDto, String amountPaid) {
        this.employeeId = employeeId;
        this.payPeriodDto = payPeriodDto;
        this.amountPaid = amountPaid;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public PayPeriodDto getPayPeriodDto() {
        return payPeriodDto;
    }

    public void setPayPeriodDto(PayPeriodDto payPeriodDto) {
        this.payPeriodDto = payPeriodDto;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }
}
