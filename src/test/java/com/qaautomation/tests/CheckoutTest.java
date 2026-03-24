package com.qaautomation.tests;

import com.qaautomation.base.BaseTest;
import com.qaautomation.pages.CartPage;
import com.qaautomation.pages.CheckoutPage;
import com.qaautomation.pages.InventoryPage;
import com.qaautomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CheckoutTest extends BaseTest {

    @Test
    public void verifyUserCanCompleteCheckout() {
        String product = "Sauce Labs Backpack";
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(product);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isProductPresentInCart(product), "Product not found in cart");
        cartPage.checkout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterDetails("Reena", "R", "600001");
        checkoutPage.finishProductOrder();
        Assert.assertEquals(checkoutPage.getSuccessOrderMessage(), "Thank you for your order!");
    }

    @Test
    public void verifyCartIsEmptyWhenNoProductAdded() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty");
    }

    @Test
    public void verifyUserCanRemoveProductFromCart() {
        String product = "Sauce Labs Backpack";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addProductToCart(product);
        inventoryPage.removeProductFromCart(product);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not empty after removing product");
    }
}
