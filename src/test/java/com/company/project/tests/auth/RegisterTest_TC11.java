package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.project.utils.TestUtils;

public class RegisterTest_TC11 extends BaseTest {

    @Test(description = "TC11 - User can't create account while password and PID fields are empty")
    public void TC11_UserCannotCreateAccountWithEmptyPasswordAndPID() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = "";
        String confirmPassword = "";
        String pid = "";

        registerPage.register(randomEmail, password, confirmPassword, pid);

        String actualMainError = registerPage.getMainErrorMessage();
        String expectedMainError = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMainError, expectedMainError, "Main error message does not match!");

        String actualPasswordError = registerPage.getPasswordErrorMessage();
        String expectedPasswordError = "Invalid password length";
        Assert.assertEquals(actualPasswordError, expectedPasswordError, "Password error message does not match!");

        String actualPidError = registerPage.getPidErrorMessage();
        String expectedPidError = "Invalid ID length";
        Assert.assertEquals(actualPidError, expectedPidError, "PID error message does not match!");
    }
}
