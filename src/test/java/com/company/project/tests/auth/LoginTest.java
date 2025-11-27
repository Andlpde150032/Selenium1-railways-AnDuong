package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
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
 * @function LoginTest - Test class for Login functionality (TC08)
 */
public class LoginTest extends BaseTest {

    @Test(description = "TC08 - User can't login with an account hasn't been activated")
    public void TC08_UserCannotLoginWithUnactivatedAccount() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(randomEmail, password, password, pid);

        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(randomEmail, password);

        // Verify URL indicates we are still on Login page (or not on Home page)
        String currentUrl = loginPage.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login.cshtml"), "User should remain on Login page");
    }
}
