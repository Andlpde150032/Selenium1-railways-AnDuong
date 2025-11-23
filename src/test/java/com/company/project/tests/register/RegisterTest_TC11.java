package com.company.project.tests.register;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest_TC11 extends BaseTest {

    @Test(description = "User can't create account while password and PID fields are empty")
    public void TC11() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String email = "tc11user" + System.currentTimeMillis() + "@gmail.com";
        String password = "";
        String confirmPassword = "";
        String pid = "";

        registerPage.register(email, password, confirmPassword, pid);

        // Verify general error message
        String generalError = registerPage.getGeneralErrorMessage();
        Assert.assertEquals(generalError, "There're errors in the form. Please correct the errors and try again.",
                "General error message is not displayed correctly");

        // Verify password error message
        String passwordError = registerPage.getPasswordErrorMessage();
        Assert.assertEquals(passwordError, "Invalid password length",
                "Password error message is not displayed correctly");

        // Verify PID error message
        String pidError = registerPage.getPidErrorMessage();
        Assert.assertEquals(pidError, "Invalid ID length",
                "PID error message is not displayed correctly");
    }
}
