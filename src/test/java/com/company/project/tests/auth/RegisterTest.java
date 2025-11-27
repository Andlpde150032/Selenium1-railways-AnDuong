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
 * @function RegisterTest - Test class for Register functionality (TC10)
 */
public class RegisterTest extends BaseTest {

    @Test(description = "TC10 - User can't create account with 'Confirm password' is not the same with 'Password'")
    public void TC10_UserCannotCreateAccountWithConfirmPasswordMismatch() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");
        String invalidConfirmPassword = "InvalidPassword123";

        registerPage.register(randomEmail, password, invalidConfirmPassword, pid);

        String errorMessage = registerPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "There's an error in the form. Please correct the fields and try again.",
                "Error message is not displayed correctly");
    }

}
