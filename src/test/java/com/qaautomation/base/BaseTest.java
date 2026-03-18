package com.qaautomation.base;

import com.qaautomation.utils.ConfigReader;
import com.qaautomation.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.initializeDriver();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }

    public  WebDriver getDriver() {
        return driver;
    }
}
