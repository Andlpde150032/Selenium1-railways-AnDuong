package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC01 extends BaseTest {

    @Test(description = "User can log into Railway with valid username and password")
    public void TC01() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        String email = JsonReader.getData("login", "email");
        String password = JsonReader.getData("login", "password");

        loginPage.login(email, password);

        String welcomeMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome " + email, "Welcome message is not displayed correctly");
    }
}
