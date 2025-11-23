package com.company.project.tests.account;

import com.company.project.base.BaseTest;
import com.company.project.pages.ForgotPasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.PasswordResetPage;
import com.company.project.pages.RegisterPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest_TC13 extends BaseTest {

    @Test(description = "Errors display if password and confirm password don't match when resetting password")
    public void TC13() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Pre-condition: Create a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "tc13user" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String pid = "123456789";
        registerPage.register(email, password, password, pid);

        // Navigate to Login -> Forgot Password
        LoginPage loginPage = homePage.goToLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.goToForgotPasswordPage();

        // Enter email and click Send Instructions
        // Note: This step is known to cause a server crash (SMTP error).
        // We proceed with the test by manually navigating to the reset page.
        try {
            forgotPasswordPage.sendInstructions(email);
        } catch (Exception e) {
            // Ignore potential crash or timeout here
        }

        // WORKAROUND: Navigate directly to Password Reset page
        // because the email cannot be sent due to server error.
        homePage.getDriver().get("http://railwayb2.somee.com/Account/PasswordReset?resetToken=sometoken");
        PasswordResetPage passwordResetPage = new PasswordResetPage();

        // Enter mismatched passwords
        String newPassword = "NewPassword123";
        String confirmPassword = "DifferentPassword456";

        // Enter passwords manually to have full control over the fields
        passwordResetPage.getDriver().findElement(By.id("newPassword")).sendKeys(newPassword);
        passwordResetPage.getDriver().findElement(By.id("confirmPassword")).sendKeys(confirmPassword);
        passwordResetPage.getDriver().findElement(By.id("resetToken")).clear();
        passwordResetPage.getDriver().findElement(By.id("resetToken")).sendKeys("sometoken");
        passwordResetPage.getDriver().findElement(By.cssSelector("input[title='Reset password']")).click();

        // Verify error messages
        String formError = passwordResetPage.getFormErrorMessage();
        Assert.assertEquals(formError, "Could not reset password. Please correct the errors and try again.",
                "Form error message is not displayed correctly");

        String confirmPasswordError = passwordResetPage.getConfirmPasswordErrorMessage();
        Assert.assertEquals(confirmPasswordError, "The password confirmation did not match the new password.",
                "Confirm password error message is not displayed correctly");
    }
}
