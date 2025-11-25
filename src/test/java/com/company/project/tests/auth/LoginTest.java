
package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.models.User;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

<<<<<<<< HEAD:src/test/java/com/company/project/tests/auth/LoginPageTest_TC02.java
public class LoginPageTest_TC02 extends BaseTest {

    @Test(description = "User can't login with blank 'Username' textbox")
    public void TC02() {
========
/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function LoginTest - Test class for Login functionality
 */
public class LoginTest extends BaseTest {

    @Test(description = "User can log into Railway with valid username and password")
    public void testLoginWithValidCredentials() {
>>>>>>>> TC01-Login:src/test/java/com/company/project/tests/auth/LoginTest.java
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

<<<<<<<< HEAD:src/test/java/com/company/project/tests/auth/LoginPageTest_TC02.java
        String password = JsonReader.getData("login", "password");

        // Enter valid password but leave username blank
        loginPage.login("", password);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "There was a problem with your login and/or errors exist in your form.",
                "Error message is not displayed correctly");
========
        User user = User.getValidUser();

        loginPage.login(user.getEmail(), user.getPassword());

        String welcomeMessage = loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome " + user.getEmail(), "Welcome message is not displayed correctly");
>>>>>>>> TC01-Login:src/test/java/com/company/project/tests/auth/LoginTest.java
    }
}
