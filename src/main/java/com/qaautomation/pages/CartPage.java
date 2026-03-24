package com.qaautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;

    private final By checkoutButton = By.id("checkout");
    private final By cartItems = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkout() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public boolean isProductPresentInCart(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for(WebElement item : items) {
            if (item.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }
}
