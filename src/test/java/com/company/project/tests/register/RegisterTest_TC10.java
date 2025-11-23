package com.company.project.tests.register;

import com.company.project.base.BaseTest;
import com.company.project.pages.HomePage;
import com.company.project.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest_TC10 extends BaseTest {

    @Test(description = "User can't create account with 'Confirm password' is not the same with 'Password'")
    public void TC10() {
        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.goToRegisterPage();

        String email = "tc10user" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password123";
        String confirmPassword = "Password1234"; // Mismatched
        String pid = "123456789";

        registerPage.register(email, password, confirmPassword, pid);

        String errorMessage = registerPage.getGeneralErrorMessage();
        Assert.assertEquals(errorMessage, "There're errors in the form. Please correct the errors and try again.",
                "Error message is not displayed correctly");
    }
}
