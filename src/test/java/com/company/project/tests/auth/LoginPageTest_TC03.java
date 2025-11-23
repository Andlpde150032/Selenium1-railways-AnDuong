package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC03 extends BaseTest {

    @Test(description = "User cannot log into Railway with invalid password")
    public void TC03() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        String email = JsonReader.getData("login", "email");
        String invalidPassword = "InvalidPassword123";

        loginPage.login(email, invalidPassword);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid username or password. Please try again.",
                "Error message is not displayed correctly");
    }
}
