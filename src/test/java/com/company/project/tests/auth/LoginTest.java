package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginTest - Test class for Login functionality (TC01)
 */
public class LoginTest extends BaseTest {

    @Test(description = "TC01 - User can log into Railway with valid username and password")
    public void TC01_LoginWithValidCredentials() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();

        loginPage.login(user.getEmail(), user.getPassword());

        String welcomeMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome " + user.getEmail(), "Welcome message is not displayed correctly");
    }
}
