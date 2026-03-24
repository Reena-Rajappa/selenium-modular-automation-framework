package com.qaautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    private WebDriverWait wait;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By successOrderMessage = By.className("complete-header");
    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterDetails(String fName, String lName, String zipCode){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("checkout-step-one"));

        WebElement first = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        first.clear();
        first.sendKeys(fName);

        WebElement last = driver.findElement(lastName);
        last.clear();
        last.sendKeys(lName);

        WebElement postal = driver.findElement(postalCode);
        postal.clear();
        postal.sendKeys(zipCode);

        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));

        for (int i = 0; i < 2; i++) {
            continueBtn.click();
            try {
                wait.until(ExpectedConditions.urlContains("checkout-step-two"));
                break;
            } catch (Exception e) {
               logger.error("Retry by clicking continue button");
            }
        }
    }

    public void finishProductOrder(){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));
        driver.findElement(finishButton).click();
    }

    public String getSuccessOrderMessage(){
        return driver.findElement(successOrderMessage).getText();
    }

}
