package com.arsal.payrollservice.service;

import com.arsal.payrollservice.domain.PayrollReport;
import com.arsal.payrollservice.dto.EmployeeReportDto;
import com.arsal.payrollservice.dto.PayPeriodDto;
import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.repo.PayrollReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    @Transactional
    public void save(Map<String, PayrollReport> payrollReportMap) {
        logger.info("Request to create payroll-report.");
        for(PayrollReport payrollReport : payrollReportMap.values()) {
            if (payrollReportRepository.existsByEmployeePayPeriodId(payrollReport.getEmployeePayPeriodId())) {
                PayrollReport payrollReportToPersist = payrollReportRepository.findByEmployeePayPeriodId(payrollReport.getEmployeePayPeriodId());
                Double totalAmount = payrollReportToPersist.getAmountPaid().doubleValue() + payrollReport.getAmountPaid().doubleValue();
                payrollReportToPersist.setAmountPaid(BigDecimal.valueOf(totalAmount));
            } else {
                payrollReportRepository.save(payrollReport);
            }
        }

    }

    public PayrollReportDto findAll() {
        logger.info("Request to get payroll-report.");
        PayrollReportDto payrollReportDto = new PayrollReportDto();
        List<EmployeeReportDto> employeeReportDtoList = new ArrayList<>();

        List<PayrollReport> payrollReportList = payrollReportRepository.findAllOrderedByEmployeeIdAndStartDate();
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
