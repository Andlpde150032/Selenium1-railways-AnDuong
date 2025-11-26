package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.ForgotPasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.PasswordResetPage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ForgotPasswordTest_TC13 extends BaseTest {

    @Test(description = "TC13 - Errors display if password and confirm password don't match when resetting password")
    public void TC13_ErrorsDisplayWhenPasswordMismatch() {
        HomePage homePage = new HomePage();
        homePage.open();

        // 1. Register a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String randomEmail = "tc13_user_" + new Random().nextInt(100000) + "@example.com";
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");
        registerPage.register(randomEmail, password, password, pid);

        // 2. Navigate to Login Page
        LoginPage loginPage = homePage.goToLoginPage();

        // 3. Click "Forgot Password" link
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();

        // 4. Enter the registered email
        // 5. Click "Send Instructions"
        forgotPasswordPage.sendInstructions(randomEmail);

        // 6. Workaround: Navigate directly to /Account/PasswordReset.cshtml
        PasswordResetPage passwordResetPage = new PasswordResetPage();
        passwordResetPage.openUrl("http://railwayb2.somee.com/Account/PasswordReset.cshtml");

        // 7. Enter new password
        String newPassword = "NewPassword123";
        // 8. Enter mismatched confirm password
        String confirmPassword = "Mismatch123";
        // 9. Enter any token
        String token = "token123";

        passwordResetPage.resetPassword(newPassword, confirmPassword, token);

        // 10. Assert actual error messages
        String actualMainError = passwordResetPage.getMainErrorMessage();
        String expectedMainError = "Could not reset password. Please correct the errors and try again.";
        Assert.assertEquals(actualMainError, expectedMainError, "Main error message does not match!");

        String actualConfirmError = passwordResetPage.getConfirmPasswordErrorMessage();
        String expectedConfirmError = "The password confirmation did not match the new password.";
        Assert.assertEquals(actualConfirmError, expectedConfirmError, "Confirm password error message does not match!");
    }
}
