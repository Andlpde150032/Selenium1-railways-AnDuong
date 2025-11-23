package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC04 extends BaseTest {

    @Test(description = "Login page displays when un-logged User clicks on 'Book ticket' tab")
    public void TC04() {
        HomePage homePage = new HomePage();
        homePage.open();

        homePage.clickBookTicketTab();

        String currentUrl = homePage.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login.cshtml"), "User is not redirected to Login page");

        // Verify title or other elements if needed, but URL check is a strong indicator
        // for redirection
        String pageTitle = homePage.getDriver().getTitle();
        Assert.assertEquals(pageTitle, "Safe Railway - Login", "Page title is incorrect");
    }
}
