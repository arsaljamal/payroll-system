package com.arsal.payrollservice.controller;

import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.service.PayrollReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PayrollController {

    private final PayrollReportService payrollReportService;

    @Autowired
    public PayrollController(PayrollReportService payrollReportService) {
        this.payrollReportService = payrollReportService;
    }

    @GetMapping(path = "/report")
    public ResponseEntity<PayrollReportDto> getPayrollReport() {
        PayrollReportDto payrollReportDto = payrollReportService.findAll();
        return new ResponseEntity<>(payrollReportDto, HttpStatus.OK);
    }
}
