/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginBookTicketRedirectTest - Test class for Login redirection from Book Ticket
 */
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBookTicketRedirectTest extends BaseTest {

    @Test(description = "Login page displays when un-logged User clicks on 'Book ticket' tab")
    public void testLoginRedirectFromBookTicket() {
        HomePage homePage = new HomePage();
        homePage.open();

        homePage.clickBookTicketTab();

        String currentUrl = homePage.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login.cshtml"), "User is not redirected to Login page");

        String pageTitle = homePage.getDriver().getTitle();
        Assert.assertEquals(pageTitle, "Safe Railway - Login", "Page title is incorrect");
    }
}
