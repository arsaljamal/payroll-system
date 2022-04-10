package com.arsal.payrollservice.dto;

public class EmployeeReportDto {

    public String employeeId;

    public PayPeriodDto payPeriodDto;

    public Double amountPaid;

    public EmployeeReportDto() {
    }

    public EmployeeReportDto(String employeeId, PayPeriodDto payPeriodDto, Double amountPaid) {
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

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "{" +
                "employeeId='" + employeeId + '\'' +
                ", payPeriod=" + payPeriodDto +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
