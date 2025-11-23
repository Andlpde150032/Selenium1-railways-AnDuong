package com.company.project.tests.account;

import com.company.project.base.BaseTest;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest_TC09 extends BaseTest {

    @Test(description = "User can change password")
    public void TC09() {
        HomePage homePage = new HomePage();
        homePage.open();

        // Pre-condition: Create a new account
        RegisterPage registerPage = homePage.goToRegisterPage();
        String email = "tc09user" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String newPassword = "NewPassword123";
        String pid = "123456789";
        registerPage.register(email, password, password, pid);

        // Login with the newly created account
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(email, password);

        // Navigate to Change Password page
        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();

        // Change password
        changePasswordPage.changePassword(password, newPassword, newPassword);

        // Verify success message
        String successMessage = changePasswordPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Your password has been updated!",
                "Success message is not displayed correctly");
    }
}
