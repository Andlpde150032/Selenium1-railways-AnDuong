package com.company.project.tests.auth;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import com.company.project.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegisterTest_TC07 extends BaseTest {

    @Test(description = "TC07 - User can create new account")
    public void TC07_UserCanCreateNewAccount() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String randomEmail = "test_user_" + new Random().nextInt(100000) + "@example.com";
        String password = JsonReader.getData("register", "password");
        String pid = JsonReader.getData("register", "pid");

        registerPage.register(randomEmail, password, password, pid);

        String actualMessage = registerPage.getSuccessMessage();
        String expectedMessage = "Thank you for registering your account";

        Assert.assertEquals(actualMessage, expectedMessage, "Success message does not match!");
    }
}
