package com.arsal.payrollservice.service;

import com.arsal.payrollservice.Utility.DateUtility;
import com.arsal.payrollservice.domain.TimeReport;
import com.arsal.payrollservice.repo.TimeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class TimeReportService {
    private Logger logger = Logger.getLogger(String.valueOf(TimeReportService.class));

    private final TimeReportRepository timeReportRepository;

    private final JobGroupService jobGroupService;

    private final PayrollReportService payrollReportService;

    @Autowired
    public TimeReportService(TimeReportRepository timeReportRepository, JobGroupService jobGroupService, PayrollReportService payrollReportService) {
        this.timeReportRepository = timeReportRepository;
        this.jobGroupService = jobGroupService;
        this.payrollReportService = payrollReportService;
    }

    public void save(MultipartFile multipartFile) throws Exception {
        logger.info("Request to create time-report.");
        String reportId = getReportId(multipartFile);
        if (timeReportRepository.existsByReportId(reportId)) {
            logger.info("ReportID already exits " + reportId);
            throw new Exception("Report Already Exits.");
        }

        List<TimeReport> timeReportList = uploadTimeReportFromFile(multipartFile, reportId);
        timeReportList = timeReportRepository.saveAll(timeReportList);
        payrollReportService.save(timeReportList);
    }

    private String getReportId(MultipartFile multipartFile) {
        return multipartFile.getOriginalFilename().
                replace("time-report-","").
                replace(".csv","").replace(" ", "");
    }

    private List<TimeReport> uploadTimeReportFromFile(MultipartFile multipartFile, String reportId) throws Exception {
        List<TimeReport> timeReportList = new ArrayList<>();
        try {
            Stream<String> rows = (new BufferedReader
                    (new InputStreamReader(multipartFile.getInputStream()))).lines();
            rows.skip(1).forEach(r -> {
                String [] values = r.split(",");
                TimeReport timeReport = new TimeReport();
                timeReport.setReportId(reportId);
                timeReport.setDate(DateUtility.getDate(values[0]));
                timeReport.setHoursWorked(Double.valueOf(values[1]));
                timeReport.setEmployeeId(values[2]);
                timeReport.setJobGroup(jobGroupService.find(values[3]));
                timeReportList.add(timeReport);
            });
        } catch (IOException e) {
            logger.info("Got Error when parsing report " + reportId + " : " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return timeReportList;
    }
}
