package com.arsal.payrollservice.service;

import com.arsal.payrollservice.domain.PayrollReport;
import com.arsal.payrollservice.dto.EmployeeReportDto;
import com.arsal.payrollservice.dto.PayPeriodDto;
import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.repo.PayrollReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class PayrollReportService {
    private Logger logger = Logger.getLogger(String.valueOf(PayrollReportService.class));

    private final PayrollReportRepository payrollReportRepository;

    @Autowired
    public PayrollReportService(PayrollReportRepository payrollReportRepository) {
        this.payrollReportRepository = payrollReportRepository;
    }

    public void save(Map<String, PayrollReport> payrollReportMap) {
        logger.info("Request to create payroll-report.");
        payrollReportRepository.saveAll(payrollReportMap.values());
    }

    public PayrollReportDto findAll() {
        logger.info("Request to get payroll-report.");
        PayrollReportDto payrollReportDto = new PayrollReportDto();
        List<EmployeeReportDto> employeeReportDtoList = new ArrayList<>();

        List<PayrollReport> payrollReportList = payrollReportRepository.findAll();
        for (PayrollReport payrollReport : payrollReportList) {
            EmployeeReportDto employeeReportDto = new EmployeeReportDto();
            PayPeriodDto payPeriodDto = new PayPeriodDto(payrollReport.getStartDate(), payrollReport.getEndDate());
            employeeReportDto.setEmployeeId(payrollReport.getEmployeeId());
            employeeReportDto.setAmountPaid(payrollReport.getAmountPaid());
            employeeReportDto.setPayPeriodDto(payPeriodDto);
            employeeReportDtoList.add(employeeReportDto);
        }
        payrollReportDto.setEmployeeReportDtoList(employeeReportDtoList);
        return payrollReportDto;
    }

}
