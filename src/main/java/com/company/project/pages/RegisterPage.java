package com.company.project.pages;

import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function RegisterPage - Page object for Registration
 */
public class RegisterPage extends BasePage {

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By confirmPasswordInput = By.id("confirmPassword");
    private final By pidInput = By.id("pid");
    private final By registerButton = By.cssSelector("input[value='Register']");
    private final By successMessage = By.xpath("//div[@id='content']/p");
    private final By generalErrorMessage = By.xpath("//p[@class='message error']");
    private final By passwordError = By.xpath("//label[@for='password' and @class='validation-error']");
    private final By pidError = By.xpath("//label[@for='pid' and @class='validation-error']");

    public void register(String email, String password, String confirmPassword, String pid) {
        enterText(emailInput, email);
        enterText(passwordInput, password);
        enterText(confirmPasswordInput, confirmPassword);
        enterText(pidInput, pid);
        scrollToElement(registerButton);
        click(registerButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public String getGeneralErrorMessage() {
        return getText(generalErrorMessage);
    }

    public String getPasswordError() {
        return getText(passwordError);
    }

    public String getPidError() {
        return getText(pidError);
    }
}
