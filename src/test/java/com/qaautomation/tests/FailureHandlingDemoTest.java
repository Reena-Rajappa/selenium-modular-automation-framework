package com.qaautomation.tests;

import com.qaautomation.base.BaseTest;
import com.qaautomation.pages.InventoryPage;
import com.qaautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FailureHandlingDemoTest extends BaseTest {

    @DataProvider(name="loginData")
    public Object[][] getLoginData(){
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    // This test is intentionally disabled and designed to fail to demonstrate screenshot capture and reporting functionality
    @Test(enabled = false, dataProvider = "loginData")
    public void verifyScreenshotCaptureOnFailure(String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        InventoryPage inventoryPage = new  InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getPageTitle(), "WrongTitle");
    }
}
