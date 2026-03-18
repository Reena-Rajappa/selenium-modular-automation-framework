package com.qaautomation.tests;

import com.qaautomation.base.BaseTest;
import com.qaautomation.pages.InventoryPage;
import com.qaautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @DataProvider(name = "products")
    public Object[][] products() {
        return new Object[][]{
                    {"Sauce Labs Backpack"},
                    {"Sauce Labs Bike Light"},
                    {"Sauce Labs Bolt T-Shirt"}
        };
    }

    @Test(dataProvider = "products")
    public void addProductToCart(String productName) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(productName);
        String cartCount =  inventoryPage.getCartCount();
        Assert.assertEquals(cartCount, "1", "Product not added to cart");
    }
}
