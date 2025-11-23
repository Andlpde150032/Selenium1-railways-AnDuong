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

        // Repeat login 4 times
        for (int i = 0; i < 4; i++) {
            loginPage.login(email, invalidPassword);
        }

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,
                "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.",
                "Error message is not displayed correctly");
    }
}
