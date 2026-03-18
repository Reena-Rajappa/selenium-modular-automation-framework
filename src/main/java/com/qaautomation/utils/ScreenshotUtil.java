package com.qaautomation.utils;

import com.qaautomation.constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver, String testName) {

        String screenshotDir = FrameworkConstants.SCREENSHOT_PATH;

        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String fileName = testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = screenshotDir + fileName;

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            logger.error("Screenshot capture failed", e);
        }

        return "../screenshots/" + fileName;
    }
}
