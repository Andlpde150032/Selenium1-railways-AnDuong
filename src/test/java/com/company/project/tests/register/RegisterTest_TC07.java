package com.company.project.tests.register;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest_TC07 extends BaseTest {

    @Test(description = "User can create new account")
    public void TC07() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String email = "testuser" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String pid = "123456789";

        registerPage.register(email, password, password, pid);

        String successMessage = registerPage.getSuccessMessage();

        // This assertion is expected to fail because the actual message is "You're
        // here"
        Assert.assertEquals(successMessage.trim(), "Thank you for registering your account",
                "Success message is not displayed correctly");
    }
}
