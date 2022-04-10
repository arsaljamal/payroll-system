package com.arsal.payrollservice.controller;

import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.service.PayrollReportService;
import com.arsal.payrollservice.service.TimeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "payroll")
public class PayrollController {

    private final TimeReportService timeReportService;

    private final PayrollReportService payrollReportService;

    @Autowired
    public PayrollController(TimeReportService timeReportService, PayrollReportService payrollReportService) {
        this.timeReportService = timeReportService;
        this.payrollReportService = payrollReportService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<String> uploadMultiPartFile(@RequestParam("file") MultipartFile multiPartFile) throws Exception {
        timeReportService.save(multiPartFile);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(path = "/report")
    public ResponseEntity<PayrollReportDto> getPayrollReport() {
        PayrollReportDto payrollReportDto = payrollReportService.findAll();
        return new ResponseEntity<>(payrollReportDto, HttpStatus.OK);
    }
}
