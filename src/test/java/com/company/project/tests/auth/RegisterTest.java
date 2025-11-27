package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function RegisterTest - Test class for Register functionality (TC07, TC10,
 *           TC11)
 */
public class RegisterTest extends BaseTest {

    @Test(description = "TC07 - User can create new account")
    public void TC07_UserCanCreateNewAccount() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(randomEmail, password, password, pid);

        String actualMessage = registerPage.getSuccessMessage();
        String expectedMessage = "Thank you for registering your account";

        Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match!");
    }

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
