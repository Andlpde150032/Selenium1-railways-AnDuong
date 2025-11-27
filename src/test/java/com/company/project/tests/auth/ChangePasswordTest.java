package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function ChangePasswordTest - Test class for Change Password functionality (TC09)
 */
public class ChangePasswordTest extends BaseTest {

    @Test(description = "TC09 - User can change password")
    public void TC09_UserCanChangePassword() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(randomEmail, password, password, pid);

        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(randomEmail, password);

        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();

        String newPassword = "NewPassword123";
        changePasswordPage.changePassword(password, newPassword, newPassword);

        String actualMessage = changePasswordPage.getSuccessMessage();
        String expectedMessage = "Your password has been updated!";

        Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match!");
    }
}