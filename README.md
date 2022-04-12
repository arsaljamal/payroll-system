# Documentation

## Build and run using Docker

### Required Depdencies

1. Docker ([macOS](https://docs.docker.com/desktop/mac/install/)/[Linux](https://docs.docker.com/engine/install/#server))

### Getting Started

The quickest way to get up and running with the application is to run docker-compose from the root of the project directory:

```
cd payroll-system
```
```
docker-compose up -d
```

By default the API can be found at the follow URL: http://localhost:8080

## Using the API

As requested in the challenge description, the API has two endpoints, one for uploading the time reports, and one for requesting payroll reports.

### Uploading Time Reports

The upload endpoint is setup as a multipart form with one field "file",  which allows you to provide a file path to the time-report you want to upload. You can send a request to the upload endpoint by opening the command line, navigating to the location of the sample time report (payroll-service/src/test/resources/time-report-42.csv) and running the following command.

Request: 

```
curl --request POST \
  --url http://localhost:8080/payroll/upload \
  --header 'Content-Type: multipart/form-data' \
  --form file=@time-report-42.csv
```

Response:

```
"Success"
```

Attempting to upload previously uploaded time-reports will result in the following response:

```
"Report Already Exits."
```


### Requesting Payroll Reports

Requesting a payroll report is comparatively simpler than uploading, all you need to do is send a GET request to the /payroll/report endpoint, you can do so by running the command below.

Request:

```
curl --request GET \
  --url http://localhost:8080/payroll/report
```

Response (after uploading sample time report):

```
{
    "employeeReportDtoList": [
        {
            "employeeId": "1",
            "payPeriodDto": {
                "startDate": "2023-11-01",
                "endDate": "2023-11-15"
            },
            "amountPaid": 150.0
        },
        {
            "employeeId": "1",
            "payPeriodDto": {
                "startDate": "2023-11-16",
                "endDate": "2023-11-30"
            },
            "amountPaid": 220.0
        },
        {
            "employeeId": "1",
            "payPeriodDto": {
                "startDate": "2023-12-01",
                "endDate": "2023-12-15"
            },
            "amountPaid": 150.0
        },
        {
            "employeeId": "1",
            "payPeriodDto": {
                "startDate": "2023-12-16",
                "endDate": "2023-12-31"
            },
            "amountPaid": 220.0
        },
        {
            "employeeId": "2",
            "payPeriodDto": {
                "startDate": "2023-11-01",
                "endDate": "2023-11-15"
            },
            "amountPaid": 930.0
        },
        {
            "employeeId": "2",
            "payPeriodDto": {
                "startDate": "2023-12-01",
                "endDate": "2023-12-15"
            },
            "amountPaid": 930.0
        },
        {
            "employeeId": "3",
            "payPeriodDto": {
                "startDate": "2023-11-01",
                "endDate": "2023-11-15"
            },
            "amountPaid": 590.0
        },
        {
            "employeeId": "3",
            "payPeriodDto": {
                "startDate": "2023-12-01",
                "endDate": "2023-12-15"
            },
            "amountPaid": 470.0
        },
        {
            "employeeId": "4",
            "payPeriodDto": {
                "startDate": "2023-02-16",
                "endDate": "2023-02-28"
            },
            "amountPaid": 150.0
        },
        {
            "employeeId": "4",
            "payPeriodDto": {
                "startDate": "2023-11-01",
                "endDate": "2023-11-15"
            },
            "amountPaid": 150.0
        },
        {
            "employeeId": "4",
            "payPeriodDto": {
                "startDate": "2023-11-16",
                "endDate": "2023-11-30"
            },
            "amountPaid": 450.0
        },
        {
            "employeeId": "4",
            "payPeriodDto": {
                "startDate": "2023-12-01",
                "endDate": "2023-12-15"
            },
            "amountPaid": 150.0
        },
        {
            "employeeId": "4",
            "payPeriodDto": {
                "startDate": "2023-12-16",
                "endDate": "2023-12-31"
            },
            "amountPaid": 450.0
        }
    ]
}
```


## Running Tests

In order to run the tests we can change here https://github.com/arsaljamal/payroll-system/blob/3c507652a0ffc9cbad07fcd9e4b1d63d88dc1b72/payroll-service/DockerFile#L6 :

from 
```
RUN mvn -DskipTests package
```
to
```
RUN mvn package
```
This will run all the tests before the project is built.

## Additional Details

1. How did you test that your implementation was correct?

Before writing any of the implementation, I started the project by first leveraging the time-report and expected payroll report in the challenge description above to write a unit test.

Initially the unit test only tested the external API by sending in the time-report and asserting the correct response for the payroll report.

As I began to write the code, I ensured that the tests for the relevant code areas were passing, and that my code worked as expected.

Furthermore, I tested my API manually by submitting the sample time-report and verifying that the returned payroll report matched what I expected.

2. If this application was destined for a production environment, what would you add or change? 

The most pressing issue to be resolved before deployment to a production environment are the hard-coded database credentials. Given that hard-coding credentials into source code is generally considered insecure, and a potential vector for attack, I would likely leverage a secret management tool to retrieve the credentials at runtime.

From a scalability perspective there are also several issues worth addressing.

Depending on how the application is used, there will likely be many requests where we are re-calculating all pay periods for all employees, even when nothing has changed since the last request. Given a large enough number of time-report entries in the database, this can lead to signifcant performance degredation. We can resolve this issue by caching previously calculated pay periods for employees, invalidating and updating the cache when newly ingested time-reports contain data that can change the values of previously cached pay periods.

In order to avoid having to perform calculations on a potentially large amount of data in the first place, it would also be prudent to provide options for scoping payroll report requests. Currently every payroll report request requires us to perform calculations on all time report records ever uploaded. As the number of time report entries grows, the calculations will become proprtionally more expensive (O(n)). It is also diffcult to imagine that users in the real world will always need a payroll report with every pay period, for every employee. Adding the ability to scope payroll reports with date, employee ids, or job group would reduce the amount of data the payroll report logic needs to comb through, helping alleviate any potential performance bottlenecks.

While scoping payroll report requests helps, it does not solve the problem of returning more data than the front-end can render in the current view. This issue can be resolved in a straightforward manner by adding limit and offset parameters to the payroll report endpoint. This way the front-end requests only as much data as it needs, and the back-end can avoid having to calculate all pay periods for every request.

Given how expensive the payroll report request can be, it also makes sense to put in place some rate limiting. This way we avoid situations where a large number of expensive requests within a short time period, causes performance issues. 

3. What compromises did you have to make as a result of the time constraints of this challenge?

In addition to the items I discussed above, there are several areas where I made compromises for the sake of time.

The application's test cases are one of the areas where I have made these compromises. Specifically, I have written integration tests such that they require a database to work. While this is acceptable for tests that are specfically geared towards testing the database, there should be unit tests that covers testing the functionality and mocking the database.

Another area with room for improvement is the payroll report calculator. In the process of calculating the pay periods for the all employees, and creating the JSON to be returned, the code goes over the data several times. The time complexity of this is O(cn), while this is sufficient for the purposes of a small project, I believe there is room for further optimization to get it closer to O(n). 
