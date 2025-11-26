/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginInvalidPasswordTest - Test class for Login with invalid password
 */
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest_TC03 extends BaseTest {

    @Test(description = "User cannot log into Railway with invalid password")
    public void testLoginWithInvalidPassword() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        String invalidPassword = "InvalidPassword123";

        loginPage.login(user.getEmail(), invalidPassword);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Invalid username or password. Please try again.",
                "Error message is not displayed correctly");
    }
}
