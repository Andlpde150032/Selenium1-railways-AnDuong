package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
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

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();

        String newPassword = JsonReader.getData("register", "password");
        changePasswordPage.changePassword(user.getPassword(), newPassword, newPassword);

        String successMessage = changePasswordPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Your password has been updated!",
                "Success message is not displayed correctly");
    }
}
