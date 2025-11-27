package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.ChangePasswordPage;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.pages.MyTicketPage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import com.company.project.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginTest - Test class for Login functionality (TC01-TC06, TC08)
 */
public class LoginTest extends BaseTest {

    @Test(description = "TC01 - User can log into Railway with valid username and password")
    public void TC01_LoginWithValidCredentials() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();

        loginPage.login(user.getEmail(), user.getPassword());

        String welcomeMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome " + user.getEmail(), "Welcome message is not displayed correctly");
    }

    @Test(description = "TC02 - User can't login with blank 'Username' textbox")
    public void TC02_LoginWithBlankUsername() {
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

    @Test(description = "TC03 - User cannot log into Railway with invalid password")
    public void TC03_LoginWithInvalidPassword() {
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

    @Test(description = "TC04 - Login page displays when un-logged User clicks on 'Book ticket' tab")
    public void TC04_LoginRedirectFromBookTicket() {
        HomePage homePage = new HomePage();
        homePage.open();

        homePage.clickBookTicketTab();

        String currentUrl = homePage.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login.cshtml"), "User is not redirected to Login page");

        String pageTitle = homePage.getDriver().getTitle();
        Assert.assertEquals(pageTitle, "Safe Railway - Login", "Page title is incorrect");
    }

    @Test(description = "TC05 - System shows message when user enters wrong password several times")
    public void TC05_LoginWithMultipleWrongPasswords() {
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

    @Test(description = "TC06 - Additional pages display once user logged in")
    public void TC06_AdditionalTabsDisplayAfterLogin() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        User user = User.getValidUser();
        loginPage.login(user.getEmail(), user.getPassword());

        // Verify additional tabs are displayed after login
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        // Verify guest tabs are NOT displayed
        Assert.assertFalse(homePage.isLoginTabDisplayed(), "Login tab is still displayed after login");
        Assert.assertFalse(homePage.isRegisterTabDisplayed(), "Register tab is still displayed after login");

        // Click My ticket tab and verify navigation via URL
        MyTicketPage myTicketPage = homePage.goToMyTicketPage();
        String myTicketUrl = myTicketPage.getDriver().getCurrentUrl();
        Assert.assertEquals(myTicketUrl, MyTicketPage.PAGE_URL, "User is not redirected to My Ticket page");

        // Navigate back to home page
        homePage.open();

        // Click Change password tab and verify navigation via URL
        ChangePasswordPage changePasswordPage = homePage.goToChangePasswordPage();
        String changePasswordUrl = changePasswordPage.getDriver().getCurrentUrl();
        Assert.assertEquals(changePasswordUrl, ChangePasswordPage.PAGE_URL,
                "User is not redirected to Change Password page");
    }

    @Test(description = "TC08 - User can't login with an account hasn't been activated")
    public void TC08_UserCannotLoginWithUnactivatedAccount() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = TestUtils.generateRandomEmail();
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(randomEmail, password, password, pid);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertEquals(errorMessage, "Invalid username or password. Please try again.",
                    "Error message mismatch at attempt " + (i + 1));
        }}}
