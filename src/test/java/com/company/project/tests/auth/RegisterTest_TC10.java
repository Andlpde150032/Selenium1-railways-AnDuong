package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.project.utils.TestUtils;

public class RegisterTest_TC10 extends BaseTest {

    @Test(description = "TC10 - User can't create account with 'Confirm password' is not the same with 'Password'")
    public void TC10_UserCannotCreateAccountWithPasswordMismatch() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = "Password123";
        String confirmPassword = "Mismatch123";
        String pid = "123456789";

        registerPage.register(randomEmail, password, confirmPassword, pid);

        String actualMainError = registerPage.getMainErrorMessage();
        String expectedMainError = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMainError, expectedMainError, "Main error message does not match!");

        String actualConfirmError = registerPage.getConfirmPasswordErrorMessage();
        String expectedConfirmError = "The two passwords do not match";
        Assert.assertEquals(actualConfirmError, expectedConfirmError, "Confirm password error message does not match!");
    }
}
