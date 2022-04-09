package com.arsal.payrollservice.service;

import com.arsal.payrollservice.domain.PayrollReport;
import com.arsal.payrollservice.domain.TimeReport;
import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.repo.PayrollReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PayrollReportService {
    private Logger logger = Logger.getLogger(String.valueOf(PayrollReportService.class));

    private final PayrollReportRepository payrollReportRepository;

    @Autowired
    public PayrollReportService(PayrollReportRepository payrollReportRepository) {
        this.payrollReportRepository = payrollReportRepository;
    }

    public void save(List<TimeReport> timeReportList) {

    }

    public List<PayrollReportDto> findAll() {
        List<PayrollReportDto> payrollReportDtoList = new ArrayList<>();
        List<PayrollReport> payrollReportList = payrollReportRepository.findAll();
        return payrollReportDtoList;
    }

}
