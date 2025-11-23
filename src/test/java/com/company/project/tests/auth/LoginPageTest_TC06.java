package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC06 extends BaseTest {

    @Test(description = "Additional pages display once user logged in")
    public void TC06() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        String email = JsonReader.getData("login", "email");
        String password = JsonReader.getData("login", "password");

        loginPage.login(email, password);

        // Verify additional tabs are displayed after login
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        // Click My ticket tab and verify navigation
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();
        Assert.assertTrue(myTicketPage.getPageTitle().contains("My Ticket"),
                "My Ticket page title is incorrect");

        // Navigate back to home page
        homePage.open();

        // Click Change password tab and verify navigation
        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();
        Assert.assertTrue(changePasswordPage.getPageTitle().contains("Change Password"),
                "Change Password page title is incorrect");
    }
}
