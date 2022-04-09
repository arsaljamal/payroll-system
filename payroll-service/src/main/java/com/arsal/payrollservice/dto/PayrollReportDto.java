package com.arsal.payrollservice.dto;

import java.util.List;

public class PayrollReportDto {

    public List<EmployeeReportDto> employeeReportDtoList;

    public PayrollReportDto(List<EmployeeReportDto> employeeReportDtoList) {
        this.employeeReportDtoList = employeeReportDtoList;
    }

    public List<EmployeeReportDto> getEmployeeReportDtoList() {
        return employeeReportDtoList;
    }

    public void setEmployeeReportDtoList(List<EmployeeReportDto> employeeReportDtoList) {
        this.employeeReportDtoList = employeeReportDtoList;
    }
}
