package com.arsal.payrollservice.controller;


import com.arsal.payrollservice.service.TimeReportService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
public class TimeReportController {

    private Gson gson = new Gson();

    private final TimeReportService timeReportService;

    @Autowired
    public TimeReportController(TimeReportService timeReportService) {
        this.timeReportService = timeReportService;
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<String> uploadMultiPartFile(@RequestParam("file") MultipartFile multiPartFile) throws Exception {
        timeReportService.save(multiPartFile);
        return new ResponseEntity<>(gson.toJson("Success"), HttpStatus.OK);
    }
}
