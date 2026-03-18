package com.qaautomation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qaautomation.constants.FrameworkConstants;

public class ExtentManager {
    public static ExtentReports extent;
    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = FrameworkConstants.REPORT_PATH;
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Modular Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Author", "Reena");
        }
        return extent;
    }
}
