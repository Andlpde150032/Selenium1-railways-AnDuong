/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginMultipleWrongPasswordTest - Test class for Login with multiple wrong passwords
 */
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginMultipleWrongPasswordTest extends BaseTest {

    @Test(description = "System shows message when user enters wrong password several times")
    public void testLoginWithMultipleWrongPasswords() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        String invalidPassword = "InvalidPassword123";

        // Perform 4 failed login attempts
        performMultipleLoginAttempts(loginPage, user.getEmail(), invalidPassword, 4);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage,
                "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.",
                "Error message is not displayed correctly");
    }

    private void performMultipleLoginAttempts(LoginPage loginPage, String username, String password, int attempts) {
        for (int i = 0; i < attempts; i++) {
            loginPage.login(username, password);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(errorMessage, "Invalid username or password. Please try again.",
                    "Error message mismatch at attempt " + (i + 1));
        }
    }
}
