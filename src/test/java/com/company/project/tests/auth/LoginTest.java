package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginTest - Test class for Login functionality (TC06)
 */
public class LoginTest extends BaseTest {

    @Test(description = "TC06 - Additional pages display once user logged in")
    public void TC06_AdditionalTabsDisplayAfterLogin() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        // Verify additional tabs are displayed after login
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        // Verify guest tabs are NOT displayed
        Assert.assertFalse(homePage.isLoginTabDisplayed(), "Login tab is still displayed after login");
        Assert.assertFalse(homePage.isRegisterTabDisplayed(), "Register tab is still displayed after login");

        // Click My ticket tab and verify navigation via URL
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();
        String myTicketUrl = myTicketPage.getDriver().getCurrentUrl();
        Assert.assertEquals(myTicketUrl, MyTicketPage.PAGE_URL, "User is not redirected to My Ticket page");

        // Navigate back to home page
        homePage.open();

        // Click Change password tab and verify navigation via URL
        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();
        String changePasswordUrl = changePasswordPage.getDriver().getCurrentUrl();
        Assert.assertEquals(changePasswordUrl, ChangePasswordPage.PAGE_URL,
                "User is not redirected to Change Password page");
    }
}
