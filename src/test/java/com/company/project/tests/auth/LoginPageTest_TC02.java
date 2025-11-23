package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.LoginPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest_TC02 extends BaseTest {

    @Test(description = "User can't login with blank 'Username' textbox")
    public void TC02() {
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.goToLoginPage();

        String password = JsonReader.getData("login", "password");

        // Enter valid password but leave username blank
        loginPage.login("", password);

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "There was a problem with your login and/or errors exist in your form.",
                "Error message is not displayed correctly");
    }
}
