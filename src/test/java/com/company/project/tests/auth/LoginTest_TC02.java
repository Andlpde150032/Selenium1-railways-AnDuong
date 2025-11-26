/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginBlankUsernameTest - Test class for Login with blank username
 */
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest_TC02 extends BaseTest {

    @Test(description = "User can't login with blank 'Username' textbox")
    public void testLoginWithBlankUsername() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();

        // Enter valid password but leave username blank
        loginPage.login("", user.getPassword());

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "There was a problem with your login and/or errors exist in your form.",
                "Error message is not displayed correctly");
    }
}
