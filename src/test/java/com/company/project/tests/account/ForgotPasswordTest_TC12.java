package com.company.project.tests.account;

import com.company.project.base.BaseTest;
import com.company.project.pages.ForgotPasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.PasswordResetPage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest_TC12 extends BaseTest {

    @Test(description = "Errors display when password reset token is blank")
    public void TC12() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Pre-condition: Create a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "tc12user" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String pid = "123456789";
        registerPage.register(email, password, password, pid);

        // Navigate to Login -> Forgot Password
        LoginPage loginPage = homePage.goToLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();

        // Enter email and click Send Instructions
        // Note: This step is known to cause a server crash. We proceed anyway.
        try {
            forgotPasswordPage.sendInstructions(email);
        } catch (Exception e) {
            // Ignore potential crash or timeout here as we will manually navigate next
        }

        // Navigate directly to Password Reset page with blank token to simulate the
        // scenario
        homePage.getDriver().get("http://railwayb2.somee.com/Account/PasswordReset?resetToken=");
        PasswordResetPage passwordResetPage = new PasswordResetPage();

        // Enter new passwords and click Reset
        String newPassword = "NewPassword123";
        passwordResetPage.resetPassword(newPassword, newPassword);

        // Verify error messages
        String formError = passwordResetPage.getFormErrorMessage();
        Assert.assertEquals(formError, "Could not reset password. Please correct the errors and try again.",
                "Form error message is not displayed correctly");

        String tokenError = passwordResetPage.getTokenErrorMessage();
        Assert.assertEquals(tokenError,
                "Please enter your password reset token. It should have been sent to you in an email.",
                "Token error message is not displayed correctly");
    }
}
