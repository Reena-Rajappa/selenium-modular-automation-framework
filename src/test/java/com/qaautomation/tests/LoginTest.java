package com.qaautomation.tests;

import com.qaautomation.base.BaseTest;
import com.qaautomation.pages.InventoryPage;
import com.qaautomation.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @DataProvider(name="loginData")
    public Object[][] getLoginData(){
        return new Object[][]{
                {"standard_user", "secret_sauce", "Products"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"},
                {"performance_glitch_user", "secret_sauce", "Products"}
        };
    }
    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password, String expectedResult){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        logger.info("Executing login test for user: {} with expected status: {}", username, expectedResult);
        if("Products".equals(expectedResult)){
            InventoryPage inventoryPage = new InventoryPage(driver);
            Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Valid login failed - Inventory page not loaded");
        } else {
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Login error message not displayed");
            Assert.assertEquals(errorMessage, expectedResult, "Incorrect error message displayed");
        }
    }
}
