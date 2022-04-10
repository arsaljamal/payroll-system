package com.arsal.payrollservice;

import com.arsal.payrollservice.dto.PayrollReportDto;
import com.arsal.payrollservice.service.PayrollReportService;
import com.arsal.payrollservice.service.TimeReportService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PayrollServiceIntegrationTest {

    @Autowired
    private TimeReportService timeReportService;

    @Autowired
    private PayrollReportService payrollReportService;

    private MockMultipartFile multipart;

    private Gson gson = new Gson();

    @Test
    public void testGetPayrollReport() throws Exception {
        String timeReportFile = new File(".").getCanonicalPath()+
                "/src/test/resources/time-report-43.csv";
        FileInputStream fileInputStream = new FileInputStream(new File(timeReportFile));
        multipart = new MockMultipartFile("time-report-43.csv",
                "time-report-43.csv", "multipart/form-data", fileInputStream);

        timeReportService.save(multipart);
        PayrollReportDto payrollReportDto = payrollReportService.findAll();
        String expectedResult = "{\"employeeReportDtoList\":[{\"employeeId\":\"1\",\"payPeriodDto\":{\"startDate\":\"Jan 1, 2023, 12:00:00 AM\",\"endDate\":\"Jan 15, 2023, 12:00:00 AM\"},\"amountPaid\":300.0},{\"employeeId\":\"1\",\"payPeriodDto\":{\"startDate\":\"Jan 16, 2023, 12:00:00 AM\",\"endDate\":\"Jan 31, 2023, 12:00:00 AM\"},\"amountPaid\":80.0},{\"employeeId\":\"2\",\"payPeriodDto\":{\"startDate\":\"Jan 16, 2023, 12:00:00 AM\",\"endDate\":\"Jan 31, 2023, 12:00:00 AM\"},\"amountPaid\":90.0}]}";
        String actualResult = gson.toJson(payrollReportDto);

        Assert.assertEquals(expectedResult, actualResult);
    }
}
