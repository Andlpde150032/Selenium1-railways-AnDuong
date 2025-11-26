/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginAdditionalTabsTest - Test class for verifying additional tabs after login
 */
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest_TC06 extends BaseTest {

    @Test(description = "Additional pages display once user logged in")
    public void testAdditionalTabsDisplayAfterLogin() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        // Verify additional tabs are displayed after login
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        // Click My ticket tab and verify navigation
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();
        Assert.assertTrue(myTicketPage.getPageTitle().contains("My Ticket"),
                "My Ticket page title is incorrect");

        // Navigate back to home page (or just use the menu from current page if
        // available, but open() is safer)
        // Note: In a real app, the menu is likely persistent, so we might not need to
        // reopen homePage.
        // However, to be consistent with the original test logic:
        homePage.open();
        // Re-check if tabs are still visible or if we need to ensure we are in a state
        // to click them.
        // Since we opened home page, we should be logged in still.

        // Click Change password tab and verify navigation
        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();
        Assert.assertTrue(changePasswordPage.getPageTitle().contains("Change Password"),
                "Change Password page title is incorrect");
    }
}
