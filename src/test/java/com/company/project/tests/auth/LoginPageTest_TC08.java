package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC08 extends BaseTest {

    @Test(description = "User can't login with an account hasn't been activated")
    public void TC08() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Pre-condition: Create a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "tc08user" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String pid = "123456789";
        registerPage.register(email, password, password, pid);

        // Navigate to Login page
        LoginPage loginPage = homePage.goToLoginPage();

        // Login with the newly created account
        loginPage.login(email, password);

        // Verify error message
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid username or password. Please try again.",
                "Error message is not displayed correctly");
    }
}
