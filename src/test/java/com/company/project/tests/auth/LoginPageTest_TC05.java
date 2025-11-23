package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC05 extends BaseTest {

    @Test(description = "System shows message when user enters wrong password several times")
    public void TC05() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        String email = JsonReader.getData("login", "email");
        String invalidPassword = "InvalidPassword123";

        // Perform 4 failed login attempts
        performMultipleLoginAttempts(loginPage, email, invalidPassword, 4);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,
                "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.",
                "Error message is not displayed correctly");
    }

    private void performMultipleLoginAttempts(LoginPage loginPage, String username, String password, int attempts) {
        for (int i = 0; i < attempts; i++) {
            loginPage.login(username, password);
        }
    }
}
