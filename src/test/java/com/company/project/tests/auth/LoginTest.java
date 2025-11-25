package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "User can log into Railway with valid username and password")
    public void testLoginWithValidCredentials() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();

        loginPage.login(user.getEmail(), user.getPassword());

        String welcomeMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome " + user.getEmail(), "Welcome message is not displayed correctly");
    }
}
