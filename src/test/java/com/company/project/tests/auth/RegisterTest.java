package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function RegisterTest - Test class for Register functionality (TC11)
 */
public class RegisterTest extends BaseTest {

    @Test(description = "TC11 - User can't create account while password and PID fields are empty")
    public void TC11_UserCannotCreateAccountWithEmptyPasswordAndPID() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();

        registerPage.register(randomEmail, "", "", "");

        String errorMessage = registerPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "There's an error in the form. Please correct the fields and try again.",
                "Error message is not displayed correctly");
    }

}

    